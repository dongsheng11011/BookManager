/*
 * Created by JFormDesigner on Mon Dec 02 12:41:46 CST 2024
 */

package view;

import entity.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author AIM
 */
public class MainForm extends JFrame {
    public MainForm() {
        initComponents();
    }
    // view
    private static MainForm mainForm;

    public static MainForm getMainForm() {
        if(mainForm==null){
            mainForm = new MainForm();
        }
        return mainForm;
    }

    private BookManager bookManager = BookManager.getBookManager();
    private User user = User.getUser();
    private BookBorrow bookBorrow = null;
    private BookType bookType = null;
    private UserManager userManager = null;
    private Aboutus setting = null;

    private Dimension Inner_Dme = null;// 桌面大小设置。
    private int index = 0;// 显示顺序。

    private void BookManagerBtn(ActionEvent e) {
        // TODO add your code here
        ShowFrom(bookManager);
    }

    private void BookBorrowBtn(ActionEvent e) {
        // TODO add your code here
        if (bookBorrow == null){
            bookBorrow = new BookBorrow();
            bookBorrow.setSize(Inner_Dme);
            bookBorrow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Desktop.add(bookBorrow);
        }
        ShowFrom(bookBorrow);
    }

    private void BookTypeBtn(ActionEvent e) {
        // TODO add your code here
        if (bookType == null){
            bookType = new BookType();
            bookType.setSize(Inner_Dme);
            bookType.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Desktop.add(bookType);
        }
        ShowFrom(bookType);
    }

    private void UserManagerBtn(ActionEvent e) {
        // TODO add your code here
        if (userManager == null){
            userManager = new UserManager();
            userManager.setSize(Inner_Dme);
            userManager.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Desktop.add(userManager);
        }
        ShowFrom(userManager);
    }

    private void SettingBtn(ActionEvent e) {
        // TODO add your code here
        if (setting == null){
            setting = new Aboutus();
            setting.setSize(Inner_Dme);
            setting.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Desktop.add(setting);
        }
        ShowFrom(setting);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Desktop = new JDesktopPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        BookManagerBtn = new JButton();
        BookTypeBtn = new JButton();
        BookBorrowBtn = new JButton();
        UserManagerBtn = new JButton();
        Setting = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf              \u5f53\u524d\u7528\u6237\uff1a");
        setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        setResizable(false);
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- label1 ----
            label1.setText("\u529f\u80fd\u5217\u8868");
            label1.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

            //---- BookManagerBtn ----
            BookManagerBtn.setText("\u56fe\u4e66\u7ba1\u7406");
            BookManagerBtn.addActionListener(e -> BookManagerBtn(e));

            //---- BookTypeBtn ----
            BookTypeBtn.setText("\u56fe\u4e66\u5217\u8868");
            BookTypeBtn.addActionListener(e -> BookTypeBtn(e));

            //---- BookBorrowBtn ----
            BookBorrowBtn.setText("\u501f\u9605\u8bb0\u5f55");
            BookBorrowBtn.addActionListener(e -> BookBorrowBtn(e));

            //---- UserManagerBtn ----
            UserManagerBtn.setText("\u7528\u6237\u7ba1\u7406");
            UserManagerBtn.addActionListener(e -> UserManagerBtn(e));

            //---- Setting ----
            Setting.setText(" \u5173\u4e8e");
            Setting.addActionListener(e -> SettingBtn(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(BookManagerBtn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                            .addComponent(BookTypeBtn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                            .addComponent(BookBorrowBtn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Setting, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(UserManagerBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BookManagerBtn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BookTypeBtn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BookBorrowBtn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserManagerBtn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Setting, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(53, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Desktop, GroupLayout.PREFERRED_SIZE, 582, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addComponent(Desktop, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:o
        String s = this.getTitle() + user.getUserName()+"   身份: "+user.getIdentity();
        this.setTitle(s);
        Inner_Dme = new Dimension(Desktop.getWidth(),Desktop.getHeight());

        bookManager.setSize(Inner_Dme);
        bookManager.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bookManager.setVisible(true);

        // 显示顺序
        index = Desktop.getComponentCount();
        Desktop.add(bookManager,index);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JDesktopPane Desktop;
    private JPanel panel1;
    private JLabel label1;
    private JButton BookManagerBtn;
    private JButton BookTypeBtn;
    private JButton BookBorrowBtn;
    private JButton UserManagerBtn;
    private JButton Setting;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private void ShowFrom(JInternalFrame JFrom){
        try {
            JFrom.setClosed(false);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        try {
            JFrom.setIcon(false);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        JFrom.setVisible(true);
        Desktop.setComponentZOrder(JFrom, 0);
        synchronized (Desktop.getTreeLock()) {
            Rectangle bounds = new Rectangle(0, 0,Inner_Dme.width,Inner_Dme.height);
            int componentsCount = Desktop.getComponentCount(); // 获取容器中组件的数量
            for (int i = 0; i < componentsCount; i++) {
                Component comp = Desktop.getComponent(i); // 获取当前索引下的组件
                comp.setBounds(bounds); // 将所有组件设置为填满整个容器的尺寸
                Desktop.setComponentZOrder(comp, componentsCount - i - 1); // 设置组件的堆叠顺序
            }
        }
    }
}
