// Name: Ryan Park   
// Date: 10/7/21

import java.util.*;
import java.io.*;
import java.io.File;

public class MazeGrandMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next());
      // Maze m = new Maze();    
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Find the shortest path\n\tIf no path exists, say so.");
      System.out.println("2: Mark only the shortest correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
   } 
}

class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //Instance Fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This no a arg constructor that generates a random maze
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
            if(maze[r][c] == START)      //identify start
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
         infile = new Scanner(new File(filename + ".txt"));
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
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n)
   {
      switch(n)
      {    
         case 1:
         {   
            int shortestPath = findShortestLengthPath(startRow, startCol);
            if( shortestPath!=-1 )
               System.out.println("Shortest path is " + shortestPath);
            else
               System.out.println("No path exists."); 
            break;
         }   
            
         case 2:
         {
            String strShortestPath = findShortestPath(startRow, startCol);
         //    System.out.println(strShortestPath);
            if( strShortestPath.length()!=0 )
            {
               System.out.println("Sortest lenght path is: " + getPathLength(strShortestPath));
               System.out.println("  Sortest path is: " + strShortestPath);
               markPath(strShortestPath);
               display();  //display solved maze
            }
            else
               System.out.println("No path exists."); 
            break;
         }
         default:
            System.out.println("File not found");   
      }
   }
   
 /*  1   recur until you find E, then return the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */ 
   public int findMin(int up, int down, int left, int right)
   {
      int min = -1;
      
      if(right != -1)
      {
         if(min == -1)
            min = right;
         else
            min = Math.min(right, min);
      }
               
      if(left != -1)
      {
         if(min == -1)
            min = left;
         else
            min = Math.min(left, min);
      }
               
      if(up != -1)
      {
         if(min == -1)
            min = up;
         else
            min = Math.min(up, min);
      }
               
      if(down != -1)
      {
         if(min == -1)
            min = down;
         else
            min = Math.min(down, min);
      }
      
      return min;
   }
 
   public int findShortestLengthPath(int r, int c)
   {
   
      //this method finds the shortest length 
                                        
      if(c < 0 || c >= maze[0].length || r < 0 || r >= maze.length)
         return -1;
      else if(maze[r][c] == WALL || maze[r][c] == 'O')
         return -1;
      else if(maze[r][c] == EXIT)
         return 0;
      
      if(maze[r][c] == START || maze[r][c] == DOT)
      {
         if(maze[r][c] == DOT)
            maze[r][c] = 'O';
      
         int right = findShortestLengthPath(r,c+1);
         int left = findShortestLengthPath(r,c-1);
         int up = findShortestLengthPath(r+1,c);
         int down = findShortestLengthPath(r-1,c);
      
         if(maze[r][c] != START)
         {
            maze[r][c] = DOT;
            if(right != -1 || left != -1 || up != -1 || down != -1)
               return 1 + findMin(up, down, left, right);
            else 
               return -1;
         }
         else{
            int path = findMin(up, down, left, right);
            if(path != -1)
               return path + 1;
            else
               return -1;
         }
      }
      
      return -1;
   }
   
   
   public int getPathLength(String str)
   {
      if(str.length() == 0)
         return -1;
   
      int end = str.indexOf(")", str.indexOf(")") + 1);
      int start = str.indexOf(",", str.indexOf(",") + 1);
      return Integer.parseInt(str.substring(start + 1, end));
   }  
   
/*  2   recur until you find E, then build the True path 
     use only the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */
   public String findShortestPath(int r, int c)
   {
      
      if(c < 0 || c >= maze[0].length || r < 0 || r >= maze.length)
         return "";
      else if(maze[r][c] == WALL || maze[r][c] == 'O')
         return "";
      else if(maze[r][c] == EXIT)
         return "((" + r + "," + c + ")," + 0 + ")"; 
      
      if(maze[r][c] == START || maze[r][c] == DOT)
      {
         if(maze[r][c] == DOT)
            maze[r][c] = 'O';
      
         String strRight = findShortestPath(r,c+1);
         String strLeft = findShortestPath(r,c-1);
         String strUp = findShortestPath(r+1,c);
         String strDown = findShortestPath(r-1,c);
         int lenRight = getPathLength(strRight);
         int lenLeft = getPathLength(strLeft);
         int lenUp = getPathLength(strUp);
         int lenDown = getPathLength(strDown);
         
         if(maze[r][c] != START)
         {
            maze[r][c] = DOT;
            
            if(lenRight != -1 || lenLeft != -1 || lenUp != -1 || lenDown != -1)
            {
               int min = findMin(lenUp, lenDown, lenLeft, lenRight);
               if(min == lenUp)
                  return "((" + r + "," + c + ")," + (min + 1) + ")," + strUp; 
               if(min == lenDown)
                  return "((" + r + "," + c + ")," + (min + 1) + ")," + strDown; 
               if(min == lenLeft)
                  return "((" + r + "," + c + ")," + (min + 1) + ")," + strLeft; 
               if(min == lenRight)
                  return "((" + r + "," + c + ")," + (min + 1) + ")," + strRight;
            }
            else 
               return "";
         }
         else
         {
            int path = findMin(lenUp, lenDown, lenLeft, lenRight);
            if(path != -1)
            {
               if(path == lenUp)
                  return "((" + r + "," + c + ")," + (path + 1) + ")," + strUp; 
                  
               if(path == lenDown)
                  return "((" + r + "," + c + ")," + (path + 1) + ")," + strDown; 
               
               if(path == lenLeft)
                  return "((" + r + "," + c + ")," + (path + 1) + ")," + strLeft; 
               
               if(path == lenRight)
                  return "((" + r + "," + c + ")," + (path + 1) + ")," + strRight;               
            }
            else
               return "";
              
         }
      }
     
      
      return ""; //replace this with something useful
   }	

   //a recursive method that takes an argument created by the method 2 in the form of 
   //((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
   //and it marks the actual path in the maze with STEP
   //precondition:   the String is either an empty String or one that has the correct format above
   //                the indexes must be correct for this method to work  
   public void markPath(String strPath)
   {
      if(strPath != "")
      {
         String row = strPath.substring(strPath.indexOf("(", strPath.indexOf("(") + 1) + 1, strPath.indexOf(","));
         String col = strPath.substring(strPath.indexOf(",") + 1, strPath.indexOf(")", strPath.indexOf(")")));
         int r = Integer.parseInt(row);
         int c = Integer.parseInt(col);
         if(maze[r][c] != START && maze[r][c] != EXIT)
            maze[r][c] = STEP;
         strPath = strPath.substring(strPath.indexOf(")", strPath.indexOf(")") + 1));
         if(strPath.length() != 1)
            markPath(strPath.substring(2));
      }
   }
}

 // Enter the maze's filename (no .txt): maze0
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW.E
 // S.W.WW.W
 // W......W
 // WWWWWWWW
 // 
 // Options: 
 // 1: Find the shortest path
 // 	If no path exists, say so.
 // 2: Mark only the shortest correct path and display the count of STEPs.
 // 	If no path exists, say so.
 // Please make a selection: 2
 // Sortest lenght path is: 10
 //   Sortest path is: ((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW*E
 // S*W.WW*W
 // W******W
 // WWWWWWWW