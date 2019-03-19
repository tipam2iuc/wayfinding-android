package com.example.wayfindinginterface.modele;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Annonce {
    private int id;
    private String titre;
    private int Auteur;
    private String message;

    // contructor
    public Annonce(int id, String titre, int auteur, String message) {
        this.id = id;
        this.titre = titre;
        Auteur = auteur;
        this.message = message;
    }

    public Annonce() {
    }

    //getter and setter atributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAuteur() {
        return Auteur;
    }

    public void setAuteur(int auteur) {
        Auteur = auteur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //equals, getHashCode and toString override

    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", Auteur=" + Auteur +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Annonce annonce = (Annonce) o;
        return id == annonce.id;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
