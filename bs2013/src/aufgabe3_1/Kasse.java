package aufgabe3_1;

import java.util.concurrent.Semaphore;

public class Kasse extends Semaphore{

	private static final long serialVersionUID = -6892863064749209179L;

	public Kasse() {
		super(Constants.MAX_CUSTOMERS_ALLOWED, true);
	}
	
}
