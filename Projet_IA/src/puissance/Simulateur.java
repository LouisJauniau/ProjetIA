import java.util.Scanner;

public class Simulateur {

    public static void main(String[] args)
    {
        Plateau plateau = new Plateau();
        System.out.println("Voulez vous jouer contre l'IA ? (O/N)\n");
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine().trim().toUpperCase(); // Lecture et normalisation de l'entrée

        boolean isIAplays = input.equals("O"); // Si "O", alors true, sinon false

        System.out.println("Mode de jeu : " + (isIAplays ? "Contre IA" : "Joueur vs Joueur"));

        if (isIAplays)
        {
            JoueurHumain joueur= new JoueurHumain("joueur");
            JoueurIA ia = new JoueurIA("IA");
            int tour = 0;
            while(!plateau.Victoire() || !plateau.Egalite())
            {
                if (tour%2 == 0)
                {
                    joueur.jouer();
                }
                else
                {
                    ia.jouer();
                }
                //Faire jouer joueur contre ia avec methode jouer()
                tour++;
            }
            //Appeler une instance de Simulateur J vs IA
        }
        else{
            JoueurHumain joueurA= new JoueurHumain("joueurA");
            JoueurHumain joueurB= new JoueurHumain("joueurB");

            //Appeler une instance de Simulateur J vs IA
        }

    }

}