// Name: Ryan Park
// Date: 9/30/21

import java.util.*;
import java.io.*;

public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next()+".txt");
      //Maze m = new Maze();    //extension
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
   public Maze()
   {
      maze = new char[10][10];
      
      startRow = (int)(Math.random() * 10);
      startCol = (int)(Math.random() * 10);
      
      for(int row = 0; row < maze.length; row++)
      {
         for(int column = 0; column < maze[0].length; column++)
         {
            if((int)((Math.random() * 100) % 2) == 0)
               maze[row][column] = WALL;
            else
               maze[row][column] = DOT;            
         }
      }
      
      maze[startRow][startCol] = START;
      
      int endRow = (int)(Math.random() * 10);
      int endCol = (int)(Math.random() * 10);
      if(endRow == startRow && endCol == startCol)
         endRow = (int)(Math.random() * 10);
         
      maze[endRow][endCol] = EXIT;
   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
   public Maze(String filename)    
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
      }
      /* enter your code here */
      
      int row = infile.nextInt();
      int column = infile.nextInt();
      
      maze = new char[row][column];
      for(int y = 0; y < row; y++)
      {
         String line = infile.next();
         for(int x = 0; x < column; x++)
         {
            char character = line.charAt(x);
            maze[y][x] = character;
            
            if(maze[y][x] == START)
            {
               startRow = y;
               startCol = x;
            }
         }
      }   
      System.out.println(startRow);
      System.out.println(startCol);      
      System.out.println(maze[startRow][startCol]);
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
         {
            markAll(startRow, startCol);
            break;
         }
         case 2:
         {
            int count = markAllAndCountRecursions(startRow, startCol);
            System.out.println("Number of recursions = " + count);
            break;
         }
         case 3:
         {
            markTheCorrectPath(startRow, startCol);
            break;
         }
         case 4:         //use mazeNoPath.txt 
         {
            if( !markTheCorrectPath(startRow, startCol) )
               System.out.println("No path exists."); 
            break;
         }
         case 5:
         {
            if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
               System.out.println("No path exists."); 
            break;
         }
         default:
            System.out.println("File not found");   
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {
      if(r == startRow && c == startCol)
         maze[r][c] = DOT;
   
      if(c < 0 || c >= maze[0].length)
      {
      }
      else if(r < 0 || r == maze.length)
      {
      }      
      else if(maze[r][c] != DOT)
      {
      }
      else{
         maze[r][c] = PATH; 
         markAll(r, c-1);
         markAll(r, c+1);
         markAll(r-1, c);
         markAll(r+1, c);
      }
      
      maze[startRow][startCol] = START;
         
   }

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {
   
      if(r == startRow && c == startCol)
         maze[r][c] = DOT;
            
      if(c < 0 || c >= maze[0].length)
      {
         return 1;
      }
      else if(r < 0 || r == maze.length)
      {
         return 1;
      }      
      else if(maze[r][c] != DOT)
      {
         return 1;
      }
      else{
         maze[r][c] = PATH; 
      }
      
      maze[startRow][startCol] = START;
      return 1 + markAllAndCountRecursions(r, c-1) + markAllAndCountRecursions(r, c+1) + 
            markAllAndCountRecursions(r-1, c) + markAllAndCountRecursions(r+1, c);
   }

   /* 
	 * From handout, #3.
	 * Solve the maze, OR the booleans, and mark the path through it with an asterisk
	 * Recur until you find E, then mark the True path.
	 */ 	
   public boolean markTheCorrectPath(int r, int c)
   {
      if(r == startRow && c == startCol)
         maze[r][c] = DOT;
                                       
      if(c < 0 || c >= maze[0].length)
         return false;
      else if(r < 0 || r >= maze.length)
         return false;
      else if(maze[r][c] == WALL)
         return false; 
      else if(maze[r][c] == EXIT)
         return true;
      else if(maze[r][c] == TEMP || maze[r][c] == START)
         return false;
      //another base case when it would equal true
      else{         
         if(maze[r][c] == DOT)
            maze[r][c] = TEMP;
         
      
         if(markTheCorrectPath(r+1,c) || markTheCorrectPath(r-1,c) || markTheCorrectPath(r,c+1) || markTheCorrectPath(r,c-1))
         {
            maze[r][c] = PATH;
            if(r == startRow && c == startCol)
               maze[startRow][startCol] = START;
            return true;
         }
         else
            maze[r][c] = DOT;
         
         maze[startRow][startCol] = START;
         return false;
      }
   }
	
	
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need. */
      

   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      if(r == startRow && c == startCol)
         maze[r][c] = DOT;
                                       
      if(c < 0 || c >= maze[0].length)
      {
         count++;
         return false;
      }
      else if(r < 0 || r >= maze.length)
      {
         count++;
         return false;
      }
      else if(maze[r][c] == WALL)
      {
         count++;
         return false; 
      }
      else if(maze[r][c] == EXIT)
      {
         count++;
         return true;
      }
      else if(maze[r][c] == TEMP || maze[r][c] == START)
      {
         count++;
         return false;
      }
      else{         
         count++;
         if(maze[r][c] == DOT)
            maze[r][c] = TEMP;
         
      
         if(markCorrectPathAndCountSteps(r+1,c,count) || markCorrectPathAndCountSteps(r-1,c,count) || markCorrectPathAndCountSteps(r,c+1,count) || markCorrectPathAndCountSteps(r,c-1, count))
         {
            maze[r][c] = PATH;
            if(r == startRow && c == startCol)
               maze[startRow][startCol] = START;
            return true;
         }
         else
            maze[r][c] = DOT;
         
         maze[startRow][startCol] = START;
         return false;
      }
   }
}

/*****************************************
 
 ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 3
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
     
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 5
 Number of steps = 14
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  ********************************************/