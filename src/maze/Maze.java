package maze;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
* @author Alexis-Mihai Costache
*/

public class Maze implements Serializable {
/**
* This is the Maze class which contains different methods
* which help us find the location of a Tile, convert a maze from String to Tiles etc.
*/
	private Tile entrance;
	private Tile exit;
    private List<List<Tile>> tiles = new ArrayList<List<Tile>>();
	
	private Maze (){
/**
* Empty constructor
*/
	}

public static Maze fromTxt (String mazeinput) throws FileNotFoundException, IOException, InvalidMazeException {
/**
* Gets the name of a text file and creates a maze based on the data from that text file.
* @param mazeinput is the text file from where we get the data.
* @return Returns a maze object.
* @throws java.io.IOException Indicates a problem with mazeinput.
* @throws java.io.FileNotFoundException Indicates a problem with opening mazeinput.
* @throws java.io.InvalidMazeException Indicates that an invalid character was found in the maze.
* @throws java.io.RaggedMazeException Indicates that the maze is ragged.
* @throws java.io.NoExitException Indicates that there is no exit in the maze.
* @throws java.io.NoEntranceException Indicates that there is no entrance in the maze.
*/
		Maze maze = new Maze();
		int row = 0;
		int numbExit = 0;
		int numbEntrance = 0; 
		String str = ".#ex";
		BufferedReader mazestream = new BufferedReader(new FileReader(mazeinput));
		String contentLine = mazestream.readLine();
		int mazeLength = contentLine.length();
		while (contentLine != null){
			maze.tiles.add(new ArrayList<Tile>());
			int pos = 0;
			if(mazeLength != contentLine.length())
    				{throw new RaggedMazeException("The maze is ragged.");}
    	else 
			while (pos < contentLine.length()){

				if (str.indexOf(contentLine.charAt(pos)) == -1){
					throw new InvalidMazeException("An invalid character was found in the maze");
				}

				maze.tiles.get(row).add(Tile.fromChar(contentLine.charAt(pos)));
				if (contentLine.charAt(pos) == 'e') {;
								maze.setEntrance(maze.tiles.get(row).get(maze.tiles.get(row).size() - 1));
							numbEntrance ++;
				}

				else if (contentLine.charAt(pos) == 'x') {
						maze.setExit(maze.tiles.get(row).get(maze.tiles.get(row).size() - 1));
						numbExit ++;
				}

				pos ++;
			}
			row++;
			 contentLine = mazestream.readLine();
		}

		if (numbExit == 0){
			throw new NoExitException("No exit found");
		}

		if (numbEntrance == 0){
			throw new NoEntranceException("No entrance found");
		}

		return maze;
	}
	public Tile getAdjacentTile(Tile t , Direction d)
	{ 
		/**
* Gets a tile and a direction and returns the tile next to the specified tile in a given direction, if the conditions are met.
* @param t is the specified tile.
* @param d is the given direction in which the tile should move.
* @return Returns the tile next to the specified tile in a given direction or null if there is not a tile that respects the conditons.
*/
	 Coordinate c = getTileLocation(t);
	 int xC = c.getX();
	 int yC = c.getY(); 
		if(d.name().equals("WEST")&&xC>0) return tiles.get(tiles.size()-yC-1).get(xC-1);
			else if(d.name().equals("EAST")&&xC<tiles.get(0).size()-1) return tiles.get(tiles.size()-yC-1).get(xC+1);
				else if(d.name().equals("SOUTH")&&yC>0) return tiles.get(tiles.size()-yC).get(xC);
					else if(d.name().equals("NORTH")&&yC<tiles.size()-1) return tiles.get(tiles.size()-yC-2).get(xC);
	 return null;
	}

	public Tile getEntrance(){
/**
* Gets the entrance tile from the maze.
* @return Returns the entrance attribute.
*/
        return entrance;
    }

    public Tile getExit(){
 /**
* Gets the exit tile from the maze.
* @return Returns the exit attribute.
*/       
        return exit;
    }

   public Tile getTileAtLocation(Coordinate c)
	{  
		/**
* Gets a coordinate and returns the tile in that location.
* @param c is the specified coordinate.
* @return Returns the tile from the specified coordinate or null if the coordinate is not in the maze.
*/
	    int xC,yC;
	    xC = c.getX();
		yC = c.getY();
		if(yC>tiles.size()||xC>tiles.get(0).size())
			return null;
		else return tiles.get(tiles.size()-yC-1).get(xC);
		
	}

   public Coordinate getTileLocation(Tile t)
	{
/**
* Gets a tile and returns the coordinates of the specified tile.
* @param t is the specified tile.
* @return Returns the location of the specified tile in the maze.
*/
		for(int i=0; i<tiles.size(); i++)
			for(int j=0; j<tiles.get(0).size(); j++)
				if(tiles.get(i).get(j) == t)
					return new Coordinate (j, (tiles.size()-i-1));
				return null;
		
	} 

    public List<List<Tile>> getTiles(){
 /**
* Gets the tiles from the maze.
* @return Returns a 2D list of tiles.
*/       
        return tiles;
    }

    private void setEntrance(Tile t) throws InvalidMazeException
	{ 
/**
* Sets as the entrance the specified tile if it is present in the maze.
* @param t is the specified tile.
* @throws java.io.MultipleEntranceException Indicates if there are multiple entrances in the maze.
*/
		 if(entrance!=null)
		 	{throw new MultipleEntranceException("The maze has multiple entrances.");}
		 if(getTileLocation(t)!=null)
                entrance = t;
	}
	private void setExit(Tile t) throws InvalidMazeException
	{
/**
* Sets as the exit the specified tile if it is present in the maze.
* @param t is the specified tile.
* @throws java.io.MultipleExitException Indicates if there are multiple exits in the maze.
*/
		if(exit!=null)
			{throw new MultipleExitException("The maze has multiple exits.");}
		if(getTileLocation(t)!=null)
		exit=t;
	}
    public String toString()
	{
/**
* This method returns the string interpretation of the maze.
* @return Returns a string consisting of ('e', 'x', '.'', '#').
*/
		String helpString="";
		for(int i=0;i<tiles.size();i++)
		{
			for(int j=0;j<tiles.get(0).size();j++)
				{helpString+=tiles.get(i).get(j).toString();
				helpString+=" ";
				}
			helpString+="\n";
		}
		return helpString;

	}

    public class Coordinate
	{	
/**
* This is the coordinate class which contains different methods
* which help us get the x or y coordinate etc.
*/
		private int x, y;
		public Coordinate( int xIn, int yIn){
/**
* The constructor initialises the x and y coordinate .
* @param xIn is the x coordinate.
* @param yIn is the y coordinate.
*/
			x = xIn;
			y = yIn;
		}
		public int getX()
		{

/**
* This method returns the x coordinate in the maze.
* @return Returns an integer representing the x coordinate, which is the column number in the maze.
*/
			return x;
		}

		public int getY()
		{
/**
* This method returns the y coordinate in the maze.
* @return Returns an integer representing the y coordinate,  which is the row number in the maze.
*/
			return y;
		}
		public String toString()
		{
/**
* This method returns the string interpretation of the coordinates.
* @return Returns a string of the form (x,y).
*/
			String helpString;
			helpString = "(" + String.valueOf(x) + ", "+String.valueOf(y)+")";
			return helpString; 
		}
	}
	public enum Direction{
/**
* Contains the four directions in which a tile can go.
*/
		NORTH, SOUTH, EAST, WEST;
	}
}