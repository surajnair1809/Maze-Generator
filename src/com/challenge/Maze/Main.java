package com.challenge.Maze;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(14,28);
        maze.generate();
        maze.show();
    }
}
