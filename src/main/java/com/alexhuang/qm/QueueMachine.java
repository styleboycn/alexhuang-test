package com.alexhuang.qm;

import java.util.Date;

//import com.sf.sgs.pickup.server.util.RedisLocker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.dao.IQueueMachineDao;
import com.alexhuang.dao.domain.QueueMachineData;
import com.alexhuang.idgenerator.IIdGenerator;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年3月30日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */

@Component
public class QueueMachine {
	
    private static final Logger logger = LoggerFactory.getLogger(QueueMachine.class);
    private static final int VERSION_INIT_SEQ = 1;
    private static final Integer USED = 1;
    private static final Integer NO_USED = 0;
    
//    @Autowired
//    private QueueMachineLocker queueMachineLocker;
    @Autowired
    private IQueueMachineDao queueMachineDao;
    @Autowired
    private IIdGenerator idGenerator;

    public <T> void execute(Class<T> messageClass, String message, IMessageHandler<T> handler) {
        Context<T> ctx = new Context<T>(messageClass, message, handler);

        //try (RedisLocker.Lock lock = queueMachineLocker.geQueueMachineLocker(ctx.getKey())) {
            // 兼容没有版本号的旧数据
            if (ctx.getVersion() == null) {
                ctx.handle();
                return;
            }

            // 检查消息是否执行过
            if (isProcessed(ctx)) {
				logger.warn("duplicate message: {}, {}", ctx.getKey(),
						ctx.message);
                return;
            }

            process(ctx, true);
        //}
    }

    private <T> void process(Context<T> ctx, boolean first) {
        if (!first || isQueued(ctx)) {
            ctx.handle();
            saveCurrentMessage(ctx, USED);
            processNextQueueMessage(ctx);
        } else {
            saveCurrentMessage(ctx, NO_USED);
        }
    }

    private <T> boolean isProcessed(Context<T> ctx) {
        ctx.queueMachineData = queueMachineDao.query(ctx.getKey(), ctx.getVersion());
        return ctx.queueMachineData != null && USED.equals(ctx.queueMachineData.getIsUsed());
    }

    private <T> boolean isQueued(Context<T> ctx) {
        int version = ctx.getVersion();
        if (version == VERSION_INIT_SEQ) {
            return true;
        }

        QueueMachineData preData = queueMachineDao.query(ctx.getKey(), version - 1);
        if (preData != null && USED.equals(preData.getIsUsed())) {
            return true;
        }

        return false;
    }

    private <T> void saveCurrentMessage(Context<T> ctx, int isUsed) {
        QueueMachineData data = ctx.queueMachineData;
        if (data == null) {
            data = new QueueMachineData();
            data.setId(idGenerator.genPickupId());
            data.setCreateTime(new Date());
            data.setKey(ctx.getKey());
            data.setMessage(ctx.message);
            data.setVersion(ctx.getVersion());
        }

        data.setModifedtime(new Date());
        data.setIsUsed(isUsed);
        queueMachineDao.saveOrUpdate(data);
    }

    private <T> void processNextQueueMessage(Context<T> ctx) {
        QueueMachineData nextData = queueMachineDao.query(ctx.getKey(), ctx.getVersion() + 1);
        if (nextData != null) {
            ctx.decode(nextData.getMessage());
            ctx.queueMachineData = nextData;
            process(ctx, false);
        }
    }
    
}
