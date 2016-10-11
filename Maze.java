/* @author: Jianyang Zhang */

package projectC;

import java.util.LinkedList;
import java.util.ListIterator;


/**
Maze class

Stores information about a maze and can find a path through the maze
(if there is one).

Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
(parameters to constructor), and the path:
  -- no outer walls given in mazeData -- search assumes there is a virtual 
     border around the maze (i.e., the maze path can't go outside of the maze
     boundaries)
  -- start location for a path is maze coordinate startLoc
  -- exit location is maze coordinate exitLoc
  -- mazeData input is a 2D array of booleans, where true means there is a wall
     at that location, and false means there isn't (see public FREE / WALL 
     constants below) 
  -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
  -- only travel in 4 compass directions (no diagonal paths)
  -- can't travel through walls
*/

public class Maze{
public static final boolean FREE = false;
public static final boolean WALL = true;

/**
   Constructs a maze.
   @param mazeData the maze to search.  See general Maze comments for what
   goes in this array.
   @param startLoc the location in maze to start the search (not necessarily on an edge)
   @param endLoc the "exit" location of the maze (not necessarily on an edge)
   PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
      and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

 */
private final boolean[][] mazeData;
private final MazeCoord startLoc;
private final MazeCoord endLoc;
private final int numRows;
private final int numCols;
public boolean[][] visited; // records the locations that has been visited
private LinkedList<MazeCoord> reversedPath; // coordinates from end location to start location
private LinkedList<MazeCoord> path; // coordinates from start location to end location
private boolean hasSearched; //records if the the user has searched the path

public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc){
	this.mazeData = mazeData;
	this.startLoc = startLoc;
	this.endLoc = endLoc;
	numRows = mazeData.length;
	numCols = mazeData[0].length;
    visited = new boolean[numRows][numCols];
    reversedPath = new LinkedList<MazeCoord>();
    path = new LinkedList<MazeCoord>();
    hasSearched = false;
}

/**
Returns the number of rows in the maze
@return number of rows
*/
public int numRows(){
	return numRows;  
}


/**
Returns the number of columns in the maze
@return number of columns
*/   
public int numCols(){
	return numCols;  
} 


/**
   Returns true if there is a wall at this location
   @param loc the location in maze coordinates
   @return whether there is a wall here
   PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
*/
public boolean hasWallAt(MazeCoord loc){   
	return mazeData[loc.getRow()-1][loc.getCol()-1];  
}


/**
   Returns the entry location of this maze.
 */
public MazeCoord getEntryLoc(){
	return startLoc;   
}


/**
Returns the exit location of this maze.
*/
public MazeCoord getExitLoc() {
	return endLoc;  
}


/**
   Returns the path through the maze. First element is starting location, and
   last element is exit location.  If there was not path, or if this is called
   before search, returns empty list.

   @return the maze path
 */
public LinkedList<MazeCoord> getPath(){
	return path;		
}


/**
   Find a path through the maze if there is one.  Client can access the
   path found via getPath method.
   @return whether path was found.
 */
public boolean search(){
	if(hasSearched == true){ //if the user has searched the path, return the result
		if(path.size()==0){
			return false;
		}else{
			return true;
		}
	}else{
		hasSearched = true;	// the real search process only run once
		// check if the start location or the end location is wall
		if(hasWallAt(startLoc)||hasWallAt(endLoc)){ 
			return false;
		}	
		//setup the "visited" 2D array
		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				visited[row][col] = false;
			}
		}		
		// do the search
		boolean searchResult = search(startLoc);
		path = reverseIt(reversedPath);
		return searchResult;
	}
}
/**
The helper routine which does the actual recursion.
*/
private boolean search(MazeCoord flag){	
	// check if this location has been visited or is wall 
	if(visited[flag.getRow()-1][flag.getCol()-1]||hasWallAt(flag)){ 
		return false;
	}	
	// mark this location as visited
	visited[flag.getRow()-1][flag.getCol()-1] = true;		
	// check if it has reached the end location
	if (flag.equals(endLoc)){		
		reversedPath.add(flag);
		return true;
	}	
	// take one step to the left if it won't be out of bound
	if(flag.getCol()> 1){
		if(search(new MazeCoord(flag.getRow(),flag.getCol()-1))){			
			reversedPath.add(flag);
			return true;
		}		
	}
	// take one step to the right if it won't be out of bound
	if(flag.getCol()< numCols){
		if(search(new MazeCoord(flag.getRow(),flag.getCol()+1))){			
			reversedPath.add(flag);
			return true;
		}		
	}
	// go up one step if it won't be out of bound
	if(flag.getRow()> 1){
		if(search(new MazeCoord(flag.getRow()-1,flag.getCol()))){			
			reversedPath.add(flag);
			return true;
		}		
	}
	// go down one step if it won't be out of bound
	if(flag.getRow()< numRows){
		if(search(new MazeCoord(flag.getRow()+1,flag.getCol()))){		
			reversedPath.add(flag);
			return true;
		}		
	}	
	return false;	
}
/**
 Return an Reversed LinkedList without modifying the original one
 */
private LinkedList<MazeCoord> reverseIt(LinkedList<MazeCoord> origin){
	LinkedList<MazeCoord> input = new LinkedList<MazeCoord>(origin);
	LinkedList<MazeCoord> output = new LinkedList<MazeCoord>();
	ListIterator<MazeCoord> iter = input.listIterator(input.size()); 
	while (iter.hasPrevious()) {
		output.add(iter.previous());
		iter.next();
		iter.remove();
	}	
	return output;	
}
}