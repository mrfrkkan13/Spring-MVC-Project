package com.example.JavaTask.repository.concrete;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.JavaTask.model.Publisher;
import com.example.JavaTask.repository.abstracts.FilePublisherRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FilePublisherRepositoryImpl implements FilePublisherRepository {

    private final String filePath;
    private final String bookFilePath;
    private final String authorFilePath;

    public FilePublisherRepositoryImpl() {
        this.filePath="C:\\Dev\\JavaTask\\src\\DB\\Yayinevi.txt";
        this.bookFilePath= "C:\\Dev\\JavaTask\\src\\DB\\Kitap.txt";
        this.authorFilePath= "C:\\Dev\\JavaTask\\src\\DB\\Yazar.txt";
    }

    @Override
    public List<Publisher> getAll() {
        List<Publisher> publishers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Publisher publisher = new Publisher();
                publisher.setPublisherId(Integer.parseInt(parts[0]));
                publisher.setPublisherName(parts[1]);
                publishers.add(publisher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publishers;
    }
    @Override
    public void save(Publisher publisher) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(publisher.getPublisherId() + ";" + publisher.getPublisherName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Publisher getById(int publisherId) {
        List<Publisher> publishers = getAll();
        for (Publisher publisher : publishers) {
            if (publisher.getPublisherId() == publisherId) {
                return publisher;
            }
        }
        return null; // Yayınevi bulunamadı
    }
    @Override
    public void update(Publisher publisher) {
        List<Publisher> publishers = getAll();
        for (int i = 0; i < publishers.size(); i++) {
            if (publishers.get(i).getPublisherId() == publisher.getPublisherId()) {
                publishers.set(i, publisher);
                break;
            }
        }
        saveAllPublishers(publishers);
    }
    @Override
    public void delete(int publisherId) {
        List<Publisher> publishers = getAll();
        for (int i = 0; i < publishers.size(); i++) {
            if (publishers.get(i).getPublisherId() == publisherId) {
                publishers.remove(i);
                break;
            }
        }
        saveAllPublishers(publishers);
    }

    private void saveAllPublishers(List<Publisher> publishers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Publisher publisher : publishers) {
                writer.write(publisher.getPublisherId() + ";" + publisher.getPublisherName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> listBooksAndAuthorsOfTwoPublishers() {
        Map<Integer, String> publishers
                = readPublishers(filePath);
        List<String> resultList = new ArrayList<>();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(bookFilePath))) {
            String line;
            while ((line = reader.readLine()) != null && count < 2) {
                String[] parts = line.split(";");
                int publisherId = Integer.parseInt(parts[4]);
                if (publishers.containsKey(publisherId)) {
                    resultList.add("Yayınevi: " + publishers.get(publisherId));
                    resultList.add("Kitap: " + parts[1]);
                    resultList.addAll(findAuthors(parts[0], authorFilePath));
                    resultList.add("");
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    private  Map<Integer, String> readPublishers(String filePath) {
        Map<Integer, String> publishers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int publisherId = Integer.parseInt(parts[0]);
                String publisherName = parts[1];
                publishers.put(publisherId, publisherName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    private  List<String> findAuthors(String bookId, String filePath) {
        List<String> authors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[3].equals(bookId)) {
                    authors.add("Yazar: " + parts[1] + " " + parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }

}
