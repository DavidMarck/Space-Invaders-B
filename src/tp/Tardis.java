package tp;

import java.util.ArrayList;
import java.util.Random;

public class Tardis extends Vaisseau implements IAptitude {
	
	Tardis()
	{
		// Structure 1, bouclier 0
		super(1,0);
	}
	
	@Override
	public int utilise(ArrayList<Vaisseau> vaisseaux) {
		// Choix des positions � �chang�
		Random rand = new Random();
		int index1 = rand.nextInt(vaisseaux.size());
		int index2 = rand.nextInt(vaisseaux.size());
		System.out.println("Le tardis �change le vaiseau � la place " + index1 + " avec le vaisseau � la place " +index2);
		// Echange du vaiseau � l'index 1 avec le vaisseau � l'index 2
		Vaisseau ves = vaisseaux.get(index1);
		vaisseaux.set(index1, vaisseaux.get(index2));
		vaisseaux.set(index2, ves);
		return 0;
	}

	@Override
	public void attaque(Vaisseau vaisseau) {
		// TODO Auto-generated method stub

	}

}
