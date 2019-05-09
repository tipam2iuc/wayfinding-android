package e.plass.acceuilwayfinding.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DomaineList {
    @SerializedName("reponse")
    private ArrayList<t_domaine> domaines;

    public DomaineList(ArrayList<t_domaine> domaines) {
        this.domaines = domaines;
    }

    public ArrayList<t_domaine> getDomaines() {
        return domaines;
    }

    public void setDomaines(ArrayList<t_domaine> domaines) {
        this.domaines = domaines;
    }
}
