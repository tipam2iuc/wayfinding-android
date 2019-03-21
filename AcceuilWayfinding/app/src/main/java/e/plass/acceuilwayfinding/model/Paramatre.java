package e.plass.acceuilwayfinding.model;

public class Paramatre {
    private int id;
    private int title;
    private int ico;
    private boolean isNext;

    public Paramatre(int id, int title, int ico, boolean isNext) {
        this.id = id;
        this.title = title;
        this.ico = ico;
        this.isNext = isNext;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }
}
