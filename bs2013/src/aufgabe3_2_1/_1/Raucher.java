package aufgabe3_2_1._1;

import java.util.Set;

public class Raucher implements Runnable {
	private Zubehoer meinTeil	= null;
	private Tisch meinTisch		= null;

	Raucher(Zubehoer z, Tisch t){
		meinTeil	= z;
		meinTisch	= t;
	}
	
	@Override
	public void run() {
		while(true){	
		//schaut, was auf dem Tisch liegt
			Set<Zubehoer> aufTisch	= meinTisch.nimmRunter();
		//Falls er rauchen kann -> Rauchen
			if (aufTisch.contains(meinTeil)){
				System.out.println(this+" hat "+ meinTeil + ", legt zurück: " + aufTisch);
				meinTisch.legeZurueck(aufTisch);
			} else {
				try {
					Thread.currentThread().sleep(300);
				} catch (InterruptedException e) {
					System.err.print(this +": Beim Rauchen interrupted!!!!!");
				}
				System.out.println(this +": Habe geraucht");
				meinTisch.habeGeraucht();
			}
		}
		
	}
	
	
}
