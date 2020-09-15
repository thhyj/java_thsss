package thsss.MoveMethod;

import thsss.MoveFunction;
import thsss.Point;
//x = at + initPosition.x, y = bt + initPosition.y;
public class LineMove extends MoveFunction {
    double a, b;
    Point initPosition;
    public LineMove(double a, double b, Point initPosition) {
        this.a = a;
        this.b = b;
        this.initPosition = new Point(initPosition);
    }
    @Override
    public Point getPosition(double t) {
        return new Point(initPosition.x + a * t, initPosition.y + b * t);
    }
}
