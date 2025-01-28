public class SimulateurJvIA {

    Plateau plateau;
    JoueurHumain joueur;
    JoueurIA ia;
    public SimulateurJvIA(Plateau plateau, JoueurHumain joueur, JoueurIA ia ) {
        this.plateau = plateau;
        this.joueur = joueur;
        this.ia = ia;
    }

    public static void main(String[] args) {

        while(!plateau.Victoire() )
    }
}
