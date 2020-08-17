package dalcoms.lib.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SpriteSimpleButton extends SpriteGameObject {
    private Viewport viewport;
    private float onTouchScale = 1.2f;
    private Color onTouchColor = new Color(0f, 0f, 0f, 1f);
    private Color originColor;
    private final float touchEffectVarTime = 0.25f;
    private boolean touched = false;
    private OnTouchEffect onTouchEffect = OnTouchEffect.SCALE;
    private SpriteGameObject sgoTouchHolo;

    public SpriteSimpleButton(Texture texture, Viewport viewport, float locationX,
            float locationY) {
        super(texture, locationX, locationY);
        this.viewport = viewport;
    }

    public SpriteSimpleButton(Texture texture, Viewport viewport, SpriteBatch spriteBatch,
            float locationX, float locationY) {
        super(texture, locationX, locationY);
        this.viewport = viewport;
        setSpriteBatch(spriteBatch);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (isTouched()) {
            checkIsTouched();
            if (getOnTouchEffect() == OnTouchEffect.HOLO) {
                drawTouchedHoloEffect(delta);
            }
        }
    }

    @Override
    public void enterTouchDown() {
        super.enterTouchDown();
        setTouched(true);
    }

    private void drawTouchedHoloEffect(float delta) {
        if (getSgoTouchHolo() != null) {
            getSgoTouchHolo().render(delta);
        }
    }

    private void checkIsTouched() {
        Vector2 touchPos = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        setTouched(Gdx.input.isTouched() & isInTouchArea(touchPos.x, touchPos.y));
    }

    public float getOnTouchScale() {
        return onTouchScale;
    }

    public void setOnTouchScale(float onTouchScale) {
        this.onTouchScale = onTouchScale;
    }

    public Color getOnTouchColor() {
        return onTouchColor;
    }

    public void setOnTouchColor(Color onTouchColor) {
        this.onTouchColor = onTouchColor;
    }

    public void setOnTouchColor(Color onTouchColor, Color originColor) {
        this.onTouchColor = onTouchColor;
        this.originColor = originColor;
    }

    public Color getOriginColor() {
        return originColor;
    }

    public void setOriginColor(Color originColor) {
        this.originColor = originColor;
    }

    public SpriteGameObject getSgoTouchHolo() {
        return sgoTouchHolo;
    }

    public void setSgoTouchHolo(SpriteGameObject sgoTouchHolo) {
        this.sgoTouchHolo = sgoTouchHolo;
        this.sgoTouchHolo.setCenterLocation(getCenterLocationX(), getCenterLocationY());
        this.sgoTouchHolo.setSpriteBatch(getSpriteBatch());
    }

    public OnTouchEffect getOnTouchEffect() {
        return onTouchEffect;
    }

    public void setOnTouchEffect(OnTouchEffect onTouchEffect) {
        this.onTouchEffect = onTouchEffect;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        if (this.touched != touched) { // Apply effect only when touch status is changed.
            checkTouchEffect(touched);
        }
        this.touched = touched;
    }

    private void touchHoloEffect(boolean isTouched) {
        if (isTouched) {
            Array<Var2TimePair> scalePath = new Array<>();
            scalePath.add(new Var2TimePair(0.8f, 0.8f, 0f));
            scalePath.add(new Var2TimePair(1f, 1f, this.touchEffectVarTime));
            getSgoTouchHolo().scale(scalePath);
            getSgoTouchHolo().setVisible(true);
        } else {
            getSgoTouchHolo().scale(0.2f, 0.2f, this.touchEffectVarTime);
            getSgoTouchHolo().setVisible(false);
        }
    }

    private void touchScaleEffect(boolean isTouched) {
        if (isTouched) {
            scale(getOnTouchScale(), getOnTouchScale(), this.touchEffectVarTime);
        } else {
            scale(1f, 1f, this.touchEffectVarTime);
        }
    }

    private void touchColorEffect(boolean isTouched) {
        if (isTouched) {
            paint(getOnTouchColor().r, getOnTouchColor().g, getOnTouchColor().b,
                  getOnTouchColor().a, this.touchEffectVarTime);
        } else {
            paint(getOriginColor().r, getOriginColor().g, getOriginColor().b,
                  getOriginColor().a, this.touchEffectVarTime);
        }
    }

    private void checkTouchEffect(boolean isTouched) {
        switch (getOnTouchEffect()) {
            case HOLO:
                touchHoloEffect(isTouched);
                break;
            case SCALE:
                touchScaleEffect(isTouched);
                break;
            case COLOR:
                touchColorEffect(isTouched);
                break;
        }
    }

    public enum OnTouchEffect {
        HOLO, SCALE, COLOR
    }
}
