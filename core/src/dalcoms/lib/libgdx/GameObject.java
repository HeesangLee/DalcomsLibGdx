package dalcoms.lib.libgdx;

import com.badlogic.gdx.utils.Array;

import dalcoms.lib.libgdx.easingfunctions.EaseLinear;
import dalcoms.lib.libgdx.easingfunctions.IEasingFunction;

public class GameObject implements Renderable, IGestureInput {
    private Array<String> tags;
    private int index = -1;
    private int indexA = -1;
    private int indexB = -1;
    private int indexC = -1;
    private Object userData;
    private float locationX = 0f, locationY = 0f, width = 0f, height = 0f, scaleX = 1f, scaleY = 1f;
    private float rotationAngle = 0f;
    private float colorR = 1f, colorG = 1f, colorB = 1f, colorA = 1f;
    private float touchScale = 1f;
    private TouchAreaType touchAreaType = TouchAreaType.RECTANGLE;
    private VariationPerTime vptMoveX, vptMoveY, vptRotate, vptPaintR, vptPaintG, vptPaintB,
            vptPaintA, vptScaleX, vptScaleY;
    private Array<Renderable> renderables;
    private VariationPerTime.EventListener elMoveX, elMoveY, elRotate, elPaintR, elPaintG, elPaintB,
            elPaintA, elScaleX, elScaleY; //el - Event Listener
    private GameGestureListener.TouchDownEventListener elTouchDown;
    private GameGestureListener.TouchUpEventListener elTouchUp;
    private GameGestureListener.TabEventListener elTab;
    private GameGestureListener.LongPressEventListener elLongPress;
    private GameGestureListener.TouchDraggedEventListener elTouchDragged;

    private boolean onMovingX = false, onMovingY = false, onRotating = false, onPaintingR = false,
            onPaintingG = false, onPaintingB = false, onPaintingA = false, onScalingX = false,
            onScalingY = false;

    public GameObject() {
        renderables = new Array<>();
        initVariationPerTime();
        tags = new Array<>();
    }

    public GameObject(float locationX, float locationY, float width, float height) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = width;
        this.height = height;

