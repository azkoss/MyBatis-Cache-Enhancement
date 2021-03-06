package com.github.wanggit.mybatis.cache.enhancement.context;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheContext {

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    private static Map<String, CacheMeta> cacheMetaMap = new HashMap<>();

    private static Map<String, List<DeleteCacheMeta>> deleteCacheMetaMap = new HashMap<>();

    public static CacheMeta getCacheMeta(String statementId){
        return cacheMetaMap.get(statementId);
    }

    public static List<DeleteCacheMeta> getDeleteCacheMetas(String statementId){
        return deleteCacheMetaMap.get(statementId);
    }

    public static synchronized void addCacheMeta(CacheMeta cacheMeta){
        cacheMetaMap.put(cacheMeta.getFullName(), cacheMeta);
    }

    public static synchronized void addDeleteCacheMeta(DeleteCacheMeta deleteCacheMeta){
        if (!deleteCacheMetaMap.containsKey(deleteCacheMeta.getFullName())){
            deleteCacheMetaMap.put(deleteCacheMeta.getFullName(), new ArrayList<>());
        }
        deleteCacheMetaMap.get(deleteCacheMeta.getFullName()).add(deleteCacheMeta);
    }

    public static void set(String mappedStatementId){
        local.set(mappedStatementId);
    }

    public static String get(){
        return local.get();
    }

    public static class DeleteCacheMeta{

        /**
         * delete target method
         */
        private String[] targetDeletes;

        private String methodName;

        /**
         * full className + # + methodName
         */
        private String fullName;

        /**
         * delete target class
         */
        private Class targetClazz;

        public String[] getFullTargets(){
            String mapperClazzName = targetClazz.getName();
            String[] fullTargets = new String[targetDeletes.length];
            for (int i = 0; i < targetDeletes.length; i++) {
                fullTargets[i] = mapperClazzName + "." + targetDeletes[i];
            }
            return fullTargets;
        }

        public String[] getTargetDeletes() {
            return targetDeletes;
        }

        public void setTargetDeletes(String[] targetDeletes) {
            this.targetDeletes = targetDeletes;
        }

        public Class getTargetClazz() {
            return targetClazz;
        }

        public void setTargetClazz(Class targetClazz) {
            this.targetClazz = targetClazz;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @Override
        public String toString() {
            return super.toString() + " fullName=" + getFullName()
                    + ", targetClazz=" + getTargetClazz().getName()
                    + ", targetDeletes=" + Arrays.toString(getTargetDeletes());
        }
    }

    public static class CacheMeta{
        /**
         * less than zero never expired
         */
        private int timeout = -1;

        private TimeUnit timeUnit;

        private String methodName;

        /**
         * full className + # + methodName
         */
        private String fullName;

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

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

        @Override
        public String toString() {
            return super.toString() + " fullName=" + getFullName()
                    + ", timeout=" + getTimeout() + ", timeUnit=" + timeUnit.name();
        }
    }

}
