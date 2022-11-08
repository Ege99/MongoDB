package com.example.demo.services.impl;

import com.example.demo.model.MongoDBConfig;
import com.example.demo.model.MongoDBKullanici;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.example.demo.services.MongoDBIslemleri;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class MongoDBIslemleriImpl implements MongoDBIslemleri
{

    MongoDBKullanici mongoDBKullanici = new MongoDBKullanici();
    MongoDBConfig mongoDBConfig = new MongoDBConfig();
    String uri = mongoDBConfig.uri;

    @Override
    public void tekElemanEkle()
    {
        System.out.println("\n\n Hello Mongo \n\n");

        try (MongoClient mongoClient = MongoClients.create(uri))
        {
            MongoDatabase database = mongoClient.getDatabase("usersDB");
            MongoCollection<Document> collection = database.getCollection("users");
            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("username", mongoDBKullanici.ad)
                        .append("phonenumber", mongoDBKullanici.telefonNo)
                        .append("email", mongoDBKullanici.email));
                System.out.println("Başarılı! Doküman id eklendi : " + result.getInsertedId());
            } catch (MongoException exception)
            {
                System.err.println("Hata! Ekleme yapılamadı. Kaynaklanan hata : " + exception);
            }
        }
    }

    @Override
    public void cokElemanEkle()
    {
        try (MongoClient mongoClient = MongoClients.create(uri))
        {
            MongoDatabase database = mongoClient.getDatabase("usersDB");
            MongoCollection<Document> collection = database.getCollection("users");

            List<Document> userList = Arrays.asList(
                    new Document().append("username", "Lincoln Henrique"),
                    new Document().append("username", "Emre Mor"));
            try {
                InsertManyResult result = collection.insertMany(userList);
                System.out.println("Başarılı! Doküman id eklendi: " + result.getInsertedIds());
            } catch (MongoException exception)
            {
                System.err.println("Hata! Ekleme yapılamadı. Kaylanana hata : " + exception);
            }

        }
    }

    @Override
    public void elemanAra()
    {
        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("usersDB");
            MongoCollection<Document> collection = database.getCollection("users");

            FindIterable<Document> iterDoc = collection.find();

            Iterator it = iterDoc.iterator();
            while (it.hasNext()) {
                //System.out.println(doc.it.next());
            }
        }
    }

    @Override
    public void elemanGuncelle()
    {
        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("usersDB");
            MongoCollection<Document> collection = database.getCollection("users");

            Document query = new Document().append("username", "ege");

            Bson updates = Updates.combine(
                    Updates.set("phonenumber", "+90507 712 106999999"),
                    Updates.addToSet("address", "666, 5th Avenue, NYC"));

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                UpdateResult result = collection.updateOne(query, updates, options);

                System.out.println("Güncellenen doküman sayısı : " + result.getModifiedCount());

                System.out.println("Eklenen belge id: " + result.getUpsertedId());
            } catch (MongoException exception)
            {
                System.err.println("Hata! Ekleme yapılamadı. Kaylanana hata : " + exception);
            }

        }
    }

    @Override
    public void silinenEleman()
    {
        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("usersDB");
            MongoCollection<Document> collection = database.getCollection("users");

            Bson query = eq("username", "ege");

            try {
                DeleteResult result = collection.deleteOne(query);
                System.out.println("Silinen doküman sayısı : " + result.getDeletedCount());
            }
            catch (MongoException exception)
            {
                System.err.println("Hata! Ekleme yapılamadı. Kaylanana hata : " + exception);
            }

        }
    }
}
