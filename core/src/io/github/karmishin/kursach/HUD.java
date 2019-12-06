package io.github.karmishin.kursach;

public class HUD {
    final Game game;
    final GameScreen gameScreen;

    public HUD(Game game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
    }

    public void renderHUD() {
        gameScreen.font.draw(gameScreen.batch, "Jumps left: " + gameScreen.player.jumpsLeft, 50, 470);
        gameScreen.font.draw(gameScreen.batch, "Time: " + gameScreen.seconds, 50, 450);
        gameScreen.font.draw(gameScreen.batch, gameScreen.player.body.getPosition().toString(), 50, 430);

        switch (game.currentLevel) {
            case 0:
                gameScreen.font.draw(gameScreen.batch, "Don't fall on these!!!", 400, 140);
                gameScreen.font.draw(gameScreen.batch, "Press SPACE to jump", 200, 340);
                gameScreen.font.draw(gameScreen.batch, "NEXT LEVEL -->", 690, 400);
                break;
            case 1:
                gameScreen.font.draw(gameScreen.batch, "Tip: You can always start over by pressing R", 500, 50);
                gameScreen.font.draw(gameScreen.batch, "NEXT LEVEL -->", 690, 450);
                break;
        }
    }
}
