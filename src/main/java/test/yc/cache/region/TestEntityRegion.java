/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package test.yc.cache.region;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;

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
public class TestEntityRegion implements EntityRegion {

    private String name;
    private CacheDataDescription meta;
    private Map<Object, Object> map = new HashMap<>();
    
    public TestEntityRegion(String regionName, Properties properties, CacheDataDescription meta) {
        this.name =  regionName;
    }

    @Override
    public boolean isTransactionAware() {
        return false;
    }

    @Override
    public CacheDataDescription getCacheDataDescription() {
        return meta;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void destroy() throws CacheException {
        map.clear();
    }

    @Override
    public boolean contains(Object key) {
        return map.containsKey(key);
    }

    @Override
    public long getSizeInMemory() {
        return -1L;
    }

    @Override
    public long getElementCountInMemory() {
        return -1L;
    }

    @Override
    public long getElementCountOnDisk() {
        return -1L;
    }

    @Override
    public Map toMap() {
        return Collections.emptyMap();
    }

    @Override
    public long nextTimestamp() {
        return System.currentTimeMillis() / 100;
    }

    @Override
    public int getTimeout() {
        return 1000;
    }

    @Override
    public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return new TestEntityRegionAccessStrategy(accessType, map, this);
    }

}
