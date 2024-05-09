package com.example.JavaTask.repository.concrete;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import com.example.JavaTask.model.Author;
import com.example.JavaTask.repository.abstracts.FileAuthorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FileAuthorRepositoryImpl implements FileAuthorRepository {

    private final String filePath;

    public FileAuthorRepositoryImpl() {
        this.filePath= "C:\\Dev\\JavaTask\\src\\DB\\Yazar.txt";
    }

    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Author author = new Author();
                author.setAuthorId(Integer.parseInt(parts[0]));
                author.setFirstName(parts[1]);
                author.setLastName(parts[2]);
                author.setBookId(Integer.parseInt(parts[3]));
                authors.add(author);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }
    @Override
    public void save(Author author) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(author.getAuthorId() + ";" + author.getFirstName() +
                    ";" + author.getLastName() + ";" + author.getBookId());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Author getById(int authorId) {
        List<Author> authors = getAll();
        for (Author author : authors) {
            if (author.getAuthorId() == authorId) {
                return author;
            }
        }
        return null; // Yazar bulunamadı
    }
    @Override
    public void update(Author author) {
        List<Author> authors = getAll();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getAuthorId() == author.getAuthorId()) {
                authors.set(i, author);
                break;
            }
        }
        saveAllAuthors(authors);
    }
    @Override
    public void delete(int authorId) {
        List<Author> authors = getAll();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getAuthorId() == authorId) {
                authors.remove(i);
                break;
            }
        }
        saveAllAuthors(authors);
    }

    private void saveAllAuthors(List<Author> authors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Author author : authors) {
                writer.write(author.getAuthorId() + ";" + author.getFirstName() + ";" + author.getLastName() + ";" + author.getBookId());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}