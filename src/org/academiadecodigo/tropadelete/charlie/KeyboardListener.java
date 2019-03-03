<<<<<<< HEAD
package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardListener implements KeyboardHandler {

    private int[] movements;
    private double posX;
    private double posY;
    private Player player1;
    private Player player2;

    public KeyboardListener(Player player1, Player player2) { //mask cast to movable

        this.player1 = player1;
        this.player2 = player2;

        int[] movements = {
                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_A,
                KeyboardEvent.KEY_Z
        };

        for (int i = 0; i < movements.length; i++) {
            KeyboardEvent move = new KeyboardEvent();
            move.setKey(movements[i]);
            move.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            Keyboard keyboard = new Keyboard(this);
            keyboard.addEventListener(move);
            keyPressed(move);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent key) {

        switch (key.getKey()) {
            case KeyboardEvent.KEY_UP:
                player1.setDirection(Direction.UP);
                break;

            case KeyboardEvent.KEY_DOWN:
                player1.setDirection(Direction.DOWN);
                break;

            case KeyboardEvent.KEY_A:
                player2.setDirection(Direction.UP);
                break;

            case KeyboardEvent.KEY_Z:
                player2.setDirection(Direction.DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent key) {

        if (key.getKey() == KeyboardEvent.KEY_UP || key.getKey() == KeyboardEvent.KEY_DOWN) {
            player1.setDirection(null);
        }

        if (key.getKey() == KeyboardEvent.KEY_A || key.getKey() == KeyboardEvent.KEY_Z) {
            player2.setDirection(null);
        }
    }

}
=======
package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Direction;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;

public class KeyboardListener implements KeyboardHandler {

    private int[] movements;
    private double posX;
    private double posY;
    private Player player1;
    private Player player2;

    public KeyboardListener(Player player1, Player player2) { //mask cast to movable

        this.player1 = player1;
        this.player2 = player2;

        int[] movements = {
                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_A,
                KeyboardEvent.KEY_Z
        };

        for (int i = 0; i < movements.length; i++) {
            KeyboardEvent move = new KeyboardEvent();
            move.setKey(movements[i]);
            move.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            Keyboard keyboard = new Keyboard(this);
            keyboard.addEventListener(move);
            keyPressed(move);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent key) {

        if (Stage.getBall() != null && Stage.getBall().isStatic()) {

            if (Stage.getBall().getStartingPlayer().equals(PlayerNumber.ONE)) {
                checkPlayerOne(Stage.getBall(), key);
            }

            if (Stage.getBall().getStartingPlayer().equals(PlayerNumber.TWO)) {
                checkPlayerTwo(Stage.getBall(), key);
            }
        }

        switch (key.getKey()) {
            case KeyboardEvent.KEY_UP:
                player2.setPlayerDirection(PlayerDirection.UP);
                break;

            case KeyboardEvent.KEY_DOWN:
                player2.setPlayerDirection(PlayerDirection.DOWN);
                break;

            case KeyboardEvent.KEY_A:
                player1.setPlayerDirection(PlayerDirection.UP);
                break;

            case KeyboardEvent.KEY_Z:
                player1.setPlayerDirection(PlayerDirection.DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent key) {

        if (key.getKey() == KeyboardEvent.KEY_UP || key.getKey() == KeyboardEvent.KEY_DOWN) {
            player1.setPlayerDirection(null);
        }

        if (key.getKey() == KeyboardEvent.KEY_A || key.getKey() == KeyboardEvent.KEY_Z) {
            player2.setPlayerDirection(null);
        }
    }

    private static void checkPlayerOne(Ball ball, KeyboardEvent key) {

        if (key.getKey() == KeyboardEvent.KEY_A || key.getKey() == KeyboardEvent.KEY_Z) {
            ball.removeStatic();
        }
    }

    private static void checkPlayerTwo(Ball ball, KeyboardEvent key) {

        if (key.getKey() == KeyboardEvent.KEY_UP || key.getKey() == KeyboardEvent.KEY_DOWN) {
            ball.removeStatic();
        }
    }

}
>>>>>>> f0ee7dbda49ef7652819601b85254cbbc3dbe0cf
