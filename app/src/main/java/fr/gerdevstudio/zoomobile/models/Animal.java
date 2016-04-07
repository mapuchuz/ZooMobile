package fr.gerdevstudio.zoomobile.models;

/**
 * Created by ger on 06/04/2016.
 */

public class Animal {


    private int id;

    private String name;
    private String espece;
    private String description;
    private String photo;

    public String getNom() {
        return name;
    }

    public Animal() {

    }

    public Animal(String name, String espece) {
        this.name = name;
        this.espece = espece;
    }

    public Animal(String name, String espece, String photo) {
        this.name = name;
        this.espece = espece;
        this.photo = photo;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEspece() {
        return espece;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
