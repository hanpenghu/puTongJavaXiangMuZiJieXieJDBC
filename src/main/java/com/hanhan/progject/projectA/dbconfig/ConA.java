package com.hanhan.progject.projectA.dbconfig;

import com.hanhan.progject.hanhan.p;

import java.sql.*;
public class ConA {

    //mysql的
//    jdbc.driver=com.mysql.jdbc.Driver
//    jdbc.url=jdbc:mysql://47.98.45.100:3306/luxclub?useUnicode=true&characterEncoding=utf-8
//    jdbc.username=juhe
//    jdbc.password=root
    public static void main(String[]args) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        p.p("-------------------------------------------------------");
        p.p(con);
        p.p("-------------------------------------------------------");
        closeAll(null,null,con);

    }



    public static Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DB_LZ17";
        //mydb为数据库
        String user = "sa";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, user, password);
        if (p.empty(conn)) {
            return null;
        } else {
            return conn;
        }
    }

    public static void conClose(Connection c) throws SQLException {
        if (p.notEmpty(c)) {
            c.close();
        } else {

        }

    }

    public static void resultSetClose(ResultSet c) throws SQLException {
        if (p.notEmpty(c)) {
            c.close();
        } else {

        }

    }

    public static void preparedStatementClose(PreparedStatement c) throws SQLException {
        if (p.notEmpty(c)) {
            c.close();
        } else {

        }

    }


    public static void closeAll(PreparedStatement p1, ResultSet r, Connection c) {
        try {
            ConA.preparedStatementClose(p1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ConA.resultSetClose(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ConA.conClose(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}