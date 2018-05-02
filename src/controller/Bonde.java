package controller;

import java.awt.*;

public class Bonde {
    private Shape gfx;
    private Color color;
    private boolean active;

    public Color getColor() {
        return color;
    }

    public Bonde(Color color) {

        this.color = color;
    }

    public boolean isActive() {

        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Shape getGfx() {
        return gfx;
    }

    public void setGfx(Shape gfx) {
        this.gfx = gfx;
    }
}
