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
        processInstructions();
        processGame();
        processWinner();
        processExit();
    }

    private void processGame() {
        final int fieldSize = field.getFieldSize();
        final int maxAmountOfMoves = fieldSize*fieldSize;

        while ((field.gameState() == Field.GAME_STATE_CONTINUES)&&(field.getMovesPassed() < maxAmountOfMoves)) {

            field.drawField();

            System.out.print(currentPlayer.getName() + "'s turn:");

            int[] move = currentPlayer.makeMove(field.getField());
            if (move[0] == Player.CODE_EXIT) {
                processExit();
            } else if (move[0] == Player.CODE_TAKE_STEP_BACK) {
                field.getOneStepBack();
                System.out.println("shall change");
            } else {
                char item;
                if (currentPlayer == playerX) {
                    item = Field.DEFAULT_X_ITEM;
                } else {
                    item = Field.DEFAULT_O_ITEM;
                }

                if ((move[0] == Player.CODE_INVALID_CELL_CODE)||(!field.makeMove(item, move[0], move[1]))) {
                    System.out.println("Invalid code, please type again.");
                    continue;
                }
            }
            currentPlayer = currentPlayer == playerX ? playerO : playerX;
        }
        field.drawField();
    }

    private void processInstructions() {
        System.out.println("Welcome to the game!");
        System.out.println("In order to make a move type in a cell's code(begin with capital letter).");
        System.out.println("To exit the game at any moment type 'exit'.");
        System.out.println("To step one move back type 'back'.");
    }

    private void processWinner() {
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
        System.out.println();
        System.out.println("In this game " + winner + " won!\n");
    }

    private void processExit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
