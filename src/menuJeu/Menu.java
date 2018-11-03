package menuJeu;
import java.util.Scanner;

import demineur.Grille;

public class Menu {

	public static void main(String[] args) 
	{
		System.out.println("Bienvenue sur le jeu démineur 9x9 :");
		Scanner sc = new Scanner(System.in);
		
		//arrêter la boucle infinie en cas de game over ou si l'utilisateur choisit de quitter le jeu en saisissant 2
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
					//générer un grille aléatoirement
					Grille grille = new Grille();
					//System.out.println(grille.toString(true)); // dévoiler la grille pour les tests
					
					//afficher la grille : le paramètre false sert à ne afficher que les cases dévoilées. Pour l'instant aucune case n'est encore dévoilée
					System.out.println(grille.toString(false));
					boolean gameOver = false;
					do 
					{
						int x,y;
						//tant que la valeur de x saisie n'est pas valide : redemander la saisie
						do
						{
							System.out.println("coordonnée x : (entre 0 et 8)");
							x = sc.nextInt();
						}while(x < 0 || x > 8);
						
						//tant que la valeur de y saisie n'est pas valide : redemander la saisie
						do
						{
							System.out.println("coordonnée y : (entre 0 et 8)");
							y = sc.nextInt();
						}while(y < 0 || y > 8);
						
						//dévoiler la case choisie par l'utilisateur 
						gameOver = grille.devoilerCase(x, y);
						//afficher le nouvel état de la grille
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
