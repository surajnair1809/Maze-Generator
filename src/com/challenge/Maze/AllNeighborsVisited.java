package com.challenge.Maze;

public class AllNeighborsVisited extends Exception {
    AllNeighborsVisited(){
        super("All Neighboring cells are visited");
    }
}
