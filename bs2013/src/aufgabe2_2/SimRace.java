package aufgabe2_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimRace {
	private final int numberOfCars;
	private List<Thread> participants = new ArrayList<Thread>();
	private List<Car> finishLine = new ArrayList<Car>();
	public final int numberOfRounds;

	public List<Thread> getParticipants() {
		return participants;
	}
	
	public SimRace(int numberOfCars, int numberOfRounds) {
		this.numberOfCars 	= numberOfCars;
		this.numberOfRounds = numberOfRounds;
	}
	
	protected void finish(Car c){
		finishLine.add(c);
	}
	
	public String resultTable(){
		StringBuffer table = new StringBuffer("");
		
		Collections.sort(finishLine, new Comparator<Car>(){
			public int compare(Car c1, Car c2){
				return (c1.getTime()>c2.getTime() ? 1 : (c1.getTime()==c2.getTime() ? 0 : -1));
			}
		});
		
		int index = 1;
		for (Car c: finishLine){
			table.append(index++ + ". Platz: Wagen " + c.getStartnummer() + " Zeit: " + c.getTime() + "\n");
		}
		
		return table.toString();
	}
	
	public void start(){
		Thread acc = new Thread(new Accident(Thread.currentThread()));
		acc.start();
		for (int i = 0; i<numberOfCars; i++){
			if (Thread.currentThread().isInterrupted()){return;}
			 Thread t = new Thread(new Car(this, i));
			 participants.add(t);
			 t.start();
		}

		for (int i = 0; i < numberOfCars; i++) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Unfall vor dem ersten Join");
				for (Thread car: participants){
					car.interrupt();
				}
				return;
			} else {
				try {
					participants.get(i).join();
				} catch (InterruptedException e) {
					
					//Alle Autos anhalten
					for (Thread car: participants){
						car.interrupt();
					}
					
					//Unfall melden
					System.out.println("Race-Thread:\tUnfall erkannt");
					return;
				}
			}
		}
		
		//Wenn Rennen durch: Unfall-Thread anhalten und Ergebnis ausgeben
		acc.interrupt();
		System.out.println(resultTable());
	}
	
/**
 * 
 * @param args
 */
	public static void main(String[] args) {
		SimRace race = new SimRace(5, 20);
		race.start();
	}

}
