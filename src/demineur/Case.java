package demineur;

public class Case 
{
	protected int x;
	protected int y;
	// devoilé = true quand le contenu de la case est dévoilé
	protected boolean devoile;
	//le contenu d'une case peut être un vide ou une mine
	protected String contenu;
	
	public Case(int x, int y, String contenu)
	{
		this.x = x;
		this.y = y;
		//le contenu d'une vide est caché par défaut
		this.devoile = false;
		this.contenu = contenu;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean getDevoile()
	{
		return devoile;
	}
	
	public String getContenu()
	{
		return contenu;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setDevoile(boolean devoile)
	{
		this.devoile = devoile;
	}
	
	public void setContenu(String contenu)
	{
		this.contenu = contenu;
	}

}
