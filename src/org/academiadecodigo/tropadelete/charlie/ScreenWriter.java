package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;

public class ScreenWriter {

    private Text winner;
    private Text player1Score;
    private Text player2Score;
    private Text startBallText;

    private int p1ScoreX;
    private int p2ScoreX;
    private int pScoreY;

    private Rectangle winnerBackground;
    private Rectangle startBallRectangle;

    private Player player1;
    private Player player2;
    private Rectangle canvas;

    public ScreenWriter(Player player1, Player player2, Rectangle canvas) {
        this.player1 = player1;
        this.player2 = player2;
        this.canvas = canvas;
    }


    public void setPlayerScore() {

        int spacing = 30;
        pScoreY = canvas.getY() + spacing;

        p1ScoreX = player1.getRectangle().getX() + player1.getRectangle().getWidth() + spacing;
        player1Score = new Text(p1ScoreX, pScoreY, Integer.toString(player1.getPoints()));

        p2ScoreX = player2.getRectangle().getX() - spacing;
        player2Score = new Text(p2ScoreX, pScoreY, Integer.toString(player2.getPoints()));

        player1Score.setColor(Color.YELLOW);
        player2Score.setColor(Color.YELLOW);

        player1Score.grow(20,40);
        player2Score.grow(20,40);
    }

    public void drawPlayerScores() {
        player1Score.draw();
        player2Score.draw();
    }

    public void updatePlayerScores() {
        player1Score.setText(Integer.toString(player1.getPoints()));
        player2Score.setText(Integer.toString(player2.getPoints()));
    }

    public void setWinner(PlayerNumber playerNumber) {

        int winner = playerNumber.equals(PlayerNumber.ONE) ? 1 : 2;
        int x = canvas.getX() + (canvas.getWidth() / 2);
        int y = canvas.getY() + (canvas.getHeight() / 2);

        this.winner = new Text(x, y, "WINNER: Player " + winner);
        this.winner.grow(70,30);
        this.winner.setColor(Color.GREEN);

        this.winnerBackground = new Rectangle(this.winner.getX() - 10, this.winner.getY() - 5,
                                             this.winner.getWidth() - 10, this.winner.getHeight());
    }

    public void showWinner() {
        winnerBackground.fill();
        winner.draw();
    }

    public void setStartBall() {

        startBallText = new Text(canvas.getX() + (canvas.getWidth() / 2), canvas.getY() + (canvas.getHeight() / 2),
                                    "Press Movement Key to start the ball!");
        startBallText.grow(70,30);
        startBallText.setColor(Color.WHITE);

        startBallRectangle = new Rectangle(startBallText.getX() - 10, startBallText.getY() - 5,
                                           startBallText.getWidth() - 120, startBallText.getHeight());
    }

    public void showStartBallText() {
        startBallRectangle.fill();
        startBallText.draw();
    }

    public void deleteStartBallText() {
        startBallRectangle.delete();
        startBallText.delete();
    }

}
