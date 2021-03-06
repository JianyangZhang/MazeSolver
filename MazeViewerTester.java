/* @author: Jianyang Zhang */

package projectC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;


/**
* MazeViewer class
* 
* Program to read in and display a maze and a path through the maze. At user
* command displays a path through the maze if there is one.
* 
* How to call it from the command line:
* 
*      java MazeViewer mazeFile
* 
* where mazeFile is a text file of the maze. The format is the number of rows
* and number of columns, followed by one line per row, followed by the start location, 
* and ending with the exit location. Each maze location is
* either a wall (1) or free (0). Here is an example of contents of a file for
* a 3x4 maze, with start location as the top left, and exit location as the bottom right
* (we count locations from 0, similar to Java arrays):
* 
* 3 4 
* 0111
* 0000
* 1110
* 0 0
* 2 3
* 
*/

public class MazeViewerTester {

private static final char WALL_CHAR = '1';
private static final char FREE_CHAR = '0';

public static void main(String[] args)  {

   String fileName = "testmaze";

   try {

      if (fileName.length() < 1) {
         System.out.println("ERROR: missing file name command line argument");
      }
      else {
         
         JFrame frame = readMazeFile(fileName);

         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         frame.setVisible(true);
      }

   }
   catch (FileNotFoundException exc) {
      System.out.println("File not found: " + fileName);
   }
   catch (IOException exc) {
      exc.printStackTrace();
   }
}

/**
 readMazeFile reads in maze from the file whose name is given and 
 returns a MazeFrame created from it.

@param fileName
          the name of a file to read from (file format shown in class comments, above)
@returns a MazeFrame containing the data from the file.
     
@throws FileNotFoundException
           if there's no such file (subclass of IOException)
@throws IOException
           (hook given in case you want to do more error-checking.
            that would also involve changing main to catch other exceptions)
*/
private static MazeFrame readMazeFile(String fileName) throws IOException {
	File file = new File(fileName);
	Scanner in = new Scanner(file);
	int row = in.nextInt();
	int col = in.nextInt();	
	char[][] charMaze = new char[row][col];
	String[] line = new String[row];
	in.nextLine();	
	for(int i = 0; i < row; i++){
		line[i] = in.nextLine();
		char[] location = line[i].toCharArray();
		for(int j = 0; j < col; j++){
   		charMaze[i][j] = location[j];
		}	    
	}
	boolean[][] maze = new boolean[row][col];
	for(int i = 0; i < row; i++){
		for(int j = 0; j < col; j++){
			if(charMaze[i][j]== FREE_CHAR){
				maze[i][j] = false;
			}
			if(charMaze[i][j]== WALL_CHAR){
	   			maze[i][j] = true;
			}
		}
	}
	MazeCoord startLoc = new MazeCoord(in.nextInt()+1, in.nextInt()+1);
	in.nextLine();
	MazeCoord endLoc = new MazeCoord(in.nextInt()+1, in.nextInt()+1);
	return new MazeFrame(maze, startLoc, endLoc);
}
}