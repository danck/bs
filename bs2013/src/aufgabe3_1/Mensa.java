package aufgabe3_1;

import java.util.ArrayList;
import java.util.List;

public class Mensa implements Runnable{
	private List<Kasse> kassen		= null;
	private List<Thread> studenten	= null;
	
	public Mensa(int number_students, int number_counters){
		kassen 		= new ArrayList<Kasse>();
		studenten 	= new ArrayList<Thread>();

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
	
	public List<Kasse> getKassen() {
		return kassen;
	}
	
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
