package com.sky.library;

import java.util.Arrays;

public class BookServiceImpl implements BookService {
    BookRepository repository;

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        if (!bookReference.toUpperCase().startsWith("BOOK-")) throw new BookNotFoundException(bookReference, true);
        try {
            Book book = repository.retrieveBook(bookReference);
            if (book == null) throw new Exception();
            return book;
        } catch (Exception e) {
            throw new BookNotFoundException(bookReference, false);
        }
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        if (!bookReference.toUpperCase().startsWith("BOOK-")) throw new BookNotFoundException(bookReference, true);
        try {
            Book book = this.retrieveBook(bookReference);
            if (book == null) throw new Exception();

            String[] wordsInReview = book.getReview().split(" ");

            if (wordsInReview.length > 9) {
                return String.format("[%s] %s - %s...",
                        book.getReference(),
                        book.getTitle(),
                        String.join(" ", Arrays.copyOf(wordsInReview, 9)));
            }

            return String.format("[%s] %s - %s",
                    book.getReference(),
                    book.getTitle(),
                    book.getReview()
            );
        } catch (Exception e) {
            throw new BookNotFoundException(bookReference, false);
        }
    }
}
