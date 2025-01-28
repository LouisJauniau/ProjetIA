
import java.util.*;


/*
Classe qui represente tout le plateau de jeu
 */
public class Plateau
{
    private HashMap<Integer, Pile> colonnes;

    public Plateau()
    {
        colonnes = new HashMap<>();
        for (int i=0; i<7; i++)
        {
            colonnes.put(i+1, new Pile());
        }
    }

    public boolean Egalite()
    {
        boolean placeLibre = false;
        for (Integer i : colonnes.keySet())
        {
            if (!colonnes.get(i).isFull())
                return false;
        }
        return true;
    }

    public void add(Integer i, Jeton j)
    {
        colonnes.get(i).ajouter(j);
    }


    //Doit retourner la couleur gagnante mais je sais pas comment faire
    public boolean Victoire()
    {
        if (VerifColonnes() || VerifLignes() || VerifDiagonales())
        {
            return true;
        }
        return false;
    }

    public boolean VerifColonnes() {
        for (Pile p : colonnes.values()) {
            Jeton.Couleur last = null;
            int cpt = 0;
            for (Jeton j : p.getColonne()) {
                cpt = (j.getCouleur() == last) ? cpt + 1 : 1;
                last = j.getCouleur();
                if (cpt == 4) return true;
            }
        }
        return false;
    }

    public boolean VerifLignes() {
        for (int row = 0; row < 6; row++) {
            Jeton.Couleur last = null;
            int cpt = 0;
            for (int col = 1; col <= 7; col++) {
                ArrayList<Jeton> pile = colonnes.get(col).getColonne();
                Jeton.Couleur cur = (pile.size() > row) ? pile.get(row).getCouleur() : null;
                cpt = (cur != null && cur == last) ? cpt + 1 : (cur != null ? 1 : 0);
                last = cur;
                if (cpt == 4) return true;
            }
        }
        return false;
    }



    public boolean VerifDiagonales() {
        for(int r = 0; r < 6; r++) {
            for(int c = 1; c <= 7; c++) {
                Jeton.Couleur couleur = getColor(r, c);
                if (couleur != null) {
                    // Diagonale "montante" (bas-gauche -> haut-droite)
                    if (couleur == getColor(r + 1, c + 1)
                            && couleur == getColor(r + 2, c + 2)
                            && couleur == getColor(r + 3, c + 3)) return true;

                    // Diagonale "descendante" (haut-gauche -> bas-droite)
                    if (couleur == getColor(r + 1, c - 1)
                            && couleur == getColor(r + 2, c - 2)
                            && couleur == getColor(r + 3, c - 3)) return true;
                }
            }
        }
        return false;
    }

    private Jeton.Couleur getColor(int row, int col) {
        if (row < 0 || row > 5 || col < 1 || col > 7) return null;
        ArrayList<Jeton> pile = colonnes.get(col).getColonne();
        return (pile.size() > row) ? pile.get(row).getCouleur() : null;
    }

}
