package com.example.JavaTask.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PublisherTest {

    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        publisher = new Publisher(1, "Publisher Name");
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(publisher);
        assertEquals(1, publisher.getPublisherId());
        assertEquals("Publisher Name", publisher.getPublisherName());
    }

    @Test
    public void testSetters() {
        publisher.setPublisherId(2);
        publisher.setPublisherName("Updated Publisher Name");

        assertEquals(2, publisher.getPublisherId());
        assertEquals("Updated Publisher Name", publisher.getPublisherName());
    }

    @Test
    public void testEquals() {
        Publisher samePublisher = new Publisher(1, "Publisher Name");
        Publisher differentPublisher = new Publisher(2, "Different Publisher Name");

        assertEquals(publisher, samePublisher);
        assertNotEquals(publisher, differentPublisher);
    }

    @Test
    public void testHashCode() {
        Publisher samePublisher = new Publisher(1, "Publisher Name");

        assertEquals(publisher.hashCode(), samePublisher.hashCode());
    }
}
