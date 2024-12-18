package dao;

import entity.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 用户Dao类
 * 处理数据库与业务逻辑。
 */
public class UserDao {
    private static UserDao userDao;
    private User user = User.getUser();

    public static UserDao getUserDao() {
        if(userDao==null){
            userDao = new UserDao();
        }
        return userDao;
    }

    /**
     * 登录验证。
     * @param con
     * @param user
     * @return
     * @throws Exception
     */
    public User login(Connection con, User user)throws Exception{
        String sql="select * from t_user where userName=? and passWord=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //填空。
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassWord());
        //执行，获取结果集
        ResultSet rs = pstmt.executeQuery();
        User resultUser = null;
        if(rs.next()){// 如果存在一条数据。
            this.user = User.getUser();
            this.user.setId(rs.getInt("id"));
            this.user.setUserName(rs.getString("userName"));
            this.user.setPassWord(rs.getString("passWord"));
            this.user.setIdentity(rs.getString("identity"));
            resultUser = this.user;
        }
        return resultUser;
    }

    public boolean ModifyUserName(Connection con, String userName)throws Exception {
        String sql="update t_user set userName = ? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,userName);
        pstmt.setInt(2,user.getId());
        int result = pstmt.executeUpdate();
        return result!=0;
    }
    public boolean ModifyUserPwd(Connection con, String userPwd)throws Exception {
        String querySql = "select passWord from t_user where id = ?";
        PreparedStatement qstmt = con.prepareStatement(querySql);
        qstmt.setInt(1,user.getId());
        ResultSet resultSet = qstmt.executeQuery();
        // 处理查询结果
        StringBuilder resultq = new StringBuilder();
        while(resultSet.next()){
            String data = resultSet.getString("passWord");
            resultq.append(data);
        }
        // 返回查询结果。
        String s = resultq.toString();
        if(s.equals(userPwd)) {
            JOptionPane.showMessageDialog(null,"错误，与原密码一致");
            return false;// 判断与修改的密码是否相等，相等则失败。
        }
        // 不相等，更新数据库中的数据。
        String sql="update t_user set passWord = ? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,userPwd);
        pstmt.setInt(2,user.getId());
        int result = pstmt.executeUpdate();
        return result!=0;
    }
}
