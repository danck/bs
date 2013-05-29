package aufgabe3_2_2;

import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tisch {
	final long RAUCHDAUER	= 3000; // milliseconds
	final Lock lock = new ReentrantLock();
	final Condition tischLeer	= lock.newCondition(); 
	final Condition tischVoll	= lock.newCondition(); 
	
	Set<Zubehoer> auflage	= null;
	
	public void legeDrauf(Set<Zubehoer> zubehoere) {
		lock.lock();
		try {
			while (auflage != null) {
				tischLeer.await();
			}
			auflage = zubehoere;
			System.out.println(zubehoere);
			tischVoll.signalAll();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			lock.unlock();
		}

	}
	
	public void versucheRauchen(Raucher raucher) {
		lock.lock();
		try {
			// Warte solange nichts auf dem Tisch liegt
			while (auflage == null) {
				tischVoll.await();
			}

			// Falls das Richtige auf dem Tisch liegt, nimms runter und rauche
			if (!auflage.contains(raucher.getMeinTeil())) {
				auflage = null;
				System.out.println(raucher + " raucht.");
				try {
					Thread.currentThread();
					Thread.sleep(RAUCHDAUER);
					tischLeer.signal();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			lock.unlock();
		}
	}
}