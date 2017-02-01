package tp;

import java.util.ArrayList;

public class Assault extends Vaisseau implements IAptitude {
	
	
	Assault()
	{
		// Structure 15, bouclier 0
		super(15,0);
	}
	
	@Override
	public int utilise(ArrayList<Vaisseau> vaisseaux) {
		if(vaisseaux.get(0) == this){
			this.structurePoint = 0;
			this.estDetruit = true;
			System.out.format("\"Puissions-nous mourir%nComme au printemps%nLes fleurs de cerisier%nPures et brillantes\"%n Le kamikaze explose au contact du joueur %n");
			return 10;
		}
		else
			return 0;
		
	}

	@Override
	public void attaque(Vaisseau vaisseau) {
	}

}
