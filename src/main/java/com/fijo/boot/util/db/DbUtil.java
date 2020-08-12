package com.fijo.boot.util.db;


//import com.fijo.boot.base.model.JdbcInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhangbo.
 * @ Date       ：Created in 23:02 2019/12/20
 * @ Description：
 * @ Modified By：
 * @Version:
 */

@Slf4j
public class DbUtil {

    @Value("${spring.datasource.url}")
    private String dbUrl; //数据库连接url
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver; //数据库驱动
    @Value("${spring.datasource.username}")
    private String dbUsername; //数据库用户名
    @Value("${spring.datasource.password}")
    private String dbPassword; //数据库密码

    private static String dbUrl_;
    private static String dbDriver_;
    private static String dbUsername_;
    private static String dbPassword_;


    public void init(){
        log.warn("【系统启动: 加载数据库配置信息dbUrl_:{}】",dbUrl);
        log.warn("【系统启动: 加载数据库配置信息dbDriver_:{}】",dbDriver);
        log.warn("【系统启动: 加载数据库配置信息dbUsername_:{}】",dbUsername);
        log.warn("【系统启动: 加载数据库配置信息dbPassword_:{}】",dbPassword);
        dbUrl_ = dbUrl;
        dbDriver_ = dbDriver;
        dbUsername_ = dbUsername;
        dbPassword_ = dbPassword;
    }

    public static Connection getConnection(){
        //创建连接
        Connection con = null;
        try{
            try {
                log.info("【class.forName】{}",dbDriver_);
                Class.forName(dbDriver_);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                log.error(e1.getMessage());
            }
            con = DriverManager.getConnection(dbUrl_,dbUsername_,dbPassword_);
            log.info("【con】建立数据库连接");

        }catch (Exception e){

        }
        return con;
    }

    public static List<Map<String,Object>> executeQuery(String sql){
        Connection con = getConnection();
        log.info("【执行自定义sql】"+sql);
        ResultSet rs = null;
        try {
            //从jdbc连接中获取statement
            Statement _pStemt = (Statement) con.createStatement();
            rs = _pStemt.executeQuery(sql);

            List<Map<String,Object>> resultList = new ArrayList<>();
            while (rs.next()) {
                Map<String,Object> colomMap = new HashMap<>();
                ResultSetMetaData rsmd =  rs.getMetaData();
                for(int i = 1 ; i <= rsmd.getColumnCount();i++){
                    String colName = rsmd.getColumnLabel(i);
                    colomMap.put(colName,rs.getString(colName));
                }
                resultList.add(colomMap);
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            try {
                if (con != null){
                    con.close();
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return null;
    }

    public static int executeUpdate(String sql){
        Connection con = getConnection();
        log.info("【执行自定义sql】"+sql);
        int rs = 0;
        try {
            //从jdbc连接中获取statement
            Statement _pStemt = (Statement) con.createStatement();
            rs = _pStemt.executeUpdate(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            try {
                if (con != null){
                    con.close();
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return rs;
    }
}
