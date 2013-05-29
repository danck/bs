package aufgabe3_2_1._1;

import java.util.Set;

public class Tisch implements Runnable {
	Set<Zubehoer> auflage	= null;

	@Override
	public void run() {
		//bekommt vom Agenten etwas draufgelegt
		//laesst Nur einen Raucher zur Zeit Rauchen
		//Notified Raucher bei neuen sachen????
		
	}
	
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
	
	public synchronized Set<Zubehoer> nimmRunter(){
		while(auflage==null){ 
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return null;
			}
		}
		Set<Zubehoer> temp = auflage;
		return temp;
	}
	
	public synchronized void legeZurueck(Set<Zubehoer> waren){
		auflage = waren;
		this.notifyAll();
	}
	
	public void habeGeraucht(){
		auflage = null;
		this.notifyAll();
	}
}