package com.alexhuang.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.dao.IDsProxyDao;
import com.alexhuang.dao.base.impl.BaseEntityDao;
import com.alexhuang.dao.domain.DsProxyDomain;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年5月16日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */

@Component
public class DsProxyDao extends BaseEntityDao<DsProxyDomain> implements IDsProxyDao {

	@Autowired(required = false)
	protected SessionFactory sessionFactory;

	public void modifyData() {
		Session currentSession = sessionFactory.getCurrentSession();
		DsProxyDomain td = (DsProxyDomain) currentSession.load(DsProxyDomain.class, 1L);
		td.txt = "xx:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}