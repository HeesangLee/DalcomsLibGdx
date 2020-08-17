package dalcoms.lib.libgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteGameObject extends GameObject {
    private Sprite sprite;
    private SpriteBatch spriteBatch;
    private boolean visible = true;

    public SpriteGameObject(Texture texture, float locationX, float locationY) {
        super(locationX, locationY, texture.getWidth(), texture.getHeight());
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(locationX, locationY);
    }

    public SpriteGameObject setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        return this;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (isVisible()) {
            draw(delta);
        }
    }

    private void draw(float delta) {
        checkSpriteMotionScaleColor();
        sprite.draw(spriteBatch);
    }

    private void checkSpriteMotionScaleColor() {
        checkSpriteMotion();
        checkSpriteRotation();
        checkSpriteScaling();
        checkSpriteColor();
    }

    private void checkSpriteMotion() {
        if (isOnMovingX()) {
            sprite.setX(getLocationX());
        }
        if (isOnMovingY()) {
            sprite.setY(getLocationY());
        }
    }

    private void checkSpriteRotation() {
        if (isOnRotating()) {
            sprite.setRotation(getRotationAngle());
        }
    }

    private void checkSpriteScaling() {
        if (isOnScaling()) {
            sprite.setScale(getScaleX(), getScaleY());
        }
    }

    private void checkSpriteColor() {
        if (isOnPainting()) {
            sprite.setColor(getColorR(), getColorG(), getColorB(), getColorA());
        } else if (isOnPaintingA()) {
            sprite.setAlpha(getColorA());
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
    }

    @Override
    public void setLocationX(float locationX) {
        super.setLocationX(locationX);
        this.sprite.setX(locationX);
    }

    @Override
    public void setLocationY(float locationY) {
        super.setLocationY(locationY);
        this.sprite.setY(locationY);
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
        this.sprite.setCenterX(x);
    }

    @Override
    public void setCenterLocationY(float y) {
        super.setCenterLocationY(y);
        this.sprite.setCenterY(y);
    }

    @Override
    public void setCenterLocation(float x, float y) {
        super.setCenterLocation(x, y);
        this.sprite.setCenter(x, y);
    }

    @Override
    public void setRotationAngle(float rotationAngle) {
        super.setRotationAngle(rotationAngle);
        this.sprite.setRotation(rotationAngle);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        this.sprite.setSize(width, this.sprite.getHeight());
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        this.sprite.setSize(this.sprite.getWidth(), height);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        this.sprite.setSize(width, height);
    }

    @Override
    public void setSize(Point2DFloat size) {
//        super.setSize(size);
        setSize(size.getX(), size.getY());
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        this.sprite.setScale(scaleX, this.sprite.getScaleY());
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
        this.sprite.setScale(this.sprite.getScaleX(), scaleY);
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        this.sprite.setScale(scale);
    }

    @Override
    public void setColorR(float colorR) {
        super.setColorR(colorR);
        this.sprite.setColor(colorR, this.sprite.getColor().g, this.sprite.getColor().b,
                             this.sprite.getColor().a);
    }

    @Override
    public void setColorG(float colorG) {
        super.setColorG(colorG);
        this.sprite.setColor(this.sprite.getColor().r, colorG,
                             this.sprite.getColor().b, this.sprite.getColor().a);
    }

    @Override
    public void setColorB(float colorB) {
        super.setColorB(colorB);
        this.sprite.setColor(this.sprite.getColor().r, this.sprite.getColor().g,
                             colorB, this.sprite.getColor().a);
    }

    @Override
    public void setColorA(float colorA) {
        super.setColorA(colorA);
        this.sprite.setColor(this.sprite.getColor().r, this.sprite.getColor().g,
                             this.sprite.getColor().b, colorA);
    }

    @Override
    public void setColor(float colorR, float colorG, float colorB, float colorA) {
        super.setColor(colorR, colorG, colorB, colorA);
        this.sprite.setColor(colorR, colorG, colorB, colorA);
    }

    public void setColor(Color tint) {
//        super.setColor(tint.r, tint.g, tint.b, tint.a);
//        this.sprite.setColor(tint);
        setColor(tint.r, tint.g, tint.b, tint.a);
    }
}
