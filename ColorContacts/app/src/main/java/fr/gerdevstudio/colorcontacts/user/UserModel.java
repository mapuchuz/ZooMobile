package fr.gerdevstudio.colorcontacts.user;

/**
 * Created by ger on 04/04/2016.
 */
public class UserModel {

    private String nom;
    private String prenom;
    private boolean isActive;

    public UserModel(String nom,String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        isActive = false;
    }

    public UserModel(String nom,String prenom,Boolean isActive) {
        this.nom = nom;
        this.prenom = prenom;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return getNom()+" "+getPrenom();
    }
}
