package fr.gerdevstudio.zoomobile.models;

/**
 * Created by ger on 06/04/2016.
 */
public class Enclos {
    private int id;

    private String name;

    private String photo;

    public Enclos() {

    }

    public Enclos(String nom) {
        this.name = nom;
    }

    public Enclos(String nom, String photo) {
        this.name = nom;
        this.photo = photo;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
}
