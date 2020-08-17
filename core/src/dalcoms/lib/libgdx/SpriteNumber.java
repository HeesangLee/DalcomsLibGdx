package dalcoms.lib.libgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class SpriteNumber extends GameObject {
    private SpriteBatch spriteBatch;
    private boolean visible = true;

    Array<Texture> textureArrayOfNumbers;
    Array<Sprite> spriteNumberArray;

    private int number = -1;
    private float gap = 8f;
    private AlignTo alignTo = AlignTo.LEFT;

    public SpriteNumber(Array<Texture> textureArrayOfNumbers, int number, float locationX,
                        float locationY) {
        super(locationX, locationY, 2, 2);
        spriteNumberArray = new Array<Sprite>();
        this.textureArrayOfNumbers = textureArrayOfNumbers;
        this.setNumber(number);
    }

    public SpriteNumber setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        return this;
    }

    public void setNumber(int number) {
        if (getNumber() != number) {
            this.number = number;
            this.setSpriteNumber(number);
        }
    }

    public int getNumber() {
        return number;
    }

    private void setSpriteNumber(int number) {
        char[] charNum = String.valueOf(number).toCharArray();
        int digitNum;
        float positionX = 0f;

        if (spriteNumberArray == null) {
            spriteNumberArray = new Array<Sprite>();
        } else {
            spriteNumberArray.clear();
        }

        for (int i = 0; i < charNum.length; i++) {
            digitNum = Character.getNumericValue(charNum[i]);

            Sprite tempSprite = new Sprite(textureArrayOfNumbers.get(digitNum));
            tempSprite.setPosition(positionX, getLocationY());

            spriteNumberArray.add(tempSprite);
            positionX += (float) textureArrayOfNumbers.get(digitNum).getWidth() + getGap();
        }

        positionX = getNumLocationX(getNumWidth());
        for (Sprite sprite : getSpriteNumberArray()) {
            sprite.setX(sprite.getX() + positionX);
        }
    }

    public float getNumWidth() {
        return getSpriteNumberArray().get(0).getX() +
               getSpriteNumberArray().get(getSpriteNumberArray().size - 1).getWidth();
    }


    public float getNumLocationX(float numWidth) {
        float ret = 0f;
        switch (getAlignTo()) {
            case LEFT:
                ret = getLocationX();
                break;
            case CENTER:
                ret = getCenterLocationX() - numWidth / 2f;
                break;
            case RIGHT:
                break;
            default://LEFFT
                ret = getLocationX() + getWidth() - numWidth;
                break;
        }
        return ret;
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
        for (Sprite sprite : spriteNumberArray) {
            sprite.draw(spriteBatch);
        }
    }

    private void checkSpriteMotionScaleColor() {
        checkSpriteMotion();
        checkSpriteRotation();
        checkSpriteScaling();
        checkSpriteColor();
    }

    private void checkSpriteMotion() {
        if (isOnMovingX()) {
            setLocationX(getLocationX());
        }
        if (isOnMovingY()) {
            for (Sprite sprite : spriteNumberArray) {
                sprite.setY(getLocationY());
            }
        }
    }

    private void checkSpriteRotation() {
        if (isOnRotating()) {
            for (Sprite sprite : spriteNumberArray) {
                sprite.setRotation(getRotationAngle());
            }
        }
    }

    private void checkSpriteScaling() {
        if (isOnScaling()) {
            for (Sprite sprite : spriteNumberArray) {
                sprite.setScale(getScaleX(), getScaleY());
            }
        }
    }

    private void checkSpriteColor() {
        if (isOnPainting()) {
            for (Sprite sprite : spriteNumberArray) {
                sprite.setColor(getColorR(), getColorG(), getColorB(), getColorA());
            }
        } else if (isOnPaintingA()) {
            for (Sprite sprite : spriteNumberArray) {
                sprite.setAlpha(getColorA());
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    @Override
    public void setLocationX(float locationX) {
        super.setLocationX(locationX);
        float diff = getNumLocationX(getNumWidth()) - getSpriteNumberArray().get(0).getX();//new-old
        for (Sprite sprite : getSpriteNumberArray()) {
            sprite.setX(sprite.getX() + diff);
        }
    }

    @Override
    public void setLocationY(float locationY) {
        super.setLocationY(locationY);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setY(locationY);
        }
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
        setLocationX(x - getWidth() / 2f);
    }

    @Override
    public void setCenterLocationY(float y) {
        super.setCenterLocationY(y);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setCenterY(y);
        }

    }

    @Override
    public void setCenterLocation(float x, float y) {
        setCenterLocationX(x);
        setCenterLocationY(y);
    }

    @Override
    public void setRotationAngle(float rotationAngle) {
        super.setRotationAngle(rotationAngle);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setRotation(rotationAngle);
        }
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        //only change location x
        setLocationX(getLocationX());
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
    }

    @Override
    public void setSize(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void setSize(Point2DFloat size) {
//        super.setSize(size);
        setSize(size.getX(), size.getY());
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setScale(scaleX, sprite.getScaleY());
        }
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setScale(sprite.getScaleX(), scaleY);
        }
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setScale(scale);
        }
    }

    @Override
    public void setColorR(float colorR) {
        super.setColorR(colorR);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setColor(colorR, sprite.getColor().g, sprite.getColor().b, sprite.getColor().a);
        }
    }

    @Override
    public void setColorG(float colorG) {
        super.setColorG(colorG);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setColor(sprite.getColor().r, colorG, sprite.getColor().b, sprite.getColor().a);
        }
    }

    @Override
    public void setColorB(float colorB) {
        super.setColorB(colorB);

        for (Sprite sprite : spriteNumberArray) {
            sprite.setColor(sprite.getColor().r, sprite.getColor().g, colorB, sprite.getColor().a);
        }
    }

    @Override
    public void setColorA(float colorA) {
        super.setColorA(colorA);

        for (Sprite sprite : spriteNumberArray) {
            sprite.setColor(sprite.getColor().r, sprite.getColor().g, sprite.getColor().b, colorA);
        }
    }

    @Override
    public void setColor(float colorR, float colorG, float colorB, float colorA) {
        super.setColor(colorR, colorG, colorB, colorA);
        for (Sprite sprite : spriteNumberArray) {
            sprite.setColor(colorR, colorG, colorB, colorA);
        }
    }

    public void setColor(Color tint) {
//        super.setColor(tint.r, tint.g, tint.b, tint.a);
//        this.sprite.setColor(tint);
        setColor(tint.r, tint.g, tint.b, tint.a);
    }

    public void setTextureArrayOfNumbers(
            Array<Texture> textureArrayOfNumbers) {
        this.textureArrayOfNumbers = textureArrayOfNumbers;
    }

    public float getGap() {
        return gap;
    }

    public void setGap(float gap) {
        this.gap = gap;
    }

    public AlignTo getAlignTo() {
        return alignTo;
    }

    public void setAlignTo(AlignTo alignTo) {
        this.alignTo = alignTo;
    }

    private Array<Sprite> getSpriteNumberArray() {
        return spriteNumberArray;
    }

    public enum AlignTo {
        LEFT, CENTER, RIGHT
    }
}
