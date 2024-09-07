/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minesweeper;

/**
 *
 * @author Andrew Larson
 */
public class Grid {
    private int [][] grid;
    
    public Grid(int height, int width)
    {
        grid = new int [height][width];
        randomFillGrid();        
    }
    /* -2 is a non-mine & unrevealed cell, -1 is a mine and unrevealed cell, 0 is blank (revealed cell with no mines adjacent), 
    numbers above zero on a cell, indicate there is that number many of mines adjacent to it(semi-revealed?)
        */

    private void randomFillGrid() 
    {
        int mines = 0;
        int randNum = (int) (Math.random() * 10);
        
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[row].length; col++)
            {
                if(randNum > 5 && mines < 10)
                {
                    grid[row][col] = -1;
                    mines++;
                }else
                {
                    grid[row][col] = -2;
                }
                randNum = (int) (Math.random() * 10);
            }
        }
    }
    public void displayGridData()
    {
       for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[row].length; col++)
            {
                System.out.printf("%2s ",grid[row][col]);
            }
            System.out.println();
        }
    }

    public void displayGrid() 
    {
        System.out.print("  ");
        for(int i = 1; i <= grid[0].length; i++)

        {
            System.out.printf("%2s ", i);
        }
        System.out.println();
        for(int row = 0; row < grid.length; row++)//
        {
            if(row < 9)
            {
                System.out.printf("%-2s ", (row + 1));
            }else
            {
                System.out.printf("%-2s ", (row + 1));
            }
            for(int col = 0; col < grid[row].length; col++)
            {
                if(grid[row][col] == 0)
                {
                    System.out.printf("%-2s ","0 ");
                }else if(grid[row][col] < 0)
                {
                    System.out.printf("%-2s ","* ");
                }else
                {
                    System.out.printf("%-2s ", grid[row][col]);
                }
            }
            System.out.println();
        }
    }
    public int getCell(int curRow, int curCol)
    {
        return grid[curRow][curCol];
    }

    public void setCell(int i, int curRow, int curCol) 
    {
        grid[curRow][curCol] = i;
    }
    
}