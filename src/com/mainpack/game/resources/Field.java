package com.mainpack.game.resources;

public class Field {

    private final int FIELD_SIZE;

    private static final int MIN_FIELD_SIZE = 2;
    private static final int MAX_FIELD_SIZE = 9;
    private static final char DEFAULT_CELL_ITEM = ' ';

    public static final char DEFAULT_X_ITEM = 'X';
    public static final char DEFAULT_O_ITEM = '0';

    // For history
    private int movesPassed = 0;

    public static final boolean GAME_STATE_CONTINUES    = false;
    public static final boolean GAME_STATE_ENDED        = true;
    //private boolean gameState = GAME_STATE_CONTINUES;

    private final char[][] field;

    public final char[][] getField() {
        return field;
    }

    public int getMovesPassed() {
        return movesPassed;
    }

    public final int getFieldSize() {
        return FIELD_SIZE;
    }

    public Field(int fieldSize) {
        if (!((fieldSize >= MIN_FIELD_SIZE)&&(fieldSize <= MAX_FIELD_SIZE))) {
            fieldSize = 3;
        }

        FIELD_SIZE = fieldSize;
        field = new char[FIELD_SIZE][FIELD_SIZE];
        clearField();
    }

    public Field() {
        this(3);
    }

    public void clearField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = DEFAULT_CELL_ITEM;
            }
        }
    }

    public void drawField() {
        char codeNumber = '0';
        char codeLetter = 'A';

        System.out.print("  ");
        for(int i=0; i < FIELD_SIZE; i++) {
            System.out.print(" " + codeNumber + " ");
            codeNumber++;
        }
        System.out.print("\n");
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(codeLetter + " ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.print('\n');
            codeLetter++;
        }
        System.out.println();
    }

    // Not very efficient method of checking the whole field
    public boolean gameState() {

        boolean state = GAME_STATE_CONTINUES;


        // In one iteration checks one horizontal and one vertical line
        for (int i=0; i < FIELD_SIZE; i++) {
            // Vertical
            int j = 0;
            while (j < FIELD_SIZE-1) {
                if ((field[i][j+1] == field[i][j])&&(field[i][j] != Field.DEFAULT_CELL_ITEM)) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == (FIELD_SIZE -1)) {
                state = GAME_STATE_ENDED;
                return state;
            }

            // Horizontal
            j = 0;
            while (j < FIELD_SIZE-1) {
                if ((field[j+1][i] == field[j][i])&&(field[j][i] != Field.DEFAULT_CELL_ITEM)) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == (FIELD_SIZE -1)) {
                state = GAME_STATE_ENDED;
                return state;
            }
        }

        // First diagonal
        int i;
        for (i=0; i < FIELD_SIZE-1; i++) {
            if ((field[i][i] == field[i+1][i+1])&&(field[i][i] != Field.DEFAULT_CELL_ITEM)) {
                i++;
            } else {
                break;
            }
        }
        if (i == (FIELD_SIZE -1)) {
            state = GAME_STATE_ENDED;
            return state;
        }

        // Second diagonal

        for (i=0; i < FIELD_SIZE-1; i++) {
            if ((field[i][FIELD_SIZE - i - 1] == field[i+1][FIELD_SIZE - i - 2])&&(field[i][FIELD_SIZE - i - 1] != Field.DEFAULT_CELL_ITEM)) {
                i++;
            } else {
                break;
            }
        }
        if (i == (FIELD_SIZE -1)) {
            state = GAME_STATE_ENDED;
            return state;
        }

        return state;
    }

    public boolean makeMove(char item, int x, int y) {
        if ((item == DEFAULT_X_ITEM)||(item == DEFAULT_O_ITEM)) {
            if ((x*y >= 0)&&(x < FIELD_SIZE)&&(y < FIELD_SIZE)) { // we can assume that x >= 0
                                                                  // if x is not, game would have exit in class Game
                if (field[x][y] == DEFAULT_CELL_ITEM) {
                    putInCell(item, x, y);
                    movesPassed++;
                    return true;
                }
            }
        }
        return false;
    }

    private void putInCell(char item, int x, int y) {
        field[x][y] = item;
    }
}
