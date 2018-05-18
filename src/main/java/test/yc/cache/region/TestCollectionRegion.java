/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package test.yc.cache.region;

import java.util.Map;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月17日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
public class TestCollectionRegion implements CollectionRegion {

    public TestCollectionRegion(String regionName, CacheDataDescription metadata) {
    }

    @Override
    public boolean isTransactionAware() {
        return false;
    }

    @Override
    public CacheDataDescription getCacheDataDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void destroy() throws CacheException {
    }

    @Override
    public boolean contains(Object key) {
        return false;
    }

    @Override
    public long getSizeInMemory() {
        return 0;
    }

    @Override
    public long getElementCountInMemory() {
        return 0;
    }

    @Override
    public long getElementCountOnDisk() {
        return 0;
    }

    @Override
    public Map toMap() {
        return null;
    }

    @Override
    public long nextTimestamp() {
        return 0;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return null;
    }

}
