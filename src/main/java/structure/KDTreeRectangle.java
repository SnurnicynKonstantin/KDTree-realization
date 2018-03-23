package structure;

public class KDTreeRectangle {
    private final double xmin, ymin;
    private final double xmax, ymax;

    public KDTreeRectangle(double xmin, double ymin, double xmax, double ymax) {
        if (Double.isNaN(xmin) || Double.isNaN(xmax))
            throw new IllegalArgumentException("x-coordinate cannot be NaN");
        if (Double.isNaN(ymin) || Double.isNaN(ymax))
            throw new IllegalArgumentException("y-coordinates cannot be NaN");
        if (xmax < xmin || ymax < ymin) {
            throw new IllegalArgumentException("Invalid rectangle");
        }
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public double xmin() {
        return xmin;
    }

    public double xmax() {
        return xmax;
    }

    public double ymin() {
        return ymin;
    }

    public double ymax() {
        return ymax;
    }

    public boolean intersects(KDTreeRectangle that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
                && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }
}
