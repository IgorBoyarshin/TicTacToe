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
    private int[] moves;

    public static final boolean GAME_STATE_CONTINUES    = false;
    public static final boolean GAME_STATE_ENDED        = true;
    //private boolean gameState = GAME_STATE_CONTINUES;

    private final char[][] field;

    public final char[][] getField() {
        return field;
    }

    public void getOneStepBack() {
        if (movesPassed > 0) {
            movesPassed--;
            field[getTens(moves[movesPassed])][moves[movesPassed] % 10] = DEFAULT_CELL_ITEM;
        }
    }

    private int getTens(int number) {
        return (number - (number % 10))/10;
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
        moves = new int[FIELD_SIZE*FIELD_SIZE];
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

    public boolean gameState() {
        if (movesPassed >= (FIELD_SIZE + FIELD_SIZE - 1)) {
            final int x = getTens(moves[movesPassed-1]);
            final int y = moves[movesPassed-1] % 10;

            int i = 0;
            while ((field[i][y] != Field.DEFAULT_CELL_ITEM)&&(field[i][y] == field[i+1][y])) {
                i++;
                if (i == (FIELD_SIZE - 1)) {
                    break;
                }
            }
            if (i == (FIELD_SIZE - 1)) {
                return GAME_STATE_ENDED;
            }

            i=0;
            while ((field[x][i] != Field.DEFAULT_CELL_ITEM)&&(field[x][i] == field[x][i+1])) {
                i++;
                if (i == (FIELD_SIZE - 1)) {
                    break;
                }
            }
            if (i == (FIELD_SIZE - 1)) {
                return GAME_STATE_ENDED;
            }

            if (x == y) {
                i=0;
                while ((field[i][i] != Field.DEFAULT_CELL_ITEM)&&(field[i][i] == field[i+1][i+1])) {
                    i++;
                    if (i == (FIELD_SIZE - 1)) {
                        break;
                    }
                }
                if (i == (FIELD_SIZE - 1)) {
                    return GAME_STATE_ENDED;
                }
            }

            if (x == (FIELD_SIZE - y -1)) {
                i=0;
                while ((field[i][FIELD_SIZE - i - 1] != Field.DEFAULT_CELL_ITEM)&&
                        (field[i][FIELD_SIZE - i - 1] == field[i+1][FIELD_SIZE - i - 2])) {
                    i++;
                    if (i == (FIELD_SIZE - 1)) {
                        break;
                    }
                }
                if (i == (FIELD_SIZE - 1)) {
                    return GAME_STATE_ENDED;
                }
            }
        }
        return GAME_STATE_CONTINUES;
    }

    public boolean makeMove(char item, int x, int y) {
        if ((item == DEFAULT_X_ITEM)||(item == DEFAULT_O_ITEM)) {
            if ((x >= 0)&&(y >= 0)&&(x < FIELD_SIZE)&&(y < FIELD_SIZE)) {
                if (field[x][y] == DEFAULT_CELL_ITEM) {
                    putInCell(item, x, y);
                    moves[movesPassed] = x*10 + y;
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
