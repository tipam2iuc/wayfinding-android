package e.plass.acceuilwayfinding.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.Objects;

public class Utilisateur {
    private int id;
    private String nom;
    private String preNom;
    private String dernierEcole;
    private String photo;
    private sexe   sexe;
    private Date   dateNaissance;

    private enum sexe{
        homme,
        femme,
        autre
    }

    //constructor

    public Utilisateur(int id, String nom, String preNom, String dernierEcole, String photo, Utilisateur.sexe sexe, Date dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.preNom = preNom;
        this.dernierEcole = dernierEcole;
        this.photo = photo;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
    }
    public Utilisateur() {
    }
    private Utilisateur(Utilisateur utilisateur){
        if(utilisateur == null){
            throw new NullPointerException("utilisateur");
        }else{
            id = utilisateur.id;
            nom = utilisateur.nom;
            preNom = utilisateur.preNom;
            dernierEcole = utilisateur.dernierEcole;
            photo = utilisateur.photo;
            sexe = utilisateur.sexe;
            dateNaissance = utilisateur.dateNaissance;
        }
    }


    //getter and setter
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPreNom() {
        return preNom;
    }

    public void setPreNom(String preNom) {
        this.preNom = preNom;
    }

    public String getDernierEcole() {
        return dernierEcole;
    }

    public void setDernierEcole(String dernierEcole) {
        this.dernierEcole = dernierEcole;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Utilisateur.sexe getSexe() {
        return sexe;
    }

    public void setSexe(Utilisateur.sexe sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    //Equals, getHasCode and toString override


    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", preNom='" + preNom + '\'' +
                ", dernierEcole='" + dernierEcole + '\'' +
                ", photo='" + photo + '\'' +
                ", sexe=" + sexe +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
