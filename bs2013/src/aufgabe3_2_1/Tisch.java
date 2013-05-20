package aufgabe3_2_1;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

/**
 * Singleton Tisch
 */
public class Tisch extends Observable {
	public Set<Zubehoer> zubehoer	= null;
	
	public Tisch(){
		this.zubehoer = new HashSet<Zubehoer>();
	};
	
		
	/**
	 * Hinterlegt zwei Zubehoerteile auf dem Tisch
	 * Die alten werden uberschrieben oder wurden schon von einem Raucher geloescht
	 * Wird nur von dem einen Agenten bedient -> Keine Synchronisation
	 */
	public synchronized void legeDrauf(Set<Zubehoer> teile){
		zubehoer = teile;
		//zubehoerVerfuegbar = true;
		notifyObservers(teile);
	}

	/**
	 * Genau ein Raucher kann das ausgelegte Zubehoer benutzen
	 * Wenn es einmal genommen wurde, kann es kein anderer Raucher nehmen
	 */
	public synchronized void benutzeZubehoer(Raucher r){
		if (!zubehoer.contains(r.getMeinTeil())){
			try {
				System.out.println("Raucher " + r + " mit " + r.getMeinTeil() + " raucht");
				Thread.currentThread();
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println(r + " hat nicht das Richtige");
		}
		notify();
	}
}