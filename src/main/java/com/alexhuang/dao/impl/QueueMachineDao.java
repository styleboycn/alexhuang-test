package com.alexhuang.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.alexhuang.dao.IQueueMachineDao;
import com.alexhuang.dao.base.impl.BaseEntityDao;
import com.alexhuang.dao.domain.QueueMachineData;

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
public class QueueMachineDao extends BaseEntityDao<QueueMachineData> implements IQueueMachineDao {

	@Override
	public QueueMachineData query(String key, int version) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq("key", key));
		criteria.add(Restrictions.eq("version", version));
		return super.findAny(criteria);
	}

}