import java.io.File;
import maze.routing.RouteFinder;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.*;
import maze.InvalidMazeException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import maze.Maze;
import maze.Tile;
import maze.routing.NoRouteFoundException;
import maze.visualisation.CreateGrid;
/**
* @author Alexis-Mihai Costache
*/

public class MazeApplication extends Application {
/**
* This is the RouteFinder class which contains different methods
* which help us create a user interface.
*/

		private RouteFinder routefinder;
		private GridPane maze;
		private GridPane menu;
		private Button step;

		public static void main(String args[]){
/**
 * The main() method is ignored in correctly deployed JavaFX application.
 * @param args the command line arguments
 */
			launch(args);
		}
		@Override
		public void start(Stage stage)
		{
/**
 * The start() method is called on the JavaFX Application Thread to start the app
 * and here we create the buttons.
 * @param stage is the main window
 */
			FileChooser file = new FileChooser();
			menu=CreateGrid.getGrid();
			maze=CreateGrid.getGrid();
			step = new Button("Step");
			step.setMinSize(200, 100);
			step.setStyle("-fx-font-size:30");
			step.setOnAction(e ->{
				try{
					if(routefinder.isFinished()==false)
					{
						
						
						routefinder.step();
						renderMaze();
					}
					if(routefinder.isFinished()==true){
						Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("CONGRATULATIONS!");
					alert.setContentText("You have solved the maze!");
					alert.show();
					}
				} 
				catch(NoRouteFoundException message){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("NoRouteFoundException");
					alert.setContentText(message.getMessage());
					alert.show();
				}
				catch(NullPointerException message)
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("NullPointerException");
					alert.setContentText("Please load the maze!");
					alert.show();
				}
			});

			Button saveRoute = new Button("Save Route");
			saveRoute.setMinSize(200, 100);
			saveRoute.setStyle("-fx-font-size:30");
			saveRoute.setOnAction(s ->{
			file.setTitle("Choose where to save route");
			file.getExtensionFilters().setAll( new FileChooser.ExtensionFilter("OBJECT", "*.obj"));
			File selectedFile = file.showOpenDialog(stage);
			if(selectedFile != null){
				routefinder.save(selectedFile.getAbsolutePath());
			}

			});

			Button loadRoute = new Button("Load Route");
			loadRoute.setMinSize(200, 100);
			loadRoute.setStyle("-fx-font-size:30");
			loadRoute.setOnAction(l ->{
			file.setTitle("Choose the file to load the route");
			file.getExtensionFilters().setAll( new FileChooser.ExtensionFilter("OBJECT", "*.obj"));
			File selectedFile = file.showOpenDialog(stage);
			if(selectedFile != null){
				routefinder=routefinder.load(selectedFile.getAbsolutePath());
				renderMaze();
				stage.sizeToScene();
			}

			});
			Button loadMap = new Button("Load Map");
			loadMap.setMinSize(200, 100);
			loadMap.setStyle("-fx-font-size:30");
			loadMap.setOnAction(m ->{
			file.setTitle("Choose the maze");
			file.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("TEXT", "*.txt"));
			File selectedFile = file.showOpenDialog(stage);
			if(selectedFile != null){
				try
				{
					routefinder=new RouteFinder(Maze.fromTxt(selectedFile.getAbsolutePath()));
					renderMaze();
					stage.sizeToScene();
			} 
			catch( InvalidMazeException message){
				Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("InvalidMazeException");
					alert.setContentText(message.getMessage());
					alert.show();
			}
			catch(FileNotFoundException message){
				Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("FileNotFoundException");
					alert.setContentText(message.getMessage());
					alert.show();
			}
			catch(IOException message){
				Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("IOException");
					alert.setContentText(message.getMessage());
					alert.show();
			}
			catch(NoRouteFoundException message){
				Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("NoRouteFoundException");
					alert.setContentText(message.getMessage());
					alert.show();
			}
			}

			});
			menu.add(loadMap,0,0);
        	menu.add(loadRoute,0,1);
        	menu.add(saveRoute,0,2);
        	menu.add(step,0,3);
			stage.setTitle("Maze Solver");
			stage.setScene(new Scene(menu));
			stage.show();
		}
		private void renderMaze()
		{
/**
 * This method creates a grid, which is our maze and it is called after every step or when we load the route or a map.
 */
			List<String> symbols = Arrays.asList(routefinder.toString().split("\n"));			
			menu.getChildren().remove(maze);
			maze.getChildren().clear();
			for(int i=0;i<symbols.size();i++)
				for(int j=0; j<symbols.get(i).length();j++)
					if(symbols.get(i).charAt(j)!='#')
						{
						StackPane stack = new StackPane();
						Text tile;
						if(symbols.get(i).charAt(j)=='.') tile=new Text(" ");
						else if(symbols.get(i).charAt(j)=='-') tile= new Text("-");
						else if(symbols.get(i).charAt(j)=='e') tile= new Text("e");
						else if(symbols.get(i).charAt(j)=='x')tile= new Text("x");
						else tile=new Text("*");
						tile.setFont(new Font(20));
						stack.getChildren().addAll(new Rectangle(40.0, 60.0, Color.WHITE),tile);
						maze.add(stack, j, i);}
					else
						maze.add(new Rectangle(40.0, 60.0, Color.GREEN),j,i);		
			menu.add(maze,0,4);
			
		}
}