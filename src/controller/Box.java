package controller;

import java.awt.*;

public class Box {
    private Point pos;
    private Spillebrikke Spillebrikke;
    private Color color;

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Spillebrikke getSpillebrikke() {
        return Spillebrikke;
    }

    public void setSpillebrikke(Spillebrikke Spillebrikke) {
        this.Spillebrikke = Spillebrikke;
    }

    public Box(Point pos, Color color) {
        this.pos = pos;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
