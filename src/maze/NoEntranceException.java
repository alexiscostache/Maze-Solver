package maze;

/**
* @author Alexis-Mihai Costache
*/

public class NoEntranceException extends InvalidMazeException
{
/**
* This is the NoEntranceException class which extends the InvalidMazeException.
* It contains the constructor, which will be called when the NoEntranceException is found.
*/
	public NoEntranceException(String message)
	{
/**
* The constructor sends to the user the message 
* that no entrance has been found in the maze.
* @param message is the exception message.
*/
		super(message);
	}
}