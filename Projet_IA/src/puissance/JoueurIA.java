package puissance;

import java.util.Random;

public class JoueurIA extends Joueur {

    public JoueurIA(String nom) {
        super(nom);
    }

    @Override
    public int jouer() {
        //Choix aléatoire entre 1 et 7 pour l'instant
        int coup = new Random().nextInt(7) + 1;
        System.out.println(getNom() + " (IA) joue en colonne : " + coup);
        return coup;
    }
}

