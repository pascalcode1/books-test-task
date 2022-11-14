package ru.pascalcode.bookstesttask.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.pascalcode.bookstesttask.model.Book;
import ru.pascalcode.bookstesttask.model.BookRequest;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class BookRepository {

    private static final String SELECT_ALL = "select * from book";

    private static final String SELECT_ALL_ORDER_BY_TITLE_DESC = "select * from book b order by b.title desc";

    private static final String SELECT_BY_ID = "select * from book where id = ?";

    private static final String INSERT_QUERY = "insert into book (title, author, description) values (?, ?, ?)";

    private static final String UPDATE_QUERY = "update book set title = ?, author = ?, description = ? where id = ?";
    private final JdbcTemplate jdbcTemplate;

    private final BeanPropertyRowMapper<Book> bookMapper = new BeanPropertyRowMapper<>(Book.class);

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query(SELECT_ALL, bookMapper);
    }

    public List<Book> findAllOrderByTitle() {
        return jdbcTemplate.query(SELECT_ALL_ORDER_BY_TITLE_DESC, bookMapper);
    }

    public Book findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, bookMapper, id);
    }

    public Long create(BookRequest book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }

    public int update(Long id, BookRequest request) {
        return jdbcTemplate.update(UPDATE_QUERY,
                request.getTitle(), request.getAuthor(),
                request.getDescription(), id);
    }
}
