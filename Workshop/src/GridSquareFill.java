import bridges.connect.Bridges;
import bridges.base.Color;
import bridges.base.ColorGrid;

import java.util.ArrayList;

public class GridSquareFill {
    public static void main(String[] args) throws Exception {
        Bridges bridges = new Bridges(6, "bridges_workshop", "1298385986627");

        int rows = 100;
        int columns = 100;

        ColorGrid grid = new ColorGrid(rows, columns, new Color("white"));

        int filledPixels = 0;
        int totalPixels = rows * columns;

        ArrayList<Point> arr = new ArrayList<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                arr.add(new Point(j, i));
            }
        }
        while (filledPixels < totalPixels) {
            int index = (int) (Math.random() * (totalPixels - filledPixels));

            Point origin = arr.remove(index);
            filledPixels++;
            grid.set(origin.x, origin.y, new Color((int)(Math.random() * 255),
                        (int)(Math.random() * 255),(int)(Math.random() * 255)));

            int radius = 0;
            while (true) {
                Point botLeft = new Point(origin.x - radius, origin.y - radius);
                Point botRight = new Point(origin.x + radius, origin.y - radius);
                Point topRight = new Point(origin.x + radius, origin.y + radius);
                Point topLeft = new Point(origin.x - radius, origin.y + radius);

                if (botLeft.outOfBounds(rows, columns) || botRight.outOfBounds(rows, columns) ||
                        topLeft.outOfBounds(rows, columns) || topRight.outOfBounds(rows, columns))
                    break;

                System.out.printf("%d, %d\n", origin.x, origin.y);
                boolean filled = false;

                ArrayList<Point> toBeFilled = new ArrayList<>();

                for (int i = botLeft.x; i <= topLeft.x; ++i) {
                    if (!grid.get(i, botLeft.y).getRepresentation().equals(new Color("white").getRepresentation()) ||
                            (!grid.get(i, botRight.y).getRepresentation().equals(new Color("white").getRepresentation())) ||
                            (!grid.get(topLeft.x, i).getRepresentation().equals(new Color("white").getRepresentation())) ||
                            (!grid.get(topRight.x, i).getRepresentation().equals(new Color("white").getRepresentation()))) {
                        filled = true;
                        System.out.println("break");
                        break;
                    }

                    System.out.println("no break");
                    toBeFilled.add(new Point(i, botLeft.y));
                    toBeFilled.add(new Point(i, botRight.y));
                    toBeFilled.add(new Point(topLeft.x, i));
                    toBeFilled.add(new Point(topRight.x, i));
                }
                if (filled)
                    break;

                Color col = new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255));

                for (Point pt : toBeFilled){
                    grid.set(pt.y, pt.x, col);
                    filledPixels++;
                    arr.remove(pt);
                }

                radius++;
            }
        }

        bridges.setDataStructure(grid);
        bridges.visualize();
    }
}

class Point {
    int x;
    int y;

    Point (int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean outOfBounds(int horzSize, int vertSize){
        return (this.x < 0 || this.x >= horzSize ||
                this.y < 0 || this.y >= vertSize);
    }
}
