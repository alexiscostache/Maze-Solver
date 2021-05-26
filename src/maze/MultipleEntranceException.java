package maze;

/**
* @author Alexis-Mihai Costache
*/

public class MultipleEntranceException extends InvalidMazeException
{
	/**
* This is the MultipleEntranceException class which extends the InvalidMazeException.
* It contains the constructor, which will be called when the MultipleEntranceException is found.
*/
	public MultipleEntranceException(String message)
	{
/**
* The constructor sends to the user the message 
* that more than one entrance has been found in the maze.
* @param message is the exception message.
*/
		super(message);
	}
}