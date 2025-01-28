public class Jeton
{

    public enum Couleur
    {
        ROUGE, JAUNE
    }

    private Couleur couleur;

    public Jeton(Couleur couleur)
    {
        this.couleur = couleur;
    }

    public Couleur getCouleur()
    {
        return couleur;
    }
}
