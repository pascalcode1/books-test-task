package ru.pascalcode.bookstesttask.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.pascalcode.bookstesttask.exception.ValidateRequestException;
import ru.pascalcode.bookstesttask.model.Book;
import ru.pascalcode.bookstesttask.model.BookRequest;
import ru.pascalcode.bookstesttask.repo.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.pascalcode.bookstesttask.utils.BookUtils.createBookFromBookRequest;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAllOrderByTitle();
    }

    public Book add(BookRequest bookRequest) throws ValidateRequestException {
        Long id = bookRepository.create(bookRequest);
        return createBookFromBookRequest(id, bookRequest);
    }

    public Map<String, List<Book>> findAllGroupByAuthor() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
    }

    public List<String> substringOccurrence(String substring) {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .filter(book -> bookTitleContainsSubstring(book, substring))
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.mapping(
                                book -> StringUtils.countOccurrencesOf(
                                        book.getTitle().toLowerCase(),
                                        substring.toLowerCase()),
                                Collectors.toList())))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .mapToLong(Long::valueOf)
                                .sum()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .map(e -> e.getKey() + ", " + e.getValue())
                .collect(Collectors.toList());
    }

    private boolean bookTitleContainsSubstring(Book book, String letter) {
        return book.getTitle() != null && book.getTitle().toLowerCase().contains(letter.toLowerCase());
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public int update(Long id, BookRequest bookRequest) {
        if (id <= 0L) {
            throw new ValidateRequestException("ID must be a positive number.");
        }
        return bookRepository.update(id, bookRequest);
    }

}
