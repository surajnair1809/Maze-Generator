package com.challenge.Maze;

import java.util.*;

public class Cell {
    public int x , y ;
    private Cell [] neighbors  ;
    private Wall[] walls ;
    private boolean visited = false ;
    public boolean inSolutionPath = false ;

    public Cell() {
        walls = new Wall[4];
        for (int i = 0; i < 4; i++) {
            walls[i] = new Wall();
        }
        neighbors = new Cell[4] ;
    }

    public Cell( int x , int y ){
        this();
        this.x = x ;
        this.y = y ;
    }

    public Cell( int x , int y , boolean visited){
        this(x,y);
        this.visited = visited ;
    }
    public Cell getRandomNeighbor() throws AllNeighborsVisited {
//        System.out.println(this.toString() + "----getRandomNeighbor----");
        List<Cell> available = new ArrayList<>();
        for( Cell neighbor : neighbors ){
            if( !neighbor.isVisited() )
                available.add(neighbor) ;
        }
        if(available.size() > 0 ){
            int direction = (int)(Math.random() * available.size());
//            System.out.println("----size----" + available.size());
//            System.out.println("----direction----" + direction);
            return available.get(direction) ;
        }

        throw new AllNeighborsVisited() ;
    }

    public Cell [] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Cell [] neighbors) {
        System.arraycopy(neighbors, 0, this.neighbors, 0, 4);
    }

    public Wall[] getWalls() {
        return walls;
    }

    public void setWalls(Wall[] walls) {
        this.walls = walls;
    }

    public boolean isVisited() {
        return visited;
    }

    public void Visited(boolean visited) {
        this.visited = visited;
    }

    public boolean isInSolutionPath(){ return this.inSolutionPath ; }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
//                ", neighbors=" + Arrays.toString(neighbors) +
                ", visited=" + visited +
                '}';
    }

    public String toStringWithNeighbors() {
        return this.toString() + ", neighbors=" + Arrays.toString(neighbors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y &&
                isVisited() == cell.isVisited() &&
                Arrays.equals(getNeighbors(), cell.getNeighbors()) ;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y, isVisited());
        result = 31 * result + Arrays.hashCode(getNeighbors());
        result = 31 * result + Arrays.hashCode(getWalls());
        return result;
    }


    public boolean isNeighbor(Cell two) {
        for( Cell neighbor : neighbors ){
            if( neighbor.equals(two) ){
                return true ;
            }
        }
        return false ;
    }

    public int getDirection(Cell two) {
        for (int i = 0; i < 4 ; i++) {
            if( neighbors[i].equals(two)){
                return i ;
            }
        }
        return  - 1;
    }

    public void removeWall(int i) {
        this.walls[i].breakWall();
    }

    public boolean hasUnvisitedNeighbors() {
        for( Cell neighbor : neighbors ){
            if( !neighbor.isVisited() ){
                return true ;
            }
        }
        return false ;
    }
}
