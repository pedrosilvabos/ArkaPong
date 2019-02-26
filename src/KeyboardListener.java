import org.academiadecodigo.simplegraphics.graphics.Movable;
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

        int[] movements = {KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN};
        for(int i = 0; i<movements.length; i++){
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
                System.out.println("down");
                player1.setPosY();
                this.posY += 10;
                this.posX += 0;
                break;
            case KeyboardEvent.KEY_UP:
                System.out.println("up");

                this.posY -= 10;
                this.posX += 0;
                break;
            case KeyboardEvent.KEY_LEFT:

                this.posY -= 0;
                this.posX -= 10;
                break;
            case KeyboardEvent.KEY_RIGHT:

                this.posY -= 0;
                this.posX += 10;
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
