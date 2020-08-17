package dalcoms.lib.libgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HsGdxLibGame extends Game {
    SpriteBatch spriteBatch;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        Gdx.app.log("screen", String.valueOf(
                (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
        setScreen(new LibTestScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
