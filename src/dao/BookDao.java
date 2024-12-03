package dao;

import entity.Book;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
