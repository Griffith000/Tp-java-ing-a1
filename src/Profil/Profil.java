package Profil;


public class Profil {
    String nom, prenom, pseudo;

    Profil(String nom, String prenom, String pseudo){
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }
    Profil() {
        this.nom = "";
        this.prenom = "";
        this.pseudo = "";
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }


}
