package puissance;


import java.util.ArrayList;

//Une "Colonne" de la grille de jeu
public class Pile {

    private ArrayList<Jeton> colonne;

    public Pile() {
        colonne = new ArrayList<>();
    }

    public void ajouter(Jeton j) {
        //On vérifie que la taille est < 6
        if (colonne.size() < 6) {
            colonne.add(j);
        } else {
            throw new IndexOutOfBoundsException("La colonne est pleine");
        }
    }

    //Colonne pleine
    public boolean isFull() {
        return colonne.size() == 6;
    }

    public ArrayList<Jeton> getColonne() {
        return colonne;
    }
}

