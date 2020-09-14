package thsss;

public class Point {
    public double x, y;
    public Point(Point a) {
        this.x = a.x;
        this.y = a.y;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    static public Point minus(Point a, Point b){
        return new Point(a.x - b.x, a.y - b.y);
    }
    static public double getdis(Point a, Point b) {
        return Math.sqrt((a.x - b.x) *(a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
    public Point minus(Point b) {
        return new Point(this.x - b.x, this.y - b.y);
    }
    public double getdis(Point b) {
        return Math.sqrt((this.x - b.x) * (this.x - b.x) + (this.y - b.y) * (this.y - b.y));
    }
}
