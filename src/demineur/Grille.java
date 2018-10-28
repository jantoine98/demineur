package demineur;

import java.util.ArrayList;
import java.util.Random;

public class Grille 
{
	protected ArrayList<Case> cases;
	protected int longueur;
	protected int largeur;
	
	public Grille() 
	{
		cases = new ArrayList<Case>();
		longueur = 9;
		largeur = 9;
		this.initialiserCasesGrille();
	}
	
	public int getLongueur()
	{
		return longueur;
	}
	
	public int getLargeur()
	{
		return largeur;
	}
	
	public ArrayList<Case> getCases()
	{
		return cases;
	}
	
	public void setLongueur(int longueur)
	{
		this.longueur = longueur;
	}
	
	public void setLargeur(int largeur)
	{
		this.largeur = largeur;
	}
	
	public void setCases(ArrayList<Case> cases)
	{
		this.cases = cases;
	}
	
	/**
	 * récupérer la case de coordonnées (x,y) de la grille 
	 * @param x
	 * @param y
	 * @return Case
	 */
	public Case getCase(int x,int y)
	{
		for(int i = 0; i < this.cases.size();i++)
		{
			Case caseGrille = this.cases.get(i);
			
			if(caseGrille.getX() == x && caseGrille.getY() == y)
			{
				return caseGrille;
			}
		}
		
		return null;
	}
	
	/**
	 * changer le contenu d'une case de la grille. Cette fonction est appelée à l'initialisation d'une grille
	 * @param x
	 * @param y
	 * @param contenu
	 */
	public void setContenuCase(int x,int y, String contenu)
	{
		Case caseGrille = this.getCase(x, y);
		caseGrille.setContenu(contenu);
	}
	
	
	/**
	 * afficher les cases d'une grille
	 */
	public String toString()
	{
		String grilleString = "";
		
		for(int i = 0; i < this.longueur; i++)
		{
			for(int j = 0; j < this.largeur; j++)
			{
				grilleString += this.getCase(i, j).getContenu() + " ";
			}
			
			grilleString += "\n";
		}
		
		return grilleString;
	}
	
	/**
	 * fonction initialise la grille du jeu
	 */
	public void initialiserCasesGrille()
	{
		this.initialiserCases();
		this.positionnerMines();
	}
	
	/**
	 * cette fonction initialise les cases de la grille avec un contenu vide au début
	 * cette fonction est privée car elle est juste appelée par la fonction initialiserCases de la classe grille
	 */
	private void initialiserCases()
	{
		for(int i = 0; i < this.longueur; i++)
		{
			for(int j = 0; j < this.largeur;j++)
			{
				Case nouvelleCase = new Case(i,j,"_");
				this.cases.add(nouvelleCase);
			}
		}
	}
	
	/**
	 * cette fonction positionne les mines de façon aléatoire dans la grille 
	 * cette fonction est privée car elle est juste appelée par la fonction initialiserCases de la classe grille
	 */
	private void positionnerMines()
	{
		Random r = new Random();
		
		for(int i = 0; i < 10; i++)
		{
			Case caseMine;
			int x,y;
			
			// faire une boucle do while pour éviter de positionner plus d'une mine dans une même case
			do
			{
				x = r.nextInt(9);
				y = r.nextInt(9);
				caseMine = this.getCase(x, y);
			} while(caseMine.getContenu() == "@");//le @ représente une mine
			
			this.setContenuCase(x, y, "@");
		}
	}

}
