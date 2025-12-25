package com.test.technique.score.cucumber;

import com.test.technique.score.TennisGame;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Cucumber tests.
 */
public class TennisScoreStepDefinitions {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private String capturedOutput;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Given("a new tennis game")
    public void aNewTennisGame() {
        outputStream.reset();
    }

    @When("the ball sequence {string} is played")
    public void theBallSequenceIsPlayed(String ballSequence) {
        TennisGame.play(ballSequence);
        capturedOutput = outputStream.toString();
        System.setOut(originalOut);
    }

    @Then("the score history should contain {string}")
    public void theScoreHistoryShouldContain(String expectedScore) {
        assertTrue(capturedOutput.contains(expectedScore),
                "Expected output to contain: '" + expectedScore + "'\nActual output:\n" + capturedOutput);
    }

    @And("the winner should be {string}")
    public void theWinnerShouldBe(String winner) {
        String expectedMessage = "Joueur Gagnant : " + winner;
        assertTrue(capturedOutput.contains(expectedMessage),
                "Expected '" + expectedMessage + "' in output.\nActual output:\n" + capturedOutput);
    }

    @And("the last score should be {string}")
    public void theLastScoreShouldBe(String expectedLastScore) {
        String[] lines = capturedOutput.trim().split("\n");
        String lastLine = lines[lines.length - 1].trim();
        assertEquals(expectedLastScore, lastLine,
                "Expected last score to be: '" + expectedLastScore + "'\nActual last line: '" + lastLine + "'");
    }
}

