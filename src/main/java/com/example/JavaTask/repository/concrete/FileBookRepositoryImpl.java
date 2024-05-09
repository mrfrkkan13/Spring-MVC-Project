package com.example.JavaTask.repository.concrete;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import com.example.JavaTask.model.Book;
import com.example.JavaTask.repository.abstracts.FileBookRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FileBookRepositoryImpl implements FileBookRepository {

    private final String filePath;

    public FileBookRepositoryImpl() {
        this.filePath= "C:\\Dev\\JavaTask\\src\\DB\\Kitap.txt";
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Book book = new Book();
                book.setBookId(Integer.parseInt(parts[0]));
                book.setTitle(parts[1]);
                book.setPrice(Double.parseDouble(parts[2]));
                book.setIsbn(parts[3]);
                book.setPublisherId(Integer.parseInt(parts[4]));
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    @Override
    public void save(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(book.getBookId() + ";" + book.getTitle() + ";" + book.getPrice() + ";"
                    + book.getIsbn() + ";" + book.getPublisherId());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Book getById(int bookId) {
        List<Book> books = getAll();
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // Kitap bulunamadı
    }
    @Override
    public void update(Book book) {
        List<Book> books = getAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == book.getBookId()) {
                books.set(i, book);
                break;
            }
        }
        saveAllBooks(books);
    }
    @Override
    public void delete(int bookId) {
        List<Book> books = getAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == bookId) {
                books.remove(i);
                break;
            }
        }
        saveAllBooks(books);
    }

    private void saveAllBooks(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.getBookId() + ";" + book.getTitle() + ";" + book.getPrice() + ";" + book.getIsbn() + ";" + book.getPublisherId());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}