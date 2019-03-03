# springboot-logging-mongo-example
A logback mongodb appender example in a springboot project



在springboot项目中让日志输出到mongo数据库的例子

mongo服务器url配置在`application.properties`中

```properties
spring.data.mongodb.uri=mongodb://localhost/example_test
```

日志存储集合名配置在`logback.xml`中`collectionName`标签

```xml
<appender name="MONGODB" class="com.example.loggingmongo.config.MongoAppender">
    <collectionName>test_logs</collectionName>
</appender>
```



**注意**: 由于连接mongo需要一点时间，实际上在初始化前（下面这个日志代表MongoAppender已经就绪）

```
[ApplicationContext] Autowire MongoTemplate, MongoAppender is ready.
```

日志是没有输入到mongo中的。