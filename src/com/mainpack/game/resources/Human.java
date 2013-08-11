package com.mainpack.game.resources;

import java.util.Scanner;

public class Human extends Player{

    private static Scanner input = new Scanner(System.in);

    private static final String DEFAULT_HUMAN_NAME = "Default Person";

    public Human(String name) {
       super(name);
    }

    public Human() {
        this(DEFAULT_HUMAN_NAME);
    }

    @Override
    public int[] makeMove(final char[][] field) {
        int[] move = new int[2];

        String cellCode = input.next();

        if (cellCode.equals("back")) {
            move[0] = Player.CODE_TAKE_STEP_BACK;
        } else if (cellCode.equals("exit")) {
            move[0] = Player.CODE_EXIT;
        } else if (cellCode.length() < 2) {
            move[0] = Player.CODE_INVALID_CELL_CODE;
        } else {
            move[0] = (int)cellCode.charAt(0) - (int)('A');
            move[1] = (int)cellCode.charAt(1) - (int)('0');
        }

        return move;
    }
}
