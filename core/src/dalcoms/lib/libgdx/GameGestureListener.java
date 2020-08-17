package dalcoms.lib.libgdx;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GameGestureListener implements GestureDetector.GestureListener {
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1,
            Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public interface TouchDownEventListener {
        void onEvent(float x, float y, int pointer, int button);
    }

    public interface TouchUpEventListener {
        void onEvent(int screenX, int screenY, int pointer, int button);
    }

    public interface TabEventListener {
        void onEvent(float x, float y, int count, int button);
    }

    public interface LongPressEventListener {
        void onEvent(float x, float y);
    }

    public interface TouchDraggedEventListener{
        void onEvent(int screenX, int screenY, int pointer);
    }
}
