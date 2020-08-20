package dalcoms.lib.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SpriteSimpleToggleButton extends SpriteSimpleButton {
    private ButtonState btnToggleState = ButtonState.DEFAULT;
    private boolean toggleOnLongPress = false;
    private boolean toggleOnTap = true;
    Texture textureDefault, textureToggle;

    public SpriteSimpleToggleButton(Texture texture1, Texture texture2, Viewport viewport,
            float locationX, float locationY) {
        super(texture1, viewport, locationX, locationY);
        this.textureDefault = texture1;
        this.textureToggle = texture2;
    }

    public SpriteSimpleToggleButton(Texture texture1, Texture texture2, Viewport viewport,
            SpriteBatch spriteBatch, float locationX, float locationY) {
        super(texture1, viewport, spriteBatch, locationX, locationY);
        this.textureDefault = texture1;
        this.textureToggle = texture2;
    }

    @Override
    public boolean longPress(float x, float y) {
        if (isToggleOnLongPress() & isInTouchArea(x, y)) {
            setBtnToggleState(getBtnToggleState() == ButtonState.DEFAULT ? ButtonState.TOGGLED :
                                      ButtonState.DEFAULT);
        }
        return super.longPress(x, y);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (isToggleOnTap() & isInTouchArea(x, y)) {
            setBtnToggleState(getBtnToggleState() == ButtonState.DEFAULT ? ButtonState.TOGGLED :
                                      ButtonState.DEFAULT);
        }
        return super.tap(x, y, count, button);
    }


    public ButtonState getBtnToggleState() {
        return btnToggleState;
    }

    public void setBtnToggleState(ButtonState btnToggleState) {
        if (this.btnToggleState != btnToggleState) {
            this.btnToggleState = btnToggleState;
            setSprite(new Sprite(getButtonTexture(btnToggleState)));
        }
    }

    private Texture getButtonTexture(ButtonState btnToggleState) {
        return btnToggleState == ButtonState.DEFAULT ? textureDefault : textureToggle;
    }

    public boolean isToggleOnLongPress() {
        return toggleOnLongPress;
    }

    public void setToggleOnLongPress(boolean toggleOnLongPress) {
        this.toggleOnLongPress = toggleOnLongPress;
    }

    public boolean isToggleOnTap() {
        return toggleOnTap;
    }

    public void setToggleOnTap(boolean toggleOnTap) {
        this.toggleOnTap = toggleOnTap;
    }

    public void setTextureDefault(Texture textureDefault) {
        this.textureDefault = textureDefault;
    }


    public void setTextureToggle(Texture textureToggle) {
        this.textureToggle = textureToggle;
    }

    public enum ButtonState {
        DEFAULT, TOGGLED
    }

}
