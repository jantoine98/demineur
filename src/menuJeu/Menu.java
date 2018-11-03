package menuJeu;
import java.util.Scanner;

import demineur.Grille;

public class Menu {

	public static void main(String[] args) 
	{
		System.out.println("Bienvenue sur le jeu d�mineur 9x9 :");
		Scanner sc = new Scanner(System.in);
		
		//arr�ter la boucle infinie en cas de game over ou si l'utilisateur choisit de quitter le jeu en saisissant 2
		while(true)
		{
			System.out.println("###########################################");
			System.out.println("Cliquez sur : ");
			System.out.println("1 : pour jouer");
			System.out.println("2 : pour quitter");
			System.out.println("###########################################");
			
			int choix = sc.nextInt(); 
			
			switch(choix)
			{
				case 1: 
					//g�n�rer un grille al�atoirement
					Grille grille = new Grille();
					//System.out.println(grille.toString(true)); // d�voiler la grille pour les tests
					
					//afficher la grille : le param�tre false sert � ne afficher que les cases d�voil�es. Pour l'instant aucune case n'est encore d�voil�e
					System.out.println(grille.toString(false));
					boolean gameOver = false;
					do 
					{
						int x,y;
						//tant que la valeur de x saisie n'est pas valide : redemander la saisie
						do
						{
							System.out.println("coordonn�e x : (entre 0 et 8)");
							x = sc.nextInt();
						}while(x < 0 || x > 8);
						
						//tant que la valeur de y saisie n'est pas valide : redemander la saisie
						do
						{
							System.out.println("coordonn�e y : (entre 0 et 8)");
							y = sc.nextInt();
						}while(y < 0 || y > 8);
						
						//d�voiler la case choisie par l'utilisateur 
						gameOver = grille.devoilerCase(x, y);
						//afficher le nouvel �tat de la grille
						System.out.println(grille.toString(false));
						if(gameOver == false)
						{
							System.out.println(" ****************** GAME OVER :( :'( ****************** ");
						}
						
					} while (gameOver != false);
	
					break;
				case 2 : 
					System.out.println("Au revoir !");
					return;
				default : 
					System.out.println("Veuillez renseigner une valeur valide");
					break;
			}	
		}
	}

}
