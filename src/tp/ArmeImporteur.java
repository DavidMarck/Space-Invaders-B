package tp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ArmeImporteur {

	private Map<String,Integer> freqArmes;
	private static int longueurMin;
	private static String black_list;
	
	/**
	 * Constructeur
	 */
	public ArmeImporteur()
	{
		freqArmes = new HashMap<String,Integer>();
		setBlackList();
		setLongueur();
	}
	
	/**
	 * Lit le fichier texte file et en extrait/compte les armes
	 * @param file : chemin du fichier à lire
	 */
	public void extraitFichier(String file)
	{
		if(file.endsWith(".txt"))
		{
			try
			{
				InputStream stream = new FileInputStream(file);
				InputStreamReader sReader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(sReader);
				String ligne;
				
				while((ligne = buffer.readLine()) != null)
				{
					String[] lesMots = ligne.split(" ");
					for(String str : lesMots)
					{
						extraitArme(str);
					}
				}
				buffer.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
	
	/**
	 * Extrait l'arme contenue dans une chaîne et l'ajoute dans freqArme
	 * (incrémente la fréquence de l'arme si elle a déjà été enregistrée)
	 * @param str : chaîne à traiter
	 */
	public void extraitArme(String str)
	{
		String arme = normaliser(str);
		
		if(valideLongueur(arme) && !isBlackListed(arme))
		{
			if(freqArmes.isEmpty())
			{
				freqArmes.put(arme, 1);
			}
			else
			{
				int freq = freqArmes.containsKey(arme) ? freqArmes.get(arme) : 0;
				freqArmes.put(arme,freq + 1);
			}
		}
	}
	
	/**
	 * Importe des armes dans l'armurerie
	 */
	public void importArmes()
	{
		int minDegat;
		int maxDegat;
		
		Random random = new Random();
		int nbRandom;
		Type type;
		
		if(!freqArmes.isEmpty())
		{
			for(Map.Entry<String, Integer> entry : freqArmes.entrySet())
			{
				minDegat = entry.getKey().length() > entry.getValue() ? entry.getValue() : entry.getKey().length();
				maxDegat = entry.getValue() < entry.getKey().length() ? entry.getKey().length() : entry.getValue();	
				
				nbRandom = random.nextInt((Type.values().length - 1) + 1) + 1;
				type = Type.values()[nbRandom-1];
				
				Armurerie.getArmurerie().addArme(entry.getKey(), minDegat, maxDegat, type);
			}
		}
		else
		{
			System.out.println("Aucune arme à importer...");
		}
	}
	
	/**
	 * Contrôle la longueur d'une chaîne (>= caracMin)
	 * @param str : chaîne à contrôler
	 * @return
	 */
	public boolean valideLongueur(String str)
	{
		return (str.length() >= longueurMin);
	}
	
	/**
	 * Demande à l'utilisateur quelle longueur minimum doivent avoir les mots à importer en arme
	 */
	public void setLongueur()
	{
		Scanner scan = new Scanner(System.in);
		int longueur;
		while(true)
		{
			System.out.println("Veuillez saisir la longueur minimum d'un mot (en nombre de caractères) : ");
			if(scan.hasNextInt())
			{
				longueur = scan.nextInt();
				break;
			}
			System.out.println("/!\\ Veuillez ne saisir un entier /!\\");
		}
		longueurMin = longueur;
	}
	
	/**
	 * Vérifie qu'un mot ne figure pas dans le fichier de liste noire
	 * @param mot : mot à vérifier
	 */
	public boolean isBlackListed(String mot)
	{
		try
		{
			InputStream stream = new FileInputStream(black_list);
			InputStreamReader sReader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(sReader);
			String ligne;
			
			while((ligne = buffer.readLine()) != null)
			{
				String[] lesMots = ligne.split(" ");
				for(String str : lesMots)
				{
					if(str.equals(mot))
					{
						return true;
					}
				}
			}
			buffer.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return false;
	}
	
	/**
	 * Demande à l'utilisateur si il veut utiliser une liste noire
	 * Si oui, on lui demande le chemin du fichier .txt à utiliser
	 */
	public void setBlackList()
	{
		Scanner scan = new Scanner(System.in);
		String yesNo;
		String path;
		
		while(true)
		{
			System.out.println("Voulez-vous définir une liste noire? (o/n) ");
			yesNo = scan.nextLine();
			if(yesNo.equals("o") || yesNo.equals("n"))
			{
				break;
			}
			System.out.println("/!\\ Ne saisir que 'o' ou 'n' /!\\");
		}
		
		if(yesNo.equals("o"))
		{
			while(true)
			{
				System.out.println("Veuillez saisir le chemin du fichier liste noire (.txt) : ");
				path = scan.nextLine();
				if(new File(path).exists() && path.endsWith(".txt"))
				{
					black_list = path;
					break;
				}
				System.out.println("/!\\ Le chemin de fichier saisit n'existe pas ou il ne s'agit pas d'un .txt /!\\er");
			}
		}
	}
	
	/**
	 * Normalise un mot (retrait de la casse et de la ponctuation)
	 * @param str : mot à normaliser
	 */
	public String normaliser(String str)
	{
		str = str.toLowerCase();
		// retire de la chaîne toute ponctuation
		str = str.replaceAll("[^a-zA-Z ]", "");
		
		return str;
	}
	
	// tests
	public static void main(String[] args)
	{
		ArmeImporteur aI = new ArmeImporteur();
		aI.extraitFichier("/home/user-cnam/Bureau/fichier.txt");
		aI.importArmes();
		//Armurerie.getArmurerie().triMoyDeg();
		Armurerie.getArmurerie().triMinDeg();
		System.out.println(Armurerie.getArmurerie());
	}
}
