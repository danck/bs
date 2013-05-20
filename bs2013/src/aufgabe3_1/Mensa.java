package aufgabe3_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mensa implements Runnable{
	private Set<Kasse> kassen		= null;
	private Set<Thread> studenten	= null;
	
	public Mensa(int number_students, int number_counters){
		kassen 		= new HashSet<Kasse>();
		studenten 	= new HashSet<Thread>();

		//Initialisiere Kassen
		for (int i=0; i<number_counters; i++){
			kassen.add(new Kasse());
		}
		
		//Initialisiere Studenten
		for (int i=0; i<number_students; i++){
			studenten.add(new Thread(new Student(this)));
		}

	}
	
	/**
	 * Die Tueren offenen sich und die Studenten stuermen zur Kasse
	 */
	private void oeffnen(){
		for (Thread s: studenten){
			s.start();
		}
	}
	
	/**
	 * Die Studenten werden interrupted
	 */
	private void schliessen(){
		for (Thread s: studenten){
			s.interrupt();
		}
	}
	
	public Set<Kasse> getKassen() {
		return kassen;
	}
	
	/**
	 * Den Betrieb aufnehmen (Methoden 'oeffnen()' und 'schliessen()')
	 */
	@Override
	public void run() {
		oeffnen();
		try {
			Thread.sleep(Constants.OEFFNUNGS_DAUER);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		schliessen();
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread cafete = new Thread(new Mensa(Constants.NUMBER_STUDENTS, Constants.NUMBER_COUNTERS));
		cafete.start();
	}





}
