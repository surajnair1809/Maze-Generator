package com.challenge.Maze;

import java.util.Stack;

public class Maze {
    public  Cell[][] grid ;
    public  int length ;
    public  int width ;

    public Maze(int length , int width) {
        this.length = length;
        this.width = width;
        grid = new Cell[length][width] ;
        initializeGrid();
        grid[0][0].getWalls()[Direction.Left].breakWall();
        grid[length - 1][width - 1].getWalls()[Direction.Right].breakWall();
    }

    private void initializeGrid() {
        for( int i = 0 ; i < length ; i++ ){
            for (int j = 0; j < width ; j++) {
                grid[i][j] = new Cell(i,j) ;
            }
        }
        setNeighbors();
    }

    private void setNeighbors() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                Cell[] neighbors = new Cell[4] ;
                Cell topCell    = i > 0             ? grid[i - 1][j] : new Cell(i - 1, j,true);
                Cell rightCell  = j < width - 1     ? grid[i][j + 1] : new Cell(i,j + 1, true);
                Cell bottomCell = i < length - 1    ? grid[i + 1][j] : new Cell(i + 1, j,true);
                Cell leftCell   = j > 0             ? grid[i][j - 1] : new Cell(i, j - 1,true);

                neighbors[Direction.Top] = topCell ;
                neighbors[Direction.Right] = rightCell ;
                neighbors[Direction.Bottom] = bottomCell ;
                neighbors[Direction.Left] = leftCell ;

                grid[i][j].setNeighbors(neighbors);
            }
        }
    }

    public void generate(){
        generate(0,0);
    }

    private void generate(int i, int j) {

        grid[i][j].Visited(true);
        Stack<Cell> cellStack = new Stack<>();
        cellStack.push(grid[i][j]) ;

        while (!cellStack.isEmpty()){
            Cell current = cellStack.pop();
            if(current.hasUnvisitedNeighbors()){
                cellStack.push(current) ;
                try {
                    Cell neighbor = current.getRandomNeighbor();
                    breakWalls(current , neighbor) ;
                    cellStack.push(neighbor);
                    neighbor.Visited(true);
                } catch (AllNeighborsVisited allNeighborsVisited) {

                }
            }
        }
    }


    public boolean breakWalls(Cell one , Cell two){
        int direction = one.getDirection(two);
        if(direction != -1 ){
            one.removeWall(direction) ;
            two.removeWall((direction + 2)%4) ;
            return true ;
        }
        return false ;
    }
    public void show(){
        for (int i = 0; i < length ; i++) {
            for (int j = 0; j < width ; j++) {
                if( j == 0 ) System.out.print("+");
                if( grid[i][j].getWalls()[Direction.Top].exist() ){
                    System.out.print("----+");
                }else {
                    System.out.print("    +");
                }
            }
            System.out.println();
            for (int j = 0; j < width ; j++) {
                if (j == 0 && grid[i][j].getWalls()[Direction.Left].exist()) {
                    System.out.print("|");
                } else if (j == 0) {
                    System.out.print(" ");
                }
                if (grid[i][j].inSolutionPath) {
                    System.out.print(" O0");
                } else {
                    System.out.print("   ");
                }
                if (grid[i][j].getWalls()[Direction.Right].exist()) {
                    System.out.print(" |");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        for (int j = 0; j < width; j++) {
            if (j == 0) System.out.print("+");
            if (grid[length - 1][j].getWalls()[Direction.Bottom].exist()) {
                System.out.print("----+");
            }
        }

        System.out.println();
    }

}
