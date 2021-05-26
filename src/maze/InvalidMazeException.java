package maze;

/**
* @author Alexis-Mihai Costache
*/

public class InvalidMazeException extends Exception
{
/**
* This is the InvalidMazeException class which extends the Exception class.
* It contains the constructor, which will be called when the InvalidMazeException is found.
*/
	public InvalidMazeException(String message)
	{ 
/**
* The constructor sends to the user the message 
* that an invalid character was found the maze.
* @param message is the exception message.
*/
		super(message);
	}
}