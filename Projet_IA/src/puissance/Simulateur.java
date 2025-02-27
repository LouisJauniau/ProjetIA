package puissance;

import java.util.Scanner;

public class Simulateur {

    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        Scanner scan = new Scanner(System.in);

        System.out.println("Voulez-vous jouer contre l'IA ? (O/N)");
        String input = scan.nextLine().trim().toUpperCase();
        boolean isIAplays = input.equals("O");

        // On crée les joueurs
        Joueur joueurA;
        Joueur joueurB;

        if (isIAplays) {
            joueurA = new JoueurHumain("Joueur");
            joueurB = new JoueurIA("IA");
        } else {
            joueurA = new JoueurHumain("Joueur A");
            joueurB = new JoueurHumain("Joueur B");
        }

        // Début de la partie
        System.out.println("=== DÉBUT DE LA PARTIE ===");
        plateau.afficherPlateau();

        int tour = 0;  // permettra d'alterner ROUGE / JAUNE
        while (!plateau.Victoire() && !plateau.Egalite()) {
            // Sélectionne le joueur courant
            Joueur joueurCourant = (tour % 2 == 0) ? joueurA : joueurB;

            // Détermine la couleur à jouer
            Jeton.Couleur couleurCourante = (tour % 2 == 0)
                    ? Jeton.Couleur.ROUGE
                    : Jeton.Couleur.JAUNE;

            // Demande ou calcule la colonne
            int colonneChoisie = joueurCourant.jouer();

            // Tente de poser le jeton
            try {
                plateau.add(colonneChoisie, new Jeton(couleurCourante));
            } catch (IndexOutOfBoundsException e) {
                // Si la colonne est pleine, on redemande immédiatement à ce même joueur
                System.out.println("Colonne pleine ! Choisissez une autre colonne.");
                continue; 
            }

            // Affiche le plateau après le coup
            plateau.afficherPlateau();

            tour++;
        }

        // Fin de partie
        if (plateau.Victoire()) {
            // Le dernier coup joué est fait par le joueur qui vient de jouer
            Joueur gagnant = ((tour - 1) % 2 == 0) ? joueurA : joueurB;
            System.out.println("Bravo, " + gagnant.getNom() + " a gagné !");
        } else {
            System.out.println("Match nul ! Le plateau est plein.");
        }

        System.out.println("=== FIN DE LA PARTIE ===");
    }
}
