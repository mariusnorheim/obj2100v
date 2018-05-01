import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class Board {
    private int width;
    private int height;
    private Set<Bonde> pieces;
    private HashMap<Point,Box> board;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Bonde> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Bonde> pieces) {
        this.pieces = pieces;
    }

    public HashMap<Point, Box> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Point, Box> board) {
        this.board = board;
    }
}
