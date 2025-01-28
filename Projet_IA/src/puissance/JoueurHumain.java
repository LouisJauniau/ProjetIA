import java.util.Scanner;

public class JoueurHumain extends Joueur{

    public JoueurHumain(String nom){
        super(nom);

    }

    @Override
    public int jouer() {
        Scanner scan = new Scanner(System.in);
        int choix = -1; // Valeur par défaut invalide

        while (true) {
            System.out.println("Entrez un nombre entre 1 et 7 : ");
            String input = scan.nextLine().trim(); // Lecture et suppression des espaces inutiles

            try {
                choix = Integer.parseInt(input); // Conversion String → int
                if (choix >= 1 && choix <= 7) {
                    break; // Sortie de la boucle si le nombre est valide
                } else {
                    System.out.println("Nombre hors limites. Veuillez entrer un nombre entre 1 et 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        return choix; // Retourne le choix valide
    }
}
