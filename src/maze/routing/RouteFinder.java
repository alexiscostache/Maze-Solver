package maze.routing;

import maze.*;
import maze.Maze;
import maze.Maze.Coordinate;
import maze.Maze.Direction;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* @author Alexis-Mihai Costache
*/

public class RouteFinder implements Serializable {
/**
* This is the RouteFinder class which contains different methods
* which help us find the route from the entrance to the exit, if it exists.
*/
	private Maze maze;
	private Stack<Tile> route;
	private boolean finished;
	private List<Tile> visited;

	public RouteFinder(Maze mazeinput) throws NoRouteFoundException{
/**
* Gets the maze and intialises finished to false, creates a list array for the visited tiles, pushes the entrance into the stack.
* @param mazeinput is the maze for which we are going to find a route.
* @return Returns a maze object. 
* @throws java.io.NoRouteFoundException Indicates there is no route from entrance to exit in the maze.
*/
		route = new Stack<Tile>();
		maze=mazeinput;
		finished=false;
		visited = new ArrayList<Tile>();
		route.push(maze.getEntrance());	
	}
	public Maze getMaze(){
/**
* Gets the maze and returns it.
* @return Returns the maze.
*/
		return maze;
	}
	public List<Tile> getRoute(){
		List<Tile> routeList = new ArrayList<Tile>();
		//Stack<Tile> routeTemp = route;
		while(route.size()!=0)
				routeList.add(route.pop());
		Collections.reverse(routeList);
        for(int i=0;i<routeList.size();i++)
            route.push(routeList.get(i));
		return routeList;
	}

    public List<Tile> getVisited(){
/**
* This method returns the visited tiles in the maze.
* @return Returns the visited list.
*/
        return visited;
    }

	public boolean isFinished(){
/**
* This method returns finished, which is true when we found the route in the maze or false otherwise. 
* @return Returns the finished attribute.
*/
		return finished;
	}

	public static RouteFinder load(String filename){
/**
* Gets the name of a text file and returns the data from that file.
* @param filename is the text file from where we get the data.
* @return Returns a RouteFinder object.
* @throws java.io.IOException Indicates a problem when reading or closing filename.
* @throws java.io.FileNotFoundException Indicates a problem with reading filename.
*/
		ObjectInputStream objectStream = null;
        try {
            objectStream = new ObjectInputStream(new FileInputStream(filename));
            return (RouteFinder)objectStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not read " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: problem when reading "+ filename);
        } finally {
            try {
                objectStream.close();
            } catch (IOException e) {
                System.out.println("Error: IOException when closing "+ filename);
            }
        }
        return null;
	}

	public void save(String filename){
/**
* Gets the name of a text file and saves the data to that file.
* @param filename is the text file where we put the data.
* @return Returns a RouteFinder object.
* @throws java.io.IOException Indicates a problem when writing or closing filename.
* @throws java.io.FileNotFoundException Indicates a problem when trying to open the filename in write mode.
*/
		ObjectOutputStream objectStream = null;
        try {
            objectStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectStream.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + filename + " for writing.");
        } catch (IOException e) {
            System.out.println("Error: IOException when writing "+ filename);
        } finally {
            try {
                objectStream.close();
            } catch (IOException e) {
                System.out.println("Error: IOException when closing "+ filename);
            }
        }
    }
	

public boolean step() throws NoRouteFoundException{
/**
* This method verifies which tile next to the last tile traveled are navigable and determines this way the route to the exit of the maze.
* @return Returns true if the route is found or false otherwise. 
* @throws java.io.NoRouteFoundException Indicates that there is no route from entrance to exit in the maze.
*/
        if(route.size()==0) 
            {throw new NoRouteFoundException("No route has been found."); }

        Tile traveled = route.peek();
        Tile left = maze.getAdjacentTile(traveled, Direction.WEST);
        Tile right =  maze.getAdjacentTile(traveled, Direction.EAST);
        Tile down = maze.getAdjacentTile(traveled, Direction.SOUTH);
        Tile up = maze.getAdjacentTile(traveled, Direction.NORTH);

        visited.add(traveled);
        if(left != null && visited.contains(left)==false && left.isNavigable())
        {   
        	route.push(left);
            if(left.equals(maze.getExit()))
        {
            finished = true;
            return true;
        }
            return false;
        }  
        if(right != null && visited.contains(right)==false && right.isNavigable())
        {
            route.push(right);
              if(right.equals(maze.getExit()))
        {
            finished = true;
            return true;
        }
            return false;
        }

        if(up != null && visited.contains(up)==false && up.isNavigable())
        {
            route.push(up);
        if(up.equals(maze.getExit()))
        {
            finished = true;
            return true;
        }
            return false;
        }
         if(down != null && visited.contains(down)==false && down.isNavigable())
        {
            route.push(down);
        if(down.equals(maze.getExit()))
        {
            finished = true;
            return true;
        }
            return false;
        }
         
        route.pop();  
        return false;
    }
	public String toString(){
/**
* This method returns the string interpretation of the maze and the route.
* @return Returns a string consisting of ('e', 'x', '.'', '#'), '*' for the found route and '-' for the tiles which are a dead end.
*/
		String helpString="";
		List<Tile> routeList = getRoute();
        List<Tile> visitedList= getVisited();
		for(int i=0;i<getMaze().getTiles().size();i++)
			{for(int j=0;j<getMaze().getTiles().get(i).size();j++)
				{
					if (routeList.contains(getMaze().getTiles().get(i).get(j)))
						helpString+="*";
                    else if(routeList.contains(getMaze().getTiles().get(i).get(j))==false && visitedList.contains(getMaze().getTiles().get(i).get(j))==true)
                        helpString+="-";
					else helpString+=getMaze().getTiles().get(i).get(j).toString();
					//helpString+=" ";
				}
				helpString+="\n";
	}
	return helpString;
	
 }
}