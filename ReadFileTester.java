/* @author: Jianyang Zhang */

package projectC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadFileTester {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner keyboard = new Scanner(System.in);
		String user = keyboard.nextLine();
		File file = new File(user);
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
				if(charMaze[i][j]== '0'){
					maze[i][j] = false;
				}
				if(charMaze[i][j]== '1'){
		   			maze[i][j] = true;
				}
	    	}
	    }
	    System.out.println(Arrays.deepToString(maze));
	    MazeCoord startLoc = new MazeCoord(in.nextInt()+1, in.nextInt()+1);
	    in.nextLine();
	    MazeCoord endLoc = new MazeCoord(in.nextInt()+1, in.nextInt()+1);
	}
}
