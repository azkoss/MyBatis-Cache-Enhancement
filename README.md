# MyBatis-Cache-Enhancement
MyBatis Cache Enhancement
1. 如何把缓存的配置信息注入到MyBatis环境中，使之在Executor中获取。
2. 重新实现Executor，在Executor中操作缓存
3. 在*Mapper.java的各个方法中添加注解，标记缓存操作

把Mapper中的缓存元信息进行解析记录，并再重写Executor方法，在操作过程中依据缓存元信息进行缓存操作。
