package ru.pascalcode.bookstesttask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pascalcode.bookstesttask.model.Book;
import ru.pascalcode.bookstesttask.model.BookRequest;
import ru.pascalcode.bookstesttask.service.BookService;

import java.util.List;
import java.util.Map;

@Api("Book Controller")
@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 1. Endpoint возвращающий список все книг, которые содержатся в таблице book,
     * отсортированный в обратном алфавитном порядке значения колонки book.title.
     *
     * @return Список книг.
     */
    @ApiOperation("Возвращает список все книг, которые содержатся в таблице book, " +
            "отсортированный в обратном алфавитном порядке значения колонки book.title")
    @GetMapping()
    public List<Book> findAll() {
        return bookService.findAll();
    }

    /**
     * 2. Endpoint добавления новой книги в таблицу book.
     *
     * @param book Книга для вставки в БД.
     * @return Книга со сгенерированным в БД ID.
     */
    @ApiOperation("Добавление новой книги в таблицу book")
    @PostMapping()
    public Book add(@RequestBody BookRequest book) {
        return bookService.add(book);
    }

    /**
     * 3. Endpoint возвращающий список всех книг, сгруппированных по автору
     * книги(book.author).
     *
     * @return Книги, сгруппированные по автору.
     */
    @ApiOperation("Возвращает список всех книг, сгруппированных по автору книги(book.author)")
    @GetMapping("authors")
    public Map<String, List<Book>> findAllGroupByAuthor() {
        return bookService.findAllGroupByAuthor();
    }

    /**
     * 4. Endpoint принимающий в качестве параметра символ и возвращающий список из 10 авторов,
     * в названии книг которых этот символ встречается наибольшее количество раз вместе с
     * количеством вхождений этого символа во все названия книг автора.
     *
     * @param substring буква(ы) которую(ые) следует искать.
     * @return Список книг.
     */
    @ApiOperation("Принимает в качестве параметра символ и возвращающий список из 10 авторов, " +
            "в названии книг которых этот символ встречается наибольшее количество раз вместе с " +
            "количеством вхождений этого символа во все названия книг автора")
    @GetMapping("occurrence/{substring}")
    public List<String> substringOccurrence(@PathVariable String substring) {
        return bookService.substringOccurrence(substring);
    }

    /**
     * Bonus! Получение книги по ID.
     *
     * @param id ID книги.
     * @return Книга.
     */
    @ApiOperation("Получение книги по ID")
    @GetMapping("{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    /**
     * Bonus! Обновление сущности.
     *
     * @param id   ID обновляемой книги.
     * @param book Тело обновляемой книги.
     * @return "1" если успех.
     */
    @ApiOperation("Обновление сущности")
    @PutMapping("{id}")
    public int update(@PathVariable Long id,
                      @RequestBody BookRequest book) {
        return bookService.update(id, book);
    }
}
