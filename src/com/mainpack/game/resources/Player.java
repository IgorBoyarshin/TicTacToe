package com.mainpack.game.resources;

public abstract class Player {
    private String name;

    public static final int CODE_EXIT = -1;
    public static final int CODE_TAKE_STEP_BACK = -2;
    public static final int CODE_INVALID_CELL_CODE = -3;

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
