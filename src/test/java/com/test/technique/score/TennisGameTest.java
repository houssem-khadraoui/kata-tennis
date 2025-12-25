package com.test.technique.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TennisGame scoring logic.
 */
class TennisGameTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        // Capture System.out for verification
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Example from requirements: ABABAA should result in Player A winning")
    void testExampleFromRequirements() {
        TennisGame.play("ABABAA");

        String output = outputStream.toString();

        assertTrue(output.contains("Joueur A 15 / Joueur B 0"));
        assertTrue(output.contains("Joueur A 15 / Joueur B 15"));
        assertTrue(output.contains("Joueur A 30 / Joueur B 15"));
        assertTrue(output.contains("Joueur A 30 / Joueur B 30"));
        assertTrue(output.contains("Joueur A 40 / Joueur B 30"));
        assertTrue(output.contains("Joueur Gagnant : A"));
    }

    @Test
    @DisplayName("Player A wins straight with AAAA")
    void testPlayerAWinsStraight() {
        TennisGame.play("AAAA");

        String output = outputStream.toString();

        assertTrue(output.contains("Joueur A 15 / Joueur B 0"));
        assertTrue(output.contains("Joueur A 30 / Joueur B 0"));
        assertTrue(output.contains("Joueur A 40 / Joueur B 0"));
        assertTrue(output.contains("Joueur Gagnant : A"));
    }

    @Test
    @DisplayName("Player B wins straight with BBBB")
    void testPlayerBWinsStraight() {
        TennisGame.play("BBBB");

        String output = outputStream.toString();

        assertTrue(output.contains("Joueur A 0 / Joueur B 15"));
        assertTrue(output.contains("Joueur A 0 / Joueur B 30"));
        assertTrue(output.contains("Joueur A 0 / Joueur B 40"));
        assertTrue(output.contains("Joueur Gagnant : B"));
    }

    @Test
    @DisplayName("Deuce situation when both players reach 40")
    void testDeuceSituation() {
        TennisGame.play("AAABBB");

        String output = outputStream.toString();

        assertTrue(output.contains("Deuce"));
    }

    @Test
    @DisplayName("Advantage Player A after deuce")
    void testAdvantagePlayerA() {
        TennisGame.play("AAABBBA");

        String output = outputStream.toString();

        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Avantage Joueur A"));
    }

    @Test
    @DisplayName("Advantage Player B after deuce")
    void testAdvantagePlayerB() {
        TennisGame.play("AAABBBB");

        String output = outputStream.toString();

        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Avantage Joueur B"));
    }

    @Test
    @DisplayName("Back to deuce after advantage")
    void testBackToDeuce() {
        TennisGame.play("AAABBBAB");

        String output = outputStream.toString();

        // First deuce
        assertTrue(output.contains("Deuce"));
        // Then advantage A
        assertTrue(output.contains("Avantage Joueur A"));
        // Score should contain at least two "Deuce" occurrences
        int deuceCount = countOccurrences(output, "Deuce");
        assertTrue(deuceCount >= 2, "Should have at least 2 deuce occurrences");
    }

    @Test
    @DisplayName("Player A wins from advantage")
    void testPlayerAWinsFromAdvantage() {
        TennisGame.play("AAABBBAA");

        String output = outputStream.toString();

        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Avantage Joueur A"));
        assertTrue(output.contains("Joueur Gagnant : A"));
    }

    @Test
    @DisplayName("Player B wins from advantage")
    void testPlayerBWinsFromAdvantage() {
        TennisGame.play("AAABBBBB");

        String output = outputStream.toString();

        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Avantage Joueur B"));
        assertTrue(output.contains("Joueur Gagnant : B"));
    }

    @Test
    @DisplayName("Extended deuce scenario with multiple advantages")
    void testExtendedDeuceScenario() {
        // AAABBB = Deuce, A = Av A, B = Deuce, B = Av B, A = Deuce, A = Av A, A = A gagne
        TennisGame.play("AAABBBABAAA");

        String output = outputStream.toString();

        assertTrue(output.contains("Joueur Gagnant : A"));
    }

    @Test
    @DisplayName("Empty input should show message")
    void testEmptyInput() {
        TennisGame.play("");

        String output = outputStream.toString();

        assertTrue(output.contains("Pas de séquence de balles fournie."));
    }

    @Test
    @DisplayName("Null input should show message")
    void testNullInput() {
        TennisGame.play(null);

        String output = outputStream.toString();

        assertTrue(output.contains("Pas de séquence de balles fournie."));
    }

    /**
     * Helper method to count occurrences of a substring in a string.
     */
    private int countOccurrences(String str, String sub) {
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }
}

