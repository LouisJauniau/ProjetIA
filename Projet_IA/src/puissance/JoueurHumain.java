package puissance;

import java.util.Scanner;

public class JoueurHumain extends Joueur {

    public JoueurHumain(String nom) {
        super(nom);
    }

    @Override
    public int jouer() {
        Scanner scan = new Scanner(System.in);
        int choix = -1;

        while (true) {
            System.out.print(getNom() + ", entrez un nombre entre 1 et 7 : ");
            String input = scan.nextLine().trim();

            try {
                choix = Integer.parseInt(input);
                if (choix >= 1 && choix <= 7) {
                    break;
                } else {
                    System.out.println("Nombre hors limites. Veuillez entrer un nombre entre 1 et 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        return choix;
    }
}
