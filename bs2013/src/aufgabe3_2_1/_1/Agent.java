package aufgabe3_2_1._1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent implements Runnable{
	private List<Zubehoer> zubehoer	= null;
	private Tisch tisch				= null;
	
	Agent(Tisch t){
		tisch = t;
		zubehoer = new ArrayList<Zubehoer>();
		zubehoer.add(Zubehoer.matches);
		zubehoer.add(Zubehoer.paper);
		zubehoer.add(Zubehoer.tobacco);
	}
	
	@Override
	public void run() {
		//legt zufaellig komponenten auf den Tisch
	}
	
	private void legeAufTisch(){
		Random rg = new Random();
		//Zubehor = rg.nextInt(zubehoer.size());
	}

}