        renderables = new Array<>();
        initVariationPerTime();
        tags = new Array<>();
    }

    private void initVariationPerTime() {
        vptMoveX = new VariationPerTime();
        vptMoveY = new VariationPerTime();
        vptRotate = new VariationPerTime();
        vptPaintR = new VariationPerTime();
        vptPaintG = new VariationPerTime();
        vptPaintB = new VariationPerTime();
        vptPaintA = new VariationPerTime();
        vptScaleX = new VariationPerTime();
        vptScaleY = new VariationPerTime();

        setEventListenerVpt();
        addVptToRenderables();
    }

    private void setEventListenerVptMoveX() {
        vptMoveX.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setLocationX(curVar);
                if (elMoveX != null) {
                    elMoveX.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elMoveX != null) {
                    elMoveX.onStart(curTime);
                }
                onMovingX = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elMoveX != null) {
                    elMoveX.onFinish(curTime, curVar);
                }
                onMovingX = false;
            }
        });
    }

    private void setEventListenerVptMoveY() {
        vptMoveY.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setLocationY(curVar);
                if (elMoveY != null) {
                    elMoveY.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elMoveY != null) {
                    elMoveY.onStart(curTime);
                }
                onMovingY = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elMoveY != null) {
                    elMoveY.onFinish(curTime, curVar);
                }
                onMovingY = false;
            }
        });
    }

    private void setEventListenerVptRotate() {
        vptRotate.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setRotationAngle(curVar);
                if (elRotate != null) {
                    elRotate.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elRotate != null) {
                    elRotate.onStart(curTime);
                }
                onRotating = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elRotate != null) {
                    elRotate.onFinish(curTime, curVar);
                }
                onRotating = false;
            }
        });
    }

    private void setEventListenerVptPaintR() {
        vptPaintR.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setColorR(curVar);
                if (elPaintR != null) {
                    elPaintR.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elPaintR != null) {
                    elPaintR.onStart(curTime);
                }
                onPaintingR = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elPaintR != null) {
                    elPaintR.onFinish(curTime, curVar);
                }
                onPaintingR = false;
            }
        });
    }

    private void setEventListenerVptPaintG() {
        vptPaintG.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setColorG(curVar);
                if (elPaintG != null) {
                    elPaintG.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elPaintG != null) {
                    elPaintG.onStart(curTime);
                }
                onPaintingG = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elPaintG != null) {
                    elPaintG.onFinish(curTime, curVar);
                }
                onPaintingG = false;
            }
        });
    }

    private void setEventListenerVptPaintB() {
        vptPaintB.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setColorB(curVar);
                if (elPaintB != null) {
                    elPaintB.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elPaintB != null) {
                    elPaintB.onStart(curTime);
                }
                onPaintingB = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elPaintB != null) {
                    elPaintB.onFinish(curTime, curVar);
                }
                onPaintingB = false;
            }
        });
    }

    private void setEventListenerVptPaintA() {
        vptPaintA.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setColorA(curVar);
                if (elPaintA != null) {
                    elPaintA.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elPaintA != null) {
                    elPaintA.onStart(curTime);
                }
                onPaintingA = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elPaintA != null) {
                    elPaintA.onFinish(curTime, curVar);
                }
                onPaintingA = false;
            }
        });
    }

    private void setEventListenerVptScaleX() {
        vptScaleX.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setScaleX(curVar);
                if (elScaleX != null) {
                    elScaleX.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elScaleX != null) {
                    elScaleX.onStart(curTime);
                }
                onScalingX = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elScaleX != null) {
                    elScaleX.onFinish(curTime, curVar);
                }
                onScalingX = false;
            }
        });
    }

    private void setEventListenerVptScaleY() {
        vptScaleY.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                setScaleY(curVar);
                if (elScaleY != null) {
                    elScaleY.onUpdate(curTime, curVar);
                }
            }

            @Override
            public void onStart(float curTime) {
                if (elScaleY != null) {
                    elScaleY.onStart(curTime);
                }
                onScalingY = true;
            }

            @Override
            public void onFinish(float curTime, float curVar) {
                if (elScaleY != null) {
                    elScaleY.onFinish(curTime, curVar);
                }
                onScalingY = false;
            }
        });
    }


    private void setEventListenerVpt() {
        setEventListenerVptMoveX();
        setEventListenerVptMoveY();
        setEventListenerVptRotate();
        setEventListenerVptPaintR();
        setEventListenerVptPaintG();
        setEventListenerVptPaintB();
        setEventListenerVptPaintA();
        setEventListenerVptScaleX();
        setEventListenerVptScaleY();
    }

    private void addVptToRenderables() {
        renderables.add(vptMoveX);
        renderables.add(vptMoveY);
        renderables.add(vptRotate);
        renderables.add(vptPaintR);
        renderables.add(vptPaintG);
        renderables.add(vptPaintB);
        renderables.add(vptPaintA);
        renderables.add(vptScaleX);
        renderables.add(vptScaleY);
    }

    public void setEventListenerMoveX(VariationPerTime.EventListener eventListener) {
        elMoveX = eventListener;
    }

    public void setEventListenerMoveY(VariationPerTime.EventListener eventListener) {
        elMoveY = eventListener;
    }

    public void setEventListenerRotate(VariationPerTime.EventListener eventListener) {
        elRotate = eventListener;
    }

    public void setEventListenerPaintR(VariationPerTime.EventListener eventListener) {
        elPaintR = eventListener;
    }

    public void setEventListenerPaintG(VariationPerTime.EventListener eventListener) {
        elPaintG = eventListener;
    }

    public void setEventListenerPaintB(VariationPerTime.EventListener eventListener) {
        elPaintB = eventListener;
    }

    public void setEventListenerPaintA(VariationPerTime.EventListener eventListener) {
        elPaintA = eventListener;
    }

    public void setEventListenerScaleX(VariationPerTime.EventListener eventListener) {
        elScaleX = eventListener;
    }

    public void setEventListenerScaleY(VariationPerTime.EventListener eventListener) {
        elScaleY = eventListener;
    }


    public void setEventListenerTouchDown(GameGestureListener.TouchDownEventListener elTouchDown) {
        this.elTouchDown = elTouchDown;
    }

    public void setEventListenerTouchUp(GameGestureListener.TouchUpEventListener elTouchUp) {
        this.elTouchUp = elTouchUp;
    }

    public void setEventListenerTab(GameGestureListener.TabEventListener elTab) {
        this.elTab = elTab;
    }

    public void setEventListenerLongPress(GameGestureListener.LongPressEventListener elLongPress) {
        this.elLongPress = elLongPress;
    }

    public void setEventListenerTouchDragged(
            GameGestureListener.TouchDraggedEventListener elTouchDragged) {
        this.elTouchDragged = elTouchDragged;
    }

    public boolean isInTouchArea(float touchX, float touchY) {
        float x0 = getCenterLocationX() - getTouchScale() * getWidth() / 2f;
        float x1 = x0 + getTouchScale() * getWidth();
        float y0 = getCenterLocationY() - getTouchScale() * getHeight() / 2f;
        float y1 = y0 + getTouchScale() * getHeight();

        if (getTouchAreaType() == TouchAreaType.RECTANGLE) {
            return touchX >= x0 & touchX <= x1 & touchY >= y0 & touchY <= y1;
        } else if (getTouchAreaType() == TouchAreaType.CIRCLE_WIDTH) {
            return Math.sqrt(Math.pow(touchX - getCenterLocationX(), 2) +
                             Math.pow(touchY - getCenterLocationY(), 2)) <
                   getWidth() * getTouchScale() / 2f;
        } else if (getTouchAreaType() == TouchAreaType.CIRCLE_HEIGHT) {
            return Math.sqrt(Math.pow(touchX - getCenterLocationX(), 2) +
                             Math.pow(touchY - getCenterLocationY(), 2)) <
                   getHeight() * getTouchScale() / 2f;
        } else {
            return false;
        }
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
//        Gdx.app.log("DebTestScreen", "x1 : " + x + "\ty1 : " + y);
//        if (isInTouchArea(x, y) & elTouchDown != null) {
//            elTouchDown.onEvent(x, y, pointer, button);
//            return true;
//        }
        if (isInTouchArea(x, y)) {
            enterTouchDown();
            if (elTouchDown != null) {
                elTouchDown.onEvent(x, y, pointer, button);
                return true;
            }
        }
        return false;
    }

    public void enterTouchDown() {

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        Gdx.app.log("DebTestScreen", "UP x1 : " + screenX + "\ty1 : " + screenY);
        if (isInTouchArea(screenX, screenY) & elTouchUp != null) {
            elTouchUp.onEvent(screenX, screenY, pointer, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
//        Gdx.app.log("DebTestScreen", "tap x1 : " + x + "\ty1 : " + y);
        if (isInTouchArea(x, y) & elTab != null) {
            elTab.onEvent(x, y, count, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
//        Gdx.app.log("DebTestScreen", "longPress x1 : " + x + "\ty1 : " + y);
        if (isInTouchArea(x, y) & elLongPress != null) {
            elLongPress.onEvent(x, y);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        Gdx.app.log("DebTestScreen", "TouchDragged x1 : " + screenX + "\ty1 : " + screenY);
        if (isInTouchArea(screenX, screenY) & elTouchDragged != null) {
            elTouchDragged.onEvent(screenX, screenY, pointer);
            return true;
        }
        return false;
    }


    public void move(float toX, float toY, float time) {
        move(toX, toY, time, EaseLinear.getInstance());
    }

    public void move(float toX, float toY, float time, IEasingFunction easingFunction) {
        move(new Var2TimePair(toX, toY, time), easingFunction);
    }


    public void move(Var2TimePair toXYT) {
        move(toXYT, EaseLinear.getInstance());
    }

    public void move(Var2TimePair toXYT, IEasingFunction easingFunction) {
        Array<Var2TimePair> pairs = new Array<>();
        pairs.add(new Var2TimePair(getLocationX(), getLocationY(), 0f), toXYT);
        move(pairs, easingFunction);
    }

    public void move(Array<Var2TimePair> movingPath) {
        move(movingPath, EaseLinear.getInstance());
    }

    public void move(Array<Var2TimePair> movingPath, IEasingFunction easingFunction) {
        Array<VarTimePair> movingPathX = new Array<>();
        Array<VarTimePair> movingPathY = new Array<>();

        for (Var2TimePair var2TimePair : movingPath) {
            movingPathX.add(var2TimePair.getVar1TimePair());
            movingPathY.add(var2TimePair.getVar2TimePair());
        }
        vptMoveX.stop(true);
        vptMoveY.stop(true);

        vptMoveX.setVarTimeArray(movingPathX);
        vptMoveY.setVarTimeArray(movingPathY);

        vptMoveX.start(easingFunction);
        vptMoveY.start(easingFunction);
    }


    public void moveX(float toX, float time) {
        moveX(toX, time, EaseLinear.getInstance());
    }

    public void moveX(float toX, float time, IEasingFunction easingFunction) {
        moveX(new VarTimePair(toX, time), easingFunction);
    }


    public void moveX(VarTimePair toX) {
        moveX(toX, EaseLinear.getInstance());
    }

    public void moveX(VarTimePair toX, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getLocationX(), 0f), toX);
        moveX(pairs, easingFunction);
    }

    public void moveX(Array<VarTimePair> movingPath) {
        moveX(movingPath, EaseLinear.getInstance());
    }

    public void moveX(Array<VarTimePair> movingPath, IEasingFunction easingFunction) {
        vptMoveX.stop(true);
        vptMoveX.setVarTimeArray(movingPath); // vptMoveX.stop(true)과 Array 초기화 중복.. 크게 성능에 상관은 없을 듯
        vptMoveX.start(easingFunction);
    }

    public void moveY(float toY, float time) {
        moveY(toY, time, EaseLinear.getInstance());
    }

    public void moveY(float toY, float time, IEasingFunction easingFunction) {
        moveY(new VarTimePair(toY, time), easingFunction);
    }


    public void moveY(VarTimePair toY) {
        moveY(toY, EaseLinear.getInstance());
    }

    public void moveY(VarTimePair toY, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getLocationY(), 0f), toY);
        moveY(pairs, easingFunction);
    }

    public void moveY(Array<VarTimePair> movingPath) {
        moveY(movingPath, EaseLinear.getInstance());
    }

    public void moveY(Array<VarTimePair> movingPath, IEasingFunction easingFunction) {
        vptMoveY.stop(true);
        vptMoveY.setVarTimeArray(movingPath);
        vptMoveY.start(easingFunction);
    }

    public void moveUniformly(float velocityX, float velocityY) {

    }

    public void moveXUniformly(float velocityX) {

    }

    public void moveYUniformly(float velocityY) {

    }

    public void rotate(float toAngle, float time) {
        rotate(toAngle, time, EaseLinear.getInstance());
    }

    public void rotate(float toAngle, float time, IEasingFunction easingFunction) {
        rotate(new VarTimePair(toAngle, time), easingFunction);
    }

    public void rotate(VarTimePair toAngle) {
        rotate(toAngle, EaseLinear.getInstance());
    }

    public void rotate(VarTimePair toAngle, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getRotationAngle(), 0f), toAngle);
        rotate(pairs, easingFunction);
    }

    public void rotate(Array<VarTimePair> variationPath) {
        rotate(variationPath, EaseLinear.getInstance());
    }

    public void rotate(Array<VarTimePair> variationPath, IEasingFunction easingFunction) {
        vptRotate.stop(true);
        vptRotate.setVarTimeArray(variationPath);
        vptRotate.start(easingFunction);
    }

    public void paint(float toR, float toG, float toB, float toA, float time) {
        paint(toR, toG, toB, toA, time, EaseLinear.getInstance());
    }

    public void paint(float toR, float toG, float toB, float toA, float time,
            IEasingFunction easingFunction) {
        paint(new Var4TimePair(toR, toG, toB, toA, time), easingFunction);
    }

    public void paint(Var4TimePair toRGBAT) {
        paint(toRGBAT, EaseLinear.getInstance());
    }

    public void paint(Var4TimePair toRGBAT, IEasingFunction easingFunction) {
        Array<Var4TimePair> pairs = new Array<>();
        pairs.add(new Var4TimePair(getColorR(), getColorG(), getColorB(), getColorA(), 0f),
                  toRGBAT);
        paint(pairs, easingFunction);
    }

    public void paint(Array<Var4TimePair> colorPath) {
        paint(colorPath, EaseLinear.getInstance());
    }

    public void paint(Array<Var4TimePair> colorPath, IEasingFunction easingFunction) {
        Array<VarTimePair> pathR = new Array<>();
        Array<VarTimePair> pathG = new Array<>();
        Array<VarTimePair> pathB = new Array<>();
        Array<VarTimePair> pathA = new Array<>();

        for (Var4TimePair var4TimePair : colorPath) {
            pathR.add(var4TimePair.getVar1TimePair());
            pathG.add(var4TimePair.getVar2TimePair());
            pathB.add(var4TimePair.getVar3TimePair());
            pathA.add(var4TimePair.getVar4TimePair());
        }
        vptPaintR.stop(true);
        vptPaintG.stop(true);
        vptPaintB.stop(true);
        vptPaintA.stop(true);

        vptPaintR.setVarTimeArray(pathR);
        vptPaintG.setVarTimeArray(pathG);
        vptPaintB.setVarTimeArray(pathB);
        vptPaintA.setVarTimeArray(pathA);

        vptPaintR.start(easingFunction);
        vptPaintG.start(easingFunction);
        vptPaintB.start(easingFunction);
        vptPaintA.start(easingFunction);

    }


    public void paintR(float toColorR, float time) {
        paintR(new VarTimePair(toColorR, time), EaseLinear.getInstance());
    }

    public void paintR(float toColorR, float time, IEasingFunction easingFunction) {
        paintR(new VarTimePair(toColorR, time), easingFunction);
    }

    public void paintR(VarTimePair toColorR) {
        paintR(toColorR, EaseLinear.getInstance());
    }

    public void paintR(VarTimePair toColorR, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getColorR(), 0f), toColorR);
        paintR(pairs, easingFunction);
    }

    public void paintR(Array<VarTimePair> variationPath) {
        paintR(variationPath, EaseLinear.getInstance());
    }

    public void paintR(Array<VarTimePair> variationPath, IEasingFunction easingFunction) {
        vptPaintR.stop(true);
        vptPaintR.setVarTimeArray(variationPath);
        vptPaintR.start(easingFunction);
    }

    public void paintG(float toColorG, float time) {
        paintG(new VarTimePair(toColorG, time), EaseLinear.getInstance());
    }

    public void paintG(float toColorG, float time, IEasingFunction easingFunction) {
        paintG(new VarTimePair(toColorG, time), easingFunction);
    }

    public void paintG(VarTimePair toColorG) {
        paintG(toColorG, EaseLinear.getInstance());
    }

    public void paintG(VarTimePair toColorG, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getColorG(), 0f), toColorG);
        paintG(pairs, easingFunction);
    }

    public void paintG(Array<VarTimePair> variationPath) {
        paintG(variationPath, EaseLinear.getInstance());
    }

    public void paintG(Array<VarTimePair> variationPath, IEasingFunction easingFunction) {
        vptPaintG.stop(true);
        vptPaintG.setVarTimeArray(variationPath);
        vptPaintG.start(easingFunction);
    }

    public void paintB(float toColorB, float time) {
        paintB(new VarTimePair(toColorB, time), EaseLinear.getInstance());
    }

    public void paintB(float toColorB, float time, IEasingFunction easingFunction) {
        paintB(new VarTimePair(toColorB, time), easingFunction);
    }

    public void paintB(VarTimePair toColorB) {
        paintB(toColorB, EaseLinear.getInstance());
    }

    public void paintB(VarTimePair toColorB, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getColorB(), 0f), toColorB);
        paintB(pairs, easingFunction);
    }

    public void paintB(Array<VarTimePair> variationPath) {
        paintB(variationPath, EaseLinear.getInstance());
    }

    public void paintB(Array<VarTimePair> variationPath, IEasingFunction easingFunction) {
        vptPaintB.stop(true);
        vptPaintB.setVarTimeArray(variationPath);
        vptPaintB.start(easingFunction);
    }

    public void paintA(float toColorA, float time) {
        paintA(new VarTimePair(toColorA, time), EaseLinear.getInstance());
    }

    public void paintA(float toColorA, float time, IEasingFunction easingFunction) {
        paintA(new VarTimePair(toColorA, time), easingFunction);
    }

    public void paintA(VarTimePair toColorA) {
        paintA(toColorA, EaseLinear.getInstance());
    }

    public void paintA(VarTimePair toColorA, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getColorA(), 0f), toColorA);
        paintA(pairs, easingFunction);
    }

    public void paintA(Array<VarTimePair> variationPath) {
        paintA(variationPath, EaseLinear.getInstance());
    }

    public void paintA(Array<VarTimePair> variationPath, IEasingFunction easingFunction) {
        vptPaintA.stop(true);
        vptPaintA.setVarTimeArray(variationPath);
        vptPaintA.start(easingFunction);
    }

    public void scale(float toScaleX, float toScaleY, float time) {
        scale(toScaleX, toScaleY, time, EaseLinear.getInstance());
    }

    public void scale(float toScaleX, float toScaleY, float time, IEasingFunction
            easingFunction) {
        scale(new Var2TimePair(toScaleX, toScaleY, time), easingFunction);
    }


    public void scale(Var2TimePair toScaleXYT) {
        scale(toScaleXYT, EaseLinear.getInstance());
    }

    public void scale(Var2TimePair toScaleXYT, IEasingFunction easingFunction) {
        Array<Var2TimePair> pairs = new Array<>();
        pairs.add(new Var2TimePair(getScaleX(), getScaleY(), 0f), toScaleXYT);
        scale(pairs, easingFunction);
    }

    public void scale(Array<Var2TimePair> scalePath) {
        scale(scalePath, EaseLinear.getInstance());
    }

    public void scale(Array<Var2TimePair> scalePath, IEasingFunction easingFunction) {
        Array<VarTimePair> scalePathX = new Array<>();
        Array<VarTimePair> scalePathY = new Array<>();

        for (Var2TimePair var2TimePair : scalePath) {
            scalePathX.add(var2TimePair.getVar1TimePair());
            scalePathY.add(var2TimePair.getVar2TimePair());
        }
        vptScaleX.stop(true);
        vptScaleY.stop(true);

        vptScaleX.setVarTimeArray(scalePathX);
        vptScaleY.setVarTimeArray(scalePathY);

        vptScaleX.start(easingFunction);
        vptScaleY.start(easingFunction);
    }

    public void scaleX(float toScaleX, float time) {
        scaleX(toScaleX, time, EaseLinear.getInstance());
    }

    public void scaleX(float toScaleX, float time, IEasingFunction easingFunction) {
        scaleX(new VarTimePair(toScaleX, time), easingFunction);
    }

    public void scaleX(VarTimePair toScaleX) {
        scaleX(toScaleX, EaseLinear.getInstance());
    }

    public void scaleX(VarTimePair toScaleX, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getScaleX(), 0f), toScaleX);
        scaleX(pairs, easingFunction);
    }

    public void scaleX(Array<VarTimePair> VariationPath) {
        scaleX(VariationPath, EaseLinear.getInstance());
    }

    public void scaleX(Array<VarTimePair> VariationPath, IEasingFunction easingFunction) {
        vptScaleX.stop(true);
        vptScaleX.setVarTimeArray(VariationPath);
        vptScaleX.start(easingFunction);
    }

    public void scaleY(float toScaleY, float time, IEasingFunction easingFunction) {
        scaleY(new VarTimePair(toScaleY, time), easingFunction);
    }

    public void scaleY(VarTimePair toScaleY) {
        scaleY(toScaleY, EaseLinear.getInstance());
    }

    public void scaleY(VarTimePair toScaleY, IEasingFunction easingFunction) {
        Array<VarTimePair> pairs = new Array<>();
        pairs.add(new VarTimePair(getScaleY(), 0f), toScaleY);
        scaleY(pairs, easingFunction);
    }

    public void scaleY(Array<VarTimePair> VariationPath) {
        scaleY(VariationPath, EaseLinear.getInstance());
    }

    public void scaleY(Array<VarTimePair> VariationPath, IEasingFunction easingFunction) {
        vptScaleY.stop(true);
        vptScaleY.setVarTimeArray(VariationPath);
        vptScaleY.start(easingFunction);
    }

    @Override
    public void render(float delta) {
        for (Renderable renderable : renderables) {
            renderable.render(delta);
        }
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public void setLocation(float locationX, float locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void setLocation(Point2DFloat location) {
        if (location != null) {
            setLocation(location.getX(), location.getY());
        }
    }

    public void setCenterLocationX(float x) {
        setLocationX(x - getWidth() / 2f);
    }

    public void setCenterLocationY(float y) {
        setLocationY(y - getHeight() / 2f);
    }

    public void setCenterLocation(float x, float y) {
        setCenterLocationX(x);
        setCenterLocationY(y);
    }


    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Point2DFloat getLocation() {
        return new Point2DFloat(getLocationX(), getLocationY());
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getCenterLocationX() {
        return getLocationX() + getWidth() / 2f;
    }

    public float getCenterLocationY() {
        return getLocationY() + getHeight() / 2f;
    }

    public Point2DFloat getCenterLocation() {
        return new Point2DFloat(getCenterLocationX(), getCenterLocationY());
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(Point2DFloat size) {
        if (size != null) {
            setSize(size.getX(), size.getY());
        }
    }

    public Point2DFloat getSize() {
        return new Point2DFloat(getWidth(), getHeight());
    }


    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
//        setWidth(getWidth() * getScaleX());
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
//        setHeight(getHeight() * getScaleY());
    }

    public void setScale(float scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

    public float getColorR() {
        return colorR;
    }

    public void setColorR(float colorR) {
        this.colorR = colorR;
    }

    public float getColorG() {
        return colorG;
    }

    public void setColorG(float colorG) {
        this.colorG = colorG;
    }

    public float getColorB() {
        return colorB;
    }

    public void setColorB(float colorB) {
        this.colorB = colorB;
    }

    public float getColorA() {
        return colorA;
    }

    public void setColorA(float colorA) {
        this.colorA = colorA;
    }

    public void setColor(float colorR, float colorG, float colorB, float colorA) {
        setColorR(colorR);
        setColorG(colorG);
        setColorB(colorB);
        setColorA(colorA);
    }

    public boolean isOnMoving() {
        return onMovingX | onMovingY;
    }

    public boolean isOnMovingX() {
        return onMovingX;
    }

    public boolean isOnMovingY() {
        return onMovingY;
    }

    public boolean isOnRotating() {
        return onRotating;
    }

    public boolean isOnPainting() {
        return onPaintingR | onPaintingG | onPaintingB;
    }

    public boolean isOnPaintingA() {
        return onPaintingA;
    }

    public boolean isOnScaling() {
        return onScalingX | onScalingY;
    }

    public boolean isOnScalingX() {
        return onScalingX;
    }

    public boolean isOnScalingY() {
        return onScalingY;
    }

    public float getTouchScale() {
        return touchScale;
    }

    public void setTouchScale(float touchScale) {
        this.touchScale = touchScale;
    }

    public TouchAreaType getTouchAreaType() {
        return touchAreaType;
    }

    public void setTouchAreaType(TouchAreaType touchAreaType) {
        this.touchAreaType = touchAreaType;
    }

    public Array<String> getTags() {
        return tags;
    }

    public void setTags(Array<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void addTag(String tag1, String tag2) {
        tags.add(tag1);
        tags.add(tag2);
    }

    public void addTag(String tag1, String tag2, String tag3) {
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
    }

    public void addTag(String tag1, String tag2, String tag3, String tag4) {
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
    }

    /**
     * @param tag      String to compare if is is included in tags array.
     * @param identity If true, == comparison will be used. If false, .equals() comparison will be used.
     * @return true if tag is found
     */
    public boolean hasTag(String tag, boolean identity) {
        boolean ret = false;

        for (String tagIn : getTags()) {
            if (identity) {
                if (tag == tagIn) {
                    ret = true;
                    break;
                }
            } else {
                if (tag.equals(tagIn)) {
                    ret = true;
                    break;
                }
            }

        }

        return ret;
    }

    public boolean haTag(String tag) {
        boolean ret = false;
        for (String tagIn : getTags()) {
            if ((tag == tagIn) | tag.equals(tagIn)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public void clearTags() {
        tags = new Array<>();
    }

    /**
     * @param tag      remove if it is found
     * @param identity If true, == comparison will be used. If false, .equals() comparison will be used.
     * @return true if it was found and removed
     */
    public boolean removeTag(String tag, boolean identity) {
        return tags.removeValue(tag, identity);
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    public void resetUserData() {
        this.userData = null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndexA() {
        return indexA;
    }

    public void setIndexA(int indexA) {
        this.indexA = indexA;
    }

    public int getIndexB() {
        return indexB;
    }

    public void setIndexB(int indexB) {
        this.indexB = indexB;
    }

    public int getIndexC() {
        return indexC;
    }

    public void setIndexC(int indexC) {
        this.indexC = indexC;
    }

    public void stopMove(boolean initialState) {
        stopMoveX(initialState);
        stopMoveY(initialState);
    }

    public void stopAllMotionScalePaint(boolean initialState) {
        stopMove(initialState);
        stopPaint(initialState);
        stopScale(initialState);
    }

    public void stopMoveX(boolean initialState) {
        vptMoveX.stop(initialState);
    }

    public void stopMoveY(boolean initialState) {
        vptMoveY.stop(initialState);
    }

    public void stopRotate(boolean initialState) {
        vptRotate.stop(initialState);
    }

    public void stopPaint(boolean initialState) {
        stopPaintR(initialState);
        stopPaintG(initialState);
        stopPaintB(initialState);
        stopPaintA(initialState);
    }

    public void stopPaintR(boolean initialState) {
        vptPaintR.stop(initialState);
    }

    public void stopPaintG(boolean initialState) {
        vptPaintG.stop(initialState);
    }

    public void stopPaintB(boolean initialState) {
        vptPaintB.stop(initialState);
    }

    public void stopPaintA(boolean initialState) {
        vptPaintA.stop(initialState);
    }

    public void stopScale(boolean initialState) {
        stopScaleX(initialState);
        stopScaleY(initialState);
    }

    public void stopScaleX(boolean initialState) {
        vptScaleX.stop(initialState);
    }

    public void stopScaleY(boolean initialState) {
        vptScaleY.stop(initialState);
    }

    public enum TouchAreaType {
        RECTANGLE, CIRCLE_WIDTH, CIRCLE_HEIGHT
//        CIRCLE_WIDTH : Radius of the circle is half the width
//        CIRCLE_HEIGHT : Radius of the circle is half the height
    }
}
