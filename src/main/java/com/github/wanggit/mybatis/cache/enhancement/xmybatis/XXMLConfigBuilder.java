package com.github.wanggit.mybatis.cache.enhancement.xmybatis;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;

import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class XXMLConfigBuilder extends XMLConfigBuilder {
    public XXMLConfigBuilder(Reader reader) {
        super(reader);
    }

    public XXMLConfigBuilder(Reader reader, String environment) {
        super(reader, environment);
    }

    public XXMLConfigBuilder(Reader reader, String environment, Properties props) {
        super(reader, environment, props);
    }

    public XXMLConfigBuilder(InputStream inputStream) {
        super(inputStream);
    }

    public XXMLConfigBuilder(InputStream inputStream, String environment) {
        super(inputStream, environment);
    }

    public XXMLConfigBuilder(InputStream inputStream, String environment, Properties props) {
        super(inputStream, environment, props);
    }
}
