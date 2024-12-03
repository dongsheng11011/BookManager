package entity;

public class BookType {
    private int genreId; // 类型ID
    private String genreName; // 类型名称。

    public BookType(){

    }
    public BookType(String genreName) {
        this.genreName = genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
