package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        addRecord(new Book(1929, "A Farewell to arms", "The plot of A Farewell to Arms follows the love affair of Frederic, an injured soldier, and Catherine, his nurse during World War I in Italy. Frederic Henry falls in love with Catherine Barkley as she tends to his wounds. Frederic is called", "Literary realism, war literature, historical fiction", "Ernest Hemingway"));
        addRecord(new Book(1937, "The Hobbit", "The Hobbit is set in Middle-earth and follows home-loving Bilbo Baggins, the hobbit of the title, who joins the wizard Gandalf and the thirteen dwarves of Thorin's Company, on a quest to reclaim the dwarves' home and treasure from the dragon Smaug.", "Fantasy", "J.R.R. Tolkien"));
        addRecord(new Book(1992, "Animal Farm", "The story of a group of anthropomorphic farm animals who rebel against their human farmer, hoping to create a society where the animals can be equal, free, and happy.", "Political satire", "George Orwell"));

        new GUI();

    }
    public static void addRecord(Book newBook)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonInString = objectMapper.writeValueAsString(newBook);
            System.out.println(jsonInString);

            String postUrl = "http://localhost:8080/books";

            HttpClient httpClient = HttpClients.createDefault();

            HttpPost httpPost = new HttpPost(postUrl);

            httpPost.setHeader("Content-Type", "application/json");

            StringEntity entity = new StringEntity(jsonInString);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getRecordByTitle(String title) {
        System.out.println("======  Record by title ========");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("http://localhost:8080/books/"+title);

            JsonNode jsonNode = objectMapper.readTree(url);

            System.out.println(jsonNode);

            System.out.println("Id: " + jsonNode.get("id").asText());
            System.out.println("Published year: " + jsonNode.get("publishedYear").asText());
            System.out.println("Title: " + jsonNode.get("title").asText());
            System.out.println("Description: " + jsonNode.get("description").asText());
            System.out.println("Category: " + jsonNode.get("category").asText());
            System.out.println("Author: " + jsonNode.get("author").asText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getAllRecords()
    {
        System.out.println("====== All Records ========");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("http://localhost:8080/books");

            List<RecordBook> records = objectMapper.readValue(url,
                    new TypeReference<List<RecordBook>>() {
                    });

            for (RecordBook record : records) {
                System.out.println("ID: " + record.getId() +
                        ", \nPublished year: " + record.getPublishedYear()+
                        ", \nTitle: " + record.getTitle()+
                        ", \nDescription: " + record.getDescription()+
                        ", \nCategory: " + record.getCategory()+
                        ", \nAuthor: " + record.getAuthor());
                System.out.println("--------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}