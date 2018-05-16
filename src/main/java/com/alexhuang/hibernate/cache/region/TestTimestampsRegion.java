/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.cache.region;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.TimestampsRegion;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月16日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
public class TestTimestampsRegion implements TimestampsRegion {
    private Map<Object, Object> map = new HashMap<>();
    private String name;
    
    public TestTimestampsRegion(String regionName) {
        this.name = regionName;
    }

    @Override
    public Object get(Object key) throws CacheException {
        return map.get(key);
    }

    @Override
    public void put(Object key, Object value) throws CacheException {
        map.put(key, value);
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
    public String getName() {
        return name;
    }

    @Override
    public void destroy() throws CacheException {
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
        return map.size();
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

}
