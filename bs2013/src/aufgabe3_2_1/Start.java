package aufgabe3_2_1;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tisch tisch 	= new Tisch();
		Thread agent 	= new Thread(new Agent(tisch));
		Thread raucher1	= new Thread(new Raucher(Zubehoer.matches, tisch));
		Thread raucher2	= new Thread(new Raucher(Zubehoer.tobacco, tisch));
		Thread raucher3	= new Thread(new Raucher(Zubehoer.paper, tisch));
		
		agent.start();
		raucher1.start();
		raucher2.start();
		raucher3.start();

	}

}