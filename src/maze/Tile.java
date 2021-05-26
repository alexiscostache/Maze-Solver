package maze;
import java.io.Serializable;
/**
* @author Alexis-Mihai Costache
*/

public class Tile implements Serializable{
	private Type type;
	public enum Type{
/**
* Contains the four types of a tile.
*/
		CORRIDOR,ENTRANCE,EXIT,WALL;
	}
	private Tile (Type typeIn)
	{
/**
* The constructor initialises the type.
* @param typeIn is the type of the tile.
*/
		type = typeIn;
	}
	protected static Tile fromChar(char c)
	{
/**
* Gets a char and creates a new object of a specified tile.
* @param c is the specified char (eg. char 'e' create a new ENTRANCE tile).
* @return Returns a new object of a specified tile.
*/
		if(c=='.') return new Tile(Type.CORRIDOR);
		else if(c=='e')	return new Tile(Type.ENTRANCE);
		else if(c=='x') return new Tile(Type.EXIT);
		else return new Tile(Type.WALL);
		
	}
	public Type getType()
	{
/**
* This method returns the type of the tile.
* @return Returns the type (eg. ENTRANCE, EXIT, etc.).
*/
		return type;
	}
	public boolean isNavigable()
	{
/**
* This method returns true or false depending on the fact that you can navigate in the maze through that tile.
* @return Returns a boolean, true if you can navigate through that type or false otherwise.
*/
		if(type.name().equals("WALL")) return false;
		else return true;
	}
	public String toString()
	{ 
		/**
* This method returns the string interpretation a specific type.
* @return Returns a string of the form e if the type is ENTRANCE, # if the type is WALL etc.
*/
String helpString;
		if(type.name().equals("WALL")) helpString="#";
		else if(type.name().equals("EXIT")) helpString="x";
		else if(type.name().equals("ENTRANCE")) helpString="e";
		else helpString=".";
		return helpString;
	}
}