package thsss;

//Control Object's movement using Parametric equation
abstract public class MoveFunction {
    public Point getPosition(double t) {
        System.out.println("There must be something wrong");
        return new Point(0.0,0.0);
    }
}
