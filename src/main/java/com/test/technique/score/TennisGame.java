package com.test.technique.score;

/**
 * Tennis Game Score Computer
 * Implements the scoring rules for a single tennis game.
 */
public class TennisGame {

    private int scoreA = 0;
    private int scoreB = 0;
    private boolean gameOver = false;
    private String winner = null;

    // Liste des points en tennis
    private static final String[] POINTS = {"0", "15", "30", "40"};

    /**
     * Méthode principale pour calculer et afficher le score de tennis à partir d'une séquence de gagnants de balles.
     * @param ballSequence : est une chaîne de caractères contenant 'A' ou 'B' représentant quel joueur a gagné chaque balle.
     */
    public void computeScore(String ballSequence) {
        if (ballSequence == null || ballSequence.isEmpty()) {
            System.out.println("Pas de séquence de balles fournie.");
            return;
        }

        for (char ball : ballSequence.toCharArray()) {
            if (gameOver) {
                break;
            }

            if (ball == 'A') {
                playerScores('A');
            } else if (ball == 'B') {
                playerScores('B');
            } else {
                System.out.println("Charactère invalide : " + ball + ". Seuls 'A' ou 'B' sont autorisés.");
                continue;
            }

            printScore();
        }
    }

    /**
     *
     * Gére le score lorsqu'un joueur gagne une balle.
     * @param player Joueur qui a gagné la balle ('A' ou 'B').
     */
    private void playerScores(char player) {
        if (player == 'A') {
            handleScore('A');
        } else {
            handleScore('B');
        }
    }

    /**
     *
     * Met à jour le score en fonction du joueur qui a gagné la balle.
     * @param player Joueur qui a gagné la balle ('A' ou 'B').
     */
    private void handleScore(char player) {
        // Vérifier la situation de deuce (les deux à 40 ou plus, représenté par score >= 3)
        if (scoreA >= 3 && scoreB >= 3) {
            if (player == 'A') {
                scoreA++;
            } else {
                scoreB++;
            }

            // Vérifier si un joueur gagne depuis l'avantage
            if (scoreA >= 4 && scoreA - scoreB >= 2) {
                gameOver = true;
                winner = "A";
            } else if (scoreB >= 4 && scoreB - scoreA >= 2) {
                gameOver = true;
                winner = "B";
            }
            // Si un joueur perd l'avantage, revenir à deuce 40 - 40
            else if (scoreA == scoreB && scoreA >= 3) {
                scoreA = 3;
                scoreB = 3;
            }
        } else {
            // Score normal
            if (player == 'A') {
                scoreA++;
            } else {
                scoreB++;
            }

            // Vérifier la victoire (score atteint 4 sans deuce)
            if (scoreA >= 4) {
                gameOver = true;
                winner = "A";
            } else if (scoreB >= 4) {
                gameOver = true;
                winner = "B";
            }
        }
    }

    /**
     * Affiche le score actuel ou le gagnant si le jeu est terminé.
     */
    private void printScore() {
        if (gameOver) {
            System.out.println("Joueur Gagnant : " + winner);
        } else if (scoreA >= 3 && scoreB >= 3) {
            if (scoreA == scoreB) {
                System.out.println("Joueur A 40 / Joueur B 40");
                System.out.println("Deuce");
            } else if (scoreA > scoreB) {
                System.out.println("Joueur A AV / Joueur B 40");
                System.out.println("Avantage Joueur A");
            } else {
                System.out.println("Joueur A 40 / Joueur B AV");
                System.out.println("Avantage Joueur B");
            }
        } else {
            String pointsA = POINTS[scoreA];
            String pointsB = POINTS[scoreB];
            System.out.println("Joueur A " + pointsA + " / Joueur B " + pointsB);
        }
    }

    /**
     * Initialise ou réinitialise le jeu.
     */
    public void reset() {
        scoreA = 0;
        scoreB = 0;
        gameOver = false;
        winner = null;
    }

    /**
     * Méthode statique pour une utilisation facile - crée un nouveau jeu et calcule le score.
     * @param ballSequence La séquence des balles gagnées par les joueurs.
     */
    public static void play(String ballSequence) {
        TennisGame game = new TennisGame();
        game.computeScore(ballSequence);
    }
}

