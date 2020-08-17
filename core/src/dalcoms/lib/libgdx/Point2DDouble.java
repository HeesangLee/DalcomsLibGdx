package dalcoms.lib.libgdx;

public class Point2DDouble implements Cloneable {
    private double x = 0, y = 0;

    public Point2DDouble() {
    }

    public Point2DDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point2DDouble getPoint() {
        return new Point2DDouble(getX(), getY());
    }

    public double distance(double x2, double y2) {
        x2 -= this.getX();
        y2 -= this.getY();

        return Math.sqrt(x2 * x2 + y2 * y2);
    }

    public double distance(Point2DDouble xy) {
        double x = xy.getX() - this.getX();
        double y = xy.getY() - this.getY();
        return  Math.sqrt(x * x + y * y);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "(" + String.valueOf(getX()) + ", " + String.valueOf(getY()) + ")";
    }
}
