/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package test.yc.cache.region;

import java.util.Map;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月18日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
public class TestEntityRegionAccessStrategy implements EntityRegionAccessStrategy {
    private AccessType accessType;
    private Map<Object, Object> map;
    private TestEntityRegion testEntityRegion;
    
    public TestEntityRegionAccessStrategy(AccessType accessType, Map<Object, Object> map, TestEntityRegion testEntityRegion) {
        this.accessType = accessType;
        this.map = map;
        this.testEntityRegion = testEntityRegion;
    }

    @Override
    public Object get(Object key, long txTimestamp) throws CacheException {
        return map.get(key);
    }

    @Override
    public boolean putFromLoad(Object key, Object value, long txTimestamp, Object version) throws CacheException {
        return putFromLoad(key, value, txTimestamp, version, true);
    }

    @Override
    public boolean putFromLoad(Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride)
        throws CacheException {
        if (minimalPutOverride && map.containsKey(key)) {
            return false;
        } else {
            map.put(key, value);
            return true;
        }
    }

    @Override
    public SoftLock lockItem(Object key, Object version) throws CacheException {
        return null;
    }

    @Override
    public SoftLock lockRegion() throws CacheException {
        return null;
    }

    @Override
    public void unlockItem(Object key, SoftLock lock) throws CacheException {
    }

    @Override
    public void unlockRegion(SoftLock lock) throws CacheException {
    }

    @Override
    public void remove(Object key) throws CacheException {
    }

    @Override
    public void removeAll() throws CacheException {
    }

    @Override
    public void evict(Object key) throws CacheException {
        map.remove(key);
    }

    @Override
    public void evictAll() throws CacheException {
        map.clear();
    }

    @Override
    public EntityRegion getRegion() {
        return testEntityRegion;
    }

    @Override
    public boolean insert(Object key, Object value, Object version) throws CacheException {
        return false;
    }

    @Override
    public boolean afterInsert(Object key, Object value, Object version) throws CacheException {
        map.put(key, value);
        return true;
    }

    @Override
    public boolean update(Object key, Object value, Object currentVersion, Object previousVersion)
        throws CacheException {
        return false;
    }

    @Override
    public boolean afterUpdate(Object key, Object value, Object currentVersion, Object previousVersion, SoftLock lock)
        throws CacheException {
        map.put(key, value);
        return true;
    }

}
