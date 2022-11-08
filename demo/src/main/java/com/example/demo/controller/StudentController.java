package com.example.demo.controller;

import com.example.demo.model.MongoDBKullanici;
import com.example.demo.services.MongoDBIslemleri;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

@Component("studentController")
@RequiredArgsConstructor
public class StudentController
{
    MongoDBKullanici kullanici = new MongoDBKullanici();
    @Getter
    private MongoDBKullanici mongoDBKullanici;

    public MongoDBKullanici getMongoDBKullanici() {
        kullanici.setAd(getMongoDBKullanici().getAd());
        return kullanici;
    }

    @Getter
    public MongoDBIslemleri mongoDBIslemleri;

    public void init()
    {
        this.mongoDBKullanici = new MongoDBKullanici();
    }

    public void elemanEkle()
    {
        kullanici.ad = "ege";
        try {
            getMongoDBKullanici().getAd();
            this.mongoDBIslemleri.tekElemanEkle();
        } catch (Exception exception)
        {
            System.out.println("Hata : " + exception);
        }
    }
}
