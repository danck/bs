package aufgabe3_2_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Agent implements Runnable{
	private List<Zubehoer> zubehoer	= null;
	private Tisch tisch				= null;
	private Random rg				= null;
	
	Agent(Tisch t){
		rg = new Random();
		tisch = t;
		zubehoer = new ArrayList<Zubehoer>();
		zubehoer.add(Zubehoer.matches);
		zubehoer.add(Zubehoer.paper);
		zubehoer.add(Zubehoer.tobacco);
	}
	
	@Override
	public void run() {
		//legt zufaellig komponenten auf den Tisch
		while(true){
			legeAufTisch();
		}
	}
	
	private void legeAufTisch(){
		Set<Zubehoer> temp = new HashSet<Zubehoer>();
		for (Zubehoer z: zubehoer){
			temp.add(z);
		}
		temp.remove(zubehoer.get(rg.nextInt(zubehoer.size())));
		tisch.legeDrauf(temp);
	}

}
