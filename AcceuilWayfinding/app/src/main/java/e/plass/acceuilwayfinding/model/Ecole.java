package e.plass.acceuilwayfinding.model;

import java.util.Calendar;

public class Ecole {
    private int id;
    private float    note;
    private String name;
    private String image;
    private Calendar   creationDate;
    private String ville;
    private String quartier;
    private String directeur;
    private String descrition;


    //constructor
    public Ecole() {
    }

    public Ecole(int id, float note, String name, String image, Calendar creationDate, String ville, String quartier, String directeur, String descrition) {
        this.id = id;
        this.note = note;
        this.name = name;
        this.image = image;
        this.creationDate = creationDate;
        this.ville = ville;
        this.quartier = quartier;
        this.directeur = directeur;
        this.descrition = descrition;
    }

    public float getNote() {
        return note;
    }

    public Ecole(Ecole ecole){
        if(ecole == null){
            throw new NullPointerException("Ecole");
        }else{
            id=ecole.id;
            name=ecole.name;
            creationDate=ecole.creationDate;
            ville=ecole.ville;
            quartier=ecole.quartier;
            directeur=ecole.directeur;
        }
    }

    //getter and setter attribute


    public void setNote(float note) {
        this.note = note;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getDirecteur() {
        return directeur;
    }

    public void setDirecteur(String directeur) {
        this.directeur = directeur;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //Equals, getHashCode and toString override

    @Override
    public String toString() {
        return "Ecole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", ville='" + ville + '\'' +
                ", quartier='" + quartier + '\'' +
                ", directeur='" + directeur + '\'' +
                '}';
    }
}
