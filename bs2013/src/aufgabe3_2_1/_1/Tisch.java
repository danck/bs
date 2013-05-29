package aufgabe3_2_1._1;

import java.util.Set;

public class Tisch {
	final long RAUCHDAUER	= 3000; // milliseconds
	
	Set<Zubehoer> auflage	= null;
	
	public synchronized void legeDrauf(Set<Zubehoer> zubehoere){
		while(auflage!=null){
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
	
	public synchronized void versucheRauchen(Raucher raucher){
		
		//Warte solange nichts auf dem Tisch liegt
		while(auflage==null){ 
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		//Falls das Richtige auf dem Tisch liegt, nimms runter und rauche
		if (!auflage.contains(raucher.getMeinTeil())){
			auflage = null;
			System.out.println(raucher + " raucht.");
			try {
				Thread.currentThread();
				Thread.sleep(RAUCHDAUER);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		notify();
	}
}