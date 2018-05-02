package controller;

import java.awt.*;
import java.util.Set;

public class Player {
    private Set<Spillebrikke> Spillebrikke;
    private Color color;

    public Set<Spillebrikke> getSpillebrikke() {
        return Spillebrikke;
    }

    public void setSpillebrikke(Set<Spillebrikke> Spillebrikke) {
        this.Spillebrikke = Spillebrikke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
