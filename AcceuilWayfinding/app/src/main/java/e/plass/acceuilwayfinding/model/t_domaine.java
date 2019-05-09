package e.plass.acceuilwayfinding.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class t_domaine implements Comparable<t_domaine>{
    @SerializedName("idT_domaine")
    private int id;
    @SerializedName("nom")
    private String    name;
    @SerializedName("description")
    private String Descripetion;


    private ArrayList<String> debouches;
    private float notes;
    private String image;

    public t_domaine(int id, String name, ArrayList<String> debouches, String descripetion, float notes, String image) {
        this.id = id;
        this.name = name;

        this.debouches = debouches;
        Descripetion = descripetion;
        this.notes = notes;
        this.image = image;
    }

    public t_domaine() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getNotes() {
        return notes;
    }

    public void setNotes(float notes) {
        this.notes = notes;
    }

    public String getDescripetion() {
        return Descripetion;
    }

    public void setDescripetion(String descripetion) {
        Descripetion = descripetion;
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

    public ArrayList<String> getDebouches() {
        return debouches;
    }

    @Override
    public String toString() {
        return "t_domaine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", debouches=" + debouches +
                '}';
    }

    @Override
    public int compareTo(t_domaine o) {
        return this.getName().toLowerCase().trim().compareToIgnoreCase(o.getName().trim());
    }
    /*public static Comparator<t_domaine> comparableByName = new Comparator<t_domaine>() {
        @Override
        public int compare(t_domaine o1, t_domaine o2) {

            return o1.name.trim().toLowerCase().compareTo(o2.name.trim());
        }
    };
    public static Comparator<t_domaine> comparableByNote = new Comparator<t_domaine>() {
        @Override
        public int compare(t_domaine o1, t_domaine o2) {

            return (int) (o1.notes - o2.notes);
        }
    };*/
}
