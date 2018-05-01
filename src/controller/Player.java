import java.awt.*;
import java.util.Set;

public class Player {
    private Set<Bonde> Bondes;
    private Color color;

    public Set<Bonde> getBondes() {
        return Bondes;
    }

    public void setBondes(Set<Bonde> Bondes) {
        this.Bondes = Bondes;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
