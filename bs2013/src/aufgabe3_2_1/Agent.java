package aufgabe3_2_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Agent implements Runnable{
	
	List<Zubehoer> kram		= null;
	List<Zubehoer> tempset	= null;
	Set<Zubehoer> subset	= null;
	Tisch meinTisch			= null;
	Random rg				= new Random();

	public Agent(Tisch t){
		meinTisch	= t;
		kram	= new ArrayList<Zubehoer>();
		tempset	= new ArrayList<Zubehoer>();
		subset	= new HashSet<Zubehoer>();
		kram.add(Zubehoer.matches);
		kram.add(Zubehoer.paper);
		kram.add(Zubehoer.tobacco);
	}
	
	@Override
	public synchronized void run() {
		while (!Thread.currentThread().isInterrupted()) {
			// lege zwei zufaellige Sachen auf den Tisch
			tempset.clear();
			for (Zubehoer z : kram) {
				tempset.add(z);
			}
			subset.clear();
			subset.add(tempset.remove(rg.nextInt(tempset.size())));
			subset.add(tempset.remove(rg.nextInt(tempset.size())));

			System.out.println("Agent legt " + subset + " drauf----------------------");
			meinTisch.legeDrauf(subset);

			// sag Bescheid??
			// Warte bis Raucher alles geregelt haben
			/*
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("weiter geht's");
			}
			//*/
		}
	}

}
