
package dalcoms.lib.libgdx;

public class Point2DFloat implements Cloneable {
    private float x = 0, y = 0;

    public Point2DFloat() {
    }

    public Point2DFloat(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Point2DFloat getPoint() {
        return new Point2DFloat(getX(), getY());
    }

    public float distance(float x2, float y2) {
        x2 -= this.getX();
        y2 -= this.getY();

        return (float) Math.sqrt(x2 * x2 + y2 * y2);
    }

    public float distance(Point2DFloat xy) {
        float x = xy.getX() - this.getX();
        float y = xy.getY() - this.getY();
        return (float) Math.sqrt(x * x + y * y);
    }

    public static float getYBetween2Point(float x1, float y1, float x2, float y2, float x) {
        return ((y2 - y1) / (x2 - x1)) * (x - x2) + y2;
    }

    public static float getYBetween2Point(Point2DFloat p1, Point2DFloat p2, float x) {
        return ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX())) * (x - p2.getX()) + p2.getY();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "(" + String.valueOf(getX()) + ", " + String.valueOf(getY()) + ")";
    }
}
