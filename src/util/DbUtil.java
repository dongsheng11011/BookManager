package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具类。
 */
public class DbUtil {
    private static DbUtil dbUtil;
    private String dbUrl = "jdbc:mysql://localhost:3306/bookmanage";
    private String dbUserName = "root";
    private String dbPassWord ="123456";

    /**
     * 连接数据库。
     * @return
     * @throws Exception
     */
    public Connection getCon()throws Exception{
        Connection con = DriverManager.getConnection(dbUrl,dbUserName,dbPassWord);
        return con;
    }

    /**
     * 关闭数据库连接。
     * @param con
     * @throws Exception
     */
    public  void closeCon(Connection con)throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static DbUtil getDbUtil() {
        if(dbUtil == null){
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }
}
