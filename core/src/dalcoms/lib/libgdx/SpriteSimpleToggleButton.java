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

    //@2021-09-12
    public Sprite getSpriteDefault() {
        return spriteDefault;
    }

    public Sprite getSpriteToggled() {
        return spriteToggled;
    }

    @Override
    public void setLocationX(float locationX) {
        super.setLocationX(locationX);
        getSpriteToggled().setX(locationX);
    }

    @Override
    public void setLocationY(float locationY) {
        super.setLocationY(locationY);
        getSpriteToggled().setY(locationY);
    }

    @Override
    public void setLocation(float locationX, float locationY) {
//        super.setLocation(locationX, locationY);
        setLocationX(locationX);
        setLocationY(locationY);
    }

    @Override
    public void setLocation(Point2DFloat location) {
//        super.setLocation(location);
        setLocation(location.getX(), location.getY());
    }

    @Override
    public void setCenterLocationX(float x) {
        super.setCenterLocationX(x);
        getSpriteToggled().setCenterX(x);
    }

    @Override
    public void setCenterLocationY(float y) {
        super.setCenterLocationY(y);
        getSpriteToggled().setCenterY(y);
    }

    @Override
    public void setCenterLocation(float x, float y) {
        super.setCenterLocation(x, y);
        getSpriteToggled().setCenter(x, y);
    }

    @Override
    public void setRotationAngle(float rotationAngle) {
        super.setRotationAngle(rotationAngle);
        getSpriteToggled().setRotation(rotationAngle);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        getSpriteToggled().setSize(width, getSpriteToggled().getHeight());
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        getSpriteToggled().setSize(getSpriteToggled().getWidth(), height);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        getSpriteToggled().setSize(width, height);
    }

    @Override
    public void setSize(Point2DFloat size) {
//        super.setSize(size);
        setSize(size.getX(), size.getY());
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        getSpriteToggled().setScale(scaleX, getSpriteToggled().getScaleY());
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
        getSpriteToggled().setScale(getSpriteToggled().getScaleX(), scaleY);
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        getSpriteToggled().setScale(scale);
    }
    //====

    public enum ButtonState {
        DEFAULT, TOGGLED
    }

}
