package at.campus02.swd.game;

import at.campus02.swd.game.factory.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.input.GameInput;
import at.campus02.swd.game.gameobjects.Player;

public class Main extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    private GameInput gameInput;
    private Array<GameObject> gameObjects = new Array<>();
    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();

        Factory tileFactory = new TileFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        for (int i = 0; i < (viewport.getMinWorldHeight() / 64); i++) {
            for (int y = 0; y < (viewport.getMinWorldHeight() / 64); y++) {
                gameObjects.add(tileFactory.create(Type.MEADOW_CENTER, i * 64, y * 64));
            }
        }

        MeadowBuilder mb = new MeadowBuilder();
        for (GameObject o : mb.placeTile(-160, -160, 5, 5)) {
            gameObjects.add(o);
        }

        player = (Player) playerFactory.create(Type.PLAYER, 0, 0);
        gameObjects.add(player);

        gameInput = new GameInput(player);

        Gdx.input.setInputProcessor(this.gameInput);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
    }

    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
        player.act(delta); // Hier die act-Methode des Spielers aufrufen
    }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
        font.draw(batch, "Hello Game", -220, -220);
        batch.end();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.52f, 0.81f, 0.92f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        while (deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;
            act(logicFrameTime);
        }

        player.act(delta);

        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            player.moveUp();
        } else if (keycode == Input.Keys.DOWN) {
            player.moveDown();
        } else if (keycode == Input.Keys.LEFT) {
            player.moveLeft();
        } else if (keycode == Input.Keys.RIGHT) {
            player.moveRight();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            player.stopMovingUp();
        } else if (keycode == Input.Keys.S) {
            player.stopMovingDown();
        } else if (keycode == Input.Keys.A) {
            player.stopMovingLeft();
        } else if (keycode == Input.Keys.D) {
            player.stopMovingRight();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
