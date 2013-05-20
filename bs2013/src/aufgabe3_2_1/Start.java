package aufgabe3_2_1;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tisch t 	= new Tisch();
		Thread a	= new Thread(new Agent(t));
		Thread r1	= new Thread(new Raucher(Zubehoer.matches, t, a));
		Thread r2	= new Thread(new Raucher(Zubehoer.paper, t, a));
		Thread r3	= new Thread(new Raucher(Zubehoer.tobacco, t, a));


		

		a.start();
		r1.start();
		r2.start();
		r3.start();

	}
}
