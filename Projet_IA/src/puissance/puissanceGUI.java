package puissance;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class puissanceGUI extends Application {

    private static final int COLS = 7;  //nombre de colonnes
    private static final int ROWS = 6;  //nombre de lignes

    //Taille en pixels pour chaque cellule
    private static final int CELL_SIZE = 80;

    private Plateau plateau;
    private Joueur joueurA;
    private Joueur joueurB;
    private boolean isIA;

    private int tour = 0;

    //Tableau de cercles
    private Circle[][] circles = new Circle[ROWS][COLS];

    @Override
    public void start(Stage primaryStage) {
        //Demande par boite de dialogue si on veut jouer vs IA ou vs Joueur
        isIA = askGameMode();

        //Instancie le Plateau et les joueurs
        plateau = new Plateau();
        joueurA = new JoueurHumain("Joueur A");
        if (isIA) {
            joueurB = new JoueurIA("IA");
        } else {
            joueurB = new JoueurHumain("Joueur B");
        }

        //Construie de la scène principale
        BorderPane root = new BorderPane();

        //Barre de boutons / 7 boutons pour 7 colonnes
        ToolBar toolbar = new ToolBar();
        for (int col = 1; col <= COLS; col++) {
            Button btn = new Button("Col " + col);
            final int colIndex = col;
            btn.setOnAction(e -> handleHumanMove(colIndex));
            toolbar.getItems().add(btn);
        }
        root.setTop(toolbar);

        //Grille centrale pour les cercles
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        //Initialise tous les cercles en blanc
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Circle circle = new Circle();
                circle.setRadius(CELL_SIZE / 2.0 - 5);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);

                grid.add(circle, col, row);
                circles[row][col] = circle;
            }
        }

        root.setCenter(grid);

        //Création de la scène et affichage
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Puissance 4 - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Boite de dialogue
    private boolean askGameMode() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mode de jeu");
        alert.setHeaderText("Voulez-vous jouer contre l'IA ?");
        alert.setContentText("Choisissez votre mode de jeu.");

        ButtonType buttonJvJ = new ButtonType("Joueur vs Joueur");
        ButtonType buttonJvIA = new ButtonType("Joueur vs IA");

        alert.getButtonTypes().setAll(buttonJvJ, buttonJvIA);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonJvIA) {
            return true;  //Jouer contre IA
        } else {
            return false; //Joueur vs Joueur
        }
    }

    //Gère le choix du joueur humain
    private void handleHumanMove(int colIndex) {

        if ( (tour % 2 == 0 && joueurA instanceof JoueurHumain) ||
             (tour % 2 == 1 && joueurB instanceof JoueurHumain) ) {

            Jeton.Couleur couleur = (tour % 2 == 0) ? Jeton.Couleur.ROUGE : Jeton.Couleur.JAUNE;

            try {
                plateau.add(colIndex, new Jeton(couleur));
            } catch (IndexOutOfBoundsException e) {
                showAlert("Colonne pleine", "La colonne " + colIndex + " est déjà pleine");
                return;
            }

            //Met à jour l'affichage
            refreshBoard();

            //Vérifie victoire ou égalité
            if (checkEndOfGame()) {
                return;
            }

            tour++;

            if (isIA && (tour % 2 == 1)) {
                handleIAMove();
            }
        }
    }

  //Gère le choix de l'IA
    private void handleIAMove() {
        if (!(joueurB instanceof JoueurIA)) {
            return;
        }

        JoueurIA ia = (JoueurIA) joueurB;
        Jeton.Couleur couleur = Jeton.Couleur.JAUNE;

        //L'IA choisit une colonne
        int colIndex = ia.jouer();

        try {
            plateau.add(colIndex, new Jeton(couleur));
        } catch (IndexOutOfBoundsException e) {
            boolean placed = false;
            for (int tryCount = 0; tryCount < 10; tryCount++) {
                colIndex = 1 + new Random().nextInt(7);
                try {
                    plateau.add(colIndex, new Jeton(couleur));
                    placed = true;
                    break;
                } catch (IndexOutOfBoundsException ex) {
                    // on retente
                }
            }
            if (!placed) {
                showAlert("Erreur", "Aucune colonne n'est disponible pour l'IA !");
                return;
            }
        }

        refreshBoard();

        if (checkEndOfGame()) {
            return;
        }

        tour++;
    }

    private boolean checkEndOfGame() {
        if (plateau.Victoire()) {
            Joueur gagnant = (tour % 2 == 0) ? joueurA : joueurB;
            showAlert("Victoire", "Bravo, " + gagnant.getNom() + " a gagné !");
            resetGame();
            return true;
        }
        if (plateau.Egalite()) {
            showAlert("Égalité", "Le plateau est plein. Match nul !");
            resetGame();
            return true;
        }
        return false;
    }

    private void refreshBoard() {

        for (int col = 1; col <= COLS; col++) {
            int nbJetons = plateau.getColonnes().get(col).getColonne().size();
            for (int rowInPile = 0; rowInPile < ROWS; rowInPile++) {
                int rowDisplay = ROWS - 1 - rowInPile; // inversion
                Circle circle = circles[rowDisplay][col - 1];

                if (rowInPile < nbJetons) {
                    Jeton j = plateau.getColonnes().get(col).getColonne().get(rowInPile);
                    if (j.getCouleur() == Jeton.Couleur.ROUGE) {
                        circle.setFill(Color.RED);
                    } else {
                        circle.setFill(Color.YELLOW);
                    }
                } else {
                    circle.setFill(Color.WHITE);
                }
            }
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetGame() {
        plateau = new Plateau();
        tour = 0;
        refreshBoard();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
