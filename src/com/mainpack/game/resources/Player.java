package com.mainpack.game.resources;

public abstract class Player {
    private String name;

    public Player() {
        //this(DEFAULT_PLAYER_NAME);
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int[] makeMove(final char[][] field);
}
