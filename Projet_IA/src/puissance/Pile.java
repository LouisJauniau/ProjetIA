
import java.util.*;

/*
Classe qui represente les colonnes de la grille de puissance 4
 */
public class Pile {

    private ArrayList<Jeton> colonne;

    public Pile()
    {
        colonne = new ArrayList<>();
    }

    public void ajouter(Jeton j)
    {
        if (colonne != null || colonne.size()<6)
        {
            colonne.add(j);
        }
        else
        {
            throw new IndexOutOfBoundsException("la colonne est pleine");
        }
    }

    public boolean isFull()
    {
        return colonne.size() == 6;
    }

    public ArrayList<Jeton> getColonne()
    {
        return colonne;
    }
}
