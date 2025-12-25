package com.test.technique.score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreTennisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreTennisApplication.class, args);

		System.out.println("*** Tennis Score Computer ***\n");

		if (args.length > 0) {
			String ballSequence = args[0];
			System.out.println("Sequence des Balles: " + ballSequence);
			TennisGame.play(ballSequence);
		} else {
			System.out.println("Utiliser une chaine de caractères : <ballSequence>");
			System.out.println("Exemple: ABABAA");
			System.out.println("\nPar défault, l'exemple: ABABAA sera executé\n");
			TennisGame.play("ABABAA");
		}
	}

}
