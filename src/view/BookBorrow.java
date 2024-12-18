/*
 * Created by JFormDesigner on Mon Dec 02 13:15:26 CST 2024
 */

package view;

import javax.swing.table.*;
import dao.BookDao;
import util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * @author AIM
 */
public class BookBorrow extends JInternalFrame {
    public BookBorrow() {
        initComponents();
    }
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private BookDao bookDao = BookDao.getBookDao();

    private void fillTable(){
        //初始化 ->
        DefaultTableModel dtm = (DefaultTableModel) recordTable.getModel();
        dtm.setRowCount(0); // 设置成0 ，清空表格。
        Connection con = null;
        try{
            //
            con = dbUtil.getCon();
            // jdbc 结果集
            ResultSet rs = bookDao.list3(con);
            // 遍历。
            while(rs.next()){
                //添加一行数据。
                Vector v = new Vector();
                v.add(rs.getString("audit_id"));
                v.add(rs.getString("book_id"));
                v.add(rs.getString("new_state"));
                v.add(rs.getString("updated_at"));
                v.add(rs.getString("updated_by"));
                v.add(rs.getString("operation"));
                dtm.insertRow(0, v);; // 添加数据。
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

    private void button1(ActionEvent e) {
        // TODO add your code here
        fillTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        recordTable = new JTable();
        button1 = new JButton();

        //======== this ========
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setTitle("\u501f\u9605\u8bb0\u5f55");
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== scrollPane1 ========
            {

                //---- recordTable ----
                recordTable.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"", null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u8bb0\u5f55ID", "\u56fe\u4e66ID", "\u64cd\u4f5c\u540e\u72b6\u6001", "\u65f6\u95f4", "\u64cd\u4f5c\u4eba", "\u64cd\u4f5c"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, true, true, true, true, true
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                {
                    TableColumnModel cm = recordTable.getColumnModel();
                    cm.getColumn(0).setResizable(false);
                    cm.getColumn(0).setPreferredWidth(55);
                    cm.getColumn(1).setResizable(false);
                    cm.getColumn(1).setPreferredWidth(55);
                    cm.getColumn(2).setResizable(false);
                    cm.getColumn(2).setPreferredWidth(70);
                    cm.getColumn(3).setResizable(false);
                    cm.getColumn(3).setPreferredWidth(115);
                    cm.getColumn(4).setResizable(false);
                    cm.getColumn(5).setResizable(false);
                }
                recordTable.setEnabled(false);
                scrollPane1.setViewportView(recordTable);
            }

            //---- button1 ----
            button1.setText("\u5237\u65b0");
            button1.addActionListener(e -> button1(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(button1)
                        .addContainerGap(320, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button1)
                        .addGap(0, 90, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        fillTable();
        recordTable.getTableHeader().setReorderingAllowed(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable recordTable;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
