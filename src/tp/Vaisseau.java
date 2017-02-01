package tp;

import java.util.List;

public abstract class Vaisseau {
	protected int maxStructurePoint;
	protected int maxBouclierPoint;
	protected int bouclierPoint;
	protected int structurePoint;
	protected boolean estDetruit;
	protected final Arme[] arrayArme;
	
	public Arme[] getArrayArme() {
		return arrayArme;
	}

	Vaisseau(int maxStructurePoint, int maxBouclierPoint){
		this.maxStructurePoint = maxStructurePoint;
		this.maxBouclierPoint = maxBouclierPoint;
		this.structurePoint = maxStructurePoint;
		this.bouclierPoint = maxBouclierPoint;
		estDetruit = false;
		arrayArme = new Arme[3];
	}

	public int getMaxStructurePoint() {
		return maxStructurePoint;
	}

	public void setMaxStructurePoint(int maxStructurePoint) {
		this.maxStructurePoint = maxStructurePoint;
	}

	public int getMaxBouclierPoint() {
		return maxBouclierPoint;
	}

	public void setMaxBouclierPoint(int maxBouclierPoint) {
		this.maxBouclierPoint = maxBouclierPoint;
	}

	public boolean isEstDetruit() {
		return estDetruit;
	}

	public void setEstDetruit(boolean estDetruit) {
		this.estDetruit = estDetruit;
	}
	
	// Retourne vrai si l'arme � �t� ajout�, faux sinon
	public boolean addArme(Arme arme) throws ArmurerieException{
		if(!Armurerie.getArmurerie().getListArme().contains(arme))
			throw new ArmurerieException();
		for(int i = 0;i<arrayArme.length;i++)
		{
			// Si l'arme n'est pas initialis�
			if(arrayArme[i] == null)
			{
				arrayArme[i] = arme;
				return true;
			}
		}
		return false;
	}
	
	public void removeArme(Arme arme){
		for(int i = 0; i < arrayArme.length; i++)
		{
			if(arrayArme[i].equals(arme))
				arrayArme[i] = null;
		}
	}
	
	public void printArmes(){
		if(arrayArme.length == 0)
			System.out.println("Ce vaisseau n'est pas arm�");
		for(int i = 0; i < arrayArme.length; i++){
			System.out.println(arrayArme[i]);
		}
	}
	
	public double totalDegatMoyen(){
		double res = 0;
		for(int i = 0; i < arrayArme.length; i++){
			res += (arrayArme[i].getMaxDegat() + arrayArme[i].getMinDegat())/2;
		}
		return res;
	}
	
	public void repartirDegat(int degat){
		bouclierPoint -= degat;
		// Si on a plus de bouclier
		if(bouclierPoint < 0)
		{
			//Perte des points de structure
			structurePoint += bouclierPoint;
			bouclierPoint = 0;
		}
		System.out.format("Le vaisseau � re�u %d points de d�gats, il lui reste %d de bouclier et %d de structure %n",degat,bouclierPoint, structurePoint);
		if(structurePoint < 0)
		{
			estDetruit = true;
		}
	}
	
	public abstract void attaque(Vaisseau vaisseau);
	
	public void rechargeBouclier(int recharge){
		bouclierPoint += recharge;
		// On ne peux pas avoir des bouclier sup�rieurs au maximum du bouclier
		if(bouclierPoint > maxBouclierPoint)
			bouclierPoint = maxBouclierPoint;
	}
	
}

	
