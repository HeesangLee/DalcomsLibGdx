package dalcoms.lib.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dalcoms.lib.libgdx.easingfunctions.EaseBackIn;
import dalcoms.lib.libgdx.easingfunctions.EaseBounceIn;
import dalcoms.lib.libgdx.easingfunctions.EaseBounceInOut;
import dalcoms.lib.libgdx.easingfunctions.EaseBounceOut;
import dalcoms.lib.libgdx.easingfunctions.EaseCircIn;

public class LibTestScreen implements Screen {
    private final HsGdxLibGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Array<Renderable> renderables;
    private Array<IGestureInput> gestureDetectables;
    private final String strDebug = "DebTestScreen";

    public LibTestScreen(HsGdxLibGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        this.viewport = new FitViewport(1080, 1920, camera);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void show() {
        renderables = new Array<>();
        gestureDetectables = new Array<>();
        initGameObject();
//        testVariationPerTimeClass();
//        testGameObjectClass();
//        testSpriteGameObject();
        setInputProcessor();
//        testTouch();
//        testOverrideSetter();
//        testScale();
        testNumSprite();
        testSpriteButton();
    }

    @Override
    public void render(float delta) {
        draw(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void initGameObject() {
    }

    private void draw(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();

        for (Renderable renderable : renderables) {
            renderable.render(delta);
        }

        game.getSpriteBatch().end();
    }

    private void testScale() {
        SpriteGameObject sgoWhite =
                new SpriteGameObject(new Texture(Gdx.files.internal("test_img_white.png")), 0, 0)
                        .setSpriteBatch(this.game.getSpriteBatch());
        renderables.add(sgoWhite);
        sgoWhite.scaleX(1.2f, 3f);
        sgoWhite.setColor(new Color(0x591b1bff));
    }

    private void testSpriteButton() {
        Texture rect_1 = new Texture(Gdx.files.internal("rect_1.png"));
        SpriteSimpleButton btn1 =
                new SpriteSimpleButton(rect_1, viewport, this.game.getSpriteBatch(), 300f, 900f);
        renderables.add(btn1);
        gestureDetectables.add(btn1);
        btn1.setEventListenerTouchDown(new GameGestureListener.TouchDownEventListener() {
            @Override
            public void onEvent(float x, float y, int pointer, int button) {
                Gdx.app.log(strDebug, "btn1 is touched");
            }
        });
        btn1.setOnTouchColor(new Color(0xff0000ff), new Color(0xffffffff));
        btn1.setOnTouchEffect(SpriteSimpleButton.OnTouchEffect.HOLO);
        SpriteGameObject holo =
                new SpriteGameObject(new Texture(Gdx.files.internal("circle_112px.png")), 0, 0);
        holo.setColorA(0.3f);
        btn1.setSgoTouchHolo(holo);
    }

    private void testNumSprite() {
        final GameTimer gameTimer = new GameTimer().start();


        Array<Texture> tNum = new Array<>();
        tNum.add(new Texture(Gdx.files.internal("num_0.png")));
        tNum.add(new Texture(Gdx.files.internal("num_1.png")));
        tNum.add(new Texture(Gdx.files.internal("num_2.png")));
        tNum.add(new Texture(Gdx.files.internal("num_3.png")));
        tNum.add(new Texture(Gdx.files.internal("num_4.png")));
        tNum.add(new Texture(Gdx.files.internal("num_5.png")));
        tNum.add(new Texture(Gdx.files.internal("num_6.png")));
        tNum.add(new Texture(Gdx.files.internal("num_7.png")));
        tNum.add(new Texture(Gdx.files.internal("num_8.png")));
        tNum.add(new Texture(Gdx.files.internal("num_9.png")));

        final SpriteNumber spNum =
                new SpriteNumber(tNum, (int) gameTimer.getCurTimeSec(), 100, 100)
                        .setSpriteBatch(this.game.getSpriteBatch());
//        spNum.move(500, 500, 1f, EaseBackIn.getInstance());
        spNum.rotate(360, 3f);
        spNum.paint(1f, 0.5f, 0.5f, 1f, 3f);
        spNum.setAlignTo(SpriteNumber.AlignTo.CENTER);
        spNum.setCenterLocationX(viewport.getWorldWidth()/2f);
//        spNum.setEventListenerRotate(new VariationPerTime.EventListener() {
//            @Override
//            public void onUpdate(float curTime, float curVar) {
//
//            }
//
//            @Override
//            public void onStart(float curTime) {
//
//            }
//
//            @Override
//            public void onFinish(float curTime) {
//                spNum.paint(1f, 1f, 1f, 1f, 5f);
//                spNum.move(100, 700, 3f);
//            }
//        });
        gameTimer.setEventListener(new GameTimer.EventListener() {
            @Override
            public void onTimer1sec(float curTimeSec,int timeCount) {
                spNum.setNumber(timeCount);
                Gdx.app.log(strDebug, "On 1sec : curTime : " + curTimeSec);
            }

            @Override
            public void onTimer500msec(float curTimeSec,int timeCount) {
            }

            @Override
            public void onTimer250msec(float curTimeSec,int timeCount) {

            }
        });
        renderables.add(spNum, gameTimer);

    }

    private void testOverrideSetter() {
        SpriteGameObject sgoWhite =
                new SpriteGameObject(new Texture(Gdx.files.internal("test_img_white.png")), 0, 0)
                        .setSpriteBatch(this.game.getSpriteBatch());
        renderables.add(sgoWhite);
        sgoWhite.setColor(new Color(0xffcc00ff));
        sgoWhite.setLocation(300f, 500f);
        sgoWhite.setRotationAngle(45f);
    }

    private void testTouch() {
        Texture textureRect125test = new Texture(Gdx.files.internal("rect125test.png"));
        SpriteGameObject sgo = new SpriteGameObject(textureRect125test, 0, 0)
                .setSpriteBatch(this.game.getSpriteBatch());
        sgo.setEventListenerTouchDown(new GameGestureListener.TouchDownEventListener() {
            @Override
            public void onEvent(float x, float y, int pointer, int button) {
                Gdx.app.log(strDebug,
                            "TouchDown x" + x + ",y" + y + ",pointer" + pointer + ",button" +
                            button);
            }
        });
        sgo.setEventListenerTouchUp(new GameGestureListener.TouchUpEventListener() {
            @Override
            public void onEvent(int screenX, int screenY, int pointer, int button) {
                Gdx.app.log(strDebug,
                            "TouchUp x" + screenX + ",y" + screenY + ",pointer" + pointer +
                            ",button" +
                            button);
            }
        });
        sgo.setEventListenerTab(new GameGestureListener.TabEventListener() {
            @Override
            public void onEvent(float x, float y, int count, int button) {
                Gdx.app.log(strDebug,
                            "Tab x" + x + ",y" + y + ",count" + count + ",button" +
                            button);
            }
        });
        sgo.setEventListenerLongPress(new GameGestureListener.LongPressEventListener() {
            @Override
            public void onEvent(float x, float y) {
                Gdx.app.log(strDebug,
                            "LongPress x" + x + ",y" + y);
            }
        });
        sgo.setEventListenerTouchDragged(new GameGestureListener.TouchDraggedEventListener() {
            @Override
            public void onEvent(int screenX, int screenY, int pointer) {
                Gdx.app.log(strDebug,
                            "TouchDragged x" + screenX + ",y" + screenY + ",pointer" + pointer);
            }
        });

        renderables.add(sgo);
        gestureDetectables.add(sgo);
        sgo.move(500f, 500f, 2f, EaseBounceIn.getInstance());
        sgo.setTouchAreaType(GameObject.TouchAreaType.RECTANGLE);
        sgo.setTouchScale(1f);
        sgo.setTouchAreaType(GameObject.TouchAreaType.CIRCLE_HEIGHT);
    }

    private void testSpriteGameObject() {
        Texture textureRect125test = new Texture(Gdx.files.internal("rect125test.png"));
        SpriteGameObject sgo = new SpriteGameObject(textureRect125test, 0, 0)
                .setSpriteBatch(this.game.getSpriteBatch());
        renderables.add(sgo);

        SpriteGameObject sgoWhite =
                new SpriteGameObject(new Texture(Gdx.files.internal("test_img_white.png")), 0, 0)
                        .setSpriteBatch(this.game.getSpriteBatch());
        renderables.add(sgoWhite);

        Array<VarTimePair> movingPathX = new Array<>();
        Array<VarTimePair> movingPathY = new Array<>();
        Array<VarTimePair> rotPath = new Array<>();
        Array<VarTimePair> scalePath = new Array<>();
        movingPathX.add(new VarTimePair(50, 1),
                        new VarTimePair(500, 3),
                        new VarTimePair(900, 5));
        movingPathY.add(new VarTimePair(100, 1),
                        new VarTimePair(1600, 4),
                        new VarTimePair(900, 6));
//        sgo.moveX(movingPathX, EaseQuadIn.getInstance());
//        sgo.moveY(movingPathY,EaseBounceOut.getInstance());
//        sgo.moveX(900, 1, EaseCubicIn.getInstance());
//        sgo.moveY(1500, 3);

//        sgo.setEventListenerMoveX(new VariationPerTime.EventListener() {
//            @Override
//            public void onUpdate(float curTime, float curVar) {
//                Gdx.app.log(strDebug + "x",
//                            "varTime updated : " + String.valueOf(curTime) + "\t" +
//                            String.valueOf(curVar));
//            }
//
//            @Override
//            public void onStart(float curTime) {
//                Gdx.app.log(strDebug + "x", "Started @" + String.valueOf(curTime));
//            }
//
//            @Override
//            public void onFinish(float curTime) {
//                Gdx.app.log(strDebug + "x", "Finished @" + String.valueOf(curTime));
//            }
//        });
        rotPath.add(new VarTimePair(0, 0),
                    new VarTimePair(-360, 0.5f),
                    new VarTimePair(0, 1f),
                    new VarTimePair(360, 1.5f));
//        sgo.rotate(rotPath, EaseBounceOut.getInstance());

//        sgo.setEventListenerRotate(new VariationPerTime.EventListener() {
//            @Override
//            public void onUpdate(float curTime, float curVar) {
//                Gdx.app.log(strDebug + "Rot",
//                            "varTime updated : " + String.valueOf(curTime) + "\t" +
//                            String.valueOf(curVar));
//            }
//
//            @Override
//            public void onStart(float curTime) {
//                Gdx.app.log(strDebug + "Rot", "Started @" + String.valueOf(curTime));
//            }
//
//            @Override
//            public void onFinish(float curTime) {
//                Gdx.app.log(strDebug + "Rot", "Finished @" + String.valueOf(curTime));
//            }
//        });
        sgo.move(400f, 800, 0.1f, EaseBackIn.getInstance());
//       scalePath.add(new VarTimePair(10, 1),
//                     new VarTimePair(0.1f, 3),
//                     new VarTimePair(1, 5),
//                     new VarTimePair(10, 7));
//       sgo.scaleX(scalePath, EaseBackInOut.getInstance());
//       sgo.scaleY(scalePath, EaseBackInOut.getInstance());

        scalePath.add(new VarTimePair(0f, 0f),
                      new VarTimePair(1f, 3),
                      new VarTimePair(0f, 6));
//        sgo.paintA(scalePath, EaseCubicIn.getInstance());
        sgo.paintR(scalePath);
        sgo.paintG(scalePath);
        sgo.paintB(scalePath);

        sgo.scale(3f, 3f, 1f, EaseBounceInOut.getInstance());

        sgoWhite.move(400f, 400f, 0.5f);
//        sgoWhite.paint(1f, 0f, 0f, 1f, 3f);
        Array<Var4TimePair> colorPath = new Array<>();
        colorPath.add(new Var4TimePair(1f, 1f, 1f, 1f, 0f),
                      new Var4TimePair(1f, 0f, 0f, 1f, 1f),
                      new Var4TimePair(0f, 1f, 0f, 1f, 2f),
                      new Var4TimePair(0f, 0f, 1f, 1f, 3f));
        colorPath.add(new Var4TimePair(1f, 0.7f, 0.1f, 1f, 7f));
        sgoWhite.paint(colorPath);
    }

    private void testGameObjectClass() {
        GameObject go = new GameObject(0, 0, 1, 1);
        this.renderables.add(go);
        go.setEventListenerMoveX(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                Gdx.app.log(strDebug + "x",
                            "varTime updated : " + String.valueOf(curTime) + "\t" +
                            String.valueOf(curVar));
            }

            @Override
            public void onStart(float curTime) {
                Gdx.app.log(strDebug + "x", "Started @" + String.valueOf(curTime));
            }

            @Override
            public void onFinish(float curTime) {
                Gdx.app.log(strDebug + "x", "Finished @" + String.valueOf(curTime));
            }
        });
        go.setEventListenerMoveY(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                Gdx.app.log(strDebug + "y",
                            "varTime updated : " + String.valueOf(curTime) + "\t" +
                            String.valueOf(curVar));
            }

            @Override
            public void onStart(float curTime) {
                Gdx.app.log(strDebug + "y", "Started @" + String.valueOf(curTime));
            }

            @Override
            public void onFinish(float curTime) {
                Gdx.app.log(strDebug + "y", "Finished @" + String.valueOf(curTime));
            }
        });
//        Array<VarTimePair> mo = new Array<>();
//        mo.add(new VarTimePair(10, 1), new VarTimePair(30, 2), new VarTimePair(10, 3));
//        go.moveX(mo,EaseBounceOut.getInstance());

