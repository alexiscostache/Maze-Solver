package maze;

/**
* @author Alexis-Mihai Costache
*/

public class NoExitException extends InvalidMazeException
{
		/**
* This is the NoExitException class which extends the InvalidMazeException.
* It contains the constructor, which will be called when the NoExitException is found.
*/
	public NoExitException(String message)
	{
/**
* The constructor sends to the user the message 
* no exit has been found in the maze.
* @param message is the exception message.
*/
		super(message);
	}
}