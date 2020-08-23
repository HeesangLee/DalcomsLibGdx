package dalcoms.lib.libgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SpriteSimpleToggleButton extends SpriteSimpleButton {
    private ButtonState btnToggleState = ButtonState.DEFAULT;
    private boolean toggleOnLongPress = false;
    private boolean toggleOnTap = true;
    //    Texture textureDefault, textureToggle;
    private Sprite spriteDefault, spriteToggled;

    public SpriteSimpleToggleButton(Texture texture1, Texture texture2, Viewport viewport,
            float locationX, float locationY) {
        super(texture1, viewport, locationX, locationY);
        this.spriteDefault = getSprite();
        this.spriteToggled = new Sprite((texture2));
        spriteToggled.setPosition(locationX, locationY);
    }

    public SpriteSimpleToggleButton(Texture texture1, Texture texture2, Viewport viewport,
            SpriteBatch spriteBatch, float locationX, float locationY) {
        super(texture1, viewport, spriteBatch, locationX, locationY);
        this.spriteDefault = getSprite();
        this.spriteToggled = new Sprite((texture2));
        spriteToggled.setPosition(locationX, locationY);
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
//            setSprite(new Sprite(getButtonTexture(btnToggleState)));
            setSprite(btnToggleState == ButtonState.DEFAULT ? this.spriteDefault :
                              this.spriteToggled);
        }
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

    @Override
    public void setColorR(float colorR) {
        super.setColorR(colorR);
        this.spriteDefault
                .setColor(colorR, this.spriteDefault.getColor().g, this.spriteDefault.getColor().b,
                          this.spriteDefault.getColor().a);
        this.spriteToggled
                .setColor(colorR, this.spriteToggled.getColor().g, this.spriteToggled.getColor().b,
                          this.spriteToggled.getColor().a);

    }

    @Override
    public void setColorG(float colorG) {
        super.setColorG(colorG);
        this.spriteDefault.setColor(this.spriteDefault.getColor().r, colorG,
                                    this.spriteDefault.getColor().b,
                                    this.spriteDefault.getColor().a);
        this.spriteToggled.setColor(this.spriteToggled.getColor().r, colorG,
                                    this.spriteToggled.getColor().b,
                                    this.spriteToggled.getColor().a);
    }

    @Override
    public void setColorB(float colorB) {
        super.setColorB(colorB);
        this.spriteDefault
                .setColor(this.spriteDefault.getColor().r, this.spriteDefault.getColor().g,
                          colorB, this.spriteDefault.getColor().a);
        this.spriteToggled
                .setColor(this.spriteToggled.getColor().r, this.spriteToggled.getColor().g,
                          colorB, this.spriteToggled.getColor().a);
    }

    @Override
    public void setColorA(float colorA) {
        super.setColorA(colorA);
        this.spriteDefault
                .setColor(this.spriteDefault.getColor().r, this.spriteDefault.getColor().g,
                          this.spriteDefault.getColor().b, colorA);
        this.spriteToggled
                .setColor(this.spriteToggled.getColor().r, this.spriteToggled.getColor().g,
                          this.spriteToggled.getColor().b, colorA);
    }

    @Override
    public void setColor(float colorR, float colorG, float colorB, float colorA) {
        super.setColor(colorR, colorG, colorB, colorA);
        this.spriteDefault.setColor(colorR, colorG, colorB, colorA);
        this.spriteToggled.setColor(colorR, colorG, colorB, colorA);
    }

    public void setColor(Color tint) {
        setColor(tint.r, tint.g, tint.b, tint.a);
    }

    public enum ButtonState {
        DEFAULT, TOGGLED
    }

}
