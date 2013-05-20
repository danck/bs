package aufgabe3_2_1;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Raucher implements Observer, Runnable {
	private Zubehoer meinTeil		= null;
	private final Tisch meinTisch;
	private  Thread meinAgent	= null;

	public Raucher(Zubehoer z, Tisch t, Thread a){
		meinAgent	= a;
		meinTeil	= z;
		meinTisch	= t;
		meinTisch.addObserver(this);
	}
	
	@Override
	public void update(Observable tisch, Object zubehoerSet) {
		// Ubergabe von zubehoerSet hier nicht noetig, es sei denn hier soll schon verglichen werden
		System.out.println("Raucher " + this + " versucht mit dem Zeug zu rauchen");	
		meinTisch.benutzeZubehoer(this);
	}

	public Zubehoer getMeinTeil() {
		return meinTeil;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			System.out.println("Raucher " + this + " versucht mit dem Zeug zu rauchen");	
			meinTisch.benutzeZubehoer(this);
			meinAgent.interrupt();
			System.out.println("Danach");
		}
	}
}
