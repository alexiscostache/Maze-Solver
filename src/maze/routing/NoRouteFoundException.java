package maze.routing;

/**
* @author Alexis-Mihai Costache
*/

public class NoRouteFoundException extends Exception
{
/**
* This is the NoRouteFoundException class which extends the Exception class.
* It contains the constructor, which will be called when the NoRouteFoundException is found.
*/
	public NoRouteFoundException(String message)
	{ 
/**
* The constructor sends to the user the message 
* that no route was found from the entrance to the exit of the maze.
* @param message is the exception message.
*/
		super(message);
	}
}