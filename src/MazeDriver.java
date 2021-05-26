import maze.Maze;
import maze.routing.RouteFinder;
import maze.routing.NoRouteFoundException;
import maze.InvalidMazeException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MazeDriver {
    public static void main(String args[]) throws InvalidMazeException, IOException, NoRouteFoundException {
    		Maze maze =Maze.fromTxt("/home/csimage/GitRepos/comp16412-coursework-2_c20088ac/resources/mazes/maze1.txt");
    		System.out.println(maze.toString());
    		RouteFinder findroute = new RouteFinder(maze);
    		//System.out.println(findroute.getRoute());
    		//System.out.println(findroute.toString());
    		System.out.println(findroute.step());
    		//System.out.println(findroute.getRoute());
    		//System.out.println(findroute.toString());
    		System.out.println(findroute.step());
    		//System.out.println(findroute.getRoute());
    		//System.out.println(findroute.toString());
    		System.out.println(findroute.step());
    		//System.out.println(findroute.getRoute());
    		// System.out.println(findroute.toString());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		System.out.println(findroute.step());
    		

    }
}
