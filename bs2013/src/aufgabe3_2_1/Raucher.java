package aufgabe3_2_1;

import java.util.Set;

public class Raucher extends Thread {
	private Zubehoer meinTeil	= null;
	private Tisch meinTisch		= null;
	private String name			= null;

	Raucher(Zubehoer z, Tisch t){
		meinTeil	= z;
		meinTisch	= t;
		name		= z+ "-Man";
	}
	
	@Override
	public void run() {
		while(true){	
			meinTisch.versucheRauchen(this);
		}
	}
	
	public String toString(){
		return name;
	}

	public Zubehoer getMeinTeil() {
		return meinTeil;
	}
}
