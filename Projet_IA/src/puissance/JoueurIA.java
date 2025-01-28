import java.util.Random;

public class JoueurIA extends Joueur {

    public JoueurIA(String nom) {
        super(nom);
    }

    public int jouer()
    {
        return new Random().nextInt(7) + 1; // Génère un nombre entre 1 et 7
    }
}
