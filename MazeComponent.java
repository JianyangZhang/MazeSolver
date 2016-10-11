/* @author: Jianyang Zhang */

package projectC;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box   
   private MazeCoord entry;
   private MazeCoord exit;
   private int numRows;
   private int numCols;
   private LinkedList<MazeCoord> pathR;
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) {   
	   this.maze = maze;
	   entry = maze.getEntryLoc();
	   exit = maze.getExitLoc();
	   numRows = maze.numRows();
	   numCols = maze.numCols();
	   pathR = maze.getPath();
   }
   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g){
	   Graphics2D g2 = (Graphics2D)g;	     	   
	   // draw the white background and the black rim
	   g2.setColor(Color.white);
	   g2.fillRect(START_X, START_Y, numCols*BOX_WIDTH, numRows*BOX_HEIGHT);
	   g2.setColor(Color.black);
	   g2.drawRect(START_X, START_Y, numCols*BOX_WIDTH, numRows*BOX_HEIGHT);
	   // draw the maze body
	   for(int i = 0; i < numRows; i++){
		   for(int j = 0; j < numCols; j++){
			   if(maze.hasWallAt(new MazeCoord(i+1,j+1))){
			   g2.fillRect(START_X + j*BOX_WIDTH, START_Y + i*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
			   }
		   }
	   }
	   // draw the entry and exit location
	   g2.setColor(Color.yellow);
	   g2.fillRect(START_X + (entry.getCol()-1)*BOX_WIDTH+INSET, START_Y + (entry.getRow()-1)*BOX_HEIGHT+INSET, (BOX_WIDTH-2*INSET), (BOX_HEIGHT-2*INSET));
	   g2.setColor(Color.green);
	   g2.fillRect(START_X + (exit.getCol()-1)*BOX_WIDTH+INSET, START_Y + (exit.getRow()-1)*BOX_HEIGHT+INSET, (BOX_WIDTH-2*INSET), (BOX_HEIGHT-2*INSET));
	   
	   // draw the path
	   g2.setColor(Color.blue);
	   if(maze.getPath().size()!=0){
		   ListIterator<MazeCoord> iter = maze.getPath().listIterator(); 
		   while(iter.hasNext()){
		   MazeCoord flag1 = iter.next();
		   MazeCoord flag2 = iter.next();
		   int HALF = 10; 
		   g.drawLine(START_X+flag1.getCol()*BOX_WIDTH-HALF,START_Y+flag1.getRow()*BOX_HEIGHT-HALF,START_X+flag2.getCol()*BOX_WIDTH-HALF,START_Y+flag2.getRow()*BOX_HEIGHT-HALF);
		   if(iter.hasNext()){
			   iter.previous();
		   }
		   }
	   }

   }   
}


