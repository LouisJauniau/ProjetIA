package puissance;

public class SimulateurJvIA {

    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        JoueurHumain joueur = new JoueurHumain("Joueur");
        JoueurIA ia = new JoueurIA("IA");

        System.out.println("=== DÉBUT DE LA PARTIE (Joueur vs IA) ===");
        plateau.afficherPlateau();

        int tour = 0;
        while (!plateau.Victoire() && !plateau.Egalite()) {
            Joueur joueurCourant = (tour % 2 == 0) ? joueur : ia;
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
            Joueur gagnant = ((tour - 1) % 2 == 0) ? joueur : ia;
            System.out.println("Bravo, " + gagnant.getNom() + " a gagné !");
        } else {
            System.out.println("Match nul ! Le plateau est plein.");
        }
    }
}
