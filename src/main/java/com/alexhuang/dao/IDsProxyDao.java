package com.alexhuang.dao;

import com.alexhuang.dao.base.IEntityDao;
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

public interface IDsProxyDao extends IEntityDao<DsProxyDomain> {

	void modifyData();

}
