package aufgabe2_2;

import java.util.Random;

public class Car implements Runnable {

	private final SimRace race;
	private final int startnummer;
	private final int roundsToGo;
	private int time = 0;

	Car(final SimRace race, final int startnummer) {
		this.roundsToGo = race.numberOfRounds;
		this.race = race;
		this.startnummer = startnummer;
	}

	public int getTime() {
		return time;
	}

	public int getStartnummer() {
		return startnummer;
	}

	@Override
	public void run() {
		Random rg = new Random();
		int millis;
		for (int i = 0; i < roundsToGo; i++) {
			if (!Thread.interrupted()) {
				millis = rg.nextInt(100);
				time += millis;
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {

					// Ausgabe des betroffenen Wagens nur fuer die Variante,
					// dass der Unfall-Thread gezielt wagen anhaelt
					// System.out.println("Wagen "+ startnummer +
					// " meldet Unfall");
					Thread.currentThread().interrupt();
				}
			} else {
				// Ausgabe des betroffenen Wagens nur fuer die Variante, dass
				// der Unfall-Thread gezielt wagen anhaelt
				// System.out.println("Wagen "+ startnummer + " meldet Unfall");
				Thread.currentThread().interrupt();
			}
		}
		race.finish(this);
	}
}
