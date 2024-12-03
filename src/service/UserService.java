package service;

import dao.UserDao;
import entity.User;
import util.DbUtil;
import util.StringUtil;

import javax.swing.*;
import java.sql.Connection;

public class UserService {
    private static  UserService userService;
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private UserDao userDao = UserDao.getUserDao();
    private User user  = User.getUser();

    public static UserService getUserService() {
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public boolean UserLogin(String userName,String password){
        if(StringUtil.isEmpty(userName)){
            //弹窗
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return false;
        }
        if(StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null,"密码不能为空！");
            return false;
        }
        User tmpuser = new User(userName,password);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            User currentUser = userDao.login(con,tmpuser);
            if(currentUser!=null){
                JOptionPane.showMessageDialog(null,"登录成功");
                user = User.getUser();
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"用户名或密码错误");
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try{
                dbUtil.closeCon(con);
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }

    public boolean UserModify(String userName) {
        if(StringUtil.isEmpty(userName)){
            //弹窗
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return false;
        }
        Connection con = null;
        try {
            con = dbUtil.getCon();
            if(userDao.Modify(con,userName)){
                JOptionPane.showMessageDialog(null,"修改成功");
                user.setUserName(userName);
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"修改失败");
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try{
                dbUtil.closeCon(con);
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }
}
