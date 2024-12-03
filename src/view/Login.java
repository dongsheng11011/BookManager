/*
 * Created by JFormDesigner on Mon Dec 02 16:05:22 CST 2024
 */

package view;

import entity.User;
import service.UserService;
import util.DbUtil;
import util.StringUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author AIM
 */
public class Login extends JFrame {
    public Login() {
        initComponents();
    }
    private UserService userService = UserService.getUserService();
    private User user = User.getUser();

    private void reset(ActionEvent e) {
        // TODO add your code here
        this.userField.setText("Aries");
        this.pwdField.setText("123456");
    }

    private void login(ActionEvent e) {
        // TODO add your code here
        String userName = this.userField.getText();
        String password = new String(this.pwdField.getPassword());
        if(userService.UserLogin(userName,password)){
            dispose();
            MainForm.getMainForm().setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        resetButton = new JButton();
        loginButton = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        userField = new JTextField();
        pwdField = new JPasswordField();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            //---- resetButton ----
            resetButton.setText("\u91cd\u7f6e");
            resetButton.addActionListener(e -> reset(e));

            //---- loginButton ----
            loginButton.setText("\u767b\u5f55");
            loginButton.addActionListener(e -> login(e));

            //---- label1 ----
            label1.setText("orz  \u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
            label1.setFont(new Font("\u65b0\u5b8b\u4f53", Font.BOLD, 19));

            //---- label2 ----
            label2.setText("\u7528\u6237\u540d");

            //---- label3 ----
            label3.setText("\u5bc6   \u7801");

            //---- userField ----
            userField.setText("Aries");

            //---- pwdField ----
            pwdField.setText("123456");

            GroupLayout dialogPaneLayout = new GroupLayout(dialogPane);
            dialogPane.setLayout(dialogPaneLayout);
            dialogPaneLayout.setHorizontalGroup(
                dialogPaneLayout.createParallelGroup()
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(loginButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                .addGroup(dialogPaneLayout.createParallelGroup()
                                    .addComponent(label2)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userField, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(pwdField, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))))
                        .addContainerGap(94, Short.MAX_VALUE))
            );
            dialogPaneLayout.setVerticalGroup(
                dialogPaneLayout.createParallelGroup()
                    .addGroup(dialogPaneLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(userField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dialogPaneLayout.createParallelGroup()
                            .addComponent(label3)
                            .addComponent(pwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(resetButton)
                            .addComponent(loginButton))
                        .addContainerGap(22, Short.MAX_VALUE))
            );
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JButton resetButton;
    private JButton loginButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField userField;
    private JPasswordField pwdField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
