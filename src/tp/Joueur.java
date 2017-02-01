package tp;

public class Joueur {
	private final String nom;
	private final String prenom;
	private String pseudo;
	private Vaisseau vaisseau;

	public Joueur(String nom, String prenom, String pseudo){
		this.nom = formate(nom);
		this.prenom = formate(prenom);
		this.pseudo = pseudo;
		this.vaisseau = new Dart();
	}
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	
	public String getNomPrenom(){
		return prenom + " " + nom; 
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}
	
	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	public void setVaisseau(Vaisseau vaisseau) {
		this.vaisseau = vaisseau;
	}
	
	private static String formate(String s){
		s.toLowerCase();
		char[] charArray = s.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return new String(charArray);
	}
	
	@Override
	public String toString() {
		String res = pseudo + " (" + prenom + " " + nom + ") \n";
		// Affiche les informations du vaisseau
		res +="Vaisseau  : Maximum de point de bouclier : " + vaisseau.getMaxBouclierPoint() + 
				" Maximum de point de structure : " + vaisseau.getMaxStructurePoint(); 
		// Affiche l'armement
		Arme[] armes = vaisseau.getArrayArme();
		if(armes.length > 0)
		{
			res += "\n armement :\n";
			for(int i = 0;i < armes.length; i++)
			{
				if(armes[i] != null)
					res += armes[i].toString() + "\n";
			}
		}
		return res;
	}
	
	@Override
	public boolean equals(Object obj){
		// V�rification du type
		if(obj.getClass() == this.getClass())
		{
			// Cast en joueur
			Joueur joueur2 = (Joueur) obj;
			return joueur2.pseudo.equals(this.pseudo);
		}
		return false;
	}
	
}
