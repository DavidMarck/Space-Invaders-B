package tp;

import java.util.Random;

public class Arme {
	
	private String nom;
	private int minDegat;
	private int maxDegat;
	private Type typeArme;
	private double tempsRechargement = 3;
	private double compteurTours;
	
	Arme(String nom, int minDegat, int maxDegat, Type typeArme){
		this.nom = nom;
		this.minDegat = minDegat;
		this.maxDegat = maxDegat;
		this.typeArme = typeArme;
		this.compteurTours = this.tempsRechargement;
	}
	Arme(String nom, int minDegat, int maxDegat, Type typeArme, double tempsRechargement){
		this.nom = nom;
		this.minDegat = minDegat;
		this.maxDegat = maxDegat;
		this.typeArme = typeArme;
		this.tempsRechargement = tempsRechargement;
		this.compteurTours = this.tempsRechargement;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getMinDegat() {
		return minDegat;
	}
	public void setMinDegat(int minDegat) {
		this.minDegat = minDegat;
	}
	public int getMaxDegat() {
		return maxDegat;
	}
	public void setMaxDegat(int maxDegat) {
		this.maxDegat = maxDegat;
	}

	public Type getTypeArme() {
		return typeArme;
	}

	public void setTypeArme(Type typeArme) {
		this.typeArme = typeArme;
	}
	
	public double getTempsRechargement() {
		return tempsRechargement;
	}

	public void setTempsRechargement(double tempsRechargement) {
		// Si on modifie le temps de rechargement, on r�initialise le compteur de tour
		if(this.tempsRechargement != tempsRechargement){
			this.tempsRechargement = tempsRechargement;
			this.compteurTours = this.tempsRechargement;
		}
		
	}
	
	public double getcompteurTours() {
		return compteurTours;
	}
	public void setCompteurTours(double compteurTours) {
		this.compteurTours = compteurTours;
	}
	
	@Override
	public String toString()
	{
		return nom + " D�gat min : " + minDegat + 
				" D�gat max : " + maxDegat + " Type : " + typeArme +
				" Dégâts moyens : " + getMoyDegats();
	}
	
	public int tir()
	{
		compteurTours --;
		if(compteurTours <= 0)
		{
			compteurTours += tempsRechargement;
			int degat = new Random().nextInt(maxDegat - minDegat + 1) + minDegat;
			switch (typeArme){
				case Direct:
					//Random entre 0 et 10
					double rand10 = Math.random()*10;
					//On r�ussi 9 fois sur 10
					if(rand10>1)
						return degat;
					else
						return 0;
				case Explosif:
					//Multiplication du temps de rechargement par 2
					compteurTours *=2;
					//Une chance sur 4 de toucher
					double rand4 = Math.random()*4;
					if(rand4>1)
						//Double des d�gats
						return degat*2;
					else
						return 0;
				case Guide:
					return minDegat;
				// Cas d'erreur
				default:
					return -1;
			}
		}
		else
			return 0;
	}
	
	/**
	 * 
	 * @return moyenne des dégâts d'une arme
	 */
	public float getMoyDegats()
	{
		return (minDegat + maxDegat) / 2;
	}
}
