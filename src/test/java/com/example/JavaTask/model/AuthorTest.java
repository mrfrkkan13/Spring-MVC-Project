package com.example.JavaTask.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorTest {

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author(1, "John", "Doe", 1);
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(author);
        assertEquals(1, author.getAuthorId());
        assertEquals("John", author.getFirstName());
        assertEquals("Doe", author.getLastName());
        assertEquals(1, author.getBookId());
    }

    @Test
    public void testSetters() {
        author.setAuthorId(2);
        author.setFirstName("Jane");
        author.setLastName("Smith");
        author.setBookId(2);

        assertEquals(2, author.getAuthorId());
        assertEquals("Jane", author.getFirstName());
        assertEquals("Smith", author.getLastName());
        assertEquals(2, author.getBookId());
    }

    @Test
    public void testEquals() {
        Author sameAuthor = new Author(1, "John", "Doe", 1);
        Author differentAuthor = new Author(2, "Jane", "Smith", 2);

        assertEquals(author, sameAuthor);
        assertNotEquals(author, differentAuthor);
    }

    @Test
    public void testHashCode() {
        Author sameAuthor = new Author(1, "John", "Doe", 1);

        assertEquals(author.hashCode(), sameAuthor.hashCode());
    }
}