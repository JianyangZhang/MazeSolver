/* @author: Jianyang Zhang */

package projectC;

import java.util.Arrays;

public class MazeTester {
	public static void main(String[] args){
		boolean[][] mazeData = new boolean[][]{
			{false,true,true,true},
			{false,false,false,false},
			{true,true,true,false}
		 };
		MazeCoord entry = new MazeCoord(1,1);
		MazeCoord exit = new MazeCoord(3,4);
		Maze test = new Maze(mazeData,entry,exit);
		System.out.println(test.getEntryLoc().toString());
		System.out.println(test.getExitLoc().toString());
		System.out.println(test.getPath().toString());	
	}
}
