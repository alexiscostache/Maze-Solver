package maze;

/**
* @author Alexis-Mihai Costache
*/

public class RaggedMazeException extends InvalidMazeException
{
/**
* This is the RaggedMazeException class which extends the InvalidMazeException.
* It contains the constructor, which will be called when the RaggedMazeException is found.
*/
	public RaggedMazeException(String message)
	{
/**
* The constructor sends to the user the message 
* that the maze is ragged.
* @param message is the exception message.
*/
		super(message);
	}
}