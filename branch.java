import java.util.*;

class Node {
    int current, level;
    long cost, bound;
    int path[];
    boolean visited[];

    Node(int n) {
        path = new int[n];
        visited = new boolean[n];
    }
}

public class TSP {

    static int n;
    static int graph[][];
    static long minCost = Long.MAX_VALUE;
    static int bestPath[];

    static long calculateBound(Node node) {

        long bound = node.cost;

        for (int i = 0; i < n; i++) {

            if (i == node.current || !node.visited[i]) {

                int min = Integer.MAX_VALUE;

                for (int j = 0; j < n; j++) {

                    if (i != j && graph[i][j] != 0) {
                        min = Math.min(min, graph[i][j]);
                    }
                }

                if (min == Integer.MAX_VALUE)
                    return Long.MAX_VALUE;

                bound += min;
            }
        }

        return bound;
    }

    static void tsp(int start) {

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) ->
                Long.compare(a.bound, b.bound));

        Node root = new Node(n);

        root.current = start;
        root.level = 1;
        root.cost = 0;
        root.path[0] = start;
        root.visited[start] = true;

        root.bound = calculateBound(root);

        pq.add(root);

        while (!pq.isEmpty()) {

            Node node = pq.poll();

            if (node.bound >= minCost)
                continue;

            for (int i = 0; i < n; i++) {

                if (!node.visited[i] && graph[node.current][i] != 0) {

                    Node child = new Node(n);

                    child.current = i;
                    child.level = node.level + 1;
                    child.cost = node.cost + graph[node.current][i];

                    child.visited = node.visited.clone();
                    child.visited[i] = true;

                    child.path = node.path.clone();
                    child.path[child.level - 1] = i;

                    if (child.level == n) {

                        if (graph[i][start] != 0) {

                            long totalCost =
                                child.cost + graph[i][start];

                            if (totalCost < minCost) {

                                minCost = totalCost;
                                bestPath = child.path.clone();
                            }
                        }
                    }

                    else {

                        child.bound = calculateBound(child);

                        if (child.bound < minCost) {
                            pq.add(child);
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        n = sc.nextInt();

        graph = new int[n][n];

        System.out.println("Enter cost matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter starting city: ");
        int start = sc.nextInt();

        tsp(start);

        if (bestPath == null) {
            System.out.println("No valid tour possible.");
        } else {

            System.out.println("Minimum Cost: " + minCost);

            System.out.print("Optimal Path: ");

            for (int i = 0; i < n; i++) {
                System.out.print(bestPath[i] + " -> ");
            }

            System.out.println(start);
        }

        sc.close();
    }
}