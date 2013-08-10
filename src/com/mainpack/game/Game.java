package com.mainpack.game;

import com.mainpack.game.resources.Field;
import com.mainpack.game.resources.Human;
import com.mainpack.game.resources.Player;

public class Game {
    private Field field;

    private Player playerX;
    private Player playerO;

    private Player currentPlayer;

    public Game() {
        this(new Human("Player1"), new Human("Player2"));
    }

    public Game(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;

        currentPlayer = this.playerX;
        field = new Field(3);
    }

    public void play() {
        final int fieldSize = field.getFieldSize();
        final int maxAmountOfMoves = fieldSize*fieldSize;

        while ((field.gameState() == Field.GAME_STATE_CONTINUES)&&(field.getMovesPassed() < maxAmountOfMoves)) {

            field.drawField();
            System.out.print(currentPlayer.getName() + "'s turn (type in a cell's code(letter first)):");

            int[] move = currentPlayer.makeMove(field.getField());
            if (move[0] < 0) { // the code for 'exit'
                processExit(true);
                // Game will exit now
            }
            char item;
            if (currentPlayer == playerX) {
                item = Field.DEFAULT_X_ITEM;
            } else {
                item = Field.DEFAULT_O_ITEM;
            }
            while (!field.makeMove(item, move[0], move[1])) {
                System.out.println("wrong");
                move = currentPlayer.makeMove(field.getField());
                if (move[0] < 0) { // the code for 'exit'
                    processExit(true);
                    // Game will exit now
                }
            }

            // Change player's turn
            if (currentPlayer == playerX) {
                currentPlayer = playerO;
            } else {
                currentPlayer = playerX;
            }
        }

        field.drawField();

        String winner;
        if (field.gameState() == Field.GAME_STATE_CONTINUES) {
            winner = "nobody";
        } else {
            if (currentPlayer == playerX) {
                winner = playerO.getName();
            } else {
                winner = playerX.getName();
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println("In this game " + winner + " won!\n");

        processExit(true); // false
    }

    private void processExit(boolean exitType) {
        if (exitType == true) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else {

        }
    }
}
