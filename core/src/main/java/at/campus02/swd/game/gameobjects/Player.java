package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player implements GameObject {
    private Texture image; // Die Textur des Spielers.
    private Sprite sprite; // Der Sprite des Spielers.
    private String imagePath;
    private float x; // X-Koordinate des Spielers
    private float y; // Y-Koordinate des Spielers
    private float speed; // Geschwindigkeit des Spielers

    public Player(String imagePath) {
        this.imagePath = imagePath;
        this.image = AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
        this.x = 0;
        this.y = 0;
        this.speed = 5; // Beispielgeschwindigkeit: 5 Einheiten pro Aktualisierungsschritt
    }

    @Override
    public void act(float delta) {
        // Hier können Sie die Bewegungslogik des Spielers implementieren, die in jedem Aktualisierungsschritt aufgerufen wird.
        // Die delta-Zeit kann verwendet werden, um die Bewegung des Spielers proportional zur Zeit zu machen.

        // Beispiel: Spieler nach oben bewegen
        y += speed * delta;
    }

    // Weitere Methoden zur Steuerung der Bewegung hinzufügen
    public void moveUp() {
        y += speed;
        sprite.setPosition(x, y);
    }

    public void moveDown() {
        y -= speed;
        sprite.setPosition(x, y);
    }

    public void moveLeft() {
        x -= speed;
        sprite.setPosition(x, y);
    }

    public void moveRight() {
        x += speed;
        sprite.setPosition(x, y);
    }

    // Weitere Methoden zur Steuerung der Bewegung hinzufügen, um die Bewegung zu stoppen
    public void stopMovingUp() {
        // Optional: Hier können Sie zusätzlichen Code hinzufügen, um das Stoppen der Bewegung zu behandeln
    }

    public void stopMovingDown() {
        // Optional: Hier können Sie zusätzlichen Code hinzufügen, um das Stoppen der Bewegung zu behandeln
    }

    public void stopMovingLeft() {
        // Optional: Hier können Sie zusätzlichen Code hinzufügen, um das Stoppen der Bewegung zu behandeln
    }

    public void stopMovingRight() {
        // Optional: Hier können Sie zusätzlichen Code hinzufügen, um das Stoppen der Bewegung zu behandeln
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
