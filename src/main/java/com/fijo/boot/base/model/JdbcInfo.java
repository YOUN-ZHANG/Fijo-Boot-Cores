//package com.fijo.boot.base.model;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Data
//@Configuration
////@PropertySource(value="classpath:application-dev.yml")
//public class JdbcInfo {
//
//    public static Map<String, Object> configMap = new HashMap<>();
//
//    //@Value("${}")
//    //private String dbName; //数据库名
//    @Value("${spring.datasource.url}")
//    private String dbUrl; //数据库连接url
//    @Value("${spring.datasource.driver-class-name}")
//    private String dbDriver; //数据库驱动
//    @Value("${spring.datasource.username}")
//    private String dbUsername; //数据库用户名
//    @Value("${spring.datasource.password}")
//    private String dbPassword; //数据库密码
//
//
//    @PostConstruct
//    public void  init(){
//        //系统启动中。。。加载配置到缓存。。。
//        log.warn("【系统启动: 加载数据库配置信息到缓存。。。】");
//
//
//    };
//
//}
