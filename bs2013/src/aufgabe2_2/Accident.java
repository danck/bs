package aufgabe2_2;

import java.util.List;
import java.util.Random;

public class Accident implements Runnable{

	private Thread race;
	
	Accident(Thread race){
		this.race = race;
	}
	
	@Override
	public void run() {
		//List<Thread> opfer = race.interrupt();
		Random rg = new Random();
		int millis;
		//int number;
		millis = rg.nextInt(2500);
		//number = rg.nextInt(opfer.size());
		try {
			Thread.sleep(millis);
			race.interrupt();
			System.out.println("Unfall-Thread:\tZugeschlagen");
		} catch (InterruptedException e) {
			System.out.println("Unfall-Thread:\tAbgebrochen");
		}
		
	}

}
