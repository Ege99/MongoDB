package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MongoDBKullanici implements Serializable
{
    public String ad = "Ege";

    public String telefonNo;

    public String email;

    public String ogrenciNo;

    public String bolumAd;

    public String sinif;

}
