import java.awt.*;

public class Box {
    private Point pos;
    private Bonde Bonde;
    private Color color;

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Bonde getBonde() {
        return Bonde;
    }

    public void setBonde(Bonde Bonde) {
        this.Bonde = Bonde;
    }

    public Box(Point pos, Color color) {
        this.pos = pos;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
