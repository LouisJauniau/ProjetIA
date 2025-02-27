package puissance;

public class SimulateurJvJ {

    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        JoueurHumain joueurA = new JoueurHumain("Joueur A");
        JoueurHumain joueurB = new JoueurHumain("Joueur B");

        System.out.println("=== DÉBUT DE LA PARTIE (Joueur vs Joueur) ===");
        plateau.afficherPlateau();

        int tour = 0;
        while (!plateau.Victoire() && !plateau.Egalite()) {
            JoueurHumain joueurCourant = (tour % 2 == 0) ? joueurA : joueurB;
            Jeton.Couleur couleurCourante = (tour % 2 == 0)
                    ? Jeton.Couleur.ROUGE
                    : Jeton.Couleur.JAUNE;

            int colonne = joueurCourant.jouer();
            try {
                plateau.add(colonne, new Jeton(couleurCourante));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Colonne pleine ! Choisissez une autre colonne.");
                continue;
            }

            plateau.afficherPlateau();
            tour++;
        }

        if (plateau.Victoire()) {
            JoueurHumain gagnant = ((tour - 1) % 2 == 0) ? joueurA : joueurB;
            System.out.println("Bravo, " + gagnant.getNom() + " a gagné !");
        } else {
            System.out.println("Match nul ! Le plateau est plein.");
        }
    }
}
