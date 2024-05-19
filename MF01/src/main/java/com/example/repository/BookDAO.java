package com.example.repository;

// * DB패러다임 변화: JDBC -> MyBatis(MVC) -> Spring + MyBatis -> Spring Data JPA(Hibernate)
import com.example.entity.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC 사용 시
 * 생산성이 떨어진다.(시간이 많이 소요된다.)
 * Java코드 + SQL: 믹서구현(유지보수가 어렵다.)
 * 그래서 MyBatis로 넘어가게 되는거
 */
public class BookDAO {

    // 기본적으로 JDBC에 필요한 것들
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConnect() {
        // DB 커넥션 정보
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/books";
        String username = "root";
        String password = "hugosnodejs";

        try {
            // DB에 커넥션 시도
            Class.forName(driverClassName);
            // 성공시 conn에 담는다.
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public List<BookDTO> bookList() {
        List<BookDTO> list = new ArrayList<>();
        String SQL = "select * from book order by title desc";
        conn = getConnect(); // 커넥션 생성 -> 오버해드(시간이 걸림)가 발생
        // 오버해드는 커넥션을 생성할 때 발생함 => 썻다 버리지말고 재활용하자! - ConnectionPOOL!!!! => Mybatis 설정파일에서 설정한다

        try {
            // 작성한 쿼리를 날리고 결과를 받아온다. => 개발자가 이 모든걸 일일이 작성하기 떄문에 작업 효율이 떨어짐
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            // DB로부터 레코드를 가져온다. => next를 통해 다음 로우가 있는지 체크해서 가져오는 것
            while (rs.next()) {
                Long num = rs.getLong("num");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String author = rs.getString("author");
                int page = rs.getInt("page");

                // 묶고(dto에) -> 담는다.(리스트계열에)
                BookDTO dto = new BookDTO(num, title, price, author, page);
//                dto.setNum(num);
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close(); // 순서 중요.
                ps.close();
                conn.close(); // 커넥션 종료
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public void createBook(BookDTO dto) {
        String SQL = "INSERT INTO book (title, price, author, page) VALUES (?, ?, ?, ?)";
        conn = getConnect();

        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, dto.getTitle());
            ps.setInt(2, dto.getPrice());
            ps.setString(3, dto.getAuthor());
            ps.setInt(4, dto.getPage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BookDTO getBook(int num) {
        BookDTO dto = new BookDTO();
        String SQL = "select * from book where num = ?";
        conn = getConnect();

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, num);
            rs = ps.executeQuery();

            if (rs.next()) {
                dto.setNum(rs.getLong("num"));
                dto.setTitle(rs.getString("title"));
                dto.setPrice(rs.getInt("price"));
                dto.setAuthor(rs.getString("author"));
                dto.setPage(rs.getInt("page"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dto;
    }

    public void updateBook(BookDTO book) {
        String SQL = "UPDATE book SET title = ?, price = ?, author = ?, page = ? WHERE num = ?";
        Connection conn = getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPrice());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getPage());
            ps.setLong(5, book.getNum());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBook(int num) {
        String SQL = "DELETE FROM book WHERE num = ?";
        conn = getConnect();

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, num);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
