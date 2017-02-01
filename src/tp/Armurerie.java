package tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Singleton
public class Armurerie {
	private List<Arme> listArme;
	private static Armurerie armurerie;
	
	private Armurerie(){
		init();
	}
	// Cr�er ou r�cup�re l'instance du singleton
	public static Armurerie getArmurerie(){
		// si armurerie n'est pas initialiser
		if(armurerie == null)
			armurerie = new Armurerie();
		return armurerie;
	}
	
	// Initialise 3 armes
	public void init(){
		listArme = new ArrayList<Arme>();
		listArme.add(new Arme("rayon de la mort",10,100,Type.Direct));
		listArme.add(new Arme("missile de la mort",10,100,Type.Guide));
		listArme.add(new Arme("explosif de la mort",10,100,Type.Explosif));
		listArme.add(new Arme("laser",2,3,Type.Direct,1));
		listArme.add(new Arme("hammer",1,8,Type.Explosif,1.5));
		listArme.add(new Arme("torpille",3,3,Type.Guide,2));
		listArme.add(new Arme("mitrailleuse",2,3,Type.Direct,1));
		listArme.add(new Arme("emg",1,7,Type.Explosif,1.5));
		listArme.add(new Arme("missile",4,100,Type.Guide,4));
	}

	public List<Arme> getListArme() {
		return listArme;
	}
	
	public void addArme(Arme arme) {
		listArme.add(arme);
	}
	
	public void addArme(String nom, int minDegat, int maxDegat, Type typeArme)
	{
		listArme.add(new Arme(nom,minDegat,maxDegat,typeArme));
	}
	
	//Transfome la liste des armes en string
	@Override
	public String toString()
	{
		String res = "";
		for(Arme a : listArme)
		{
			res += a.toString() + "\n";
		}
		return res;
	}
	
	//R�cup�re la premi�re arme de nom nomArme
	public Arme getArme(String nomArme) throws ArmurerieException
	{
		for(Arme arme : this.listArme){
			if(arme.getNom() == nomArme)
				return arme;
		}
		throw new ArmurerieException();
	}
	
	/**
	 * Tri la liste des Armes selon leurs dégâts moyens
	 */
	public void triMoyDeg()
	{
		Collections.sort(listArme, new DegMoyComparateur());
	}
	
	/**
	 * Tri la liste des Armes selon leurs dégâts minimums
	 */
	public void triMinDeg()
	{
		Collections.sort(listArme, new DegMinComparateur());
	}
}
