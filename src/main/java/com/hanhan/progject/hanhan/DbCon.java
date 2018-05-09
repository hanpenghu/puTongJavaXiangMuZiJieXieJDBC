package com.hanhan.progject.hanhan;

import com.hanhan.progject.projectA.dbconfig.ConA;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DbCon {

    public static void main(String[]args) throws InterruptedException, SQLException, ClassNotFoundException {
        List<Connection> cons = getCons();
        List<Connection> cons1 = getCons();
        while(true){
            System.out.println("-------------------------------");
            System.out.println(cons.size());
            Thread.sleep(2000);
            System.out.println(cons1.size());
            Thread.sleep(2000);
            System.out.println(cons==cons1);
            System.out.println("-------------------------------");
        }
    }


   private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
   public static  List<Connection>  cons=new ArrayList<Connection>();


   public static List<Connection> getCons() throws SQLException, ClassNotFoundException {
       return new DbCon().cons;
   }



   public DbCon() throws SQLException, ClassNotFoundException {
       cons.add(getCon());
       f();
   }
    public  void  f(){
        Runnable runnable1 = new Runnable() {

                public void run() {
                    while(cons.size()<2){
                        for(int i=0;i<5;i++){
                            try {
                                cons.add(getCon());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    while(cons.size()>6){
                        cons.remove(cons.get(cons.size()-1));
                    }
                }
            };
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间,fixedRate就是当上次执行完间隔多少秒再执行
        service.scheduleAtFixedRate(runnable1, 12,12, TimeUnit.SECONDS);
    }

    public static Connection getCon() throws ClassNotFoundException, SQLException {
        Connection  conn= getCon01(p.ip127,p.port1433,p.dbTypeSqlserver,
                "DB_LZ17","sa","root");
        if (null==conn) {
            return null;
        } else {
            return conn;
        }
    }

    public static void conClose(Connection c) throws SQLException {
        if (null!=c) {
            c.close();
        } else {

        }

    }

    public static void resultSetClose(ResultSet c) throws SQLException {
        if (null!=c) {
            c.close();
        } else {

        }

    }

    public static void preparedStatementClose(PreparedStatement c) throws SQLException {
        if (null!=c) {
            c.close();
        } else {

        }

    }


    public static void closeAll(PreparedStatement p1, ResultSet r, Connection c) {
        try {
            preparedStatementClose(p1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSetClose(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conClose(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public static Connection getCon01(String ip,String port,String dbType,
                                          String dbName,String usr,String pwd)
                throws ClassNotFoundException, SQLException {
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DB_LZ17";
            String driver="";
            if("sqlserver".equals(dbType)){
                url="jdbc:sqlserver://"+ip+":"+port+";DatabaseName="+dbName;
                driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            }else if("mysql".equals(dbType)){
                url="jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8";
                driver="com.mysql.jdbc.Driver";
            }
//        //mydb为数据库
//        String user = "sa";
//        String password = "root";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            if (null!=conn) {
                return null;
            } else {
                return conn;
            }
        }



}
