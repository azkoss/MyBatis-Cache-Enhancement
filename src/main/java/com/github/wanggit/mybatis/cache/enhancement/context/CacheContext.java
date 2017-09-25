package com.github.wanggit.mybatis.cache.enhancement.context;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheContext {

    private static Map<String, CacheMeta> metas = new HashMap<>();

    public static void addCacheMeta(CacheMeta cacheMeta){
        metas.put(cacheMeta.getMethodName(), cacheMeta);
    }

    public static class CacheMeta{
        private int timeout;
        private TimeUnit timeUnit;
        private String[] deleteWith;

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        private String methodName;

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }

        public void setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
        }

        public String[] getDeleteWith() {
            return deleteWith;
        }

        public void setDeleteWith(String[] deleteWith) {
            this.deleteWith = deleteWith;
        }
    }

}
