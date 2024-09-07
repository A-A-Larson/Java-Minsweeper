package minesweeper;

import java.util.Scanner;

/**
 * @author Andrew Larson
 */
public class Game 
{
    private Grid g;
    Scanner sc;
    int height = 0;
    int width = 0;
    int revealedBlanks = 0;
    int nonMinedCells = 0;
    
    public void start()
    {
        sc = new Scanner(System.in);
        System.out.print("Enter the height (x): ");
        height = sc.nextInt();
        System.out.print("Enter the width (y): ");
        width = sc.nextInt();
        
        g = new Grid(height, width);
        nonMinedCells = (height * width) - 10;
        gameLoop();
    }
    public void gameLoop()
    {
        g.displayGrid();
        System.out.println();
        System.out.println("Pick location to check for mines:\n"
                + "First enter the row, then the column: \n");
        int curRow = (sc.nextInt()-1);
        int curCol = (sc.nextInt()-1);
        System.out.println();
        if (checkLocation(curRow, curCol))
        {
            System.out.println("BOOM! You stepped on a mine! You Lose!");
            System.exit(0);
        }else 
            
            while (revealedBlanks == nonMinedCells)
            {
            System.out.print("You Win!");
            System.exit(0);
            }
            gameLoop();
            
    }
    
    public boolean checkLocation(int curRow, int curCol)
    {
        int cellValue = g.getCell(curRow, curCol);
        if(cellValue == -1)
        {
            return true;
        }else
        {
            clearBlanks(curRow, curCol);
            return false;
        }
    }
    
    public void clearBlanks(int curRow, int curCol)
    {
        /*Check surrounding cells for mines, if there is 1 or more, sets the number
        of mines in that cell (displays it) and quit
        */        
        
        int mineCount = numberOfMines(curRow, curCol);
        
        if(mineCount > 0)
        {
            //set number of mines adjacent to that cell, in that cell
            revealedBlanks++;
            g.setCell(mineCount, curRow, curCol);
            
            
        }else
        {
            //set current cell to blank
            g.setCell(0, curRow, curCol);
            revealedBlanks++;
            
            //check neighbor cells
            for(int curCell = 1; curCell <= 8; curCell++)
            {
                switch(curCell)
                {
                    case 1://diagonal upper left
                        if(curRow != 0 && curCol !=0)
                        {
                            if(g.getCell(curRow -1, curCol -1) < 0) 
                            {
                                clearBlanks(curRow -1, curCol -1);
                            }
                        }
                        break;
                    case 2://top, right above
                        if(curRow != 0)
                        {
                            if(g.getCell(curRow -1, curCol) < 0)
                            {
                                clearBlanks(curRow -1, curCol);
                            }
                        }
                        break;
                    case 3://diagonal upper right
                        if(curRow != 0 && curCol != width -1)
                        {
                            if(g.getCell(curRow -1, curCol +1) < 0)
                            {
                                clearBlanks(curRow -1, curCol +1);
                            }
                        }
                        break;
                    case 4://right, next to the cell
                        if(curCol != width -1)
                        {
                            if(g.getCell(curRow, curCol +1) < 0)
                            {
                                clearBlanks(curRow, curCol +1);
                            }
                        }
                        break;
                    case 5://diagonal lower right
                        if(curRow != height -1 && curCol != width -1)
                        {
                            if(g.getCell(curRow + 1, curCol +1) < 0)
                            {
                                clearBlanks(curRow +1, curCol +1);
                            }
                        }
                        break;
                    case 6://below, right underneath
                        if(curRow != height -1)
                        {
                            if(g.getCell(curRow +1, curCol) < 0 )
                            {
                                clearBlanks(curRow +1, curCol);
                            }
                        }
                        break;
                    case 7://diagonal lower left
                        if(curRow != height -1 && curCol != 0)
                        {
                            if(g.getCell(curRow +1, curCol -1) < 0)
                            {
                                clearBlanks(curRow +1, curCol -1);
                            }
                        }
                        break;
                    case 8://left, next to the cell
                        if(curCol != 0)
                        {
                            if(g.getCell(curRow, curCol -1) < 0)
                            {
                                clearBlanks(curRow, curCol -1);
                            }
                        }
                        break;
                }
            }
        }
    }
    
    public int numberOfMines(int curRow, int curCol)
    {
        int numMines = 0;
        
        if(curRow != 0 && curCol != 0)//(check cell in top-left corner) can't be top row or first column
        {
            if(g.getCell(curRow - 1, curCol - 1) == -1)
            {
                numMines++;
            }
        }
        if(curRow !=0)//(check cell above) can't be top row
        {
            if(g.getCell(curRow -1 , curCol) == -1)
            {
                numMines++;
            }
        }
        if(curRow !=0 && curCol != width -1)//(check cell in top-right corner) can't be top row or last column
        {
            if(g.getCell(curRow -1, curCol +1) == -1)
            {
                numMines++;
            }
        }
        if(curCol != width -1)//(check cell to the right) can't be the last column
        {
            if(g.getCell(curRow, curCol +1) == -1)
            {
                numMines++;
            }
        }
        if(curRow != height -1 && curCol != width -1)//(check cell in bottom right corner) can't be last row or last column
        {
            if(g.getCell(curRow +1, curCol +1) == -1)
            {
                numMines++;
            }
        }
        if(curRow != height -1)//(check cell below) can't be last row
        {
            if(g.getCell(curRow +1, curCol) == -1)
            {
                numMines++;
            }
        }
        if(curRow != height -1 && curCol != 0)//(check cell in bottom left corner) can't be last row or first column
        {
            if(g.getCell(curRow +1, curCol -1) == -1)
            {
                numMines++;
            }
        }
        if(curCol != 0)//(check cell to the left) can't be first column
        {
            if(g.getCell(curRow, curCol -1) == -1)
            {
                numMines++;
            }
        }       
        
        return numMines;
    }
       
}
