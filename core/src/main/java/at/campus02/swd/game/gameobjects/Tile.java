package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile implements GameObject {
    private Texture image; // Die Textur des Tiles.
    private Sprite sprite; // Der Sprite des Tiles.

    public static final float SIZE = 64.0f;

    private String imagePath;

    public Tile(String imagePath) {
        this.imagePath=imagePath;
        this.image =AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
    }
    // Initialisiert die Textur des Tiles mit dem übergebenen Pfad.
    // Erstellt einen Sprite mit der Textur des Tiles.
    @Override
    public void act(float delta) {

    }
    // Die act-Methode wird von GameObject implementiert, hat aber hier keine Funktionalität.
    // Es wird nichts im Tile selbst aktualisiert.

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
    // Setzt die Position des Sprites auf die angegebenen Koordinaten.

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    // Zeichnet den Sprite auf den SpriteBatch, der als Parameter übergeben wird.
}
