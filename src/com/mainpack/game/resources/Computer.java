package com.mainpack.game.resources;

public class Computer extends Player{

    private static final String DEFAULT_COMPUTER_NAME = "Default Computer";

    public Computer() {
        this(DEFAULT_COMPUTER_NAME);
    }

    public Computer(String name) {
        super(name);
        System.out.println("Computer is not implemented yet, sry : (");
        System.exit(0);
    }

    @Override
    public int[] makeMove(final char[][] field) {
        int[] move = new int[2];

        // Implement logic

        return move;
    }
}
