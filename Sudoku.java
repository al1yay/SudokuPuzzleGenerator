import java.util.ArrayList;

public class Sudoku {

    public static int[][] grid = new int[9][9];
    public static int regenCount = 0;
    public static boolean regenBoolean = false;
    public static void main(String[] args) {

        
        while (!regenBoolean) {
        
        resetGrid();
        regenCount = 0;
    
        boxGen(0);
        boxGen(3);
        boxGen(6);

        regenBoolean = true;
        
        for (int a = 0; a<9; a++) {
            rowGen(a);
            if (!regenBoolean)
                break;
        }
        
        }
        
        System.out.println("-------------------------");
        
        for (int i = 0; i<grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j<grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
                if (j%3 == 2)
                    System.out.print("| ");
            }
            System.out.println();
            if (i%3 ==2)
            System.out.println("-------------------------");
        }
    }
    
    public static void resetGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = 0;
            }
        }
    }
    
    public static ArrayList<Integer> nineGen() {
        ArrayList<Integer> current = new ArrayList<Integer>();
        for (int i = 0; i<9; i++) {
            int now = ((int)(Math.random()*9)+1);
            while (current.indexOf(now) != -1) {
                now = ((int)(Math.random()*9)+1);
            }
            current.add(now);
        }
        return current;
    }
    
    public static void boxGen(int start) {
        ArrayList<Integer> nine = nineGen();
        int count = 0;
        for (int i = start; i<start+3; i++) {
            for (int j = start; j<start+3; j++) {
                grid[i][j] = nine.get(count);
                count++;
            }
        }
    }
    
    public static void rowGen(int row) {
        
        boolean working = false;
        
        while (!working) {
            working = true;
            
            for (int j = 0; j < 9; j++) {
            if (grid[row][j] != 0) {
                grid[row][j] = 0;
            }
        }
        
        for (int col = 0; col<9; col++) {
            if (grid[row][col] == 0) {
                
                ArrayList<Integer> options = new ArrayList<Integer>();
                
                
                ArrayList<Integer> rowArray = new ArrayList<Integer>();
                ArrayList<Integer> colArray = new ArrayList<Integer>();
                ArrayList<Integer> boxArray = new ArrayList<Integer>();
                
                for (int j = 0; j<9; j++) {
                    rowArray.add(grid[row][j]);
                    colArray.add(grid[j][col]);
                }
                
                int currentBoxRow = row - row%3;
                int currentBoxCol = col - col%3;
                
                for (int k = currentBoxRow; k<currentBoxRow+3; k++) {
                    for (int q = currentBoxCol; q<currentBoxCol+3; q++) {
                        boxArray.add(grid[k][q]);
                    }
                }
                
                for (int b = 1; b<=9; b++) {
                    boolean valid = true;
                    if (rowArray.indexOf(b) != -1 || colArray.indexOf(b) != -1 || boxArray.indexOf(b) != -1)
                        valid = false;
                    
                    if (valid) {
                        options.add(b);
                    }
                }
                
                if (options.size() == 0) {
                    working = false;
                    regenCount++;
                    break;
                }
                
                else {
                    //grid[row][col] = options.get((int)(Math.random()*options.size()));
                    
                    java.util.Collections.shuffle(options);
                    grid[row][col] = options.get(0);
                }
                
                if (regenCount > 200) {
                    regenBoolean = false;
                    return;
                }
                
            }
        }
        }
    }

    
}