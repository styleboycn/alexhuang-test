/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月13日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
public abstract class Log4j2Configurator {

    private static final Logger logger = LogManager.getLogger(Log4j2Configurator.class.getName());

    public static void initDomConfig(String location) throws IOException {
        String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
        URL url = ResourceUtils.getURL(resolvedLocation);
        if (ResourceUtils.URL_PROTOCOL_FILE.equals(url.getProtocol()) && !ResourceUtils.getFile(url).exists()) {
            throw new FileNotFoundException("Log4j2 config file [" + resolvedLocation + "] not found");
        }
        try (FileInputStream inputStream = new FileInputStream(ResourceUtils.getFile(url))) {
            Configurator.initialize(null, new ConfigurationSource(inputStream));
            logger.info("Log4j2Configurator initDomConfig {}", resolvedLocation);
        }
    }
}
