package aufgabe3_1;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Student implements Runnable {
	private Set<Kasse> kassen	= null;
	private String name	= null;
	private Random rg	= new Random();
	
	public Student(Set<Kasse> k){
		this.kassen = k;
		this.name	= UUID.randomUUID().toString();
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			
			//suche kuerzeste kassenschlange
			Kasse currentMinKasse 	= null;
			int currentMinLength	= Integer.MAX_VALUE;
			for (Kasse k: kassen){
				if (k.getQueueLength() < currentMinLength){
					currentMinKasse		= k;
					currentMinLength	= k.getQueueLength(); //Koennte an dieser Stelle schon wieder anders sein...
				}
			}
			
			//anstellen
			try {
				System.out.println("Student " + name + " stellt sich an Kasse " + currentMinKasse.toString()
						+ " als " + currentMinLength + "ter");
				currentMinKasse.acquire();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				gehNachhause();
			}
			
			//bezahlen
			try {
				System.out.println("Student " + name + " bezahlt");
				Thread.sleep(rg.nextInt(Constants.MAX_PAY_TIME));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				gehNachhause();
			}
			currentMinKasse.release();
			
			//essen
			try {
				System.out.println("Student " + name + " isst");
				Thread.sleep(rg.nextInt(Constants.MAX_EATING_TIME));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				gehNachhause();
			}
			
			//essen
			try {
				System.out.println("Student " + name + " macht irgendwas");
				Thread.sleep(rg.nextInt(Constants.ZWISCHEN_TIME));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				gehNachhause();
			}

		}
	}
	
	private void gehNachhause(){
		System.out.println("Student " + name + " geht nachhause");
	}

}
