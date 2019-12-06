package io.github.karmishin.kursach;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ListenerClass implements ContactListener {

    final Game game;

    public ListenerClass(Game game) {
        this.game = game;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFriction() > 10 && contact.getFriction() < 200) {
            game.setScreen(new GameOverScreen(game));
        } else if (contact.getFriction() > 500) {
            game.currentLevel += 1;
            game.setScreen(new GameScreen(game));
        }
    }
}
