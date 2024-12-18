package dao;

import entity.Book;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookDao {
    private static BookDao bookDao;

    public static BookDao getBookDao() {
        if(bookDao == null){
            bookDao = new BookDao();
        }
        return bookDao;
    }

    /**
     * 查询图书集合。
     * @param con
     * @param book
     * @return
     * @throws Exception
     */
    public ResultSet list(Connection con, Book book)throws Exception{
        StringBuffer sb= new StringBuffer("select * from t_books");
        if(StringUtil.isNotEmpty(book.getTitle())){
            sb.append(" and title like '%"+book.getTitle()+"%'");
            PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
            return pstmt.executeQuery();
        }
        // 把第一个and替换成where。
        if(StringUtil.isNotEmpty(book.getAuthor())){
            sb.append(" and author like '%"+book.getAuthor()+"%'");
            PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
            return pstmt.executeQuery();
        }
        if(StringUtil.isNotEmpty(book.getIsbn())){
            sb.append(" and isbn like '%"+book.getIsbn()+"%'");
            PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
            return pstmt.executeQuery();
        }
        if(StringUtil.isNotEmpty(book.getGenre())){
            sb.append(" and genre like '%"+book.getGenre()+"%'");
            PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));
            return pstmt.executeQuery();
        }
        return con.prepareStatement(sb.toString()).executeQuery();
    }

    public int delete(Connection con, String id) throws Exception{
        String sql = "delete from t_books where book_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,id);
        return pstmt.executeUpdate();
    }

    public int update(Connection con, Book book)throws Exception {
        String sql="update t_books set title=?,author=?,genre=?,isbn=? where book_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString (1,book.getTitle());
        pstmt.setString (2,book.getAuthor());
        pstmt.setString(3,book.getGenre());
        pstmt.setString(4,book.getIsbn());
        pstmt.setInt(5,book.getBookId());
        return pstmt.executeUpdate();
    }
    public int add (Connection con,Book book)throws Exception{
        String sql="insert into t_books(title,author,genre,isbn)values (?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString (1,book.getTitle());
        pstmt.setString (2,book.getAuthor());
        pstmt.setString(3,book.getGenre());
        pstmt.setString(4,book.getIsbn());
        return pstmt.executeUpdate();
    }

    /**
     * 全字段搜索
     * @param con
     * @param book
     * @return
     * @throws Exception
     */
    public ResultSet list2(Connection con, Book book)throws Exception{
        if(StringUtil.isEmpty(book.getTitle()) && StringUtil.isEmpty(book.getAuthor()) && StringUtil.isEmpty(book.getIsbn())  && StringUtil.isEmpty(book.getGenre()))
        {
            String sb = "SELECT t_books.*, t_borrow_status.state FROM t_books INNER JOIN t_borrow_status ON t_books.book_id = t_borrow_status.book_id;";
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sb);
        }
        String sb = "SELECT b.*, bs.state " +
                "FROM t_books b " +
                "INNER JOIN t_borrow_status bs ON b.book_id = bs.book_id " +
                "WHERE b.title LIKE ? " +
                "OR b.author LIKE ? " +
                "OR b.isbn LIKE ? " +
                "OR b.genre LIKE ?";
        PreparedStatement pstmt = con.prepareStatement(sb);

        // 设置查询参数，避免 SQL 注入
        pstmt.setString(1, "%" + book.getTitle() + "%");
        pstmt.setString(2, "%" + book.getAuthor() + "%");
        pstmt.setString(3, "%" + book.getIsbn() + "%");
        pstmt.setString(4, "%" + book.getGenre() + "%");

        // 执行查询
        return pstmt.executeQuery();
    }

    public ResultSet list3(Connection con)throws Exception{
        return con.createStatement().executeQuery("select * from borrow_status_audit");
    }

    public ResultSet list4(Connection con, Book book) throws Exception {
        // SQL 更新语句
        String updateSQL = "UPDATE t_borrow_status SET state = ? WHERE book_id = ?";
        PreparedStatement pstmt = con.prepareStatement(updateSQL);
        pstmt.setString(1, book.getState());  // 仍然是字符串类型的 book state
        pstmt.setInt(2, book.getBookId());    // 使用 setInt 来设置 int 类型的 book_id
        pstmt.executeUpdate();  // 执行更新操作

        // SQL 查询语句，获取更新后的状态
        String selectSQL = "SELECT state FROM t_borrow_status WHERE book_id = ?";
        PreparedStatement pstmt2 = con.prepareStatement(selectSQL);
        pstmt2.setInt(1, book.getBookId());  // 使用 setInt 来传递 int 类型的 book_id
        return pstmt2.executeQuery();
    }
}
