# 🎾 Tennis Score Computer

A simple Java application that computes and displays tennis game scores following official tennis rules.

## 📋 Description

This Kata implements a **tennis score computer** for a single game. The application takes a sequence of ball winners (represented by characters 'A' or 'B') and displays the score progression until a player wins the game.

### Tennis Scoring Rules

The scoring system consists of one game, divided by points:

| Balls Won | Points |
|-----------|--------|
| 0 | 0 |
| 1 | 15 |
| 2 | 30 |
| 3 | 40 |

#### Special Rules:

- **Win**: If a player has 40 points and wins the ball, they win the game
- **Deuce**: If both players have 40 points, the players are at "deuce"
- **Advantage**: If the game is in deuce, the winner of the ball will have advantage
- **Win from Advantage**: If the player with advantage wins the ball, they win the game
- **Back to Deuce**: If the player without advantage wins the ball, they go back to "deuce"

📖 More details about tennis rules: [Wikipedia - Tennis Scoring](http://en.wikipedia.org/wiki/Tennis#Scoring)

## 🛠️ Technical Stack

- **Java**: 21
- **Framework**: Spring Boot 4.0.1
- **Build Tool**: Maven
- **Testing**: 
  - JUnit 5
  - Cucumber 7.20.1 (BDD Testing)

## 📁 Project Structure

```
score/
├── src/
│   ├── main/java/com/test/technique/score/
│   │   ├── ScoreTennisApplication.java    # Main application entry point
│   │   └── TennisGame.java                # Tennis game logic
│   └── test/
│       ├── java/com/test/technique/score/
│       │   ├── TennisGameTest.java        # Unit tests
│       │   └── cucumber/
│       │       ├── CucumberTestRunner.java
│       │       └── TennisScoreStepDefinitions.java
│       └── resources/features/
│           └── tennis_score.feature       # BDD scenarios
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+** (or use the included Maven wrapper)

### Build the Application

Using Maven wrapper (recommended):
```bash
# On Windows
mvnw.cmd clean package

# On Linux/Mac
./mvnw clean package
```

Or using system Maven:
```bash
mvn clean package
```

### Run the Application

#### Option 1: Using Maven Spring Boot Plugin
```bash
# With default sequence (ABABAA)
mvnw.cmd spring-boot:run

# With custom ball sequence
mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="AAABBBAA"
```

#### Option 2: Using the JAR file
```bash
# Build first
mvnw.cmd clean package

# Run with default sequence
java -jar target/score-0.0.1-SNAPSHOT.jar

# Run with custom ball sequence
java -jar target/score-0.0.1-SNAPSHOT.jar AAABBBAA
```

### Input Format

The application accepts a **ball sequence** as argument:
- `A` = Player A wins the ball
- `B` = Player B wins the ball

### Example Usage

```bash
java -jar target/score-0.0.1-SNAPSHOT.jar ABABAA
```

**Output:**
```
*** Tennis Score Computer ***

Sequence des Balles: ABABAA
Joueur A 15 / Joueur B 0
Joueur A 15 / Joueur B 15
Joueur A 30 / Joueur B 15
Joueur A 30 / Joueur B 30
Joueur A 40 / Joueur B 30
Joueur A gagne le jeu
```

### More Examples

| Input | Description | Winner |
|-------|-------------|--------|
| `AAAA` | Player A wins straight | A |
| `BBBB` | Player B wins straight | B |
| `AAABBB` | Ends in deuce | - |
| `AAABBBA` | Advantage Player A | - |
| `AAABBBAA` | Player A wins from advantage | A |
| `AAABBBAB` | Back to deuce | - |

## 🧪 Running Tests

### Run All Tests
```bash
mvnw.cmd test
```

### Run Unit Tests Only
```bash
mvnw.cmd test -Dtest=TennisGameTest
```

### Run Cucumber BDD Tests
```bash
mvnw.cmd test -Dtest=CucumberTestRunner
```

## 📝 BDD Test Scenarios (Cucumber)

The application includes Cucumber tests covering:

1. ✅ Player A wins with sequence ABABAA
2. ✅ Player A wins straight (AAAA)
3. ✅ Player B wins straight (BBBB)
4. ✅ Deuce situation
5. ✅ Advantage after deuce
6. ✅ Player wins from advantage
7. ✅ Back to deuce after advantage

## 📄 License

This project is a technical test/kata exercise.

## 👤 Author
- Name: HOUSSEM KHADHRAOUI
- Technical Test - Tennis Score Computer Kata

