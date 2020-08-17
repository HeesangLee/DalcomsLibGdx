package dalcoms.lib.libgdx;

/**
 * GestureInput interface for game object
 * 1. GestureDetector.GestureListener
 * 2. InputAdapter
 */

public interface IGestureInput {
    public boolean touchDown(float x, float y, int pointer, int button);

    public boolean touchUp(int screenX, int screenY, int pointer, int button);

    public boolean tap(float x, float y, int count, int button);

    public boolean longPress(float x, float y);

    public boolean touchDragged(int screenX, int screenY, int pointer);
}
