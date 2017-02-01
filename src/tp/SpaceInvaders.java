package tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceInvaders {
	
	private ArrayList<Joueur> listJoueurs;
	private ArrayList<Vaisseau> ennemies;
	private Armurerie armurerie;
	
	SpaceInvaders(){}
	
	private void initJoueurs(){
		// Initialise les joueurs
		listJoueurs = new ArrayList<Joueur>();

		listJoueurs.add(new Joueur("Oberbach","Baptiste","Bob"));  
		listJoueurs.add(new Joueur("Lambs","Victor","LeDanger"));
		listJoueurs.add(new Joueur("Still","Jean","Bg"));
			
		armurerie = Armurerie.getArmurerie();
	}
	
	private void initEnnemies(){
		// Initialise les ennemies
		ennemies = new ArrayList<Vaisseau>();
		ennemies.add(new Alkesh());
		ennemies.add(new Dart());
		ennemies.add(new Slavel());
		ennemies.add(new Tardis());
		ennemies.add(new Assault());
	}
	// Initialise des armes
	private void initArme(){
		for(Joueur j : listJoueurs)
		{
			for(Arme a : armurerie.getListArme())
				try {
					j.getVaisseau().addArme(a);
				} catch (ArmurerieException e) {
					System.out.println("On ne peut pas ajouter un vaisseau n'�tant pas dans l'armurerie");
				}
		}
	}
	
	
	public void afficheJoueur(){
		for(Joueur joueur : listJoueurs)
		{
			System.out.println(joueur);
		}
	}
	
	//Suprime les �nnemies mort de la liste des ennemies
	public void supprimerEnnemiesMort(ArrayList<Vaisseau> ennemies)
	{
		for(int i = 0;i<ennemies.size();){
			if(ennemies.get(i).estDetruit){
				System.out.println("Le " + ennemies.get(i).getClass().toString() + " est d�truit");
				ennemies.remove(i);
			}
			else
				i++;
		}
	}
	
	public void jouerTour(Joueur joueur){
		// Utilisation des comp�tences
		for(Vaisseau ennemie : ennemies){
			if(ennemie instanceof IAptitude){
				joueur.getVaisseau().repartirDegat( ((IAptitude)ennemie).utilise(ennemies));
			}
		}

		//Suppresion des kamikazes
		supprimerEnnemiesMort(ennemies);
		
		int probaTire = 1;
		boolean aTire = false;
		for(Vaisseau ennemie : ennemies){
			//Tour du joueur
			//Le joueur peut tirer si il n'a pas d�j� tirer et que la chance est avec lui
			double rand = Math.random() * ennemies.size();
			if(!aTire && probaTire > rand){
				// Attaque un ennemie al�atoire
				System.out.println("Le vaisseau du joueur attaque");
				joueur.getVaisseau().attaque(ennemies.get(new Random().nextInt(ennemies.size())));
				aTire = true;
			}
			//Augmentation de la probabilit� que le joueur tire
			probaTire ++;
			
			//Tour de l'ennemie
			if(!ennemie.estDetruit)
			{
				ennemie.attaque(joueur.getVaisseau());
			}
		}
		//Suppresion des morts
		supprimerEnnemiesMort(ennemies);
		//Augmentation des boucliers
		System.out.println("Recharge du bouclier du joueur");
		joueur.getVaisseau().rechargeBouclier(2);
		System.out.println("Recharge du bouclier des ennemies");
		for(Vaisseau ennemie : ennemies)
		{
			ennemie.rechargeBouclier(2);
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("=========================================================");
		System.out.println("					Test du TP1		");
		System.out.println("=========================================================");
		// Cr�ation du jeu
		SpaceInvaders jeu = new SpaceInvaders();
		jeu.initJoueurs();
		
		System.out.println("Affichage de l'armurerie");
		System.out.println(jeu.armurerie.toString());
		
		System.out.println("Joueurs de base");
		jeu.afficheJoueur();
		
		System.out.println("Ajout d'une arme � bob");
		try {
			jeu.listJoueurs.get(0).getVaisseau().addArme(jeu.armurerie.getListArme().get(0));
		} catch (ArmurerieException e) {
			// TODO Auto-generated catch block
			System.out.println("l'arme n'est pas dans l'armurerie");
		}
		jeu.afficheJoueur();
		
		System.out.println("Ajout de 3 armes � tout les joueurs");
		jeu.initArme();
		jeu.afficheJoueur();
		
		System.out.println("=========================================================");
		System.out.println("					Test du TP2		");
		System.out.println("=========================================================");
		
		System.out.println("Remplacement du vaiseau de bob par un viperMK2");
		jeu.listJoueurs.get(0).setVaisseau(new ViperMK2());
		
		
		jeu.initEnnemies();
		int nbTour = 1;
		while(!jeu.listJoueurs.get(0).getVaisseau().estDetruit && jeu.ennemies.size()>0){
			System.out.format("%n==========================Tour %d=========================%n",nbTour);
			jeu.jouerTour(jeu.listJoueurs.get(0));
			nbTour++;
		}
			
		
		if(jeu.listJoueurs.get(0).getVaisseau().estDetruit)
		{
			System.out.println("Le joueur a perdu");
		}
		else
		{
			System.out.println("Le joueur a gagn�");
		}
		

	}

}
