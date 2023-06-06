package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {
    void act(float delta);
    void setPosition(float x, float y);
    void draw(SpriteBatch batch);
}
// Eine Methode, die die Aktion des Spielobjekts basierend auf der vergangenen Zeitdifferenz aktualisiert.
// Eine Methode, um die Position des Spielobjekts auf dem Bildschirm festzulegen.
// Eine Methode, um das Spielobjekt auf dem Bildschirm zu zeichnen.

