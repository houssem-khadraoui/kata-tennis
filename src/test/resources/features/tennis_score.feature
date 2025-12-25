Feature: Tennis Score Computer
  As a tennis match observer
  I want to track the score of a tennis game
  So that I can know the current score and the winner

  Background:
    Given a new tennis game

  Scenario: Player A wins the game with score sequence ABABAA
    When the ball sequence "ABABAA" is played
    Then the score history should contain "Joueur A 15 / Joueur B 0"
    And the score history should contain "Joueur A 15 / Joueur B 15"
    And the score history should contain "Joueur A 30 / Joueur B 15"
    And the score history should contain "Joueur A 30 / Joueur B 30"
    And the score history should contain "Joueur A 40 / Joueur B 30"
    And the winner should be "A"

  Scenario: Player A wins straight
    When the ball sequence "AAAA" is played
    Then the score history should contain "Joueur A 15 / Joueur B 0"
    And the score history should contain "Joueur A 30 / Joueur B 0"
    And the score history should contain "Joueur A 40 / Joueur B 0"
    And the winner should be "A"

  Scenario: Player B wins straight
    When the ball sequence "BBBB" is played
    Then the winner should be "B"

  Scenario: Deuce situation
    When the ball sequence "AAABBB" is played
    Then the score history should contain "Deuce"

  Scenario: Advantage after deuce
    When the ball sequence "AAABBBA" is played
    Then the score history should contain "Deuce"
    And the score history should contain "Avantage Joueur A"

  Scenario: Player wins from advantage
    When the ball sequence "AAABBBAA" is played
    Then the score history should contain "Deuce"
    And the score history should contain "Avantage Joueur A"
    And the winner should be "A"

  Scenario: Back to deuce after advantage
    When the ball sequence "AAABBBAB" is played
    Then the score history should contain "Deuce"
    And the score history should contain "Avantage Joueur A"
    And the last score should be "Deuce"

