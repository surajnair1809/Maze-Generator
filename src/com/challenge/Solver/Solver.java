package com.challenge.Solver;

import com.challenge.Maze.AllNeighborsVisited;
import com.challenge.Maze.Cell;
import com.challenge.Maze.Maze;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solver {
    Cell start;
    Cell target;

    public void solve(Maze maze) {
        this.start = maze.grid[0][0];
        this.target = maze.grid[maze.length - 1][maze.width - 1];
        dfs(maze);
    }

    private void dfs(Maze maze) {
        Stack<Cell> cellStack = new Stack<>();
        cellStack.push(start);
        while (!cellStack.isEmpty()) {
            Cell currentCell = cellStack.pop();
            currentCell.inSolutionPath();

            if (currentCell.equals(target)) break;

            int noOfOpenPaths = currentCell.getNeighborsWithPath().size();
            if (noOfOpenPaths > 1) {
                cellStack.push(currentCell);
            }
            try {
                Cell neighbor = currentCell.getRandomNeighborWithPath();
                cellStack.push(neighbor);
            } catch (AllNeighborsVisited allNeighborsVisited) {

            }
        }
    }

    private void bfs(Maze maze) {

        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty() || target.inSolutionPath) {
            Cell current = queue.remove();
            current.inSolutionPath();
            queue.addAll(current.getNeighborsWithPath());
        }
    }
}
