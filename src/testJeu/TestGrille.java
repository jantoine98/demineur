package testJeu;

import demineur.Grille;

public class TestGrille {

	public static void main(String[] args) 
	{
		System.out.println("Debut");
		Grille grille = new Grille();
		
		System.out.println(grille.toString(true));
		
		System.out.println(grille.toString(false));
		
		grille.devoilerCase(0, 0);
		
		System.out.println("après : ");
		
		System.out.println(grille.toString(false));
		

	}

}