        Array<Var2TimePair> mo = new Array<>();
        mo.add(new Var2TimePair(10, 0, 1), new Var2TimePair(20, 30, 4));
        go.move(mo, EaseCircIn.getInstance());

    }

    private void testVariationPerTimeClass() {
        Array<VarTimePair> pairs = new Array<VarTimePair>() {
            {
                add(new VarTimePair(2, 1));
                add(new VarTimePair(4, 3));
            }
        };
        VariationPerTime varTime = new VariationPerTime(pairs);
        varTime.addVarTime(new VarTimePair(5, 5));
        varTime.setEventListener(new VariationPerTime.EventListener() {
            @Override
            public void onUpdate(float curTime, float curVar) {
                Gdx.app.log(strDebug,
                            "varTime updated : " + String.valueOf(curTime) + "\t" +
                            String.valueOf(curVar));
            }

            @Override
            public void onStart(float curTime) {
                Gdx.app.log(strDebug, "varTime started @" + String.valueOf(curTime));
            }

            @Override
            public void onFinish(float curTime) {
                Gdx.app.log(strDebug, "varTime finished @" + String.valueOf(curTime));
            }
        });
        this.renderables.add(varTime);

        varTime.start(EaseBounceOut.getInstance());

        for (VarTimePair p : varTime.getVarTimeArray()) {
            Gdx.app.log(strDebug,
                        "(var,time)=" + String.valueOf(p.getVariation()) + "," +
                        String.valueOf(p.getTime()));
        }
    }

    Vector2 getNewTouchPoint(float x, float y) {
        return viewport.unproject(new Vector2(x, y));
    }

    private void setInputProcessor() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(new GameGestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                Vector2 newTouchPoint = getNewTouchPoint(x, y);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput.touchDown(newTouchPoint.x, newTouchPoint.y, pointer, button);
                }
                return super.touchDown(x, y, pointer, button);
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                Vector2 newTouchPoint = getNewTouchPoint(x, y);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput.tap(newTouchPoint.x, newTouchPoint.y, count, button);
                }
                return super.tap(x, y, count, button);
            }

            @Override
            public boolean longPress(float x, float y) {
                Vector2 newTouchPoint = getNewTouchPoint(x, y);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput.longPress(newTouchPoint.x, newTouchPoint.y);
                }
                return super.longPress(x, y);
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return super.fling(velocityX, velocityY, button);
            }
        }));
        inputMultiplexer.addProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    Gdx.app.log(strDebug, "Input.Keys.BACK : Key down");
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    Gdx.app.log(strDebug, "Input.Keys.BACK : Key up");
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 newTouchPoint = getNewTouchPoint(screenX, screenY);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput
                            .touchUp((int) newTouchPoint.x, (int) newTouchPoint.y, pointer, button);
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 newTouchPoint = getNewTouchPoint(screenX, screenY);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput
                            .touchDragged((int) newTouchPoint.x, (int) newTouchPoint.y, pointer);
                }
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

}
