/*
 * Created by JFormDesigner on Mon Dec 02 12:52:52 CST 2024
 */

package view;

import dao.BookDao;
import entity.Book;
import util.DbUtil;
import util.StringUtil;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author AIM
 */
public class BookManager extends JInternalFrame {
    public BookManager() {
        initComponents();
    }
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private BookDao bookDao = BookDao.getBookDao();

    private void BookTableMouseClicked(MouseEvent e) {
        // TODO add your code here
        int row = BookTable.getSelectedRow();
        BookIdTxt.setText((String)BookTable.getValueAt(row,0));
        BookTitleTxt.setText((String)BookTable.getValueAt(row,1));
        BookAuthorTxt.setText((String)BookTable.getValueAt(row,2));
        BookTypeTxt.setText((String)BookTable.getValueAt(row,3));
        BookIsbnTxt.setText((String)BookTable.getValueAt(row,4));
    }

    private void fillTable(Book book){
        //初始化 ->
        DefaultTableModel dtm = (DefaultTableModel) BookTable.getModel();
        dtm.setRowCount(0); // 设置成0 ，清空表格。
        Connection con = null;
        try{
            //
            con = dbUtil.getCon();
            // jdbc 结果集
            ResultSet rs = bookDao.list(con,book);
            // 遍历。
            while(rs.next()){
                //添加一行数据。
                Vector v = new Vector();
                v.add(rs.getString("book_id"));
                v.add(rs.getString("title"));
                v.add(rs.getString("author"));
                v.add(rs.getString("genre"));
                v.add(rs.getString("isbn"));
                dtm.addRow(v); // 添加数据。
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }

    private void resetTxtBtn(ActionEvent e) {
        // TODO add your code here
        BookIdTxt.setText("");
        BookTitleTxt.setText("");
        BookAuthorTxt.setText("");
        BookTypeTxt.setText("");
        BookIsbnTxt.setText("");
    }

    private void queryBtn(ActionEvent e) {
        // TODO add your code here
        Book book = new Book(BookTitleTxt.getText(),BookAuthorTxt.getText(),
                BookTypeTxt.getText(),BookIsbnTxt.getText());
        fillTable(book);
    }

    private void deleteBtn(ActionEvent e) {
        // TODO add your code here
        String id = BookIdTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择你要删除的数据");
            return;
        }
        Connection con = null;
        int n = JOptionPane.showConfirmDialog(null,"确定要删除该记录吗？");
        if(n == 0){
            try{
                con = dbUtil.getCon();
                int deleteNum = bookDao.delete(con,id);
                if(deleteNum == 1){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    this.resetTxtBtn(e);
                    this.fillTable(new Book());
                }else{
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
            }catch (Exception exp){
                exp.printStackTrace();
                JOptionPane.showMessageDialog(null,"删除失败");
            }finally {
                try {
                    dbUtil.closeCon(con);
                }
                catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        }
    }

    private void updateBtn(ActionEvent e) {
        // TODO add your code here
//        String id = BookIdTxt.getText();
//        String bookTypeName = BookTitleTxt.getText();
//        String bookType = BookTypeTxt.getText();
//        if(StringUtil.isEmpty(id)){
//            JOptionPane.showMessageDialog(null,"请选择要修改的记录！");
//            return;
//        }
//        if(StringUtil.isEmpty(bookTypeName)){
//            JOptionPane.showMessageDialog(null,"请输入图书类别，名称不能为空！");
//            return;
//        }
//        BookType bookType = new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
//        Connection con = null;
//        try{
//            con = dbUtil.getCon();
//            int modifyNum = bookTypeDao.update(con,bookType);
//            if(modifyNum == 1){
//                JOptionPane.showMessageDialog(null,"修改成功");
//                this.resetValue();// 重置数据。
//                this.fillTable(new BookType());
//            }else{
//                JOptionPane.showMessageDialog(null,"修改失败");
//            }
//        }catch (Exception exp){
//            exp.printStackTrace();
//            JOptionPane.showMessageDialog(null,"修改失败");
//        }finally {
//            try {
//                dbUtil.closeCon(con);
//            }catch (Exception exp){
//                exp.printStackTrace();
//            }
//        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        panel4 = new JPanel();
        label6 = new JLabel();
        BookTypeTxt = new JTextField();
        panel5 = new JPanel();
        label7 = new JLabel();
        BookIdTxt = new JTextField();
        panel6 = new JPanel();
        label8 = new JLabel();
        BookIsbnTxt = new JTextField();
        panel7 = new JPanel();
        label9 = new JLabel();
        BookAuthorTxt = new JTextField();
        panel8 = new JPanel();
        label10 = new JLabel();
        BookTitleTxt = new JTextField();
        queryBtn = new JButton();
        deleteBtn = new JButton();
        button3 = new JButton();
        updateBtn = new JButton();
        resetTxtBtn = new JButton();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        BookTable = new JTable();

        //======== this ========
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setTitle("\u56fe\u4e66\u7ba1\u7406");
        var contentPane = getContentPane();

        //======== panel2 ========
        {

            //======== panel4 ========
            {

                //---- label6 ----
                label6.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BookTypeTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label6)
                                .addComponent(BookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            //======== panel5 ========
            {

                //---- label7 ----
                label7.setText("\u56fe\u4e66id:");

                //---- BookIdTxt ----
                BookIdTxt.setEditable(false);

                GroupLayout panel5Layout = new GroupLayout(panel5);
                panel5.setLayout(panel5Layout);
                panel5Layout.setHorizontalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BookIdTxt, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                panel5Layout.setVerticalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7)
                                .addComponent(BookIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            //======== panel6 ========
            {

                //---- label8 ----
                label8.setText("isbn\uff1a");

                GroupLayout panel6Layout = new GroupLayout(panel6);
                panel6.setLayout(panel6Layout);
                panel6Layout.setHorizontalGroup(
                    panel6Layout.createParallelGroup()
                        .addGroup(panel6Layout.createSequentialGroup()
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BookIsbnTxt, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panel6Layout.setVerticalGroup(
                    panel6Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label8)
                                .addComponent(BookIsbnTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            //======== panel7 ========
            {

                //---- label9 ----
                label9.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");

                GroupLayout panel7Layout = new GroupLayout(panel7);
                panel7.setLayout(panel7Layout);
                panel7Layout.setHorizontalGroup(
                    panel7Layout.createParallelGroup()
                        .addGroup(panel7Layout.createSequentialGroup()
                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BookAuthorTxt, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addContainerGap())
                );
                panel7Layout.setVerticalGroup(
                    panel7Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label9)
                                .addComponent(BookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            //======== panel8 ========
            {

                //---- label10 ----
                label10.setText("\u56fe\u4e66\u6807\u9898\uff1a");

                GroupLayout panel8Layout = new GroupLayout(panel8);
                panel8.setLayout(panel8Layout);
                panel8Layout.setHorizontalGroup(
                    panel8Layout.createParallelGroup()
                        .addGroup(panel8Layout.createSequentialGroup()
                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BookTitleTxt, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
                );
                panel8Layout.setVerticalGroup(
                    panel8Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label10)
                                .addComponent(BookTitleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            //---- queryBtn ----
            queryBtn.setText("\u67e5\u8be2");
            queryBtn.addActionListener(e -> queryBtn(e));

            //---- deleteBtn ----
            deleteBtn.setText("\u5220\u9664\u56fe\u4e66");
            deleteBtn.addActionListener(e -> deleteBtn(e));

            //---- button3 ----
            button3.setText("\u6dfb\u52a0\u56fe\u4e66");

            //---- updateBtn ----
            updateBtn.setText("\u4fee\u6539\u56fe\u4e66");
            updateBtn.addActionListener(e -> updateBtn(e));

            //---- resetTxtBtn ----
            resetTxtBtn.setText("\u6e05\u7a7a");
            resetTxtBtn.addActionListener(e -> resetTxtBtn(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(button3)
                                .addGap(18, 18, 18)
                                .addComponent(updateBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addGap(263, 263, 263))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(panel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(queryBtn, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(resetTxtBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(panel5, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(47, 47, 47))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button3)
                            .addComponent(updateBtn)
                            .addComponent(deleteBtn))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(panel5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel8, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(panel6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.LEADING, panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(queryBtn)
                                .addComponent(resetTxtBtn)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //======== panel1 ========
        {

            //======== scrollPane1 ========
            {

                //---- BookTable ----
                BookTable.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "Id", "\u56fe\u4e66\u540d\u79f0", "\u4f5c\u8005", "\u56fe\u4e66\u7c7b\u578b", "isbn\u56fd\u9645\u6807\u51c6\u4e66\u53f7"
                    }
                ));
                {
                    TableColumnModel cm = BookTable.getColumnModel();
                    cm.getColumn(0).setPreferredWidth(50);
                    cm.getColumn(1).setPreferredWidth(100);
                    cm.getColumn(3).setPreferredWidth(90);
                    cm.getColumn(4).setPreferredWidth(130);
                }
                BookTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        BookTableMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(BookTable);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(panel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(45, 45, 45))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        fillTable(new Book());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel2;
    private JPanel panel4;
    private JLabel label6;
    private JTextField BookTypeTxt;
    private JPanel panel5;
    private JLabel label7;
    private JTextField BookIdTxt;
    private JPanel panel6;
    private JLabel label8;
    private JTextField BookIsbnTxt;
    private JPanel panel7;
    private JLabel label9;
    private JTextField BookAuthorTxt;
    private JPanel panel8;
    private JLabel label10;
    private JTextField BookTitleTxt;
    private JButton queryBtn;
    private JButton deleteBtn;
    private JButton button3;
    private JButton updateBtn;
    private JButton resetTxtBtn;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable BookTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
