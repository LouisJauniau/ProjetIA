package puissance;


import java.util.ArrayList;
import java.util.HashMap;


public class Plateau {

    private HashMap<Integer, Pile> colonnes;

    public Plateau() {
        colonnes = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            colonnes.put(i + 1, new Pile());
        }
    }

    //Egalité si toutes les colonnes sont pleines
    public boolean Egalite() {
        for (Integer i : colonnes.keySet()) {
            if (!colonnes.get(i).isFull()) {
                return false;
            }
        }
        return true;
    }

    //Ajoute un jeton à la colonne
    public void add(Integer i, Jeton j) {
        colonnes.get(i).ajouter(j);
    }

    //Verifie qu'il n'y a pas 4 jetons alignés
    public boolean Victoire() {
        return (VerifColonnes() || VerifLignes() || VerifDiagonales());
    }


    public void afficherPlateau() {
        System.out.println("\nÉtat actuel du plateau (colonne 1 à 7) :\n");
        
        for (int row = 5; row >= 0; row--) {
            System.out.print("L" + (row + 1) + " | ");
            for (int col = 1; col <= 7; col++) {
                ArrayList<Jeton> pile = colonnes.get(col).getColonne();
                if (pile.size() > row) {
                    Jeton.Couleur c = pile.get(row).getCouleur();
                    if (c == Jeton.Couleur.ROUGE) {
                        System.out.print("R ");
                    } else {
                        System.out.print("J ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("    ---------------------");
        System.out.println("     1  2  3  4  5  6  7\n");
    }

    //Vérifie la présence de 4 pions consécutifs dans chaque colonne
    public boolean VerifColonnes() {
        for (Pile p : colonnes.values()) {
            Jeton.Couleur last = null;
            int cpt = 0;
            for (Jeton j : p.getColonne()) {
                if (j.getCouleur() == last) {
                    cpt++;
                } else {
                    cpt = 1;
                    last = j.getCouleur();
                }
                if (cpt == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    //Vérifie la présence de 4 pions consécutifs sur une même ligne
    public boolean VerifLignes() {
        for (int row = 0; row < 6; row++) {
            Jeton.Couleur last = null;
            int cpt = 0;
            for (int col = 1; col <= 7; col++) {
                ArrayList<Jeton> pile = colonnes.get(col).getColonne();
                Jeton.Couleur cur = (pile.size() > row) ? pile.get(row).getCouleur() : null;
                if (cur != null && cur == last) {
                    cpt++;
                } else if (cur != null) {
                    cpt = 1;
                    last = cur;
                } else {
                    cpt = 0;
                    last = null;
                }
                if (cpt == 4) {
                    return true;
                }
            }
        }
        return false;
    }


    //Vérifie la présence de 4 pions consécutifs sur une même diagonale.
    public boolean VerifDiagonales() {
        for (int r = 0; r < 6; r++) {
            for (int c = 1; c <= 7; c++) {
                Jeton.Couleur couleur = getColor(r, c);
                if (couleur != null) {
                    if (couleur == getColor(r + 1, c + 1)
                            && couleur == getColor(r + 2, c + 2)
                            && couleur == getColor(r + 3, c + 3)) {
                        return true;
                    }
                    if (couleur == getColor(r + 1, c - 1)
                            && couleur == getColor(r + 2, c - 2)
                            && couleur == getColor(r + 3, c - 3)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Jeton.Couleur getColor(int row, int col) {
        if (row < 0 || row > 5 || col < 1 || col > 7) {
            return null;
        }
        ArrayList<Jeton> pile = colonnes.get(col).getColonne();
        return (pile.size() > row) ? pile.get(row).getCouleur() : null;
    }
    
    public HashMap<Integer, Pile> getColonnes() {
        return colonnes;
    }

}

