package at.campus02.swd.game;

import at.campus02.swd.game.factory.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.input.GameInput;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    // Der Viewport für die Darstellung des Spiels
    private GameInput gameInput = new GameInput();
    // Das GameInput-Objekt für die Spielersteuerung
    private Array<GameObject> gameObjects = new Array<>();
    // Eine Array-Liste von GameObjects, die im Spiel vorhanden sind
    private final float updatesPerSecond = 60;
    // Anzahl der Logikaktualisierungen pro Sekunde
    private final float logicFrameTime = 1 / updatesPerSecond;
    // Dauer eines Logik-Frames in Sekunden
    private float deltaAccumulator = 0;
    // Akkumulator für die Zeitdifferenz zwischen den Render-Aufrufen
    private BitmapFont font;
    // Ein BitmapFont-Objekt für die Textdarstellung im Spiel

    private GameObject tile;
    // Eine Referenz auf ein Spielobjekt vom Typ Tile

    private GameObject player;
    // Eine Referenz auf ein Spielobjekt vom Typ Player

///



    @Override
    public void create() {
        batch = new SpriteBatch();

        // Erzeugt einen neuen SpriteBatch für die Darstellung von Sprites

        Factory tileFactory = new TileFactory();// Eine Fabrik zum Erstellen von Tile-Objekten
        PlayerFactory player = new PlayerFactory();// Eine Fabrik zum Erstellen von Player-Objekten
        // Erzeugt eine Anordnung von MeadowCenter-Tiles basierend auf der Viewport-Größe
        for (int i = 0; i < (viewport.getMinWorldHeight() / 64); i++) {
            for (int y = 0; y < (viewport.getMinWorldHeight() / 64); y++) {
                gameObjects.add(tileFactory.create(Type.MEADOW_CENTER,i*64,y*64));
            }
        }

        MeadowBuilder mb = new MeadowBuilder();
        for (GameObject o:mb.placeTile(-160,-160,5,5)
        ) {
            gameObjects.add(o);
        }

        /*
        for(int x = -240; x<240; x+=64) {
            for (int y = 240; y > -240; y -= 64) {
                tile = new Tile();
                tile.setPosition(x,y-64);
                gameObjects.add(tile);
            }
        }
*/
        // Fügt ein Player-Objekt an der Position (0, 0) hinzu
        gameObjects.add(player.create(Type.PLAYER,0,0));
        // Erzeugt ein neues BitmapFont-Objekt für die Textdarstellung
        font = new BitmapFont();
        font.setColor(Color.WHITE); // Setzt die Farbe des Textes auf Weiß
        Gdx.input.setInputProcessor(this.gameInput); // Setzt den GameInput-Processor für die Spielersteuerung

    }

   private void act(float delta) {
        for(GameObject gameObject : gameObjects) {
           gameObject.act(delta);
       }
   }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);// Setzt die Projektionsmatrix
        // des SpriteBatch auf die kombinierte Matrix der Viewport-Kamera
        batch.begin(); // Beginnt das Zeichnen mit dem SpriteBatch
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(batch);// Zeichnet jedes Spielobjekt mit dem SpriteBatch
        }
        font.draw(batch, "Hello Game", -220, -220);// Zeichnet den Text "Hello Game" an der angegebenen Position
        batch.end();// Beendet das Zeichnen mit dem SpriteBatch
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.52f, 0.81f, 0.92f, 1f);
        // Setzt die Hintergrundfarbe des GL-Clears auf ein helles Blau
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// Löscht den Bildschirm mit der festgelegten Hintergrundfarbe


        float delta = Gdx.graphics.getDeltaTime();// Liefert die Zeitdifferenz seit dem letzten Aufruf der render-Methode


        // Führt die Spiellogik für jeden Logikframe aus
        while(deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;// Reduziert den Akkumulator um die Dauer eines Logik-Frames
            act(logicFrameTime);// Führt die Spiellogik aus
        }

        draw();// Zeichnet die Spielobjekte
    }

    @Override
    public void dispose() {
        batch.dispose();
    }// Gibt den SpriteBatch-Ressourcen frei

    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
    }// Aktualisiert den Viewport bei einer Größenänderung des Fensters
}
