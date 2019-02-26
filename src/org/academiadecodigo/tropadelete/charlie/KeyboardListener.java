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
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_DOWN:
                player1.setDirection(Direction.DOWN);
                break;
            case KeyboardEvent.KEY_UP:
                player1.setDirection(Direction.UP);
                break;
            case KeyboardEvent.KEY_A:
                System.out.println("UP");
                player2.setDirection(Direction.UP);
                break;
            case KeyboardEvent.KEY_Z:
                System.out.println("DOWN");
                player2.setDirection(Direction.DOWN);
                break;


        }
    }

    @Override
    public void keyReleased(KeyboardEvent var1) {

    }

    public double moveLogic() {
        if (posX > 0) {
            return posX;
        }
        return posY;
    }

}
