package tp;

public class ViperMK2 extends Vaisseau {
	
	ViperMK2()
	{
		// Structure 10, bouclier 15
		super(10,15);
		try {
			addArme(Armurerie.getArmurerie().getArme("mitrailleuse"));
			addArme(Armurerie.getArmurerie().getArme("emg"));
			addArme(Armurerie.getArmurerie().getArme("missile"));
		} catch (ArmurerieException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void attaque(Vaisseau vaisseau) {
		Arme meilleurArme = arrayArme[0];
		//Recherche de la meilleure arme
		for(int i = 1; i< arrayArme.length;i++)
		{
			if(arrayArme[i].getMaxDegat() > meilleurArme.getMaxDegat() && arrayArme[i].getcompteurTours() < 1)
				meilleurArme = arrayArme[i];
		}
		//D�cr�mentation du tours pour les armes non utilis�s
		for(int i = 1; i< arrayArme.length;i++)
		{
			if(arrayArme[i] !=meilleurArme)
				arrayArme[i].setCompteurTours(arrayArme[i].getcompteurTours()-1);
		}
		
		int tir = meilleurArme.tir();
		System.out.println("Le ViperMK2 attaque avec " + meilleurArme.getNom() +" pour " + tir + " de d�gat");
		vaisseau.repartirDegat(tir);
		
		
	}

}
