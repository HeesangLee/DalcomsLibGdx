package dalcoms.lib.libgdx;



public class Point2DInt implements Cloneable {
    private int x = 0, y = 0;

    public Point2DInt() {
    }

    public Point2DInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point2DInt getPoint() {
        return new Point2DInt(getX(), getY());
    }

    public int distance(int x2, int y2) {
        x2 -= this.getX();
        y2 -= this.getY();

        return (int) Math.sqrt(x2 * x2 + y2 * y2);
    }

    public int distance(Point2DInt xy) {
        int x = xy.getX() - this.getX();
        int y = xy.getY() - this.getY();
        return (int) Math.sqrt(x * x + y * y);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "(" + String.valueOf(getX()) + ", " + String.valueOf(getY()) + ")";
    }
}
