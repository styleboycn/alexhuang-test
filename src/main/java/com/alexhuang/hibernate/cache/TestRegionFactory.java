package com.alexhuang.hibernate.cache;

import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cfg.Settings;

import com.alexhuang.hibernate.cache.region.TestCollectionRegion;
import com.alexhuang.hibernate.cache.region.TestEntityRegion;
import com.alexhuang.hibernate.cache.region.TestQueryResultsRegion;
import com.alexhuang.hibernate.cache.region.TestTimestampsRegion;

public class TestRegionFactory implements RegionFactory {

    @Override
    public void start(Settings settings, Properties properties) throws CacheException {
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        return true;
    }

    @Override
    public AccessType getDefaultAccessType() {
        return AccessType.READ_WRITE;
    }

    @Override
    public long nextTimestamp() {
        return System.currentTimeMillis() / 100;
    }

    @Override
    public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata)
        throws CacheException {
        return new TestEntityRegion(regionName, properties, metadata);
    }

    @Override
    public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata)
        throws CacheException {
        throw new CacheException("buildNaturalIdRegion");
    }

    @Override
    public CollectionRegion buildCollectionRegion(String regionName, Properties properties,
        CacheDataDescription metadata) throws CacheException {
        return new TestCollectionRegion(regionName, metadata);
    }

    @Override
    public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
        return new TestQueryResultsRegion(regionName);
    }

    @Override
    public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
        return new TestTimestampsRegion(regionName);
    }

}
