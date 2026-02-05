import java.awt.*;  // Graphics ve Color için gerekli

public class Ground {

    int x, y;
    int width, height;
    Color color;

    // Constructor
    public Ground(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
}

