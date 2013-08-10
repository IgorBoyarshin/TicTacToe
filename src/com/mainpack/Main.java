package com.mainpack;

import com.mainpack.game.Game;
import com.mainpack.game.resources.Human;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Human("PlayerX"), new Human("PlayerO"));
        game.play();
    }
}