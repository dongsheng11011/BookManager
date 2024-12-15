/*
 * Created by JFormDesigner on Tue Dec 03 20:01:00 CST 2024
 */

package view;

import dao.BookDao;
import entity.Book;
import util.DbUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author AIM
 */
public class AddBookForm extends JFrame {
    public AddBookForm() {
        initComponents();
    }
    private static AddBookForm addBookForm ;
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private BookDao bookDao = BookDao.getBookDao();
    public static AddBookForm getAddBookForm() {
        if(addBookForm == null){
            addBookForm = new AddBookForm();
        }
        return addBookForm;
    }

    private void cancelBtn(ActionEvent e) {
        this.dispose();
        // TODO add your code here
    }

    private void okBtn(ActionEvent e){
        Connection conn = null;
        Book book = new Book(BookTitleTxt.getText(),BookAuthorTxt.getText(),
                BookgenreTxt.getText(),BookIsbnTxt.getText());
        Connection con = null;
        try{
            con = dbUtil.getCon();
            int modifyNum = bookDao.add(con,book);
            if(modifyNum == 1){
                JOptionPane.showMessageDialog(null,"添加成功");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"添加失败");
            }
        }catch (Exception exp){
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null,"添加失败");
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        BookTitleTxt = new JTextField();
        BookAuthorTxt = new JTextField();
        BookgenreTxt = new JTextField();
        BookIsbnTxt = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        label5 = new JLabel();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText("\u4e66\u540d\uff1a");

                //---- label2 ----
                label2.setText("\u4f5c\u8005\uff1a");

                //---- label3 ----
                label3.setText("\u56fe\u4e66\u7c7b\u578b\uff1a");

                //---- label4 ----
                label4.setText("isbn\u56fd\u9645\u6807\u51c6\u4e66\u53f7\uff1a");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap(57, Short.MAX_VALUE)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPanelLayout.createParallelGroup()
                                    .addComponent(label4, GroupLayout.Alignment.TRAILING)
                                    .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(BookAuthorTxt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                .addComponent(BookTitleTxt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                .addComponent(BookgenreTxt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                .addComponent(BookIsbnTxt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                            .addGap(45, 45, 45))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(BookTitleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(BookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, 6)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(BookgenreTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(BookIsbnTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                            .addGap(37, 37, 37))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));

                //---- okButton ----
                okButton.setText("\u786e\u5b9a");
                okButton.addActionListener(e -> okBtn(e));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                cancelButton.addActionListener(e -> cancelBtn(e));

                GroupLayout buttonBarLayout = new GroupLayout(buttonBar);
                buttonBar.setLayout(buttonBarLayout);
                buttonBarLayout.setHorizontalGroup(
                    buttonBarLayout.createParallelGroup()
                        .addGroup(buttonBarLayout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addGap(57, 57, 57)
                            .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(86, Short.MAX_VALUE))
                );
                buttonBarLayout.setVerticalGroup(
                    buttonBarLayout.createParallelGroup()
                        .addGroup(buttonBarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelButton)
                            .addComponent(okButton))
                );
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //---- label5 ----
            label5.setText("\u6dfb\u52a0\u56fe\u4e66");
            label5.setFont(new Font("\u9ed1\u4f53", Font.BOLD, 14));
            dialogPane.add(label5, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField BookTitleTxt;
    private JTextField BookAuthorTxt;
    private JTextField BookgenreTxt;
    private JTextField BookIsbnTxt;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
