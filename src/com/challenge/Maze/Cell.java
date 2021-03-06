package com.challenge.Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cell {
    private final Cell[] neighbors;
    public int x, y;
    private Wall[] walls;
    private boolean visited = false;
    public boolean inSolutionPath = false;

    public Cell() {
        walls = new Wall[4];
        for (int i = 0; i < 4; i++) {
            walls[i] = new Wall();
        }
        neighbors = new Cell[4];
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
        List<Cell> available = new ArrayList<>();
        for (Cell neighbor : neighbors) {
            if (!neighbor.isVisited())
                available.add(neighbor);
        }
        if (available.size() > 0) {
            int direction = (int) (Math.random() * available.size());
            return available.get(direction);
        }

        throw new AllNeighborsVisited();
    }


    public boolean isNeighbor(Cell two) {
        for (Cell neighbor : neighbors) {
            if (neighbor.equals(two)) {
                return true;
            }
        }
        return false;
    }

    public int getDirection(Cell two) {
        for (int i = 0; i < 4; i++) {
            if (neighbors[i].equals(two)) {
                return i;
            }
        }
        return -1;
    }

    public void removeWall(int i) {
        this.walls[i].breakWall();
    }

    public boolean hasUnvisitedNeighbors() {
        for (Cell neighbor : neighbors) {
            if (!neighbor.isVisited()) {
                return true;
            }
        }
        return false;
    }

    //For BFS
    public ArrayList<Cell> getNeighborsWithPath() {
        ArrayList<Cell> neighborsWithPath = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Wall wall = walls[i];
            if (!wall.exist() && !neighbors[i].inSolutionPath) {
                neighborsWithPath.add(neighbors[i]);
            }
        }
        return neighborsWithPath;
    }

    //For DFS
    public Cell getRandomNeighborWithPath() throws AllNeighborsVisited {
        /*
        ArrayList<Cell> neighborsWithPath = this.getNeighborsWithPath() ;
        int i = (int)(Math.random() * neighborsWithPath.size());
        return neighborsWithPath.get(i);
        */

        if (!walls[Direction.Right].exist() && !neighbors[Direction.Right].inSolutionPath)
            return neighbors[Direction.Right];
        else if (!walls[Direction.Bottom].exist() && !neighbors[Direction.Bottom].inSolutionPath)
            return neighbors[Direction.Bottom];
        else if (!walls[Direction.Top].exist() && !neighbors[Direction.Top].inSolutionPath)
            return neighbors[Direction.Top];
        else if (!walls[Direction.Left].exist() && !neighbors[Direction.Left].inSolutionPath)
            return neighbors[Direction.Left];
        else
            throw new AllNeighborsVisited();
    }

    public Cell[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Cell[] neighbors) {
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

    public void inSolutionPath(){ this.inSolutionPath = true ; }

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

}
