package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

class Background {
     private Texture texture1, texture2, texture3, texture4, texture5;
     Sprite sprite1, sprite2, sprite3, sprite4, sprite5;

     Background() {
          texture1 = new Texture(Gdx.files.internal("background/plx-1.png"));
          texture2 = new Texture(Gdx.files.internal("background/plx-2.png"));
          texture3 = new Texture(Gdx.files.internal("background/plx-3.png"));
          texture4 = new Texture(Gdx.files.internal("background/plx-4.png"));
          texture5 = new Texture(Gdx.files.internal("background/plx-5.png"));

          sprite1 = new Sprite(texture1);
          sprite2 = new Sprite(texture2);
          sprite3 = new Sprite(texture3);
          sprite4 = new Sprite(texture4);
          sprite5 = new Sprite(texture5);
     }
}
