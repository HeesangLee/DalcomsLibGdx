package dalcoms.lib.libgdx;

import com.badlogic.gdx.utils.Array;

public interface IMotionScale {
    public void move(Var2TimePair toXYT);

    public void move(float toX, float toY, float time);

    //--> Insert time zero
    public void move(Array<Var2TimePair> movingPath);

    public void moveX(VarTimePair toX);

    public void moveX(float toX, float time);

    public void moveX(Array<VarTimePair> movingPath);

    public void moveY(VarTimePair toY);

    public void moveY(float toY, float time);

    public void moveY(Array<VarTimePair> movingPath);

    public void moveUniformly(float velocityX, float velocityY);

    public void moveXUniformly(float velocityX);

    public void moveYUniformly(float velocityY);

    public void rotate(VarTimePair toAngle);

    public void rotate(float toAngle, float time);

    public void rotate(Array<VarTimePair> AnglePath);

    public void rotateUniformly(float velocity);

    public void paint(Var4TimePair toRGBAT);

    public void paint(float toR, float toG, float toB, float toA, float time);

    public void paint(Array<Var4TimePair> colorPath);

    public void paintR(VarTimePair toR);

    public void paintR(float toR, float time);

    public void paintR(Array<VarTimePair> colorPath);

    public void paintG(VarTimePair toG);

    public void paintG(float toG, float time);

    public void paintG(Array<VarTimePair> colorPath);

    public void paintB(VarTimePair toB);

    public void paintB(float toB, float time);

    public void paintB(Array<VarTimePair> colorPath);

    public void paintA(VarTimePair toA);

    public void paintA(float toA, float time);

    public void paintA(Array<VarTimePair> colorPath);
}
