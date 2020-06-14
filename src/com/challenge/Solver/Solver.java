package com.challenge.Solver;

import com.challenge.Maze.Cell;
import com.challenge.Maze.Maze;

import java.util.LinkedList;
import java.util.Queue;

public class Solver {
    Cell start ;
    Cell target ;
    public void solve(Maze maze, Cell start, Cell target){
        this.start = maze.grid[0][0] ;
        this.target = maze.grid[maze.length - 1][maze.width - 1] ;
        bfs(maze);
    }

    private void bfs(Maze maze) {

        Queue<Cell> queue = new LinkedList<>();
        queue.add(start) ;
        while ( !queue.isEmpty() || target.inSolutionPath ){
            Cell current = queue.remove();
            current.inSolutionPath();
            queue.addAll(current.getNeighborsWithPath());
        }
    }
}
