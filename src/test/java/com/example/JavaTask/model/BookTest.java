package com.example.JavaTask.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1, "Book Title", 20.0, "ISBN-1234567890", 1);
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(book);
        assertEquals(1, book.getBookId());
        assertEquals("Book Title", book.getTitle());
        assertEquals(20.0, book.getPrice());
        assertEquals("ISBN-1234567890", book.getIsbn());
        assertEquals(1, book.getPublisherId());
    }

    @Test
    public void testSetters() {
        book.setBookId(2);
        book.setTitle("Updated Book Title");
        book.setPrice(25.0);
        book.setIsbn("ISBN-0987654321");
        book.setPublisherId(2);

        assertEquals(2, book.getBookId());
        assertEquals("Updated Book Title", book.getTitle());
        assertEquals(25.0, book.getPrice());
        assertEquals("ISBN-0987654321", book.getIsbn());
        assertEquals(2, book.getPublisherId());
    }

    @Test
    public void testEquals() {
        Book sameBook = new Book(1, "Book Title", 20.0, "ISBN-1234567890", 1);
        Book differentBook = new Book(2, "Different Book Title", 25.0, "ISBN-0987654321", 2);

        assertEquals(book, sameBook);
        assertNotEquals(book, differentBook);
    }

    @Test
    public void testHashCode() {
        Book sameBook = new Book(1, "Book Title", 20.0, "ISBN-1234567890", 1);

        assertEquals(book.hashCode(), sameBook.hashCode());
    }
}
