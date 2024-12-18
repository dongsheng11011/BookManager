/*
 * Created by JFormDesigner on Mon Dec 02 14:18:53 CST 2024
 */

package view;

import dao.BookDao;
import entity.Book;
import util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Vector;

/**
 * @author dongsheng
 */
public class BookType extends JInternalFrame {
    public BookType() {
        initComponents();
    }
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private BookDao bookDao = BookDao.getBookDao();

    private void fillTable(Book book){
        //初始化 ->
        DefaultTableModel dtm = (DefaultTableModel) BookTable.getModel();
        dtm.setRowCount(0); // 设置成0 ，清空表格。
        Connection con = null;
        try{
            //
            con = dbUtil.getCon();
            // jdbc 结果集
            ResultSet rs = bookDao.list2(con,book);
            // 遍历。
            while(rs.next()){
                //添加一行数据。
                Vector v = new Vector();
                v.add(rs.getInt("book_id"));
                v.add(rs.getString("title"));
                v.add(rs.getString("author"));
                v.add(rs.getString("genre"));
                v.add(rs.getString("isbn"));
                v.add(rs.getString("state"));
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

    private void filterTable() {
        boolean filterEnabled1 = checkBox1.isSelected();
        boolean filterEnabled2 = checkBox2.isSelected();
        TableRowSorter<DefaultTableModel> sorter;
        sorter = new TableRowSorter<>((DefaultTableModel) BookTable.getModel());
        BookTable.setRowSorter(sorter);
        //两个框都勾选则显示全部
        if(filterEnabled1 && filterEnabled2){
            sorter.setRowFilter(null);
            return;
        }
        // 使用自定义 RowFilter 来实现筛选出“未借阅”的信息
        if (filterEnabled1) {
            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                    // 获取第六列的值，第六列是“借阅状态”
                    String borrowStatus = (String) entry.getValue(5); // 获取第六列的值（索引从0开始，所以第五个索引是第六列）
                    return "已借阅".equals(borrowStatus);
                }
            });
        }
        else if (filterEnabled2) {
            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                    // 获取第六列的值，第六列是“借阅状态”
                    String borrowStatus = (String) entry.getValue(5); // 获取第六列的值（索引从0开始，所以第五个索引是第六列）s
                    return "未借阅".equals(borrowStatus);
                }
            });
        }
        else {
            sorter.setRowFilter(null); // 不使用过滤器，显示所有行
        }
    }

    private void updateTable(Book book,int row){
        Connection con = null;
        try{
            con = dbUtil.getCon();
            // jdbc 结果集
            ResultSet rs = bookDao.list4(con, book);
            if (rs.next()) {
                String state = rs.getString("state");
                BookTable.setValueAt(state, row, 5);
            } else {
                System.out.println("No results found");
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
        searchTxt.setText("");
    }

    private void queryBtn(ActionEvent e) {
        // TODO add your code here
        Book book = new Book(searchTxt.getText(),searchTxt.getText(),
                searchTxt.getText(),searchTxt.getText());
        fillTable(book);
    }

    private void showAllBtn(ActionEvent e) {
        // TODO add your code here
        fillTable(new Book());
    }

    private void showTypeBtn1(ActionEvent e) {
        // TODO add your code here
        fillTable(new Book("教材"));
    }

    private void showTypeBtn2(ActionEvent e) {
        // TODO add your code here
        fillTable(new Book("散文"));
    }

    private void showTypeBtn3(ActionEvent e) {
        // TODO add your code here
        fillTable(new Book("小说"));
    }

    private void showTypeBtn4(ActionEvent e) {
        // TODO add your code here
        fillTable(new Book("传记"));
    }

    private void showTypeBtn5(ActionEvent e) {
        // TODO add your code here
        fillTable(new Book("科普"));
    }

    private void checkBox1ItemStateChanged(ItemEvent e) {
        // TODO add your code here
        filterTable();
    }

    private void checkBox2ItemStateChanged(ItemEvent e) {
        // TODO add your code here
        filterTable();
    }

    private void borrowBtn(ActionEvent e) {
        // 获取 JTable 中被选中的行
        int row = BookTable.getSelectedRow();
        // 检查是否有行被选中
        if (row == -1) {
            // 如果没有选中任何行，弹出提示框
            JOptionPane.showMessageDialog(null, "请选择你要借阅的图书");
            return;
        }
        // 获取该行的 book_id（假设 book_id 在第一列，即列索引 0）
        int book_id = (int) BookTable.getValueAt(row, 0);
        String state = (String) BookTable.getValueAt(row, 5);
        if(Objects.equals(state, "已借阅"))
        {
            JOptionPane.showMessageDialog(null, "该图书已被借阅");
            return;
        }
        // 检查 book_id 是否有效，假设 book_id 是正数
        if (book_id <= 0) {
            JOptionPane.showMessageDialog(null, "无效的图书ID");
            return;
        }
        updateTable(new Book(book_id, "已借阅"), row);
        JOptionPane.showMessageDialog(null, "借阅成功");
    }

    private void returnBtn(ActionEvent e) {
        // 获取 JTable 中被选中的行
        int row = BookTable.getSelectedRow();
        // 检查是否有行被选中
        if (row == -1) {
            // 如果没有选中任何行，弹出提示框
            JOptionPane.showMessageDialog(null, "请选择你要归还的图书");
            return;
        }
        // 获取该行的 book_id（假设 book_id 在第一列，即列索引 0）
        int book_id = (int) BookTable.getValueAt(row, 0);
        String state = (String) BookTable.getValueAt(row, 5);
        if(Objects.equals(state, "未借阅"))
        {
            JOptionPane.showMessageDialog(null, "该图书已经归还");
            return;
        }
        // 检查 book_id 是否有效，假设 book_id 是正数
        if (book_id <= 0) {
            JOptionPane.showMessageDialog(null, "无效的图书ID");
            return;
        }
        updateTable(new Book(book_id, "未借阅"), row);
        JOptionPane.showMessageDialog(null, "归还成功");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        searchTxt = new JTextField();
        queryBtn = new JButton();
        resetTxtBtn = new JButton();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        BookTable = new JTable();
        showAllBtn = new JButton();
        showTypeBtn1 = new JButton();
        showTypeBtn2 = new JButton();
        showTypeBtn3 = new JButton();
        returnBtn = new JButton();
        borrowBtn = new JButton();
        showTypeBtn4 = new JButton();
        showTypeBtn5 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u56fe\u4e66\u5217\u8868");
        setClosable(true);
        setIconifiable(true);
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- checkBox1 ----
            checkBox1.setText("\u5df2\u501f\u9605");
            checkBox1.addItemListener(e -> checkBox1ItemStateChanged(e));

            //---- checkBox2 ----
            checkBox2.setText("\u672a\u501f\u9605");
            checkBox2.addItemListener(e -> checkBox2ItemStateChanged(e));

            //---- queryBtn ----
            queryBtn.setText("\u641c\u7d22");
            queryBtn.addActionListener(e -> queryBtn(e));

            //---- resetTxtBtn ----
            resetTxtBtn.setText("\u6e05\u7a7a");
            resetTxtBtn.addActionListener(e -> resetTxtBtn(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(75, Short.MAX_VALUE)
                        .addComponent(checkBox1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBox2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(queryBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetTxtBtn)
                        .addGap(26, 26, 26))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(queryBtn)
                            .addComponent(resetTxtBtn)
                            .addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBox2)
                            .addComponent(checkBox1))
                        .addGap(15, 15, 15))
            );
        }

        //======== panel2 ========
        {

            //======== scrollPane1 ========
            {

                //---- BookTable ----
                BookTable.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"", "", null, null, null, null},
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
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "ID", "\u56fe\u4e66\u540d\u79f0", "\u4f5c\u8005", "\u56fe\u4e66\u7c7b\u578b", "ISBN\u56fd\u9645\u6807\u51c6\u4e66\u53f7", "\u501f\u9605\u60c5\u51b5"
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
                    TableColumnModel cm = BookTable.getColumnModel();
                    cm.getColumn(0).setResizable(false);
                    cm.getColumn(0).setPreferredWidth(50);
                    cm.getColumn(1).setResizable(false);
                    cm.getColumn(1).setPreferredWidth(100);
                    cm.getColumn(2).setResizable(false);
                    cm.getColumn(3).setResizable(false);
                    cm.getColumn(3).setPreferredWidth(90);
                    cm.getColumn(4).setResizable(false);
                    cm.getColumn(4).setPreferredWidth(130);
                    cm.getColumn(5).setResizable(false);
                }
                scrollPane1.setViewportView(BookTable);
            }

            //---- showAllBtn ----
            showAllBtn.setText("\u5168\u90e8");
            showAllBtn.addActionListener(e -> showAllBtn(e));

            //---- showTypeBtn1 ----
            showTypeBtn1.setText("\u6559\u6750");
            showTypeBtn1.addActionListener(e -> showTypeBtn1(e));

            //---- showTypeBtn2 ----
            showTypeBtn2.setText("\u6563\u6587");
            showTypeBtn2.addActionListener(e -> showTypeBtn2(e));

            //---- showTypeBtn3 ----
            showTypeBtn3.setText("\u5c0f\u8bf4");
            showTypeBtn3.addActionListener(e -> showTypeBtn3(e));

            //---- returnBtn ----
            returnBtn.setText("\u5f52\u8fd8");
            returnBtn.addActionListener(e -> returnBtn(e));

            //---- borrowBtn ----
            borrowBtn.setText("\u501f\u9605");
            borrowBtn.addActionListener(e -> borrowBtn(e));

            //---- showTypeBtn4 ----
            showTypeBtn4.setText("\u4f20\u8bb0");
            showTypeBtn4.addActionListener(e -> showTypeBtn4(e));

            //---- showTypeBtn5 ----
            showTypeBtn5.setText("\u79d1\u666e");
            showTypeBtn5.addActionListener(e -> showTypeBtn5(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addComponent(borrowBtn)
                                .addGap(121, 121, 121)
                                .addComponent(returnBtn)
                                .addGap(122, 122, 122))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(showTypeBtn1)
                                    .addComponent(showTypeBtn2)
                                    .addComponent(showAllBtn)
                                    .addComponent(showTypeBtn3)
                                    .addComponent(showTypeBtn4)
                                    .addComponent(showTypeBtn5))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(showAllBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showTypeBtn1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showTypeBtn2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showTypeBtn3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showTypeBtn4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showTypeBtn5))
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(returnBtn)
                            .addComponent(borrowBtn))
                        .addContainerGap(45, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        fillTable(new Book());//打开页面时就进行一次搜索展示全部内容
        BookTable.getTableHeader().setReorderingAllowed(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JTextField searchTxt;
    private JButton queryBtn;
    private JButton resetTxtBtn;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable BookTable;
    private JButton showAllBtn;
    private JButton showTypeBtn1;
    private JButton showTypeBtn2;
    private JButton showTypeBtn3;
    private JButton returnBtn;
    private JButton borrowBtn;
    private JButton showTypeBtn4;
    private JButton showTypeBtn5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
