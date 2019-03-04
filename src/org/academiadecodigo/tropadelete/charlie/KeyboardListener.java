package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Direction;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;

import java.security.Key;

public class KeyboardListener implements KeyboardHandler {

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


        Keyboard keyboard = new Keyboard(this);

        for (int i = 0; i < movements.length; i++) {

            KeyboardEvent pressed = new KeyboardEvent();
            pressed.setKey(movements[i]);
            pressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent released = new KeyboardEvent();
            released.setKey(movements[i]);
            released.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

            keyboard.addEventListener(pressed);
            keyboard.addEventListener(released);
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
                player2.setMoving(true);
                break;

            case KeyboardEvent.KEY_DOWN:
                player2.setPlayerDirection(PlayerDirection.DOWN);
                player2.setMoving(true);
                break;

            case KeyboardEvent.KEY_A:
                player1.setPlayerDirection(PlayerDirection.UP);
                player1.setMoving(true);
                break;

            case KeyboardEvent.KEY_Z:
                player1.setPlayerDirection(PlayerDirection.DOWN);
                player1.setMoving(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent key) {
        int tecla = key.getKey();

        if (tecla == KeyboardEvent.KEY_UP || tecla == KeyboardEvent.KEY_DOWN) {
            player2.setMoving(false);
        }

        if (tecla == KeyboardEvent.KEY_A || tecla == KeyboardEvent.KEY_Z) {
            player1.setMoving(false);
        }
    }
//active game init boolean game start

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
