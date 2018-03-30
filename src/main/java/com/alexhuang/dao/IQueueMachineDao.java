package com.alexhuang.dao;

import com.alexhuang.dao.base.IEntityDao;
import com.alexhuang.dao.domain.QueueMachineData;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年3月29日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */

public interface IQueueMachineDao extends IEntityDao<QueueMachineData> {

    QueueMachineData query(String key, int version);

}
