/*
 * Created by JFormDesigner on Mon Dec 02 14:19:36 CST 2024
 */

package view;

import java.awt.event.*;
import entity.User;
import service.UserService;
import util.DbUtil;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.sql.Connection;

/**
 * @author AIM
 */
public class UserManager extends JInternalFrame {
    public UserManager() {
        initComponents();
    }
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private User user = User.getUser();
    private UserService userService = UserService.getUserService();

    private void userModifyBtn(ActionEvent e) {
        // TODO add your code here
        String userName = UserNameTxt.getText();
        if(userService.UserModify(userName)){
            MainForm.getMainForm().setTitle("图书管理系统              当前用户："+user.getUserName()+"   身份: "+user.getIdentity());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        UserNameTxt = new JTextField();
        label2 = new JLabel();
        passwordTxt1 = new JPasswordField();
        label3 = new JLabel();
        identityTxt = new JTextField();
        userModifyBtn = new JButton();
        passwordTxt2 = new JPasswordField();
        pwdModifyBtn = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u7528\u6237\u7ba1\u7406");
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- label1 ----
            label1.setText("\u7528\u6237\u540d\uff1a");

            //---- label2 ----
            label2.setText("\u5bc6   \u7801\uff1a");

            //---- label3 ----
            label3.setText("\u6743   \u9650\uff1a");

            //---- identityTxt ----
            identityTxt.setEditable(false);

            //---- userModifyBtn ----
            userModifyBtn.setText("\u4fee\u6539");
            userModifyBtn.addActionListener(e -> userModifyBtn(e));

            //---- pwdModifyBtn ----
            pwdModifyBtn.setText("\u4fee\u6539");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label1)
                            .addComponent(label2)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(passwordTxt1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(UserNameTxt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(userModifyBtn, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(passwordTxt2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(identityTxt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pwdModifyBtn, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                        .addContainerGap(25, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(UserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(userModifyBtn))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(passwordTxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordTxt2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pwdModifyBtn))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(identityTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(125, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(103, 103, 103)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(127, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        UserNameTxt.setText(user.getUserName());
        passwordTxt1.setText(user.getPassWord());
        passwordTxt2.setText(user.getPassWord());
        identityTxt.setText(user.getIdentity());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JTextField UserNameTxt;
    private JLabel label2;
    private JPasswordField passwordTxt1;
    private JLabel label3;
    private JTextField identityTxt;
    private JButton userModifyBtn;
    private JPasswordField passwordTxt2;
    private JButton pwdModifyBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
