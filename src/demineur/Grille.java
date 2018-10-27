package demineur;

import java.util.ArrayList;

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
	 * afficher les cases d'une grille
	 */
	public String toString()
	{
		String grilleString = "";
		for(int i = 0; i < this.longueur; i++)
		{
			for(int j = 0; j < this.largeur; i++)
			{
				grilleString += this.getCase(i, j).getContenu();
			}
			
			grilleString += "\n";
		}
		
		return grilleString;
	}

}
