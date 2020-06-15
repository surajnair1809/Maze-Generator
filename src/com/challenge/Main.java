package com.challenge;

import com.challenge.Maze.Maze;
import com.challenge.Solver.Solver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Maze maze = new Maze(10, 10);
        maze.generate();
        maze.show();

        System.out.println("Solve?( Y | N ) : ");
        String decision = scanner.next();
        switch (decision) {
            case "Y":
            case "y":
            case "Yes":
            case "yes":
            case "YES":
                Solver solver = new Solver();
                solver.solve(maze);
                maze.show();
                break;
        }

        scanner.close();
    }
}
