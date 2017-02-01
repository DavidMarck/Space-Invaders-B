package tp;

public class Dart extends Vaisseau {
	
	Dart()
	{
		super(10,3);
		try {
			addArme(Armurerie.getArmurerie().getArme("laser"));
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
				// Si c'est une arme direct le temps de rechargement est de 1
				if(arme.getTypeArme() == Type.Direct)
				{
					arme.setTempsRechargement(1);
				}
				int tir = arme.tir();
				System.out.println("Le Dart attaque avec " + arme.getNom() +" pour " + tir + " de d�gat");
				vaisseau.repartirDegat(tir);
			}
		}

	}
	
}
