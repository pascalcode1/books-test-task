package ru.pascalcode.bookstesttask.utils;

import ru.pascalcode.bookstesttask.model.Book;
import ru.pascalcode.bookstesttask.model.BookRequest;

public class BookUtils {

    public static Book createBookFromBookRequest(Long id, BookRequest request) {
        return new Book(id, request.getTitle(), request.getAuthor(), request.getDescription());
    }

}
