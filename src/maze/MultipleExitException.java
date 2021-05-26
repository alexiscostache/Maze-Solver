package maze;

/**
* @author Alexis-Mihai Costache
*/

public class MultipleExitException extends InvalidMazeException
{
/**
* This is the MultipleExitException class which extends the InvalidMazeException.
* It contains the constructor, which will be called when the MultipleExitException is found.
*/
	public MultipleExitException(String message)
	{
/**
* The constructor sends to the user the message 
* that more than one exit has been found in the maze.
* @param message is the exception message.
*/
		super(message);
	}
}