package tp;

public class Alkesh extends Vaisseau {
	
	Alkesh()
	{
		// Structure 3, bouclier 5
		super(3,5);
		try {
			addArme(Armurerie.getArmurerie().getArme("torpille"));
		} catch (ArmurerieException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void attaque(Vaisseau vaisseau) {
		//Attaque avec toute les armes disponible
		for(Arme arme : this.arrayArme){
			if(arme != null)
			{
				int tir = arme.tir();
				System.out.println("L'Alkesh attaque avec " + arme.getNom() +" pour " + tir + " de d�gat");
				vaisseau.repartirDegat(tir);
			}
		}

	}

}
