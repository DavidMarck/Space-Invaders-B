package tp;

public class Slavel extends Vaisseau {
	
	Slavel()
	{
		// Structure 30, bouclier 0
		super(30,0);
		try {
			addArme(Armurerie.getArmurerie().getArme("hammer"));
		} catch (ArmurerieException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void attaque(Vaisseau vaisseau) {
		//Attaque avec toute les armes disponible
		for(Arme arme : this.arrayArme){
			if(arme !=null)
			{
				// Si c'est une arme direct le temps de rechargement est de 1
				if(arme.getTypeArme() == Type.Explosif)
				{
					arme.setTempsRechargement(1);
				}
				int tir = arme.tir();
				System.out.println("Le slavel attaque avec " + arme.getNom() +" pour " + tir + " de d�gat");
				vaisseau.repartirDegat(tir);
			}

		}
	}
}
