package e.plass.acceuilwayfinding.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Formation {
    private int id;
    private String    name;
    private ArrayList<String> debouches;
    private String Descripetion;
    private float notes;
    private String image;

    public Formation(int id, String name, ArrayList<String> debouches, String descripetion, float notes, String image) {
        this.id = id;
        this.name = name;

        this.debouches = debouches;
        Descripetion = descripetion;
        this.notes = notes;
        this.image = image;
    }

    public Formation() {
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

    public void setDebouches(ArrayList<String> debouches) {
        this.debouches = debouches;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", debouches=" + debouches +
                '}';
    }
    public static Comparator<Formation> comparableByName = new Comparator<Formation>() {
        @Override
        public int compare(Formation o1, Formation o2) {

            return o1.name.trim().compareTo(o2.name.trim());
        }
    };
    public static Comparator<Formation> comparableByNote = new Comparator<Formation>() {
        @Override
        public int compare(Formation o1, Formation o2) {

            return (int) (o1.notes - o2.notes);
        }
    };
}
