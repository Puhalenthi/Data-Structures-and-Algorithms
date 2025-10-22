import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; 

// [[2,1,1],[1,1,0],[0,1,1]]

// First, the algorithm again parses the input to create the integer array of the mall.
// It uses a BFS approach and loads all zombie positions into the queue. It goes through each zombie one by one and converts adjacent humans into zombies, adding them to the queue as well.
// It keeps track of time by adding a third index into the queue element which keeps track of the minutes.
// Once the queue is empty, that means all possible zombies have been processed. If humans left, returns -1. Else, returns time.

// For time complexity, each cell is processed at most once, so its O(nm). For space complexity, worst case is that the queue holds all zombies, which is O(nm).

public class ZombieAttack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the zombie grid below");
  
        // Parses Input
        String input = scanner.nextLine();
        String[] rows = input.split("\\],\\[");


        int[][] mallGrid = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            mallGrid[i] = new int[cols.length];
            for (int j = 0; j < cols.length; j++) {
                mallGrid[i][j] = Integer.parseInt(cols[j]);
            }
        }
  
        Queue<int[]> queue = new LinkedList<int[]>(); 

        int humanCount = 0;

        for (int i = 0; i < mallGrid.length;i++) {
            for (int j = 0; j < mallGrid[0].length; j++) {
                if (mallGrid[i][j] == 1) {
                    humanCount++; 
                } else if (mallGrid[i][j] == 2) {
                    queue.offer(new int[]{i,j,0});
                }
            }
        }

        int[][] directions = {{0,-1},{0,1},{1,0},{-1,0}};

        int result = 0;
  
        while (!queue.isEmpty()) {
            int[] zombie = queue.poll();
            if (mallGrid[zombie[0]][zombie[1]] != 2) continue;
            result = Math.max(result, zombie[2]);
            for (int i = 0; i < directions.length; i++) {
                int newI = zombie[0] + directions[i][0];
                int newJ = zombie[1] + directions[i][1];
                if (newI < 0 || newJ < 0) continue;
                if (newI > mallGrid.length - 1 || newJ>mallGrid[0].length-1) continue;
                if (mallGrid[newI][newJ] != 1) continue; //not human
                mallGrid[newI][newJ] = 2;
                humanCount--;
                queue.offer(new int[]{newI, newJ, zombie[2]+1}); 
            }
        }

        scanner.close();
    
        if(humanCount > 0) {
            System.out.println("Number of minutes to total infection: -1");
        } else {
            System.out.println("Number of minutes to total infection: " + result);
        }
    }
}