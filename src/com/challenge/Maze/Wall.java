package com.challenge.Maze;

public class Wall {
    private boolean exist ;
    public Wall(){
        exist = true ;
    }
    public void breakWall(){
        exist = false ;
    }

    public boolean exist(){
        return exist;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "exist=" + exist +
                '}';
    }
}
