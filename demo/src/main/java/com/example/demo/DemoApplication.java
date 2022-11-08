package com.example.demo;

import com.example.demo.controller.StudentController;
import com.example.demo.services.MongoDBIslemleri;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Provider;


@SpringBootApplication
public class DemoApplication {

    private static Provider<com.example.demo.services.MongoDBIslemleri> MongoDBIslemleri;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("\n\n\nHello\n\n\n");

        StudentController student = new StudentController();
        student.elemanEkle();

       /* String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri))
        {
            MongoDatabase database = mongoClient.getDatabase("usersDB");
            MongoCollection<Document> collection = database.getCollection("users");

            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("username", "ayspoloji")
                        .append("phonenumber", "+90 553 274 5905")
                        .append("email", "ayspoloji@gmail.com"));
                System.out.println("Success! Inserted document id: " + result.getInsertedId());
            } catch (MongoException exception)
            {
                System.err.println("Unable to insert due to an error: " + exception);
            }
        }

        */

    }

    @Autowired
    private MongoDBIslemleri mongoDBIslemleri;

    private void tekElemanEkle(){
        mongoDBIslemleri.tekElemanEkle();
    }

}
