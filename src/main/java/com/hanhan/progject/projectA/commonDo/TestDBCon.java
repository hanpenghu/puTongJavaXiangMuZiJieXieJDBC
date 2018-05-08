package com.hanhan.progject.projectA.commonDo;
import com.hanhan.progject.hanhan.p;
import com.hanhan.progject.projectA.dbconfig.*;

import java.sql.*;
import java.util.Calendar;

public class TestDBCon {


//SQLServer字段测试
    public void fDate() throws SQLException, ClassNotFoundException {
        Connection con = ConA.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT * from up_def");
        ResultSet rs = ps.executeQuery();
        UpDef upDef = new UpDef();
        while (rs.next()){
            Date s_dd = rs.getDate("s_dd");//得到日期//2018-03-08
            Time s_dd1 = rs.getTime("s_dd");//得到时间//15:01:21
            Timestamp s_dd2 = rs.getTimestamp("s_dd");//得到完全体时间//2018-03-08 15:01:21.91
            p.p(s_dd.toString());//2018-03-08
            p.p(s_dd1.toString());//15:01:21
            p.p(s_dd2.toString());//2018-03-08 15:01:21.91
            //这个例子说明,我们拿到时间其实很简单根本不用怎么转换就成了String, 只要toString就行了
        }

        p.closeAll(con,ps,rs);

    }


    public void fNumeric() throws SQLException, ClassNotFoundException {
        Connection con = ConA.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT top 1 * from up_def");
        ResultSet rs = ps.executeQuery();
        UpDef upDef = new UpDef();
        while (rs.next()){
            //numeric会直接转换成String
            String qty = rs.getString("QTY");//得到日期//2018-03-08
            p.p(qty);//100.00000000
            //这个例子说明,我们拿到时间其实很简单根本不用怎么转换就成了String, 只要toString就行了
        }

        p.closeAll(con,ps,rs);

    }

    public void ftestInt() throws SQLException, ClassNotFoundException {
        Connection con = ConA.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT top 1 * from up_def");
        ResultSet rs = ps.executeQuery();
        UpDef upDef = new UpDef();
        while (rs.next()){
            //PRE_ITM在数据库是int类型,会直接转换为String类型,牛逼
            String rem_itm =rs.getString("PRE_ITM") ;
            p.p(rem_itm);
        }

        p.closeAll(con,ps,rs);

    }
    public static void main(String[]args) throws SQLException, ClassNotFoundException {
        new TestDBCon().ftestInt();
    }




    public void fCommon() throws SQLException, ClassNotFoundException {
        Connection con = ConA.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT top 1 * from up_def");
        ResultSet rs = ps.executeQuery();
        UpDef upDef = new UpDef();
        while (rs.next()){

        }

        p.closeAll(con,ps,rs);

    }
}
/**
 PRICE_ID	varchar
 CUS_NO	varchar
 CUR_ID	varchar
 PRD_NO	varchar
 PRD_MARK	varchar
 BZ_KND	varchar
 KND	varchar
 QTY	numeric
 S_DD	datetime
 BIL_TYPE	varchar
 SUP_PRD_NO	varchar
 CUS_ARE	varchar
 UP	numeric
 DIS_CNT	numeric
 F_DD	datetime
 E_DD	datetime
 UNIT	varchar
 QTY_FREE	numeric
 REM	varchar
 CHK_MAN	varchar
 CHK_DATE	datetime
 USR	varchar
 SYS_DATE	datetime
 HJ_NO	varchar
 PRE_ITM	int
 RTO_BJST	numeric
 ZD_FLAG	varchar
 OLEFIELD	varchar
 UP_NET	numeric
 FLAG_RK_DD	varchar
 PRM_NO	varchar
 AMT_DIS_CNT	numeric
 *
 * */

/***sqlServer查看所有字段
 *
 *
 SELECT  OBJECT_SCHEMA_NAME(T.[object_id], DB_ID()) AS [架构名] ,
 T.[name] AS [表名] ,
 AC.[name] AS [列名] ,
 TY.[name] AS [系统数据类型] ,
 TY.is_user_defined AS [是否用户自定义类型],--1 = 用户定义类型,0 = SQL Server 系统数据类型
 AC.[max_length] [最大长度],
 AC.[precision] [精确度],--如果列包含的是数值，则为该列的精度；否则为0
 AC.[scale] [数值范围],--如果列包含的是数值，则为列的小数位数；否则为0
 AC.[is_nullable] [是否允许为空],
 AC.[is_ansi_padded][是否使用ANSI_PADDING]--1 = 如果列为字符、二进制或变量类型，则该列使用ANSI_PADDING ON 行为
 FROM    sys.[tables] AS T
 INNER JOIN sys.[all_columns] AC ON T.[object_id] = AC.[object_id]
 INNER JOIN sys.[types] TY ON AC.[system_type_id] = TY.[system_type_id]
 AND AC.[user_type_id] = TY.[user_type_id]
 WHERE   T.[is_ms_shipped] = 0 and
 T.[name] ='sapso'  ----去掉这个是查询所有的表
 ORDER BY T.[name] ,
 AC.[column_id]
 *
 * */