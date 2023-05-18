package com.sky.library;



public interface BookService {
    Book retrieveBook(String bookReference) throws BookNotFoundException;
    String getBookSummary(String bookReference) throws BookNotFoundException;
}
