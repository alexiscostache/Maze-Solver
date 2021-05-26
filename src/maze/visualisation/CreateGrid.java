package maze.visualisation;

import javafx.scene.layout.GridPane;
/**
* @author Alexis-Mihai Costache
*/

public class CreateGrid{
/**
* This is the Creategrid class which helps us create a new grid.
*/
	public CreateGrid(){
/**
* empty constructor
*/
	}
	public static GridPane getGrid(){
/**
* Creates a new grid and returns it.
* @return Returns the new grid.
*/
		GridPane grd = new GridPane();
		return grd;
	}
}