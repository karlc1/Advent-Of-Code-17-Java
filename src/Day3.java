import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Karl on 2017-12-18.
 */
public class Day3 {


    void part1(int input){

        int rows = 1;
        int corner = 0;
        while (rows * rows < input){
            rows+=2;
        }

        corner = rows * rows;
        int homePos = rows/2;

        int search = corner;
        int x = rows - 1;
        int y = rows - 1;


        while(search != input){

            int searched = corner - search;

            if (searched < rows-1){
                x--;
            }

            if (searched >= rows-1 && searched < (rows-1)*2){
                y--;
            }

            if (searched >= (rows-1)*2 && searched < (rows-1)*3){
                x++;
            }

            if (searched >= (rows-1)*3 && searched < (rows-1)*4){
                y++;
            }

            search--;
        }

        int distance = Math.abs(x-homePos) + Math.abs(y-homePos);

        System.out.println("Distance: " + distance);
    }

    final short right = 0;
    final short up = 1;
    final short left = 2;
    final short down = 3;

    HashMap<Point, Integer> spiral = new HashMap<>();

    int lastMove = -1;

    void part2(int input){

        Point currentPos = new Point(0,0);
        spiral.put(currentPos, 1);

        while(true){

            int nextMove = getNextMove(currentPos);

           // System.out.println("Next Move: " + nextMove);

            Point nextKey = getCoordiantesForMove(currentPos, nextMove);
            int nextValue = getSumOfAdjecentTiles(nextKey);

            //System.out.println("Next val: " + nextValue);

            spiral.put(nextKey, nextValue);
            currentPos = nextKey;



            lastMove = nextMove;
            System.out.println(nextValue);

            if (nextValue > input){
                break;
            }
        }

    }

    int getNextMove(Point curr){

        if(lastMove == right){
            if (spiral.containsKey(getCoordiantesForMove(curr, up))){
                return right;
            }else{
                return up;
            }
        }

        if(lastMove == up){
            if (spiral.containsKey(getCoordiantesForMove(curr, left))){
                return up;
            }else{
                return left;
            }
        }

        if(lastMove == left){
            if (spiral.containsKey(getCoordiantesForMove(curr, down))){
                return left;
            }else{
                return down;
            }
        }

        if(lastMove == down){
            if (spiral.containsKey(getCoordiantesForMove(curr, right))){
                return down;
            }else{
                return right;
            }
        }

        return right;
    }

    Point getCoordiantesForMove(Point curr, int direction){

        if (direction == right){
            return new Point(curr.x+1, curr.y);
        }

        if (direction == up){
            return new Point(curr.x, curr.y-1);
        }

        if (direction == left){
            return new Point(curr.x-1, curr.y);
        }

        if (direction == down){
            return new Point(curr.x, curr.y+1);
        }

        else{
            System.err.println("Direction Error");
            return null;
        }

    }

    int getSumOfAdjecentTiles(Point curr){

        int value = 0;

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){

                Point checkTile = new Point(curr.x + i, curr.y + j);

                if (checkTile != curr && spiral.containsKey(checkTile)){

                    value += spiral.get(checkTile);
                }
            }
        }

        return value;
    }

    public Day3() {
        part2(361527);
    }

    public static void main(String[] args) throws IOException, IOException {
        new Day3();
    }
}
