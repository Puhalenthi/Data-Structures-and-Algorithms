import java.util.Scanner;

// [[1,1,0],[1,1,0],[0,0,1]]

// First, the algorithms parses the input to create the object version of the adjacency matrix (each element is an integer).
// As for the actual logic, it uses DFS to go through all plots that the current plot is directly/indirectly connected to and marks them as visited. 
// Once it finished the DFS for that plot, it goes back out and continues iterating through the nodes. If already visited, it skips. It adds the farm count this way

// As for time complexity, the outer look runs n times and for each iteration, it calls DFS which can run n times as well. Thus, it is O(n^2)
// Space complexity is O(n) becuse of the visited array and the recursion stack in the DFS function because it can go as deep as n calls in some calls.

public class CountingFarms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plot connections below");
  
        // Parsing Input
        String input = scanner.nextLine();  
        String[] rows = input.split("\\],\\[");
        int[][] isConnected = new int[rows.length][rows.length];

        for (int i = 0; i < rows.length; i++) {
            isConnected[i] = new int[rows.length];
            String[] c = rows[i].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            for (int j = 0; j < c.length; j++) { 
                isConnected[i][j] = Integer.parseInt(c[j]); 
            }
        }

        boolean[] visited = new boolean[rows.length];
        int totalFarms = 0;

        for (int i = 0; i < rows.length; i++) {
            if (!visited[i])  {
                visited[i] = true;
                dfs(i, isConnected, visited);
                totalFarms++;
            }
        }
  
        System.out.println("Number of farms: " + totalFarms); 

        scanner.close();
    }

    public static void dfs(int index, int[][] isConnected, boolean[] visited) {
        for (int i = 0; i < isConnected[index].length; i++) {
            if (visited[i] || isConnected[index][i] != 1) continue; 
            visited[i] = true;
            dfs(i, isConnected, visited);
        }
    }
}