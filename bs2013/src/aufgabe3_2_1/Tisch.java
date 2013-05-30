package aufgabe3_2_1;

import java.util.Set;

public class Tisch {
	final long RAUCHDAUER = 3000; // milliseconds

	Set<Zubehoer> auflage = null;

	public synchronized void legeDrauf(Set<Zubehoer> zubehoere) {
		while (auflage != null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		auflage = zubehoere;
		System.out.println(zubehoere);
		this.notifyAll();
	}

	public synchronized void versucheRauchen(Raucher raucher) {

		// Warte solange nichts auf dem Tisch liegt
		while (auflage == null || auflage.contains(raucher.getMeinTeil())) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		// Falls das Richtige auf dem Tisch liegt, nimms runter und rauche
		auflage = null;
		System.out.println(raucher + " raucht.");
		try {
			Thread.currentThread();
			Thread.sleep(RAUCHDAUER);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		notifyAll();
	}
}