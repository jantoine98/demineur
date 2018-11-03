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
	 * r�cup�rer la case de coordonn�es (x,y) de la grille 
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
	 * changer le contenu d'une case de la grille. Cette fonction est appel�e � l'initialisation d'une grille
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
	 * @param devoile : si ce param�tre = true : afficher toutes les cases en mode d�voil� (utile pour mes tests)
	 * si devoile = false : ne d�voiler que las cases qui sont au statut d�voil�
	 * @return
	 */
	public String toString(boolean devoile)
	{
		String grilleString = "";
		
		for(int i = 0; i < this.longueur; i++)
		{
			for(int j = 0; j < this.largeur; j++)
			{
				if(devoile == true || this.getCase(i, j).getDevoile())
				{
					grilleString += this.getCase(i, j).getContenu() + " ";
				}
				else
				{
					grilleString += "X ";
				}
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
		this.calculContenuCases();
	}
	
	/**
	 * cette fonction initialise les cases de la grille avec un contenu vide au d�but
	 * cette fonction est priv�e car elle est juste appel�e par la fonction initialiserCases de la classe grille
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
	 * cette fonction positionne les mines de fa�on al�atoire dans la grille 
	 * cette fonction est priv�e car elle est juste appel�e par la fonction initialiserCases de la classe grille
	 */
	private void positionnerMines()
	{
		Random r = new Random();
		
		for(int i = 0; i < 10; i++)
		{
			Case caseMine;
			int x,y;
			
			// faire une boucle do while pour �viter de positionner plus d'une mine dans une m�me case
			do
			{
				x = r.nextInt(9);
				y = r.nextInt(9);
				caseMine = this.getCase(x, y);
			} while(caseMine.getContenu() == "@");//le @ repr�sente une mine
			
			this.setContenuCase(x, y, "@");
		}
	}
	
	/**
	 * cette fonction calcule le nombre de mines avoisinantes de chaque case de la grille
	 */
	private void calculContenuCases()
	{
		for(int i = 0; i < this.longueur; i++)
		{
			for(int j = 0; j < this.largeur; j++)
			{
				//on calcule le nombre de mines avoisinantes pour les cases qui ne contiennent pas de mines
				if(this.getCase(i, j).getContenu() != "@")
				{
					int nbMines = this.getNbMinesAvoisinantes(i, j);
					this.setContenuCase(i, j, nbMines+"");
				}
				
			}
		}
	}
	
	/**
	 * cette fonction calcule le nombre de mines avoisinantes d'une case de la grille
	 * @param x
	 * @param y
	 * @return
	 */
	private int getNbMinesAvoisinantes(int x, int y)
	{
		int xMin = x - 1, xMax = x + 1, yMin = y - 1, yMax = y + 1, nbMines = 0;
		
		//d�finir le xmin ymin xmax ymax pour traiter le cas des points qui sont sur les bords de la grille 
		if(x == 0) 
		{
			xMin = 0;	
		} 
		if(x == this.longueur - 1)
		{
			xMax = this.longueur - 1;
		}
		
		if(y == 0) 
		{
			yMin = 0;	
		} 
		if(y == this.largeur - 1)
		{
			yMax = this.largeur - 1;
		}
		
		for(int i = xMin; i <= xMax; i++)
		{
			for(int j = yMin; j <= yMax; j++)
			{
				//si la contenu est une mine : incr�mznter le nb de mines
				if(this.getCase(i, j).getContenu() == "@")
				{
					nbMines++;
				}
			}
		}
		
		return nbMines;
	}
	
	/**
	 * fonction r�cursive qui d�voile le contenu d'une ou d'un ensemble de cases.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean devoilerCase(int x, int y)
	{
		//si la case pass�e en param�re est une mine : game over
		if(this.getCase(x, y).getContenu() == "@")
		{
			return false;
		}
		//si la case pass�e en param�tre n'est pas une mine : 2 cas
		else
		{
			int nbMinesAvoisinnantes = this.getNbMinesAvoisinantes(x, y);
			
			//1er cas : si las case a une mine avoisinnante : d�voiler juste la case en question
			if(nbMinesAvoisinnantes != 0)
			{
				this.getCase(x, y).setDevoile(true);
				return true;
			}
			
			//2e cas : si la case n'a pas de mines avoisinnantes : 
			else
			{
				//d�voiler la case en param�tre
				this.getCase(x, y).setDevoile(true);
				//r�cup�rer ses case avoisinnantes
				ArrayList<Case> cases = this.getCasesAvoisinnantes(x, y);
				//pour chaque case avoisinnante qui n'est pas encore d�voil�e : refaire le traitement du d�but r�cursivement
				for(int i = 0; i < cases.size();i++)
				{
					Case caseAvoisinnante = cases.get(i);
					this.devoilerCase(caseAvoisinnante.getX(), caseAvoisinnante.getY());
				}
			}
		}
		
		return true;
	}
	
	/**
	 * cette fonction permet de r�cup�rer les cases avoisinnantes non d�voil�es d'une case
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Case> getCasesAvoisinnantes(int x,int y)
	{
		ArrayList<Case> cases = new ArrayList<Case>();
		int xMin = x - 1, xMax = x + 1, yMin = y - 1, yMax = y + 1, nbMines = 0;
		
		//d�finir le xmin ymin xmax ymax pour traiter le cas des points qui sont sur les bords de la grille 
		if(x == 0) 
		{
			xMin = 0;	
		} 
		if(x == this.longueur - 1)
		{
			xMax = this.longueur - 1;
		}
		
		if(y == 0) 
		{
			yMin = 0;	
		} 
		if(y == this.largeur - 1)
		{
			yMax = this.largeur - 1;
		}

		for(int i = xMin; i <= xMax; i++)
		{
			for(int j = yMin; j <= yMax; j++)
			{
				//ne pas inclure la case pass�e en parm�tre dans la liste des cases avoisinnantes
				if(i == x && j == y)
				{
					continue;
				}
				//ne pas inclure les cases d�voil�es dans la liste des cases avoisinnantes
				if(this.getCase(i, j).getDevoile() == true)
				{
					continue;
				}
				cases.add(this.getCase(i, j));
			}
		}
		return cases;
	}
}
