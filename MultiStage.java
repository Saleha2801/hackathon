import java.util.*;
 public class MultiStage{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter the no. of vertices: ");
		int n=sc.nextInt();

		int graph[][]=new int[n+1][n+1];
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				graph[i][j]=sc.nextInt();
			}
		}

		int cost[]=new int[n+1];
		int path[]=new int[n+1];

		cost[n]=0;

		for(int i=n-1;i>=1;i--){
			cost[i]=Integer.MAX_VALUE;
			for(int j=i+1;j<=n;j++){
				if(graph[i][j]!=0 && cost[j]!=Integer.MAX_VALUE){
					int CurrentCost=graph[i][j]+cost[j];

					if(CurrentCost<cost[i]){
						cost[i]=CurrentCost;
						path[i]=j;
					}
				}
			}
		}

		System.out.println("Minimum cost is: "+cost[1]);
		int vertex=1;

		while(vertex!=n){
			System.out.print(vertex+" -> ");
			vertex=path[vertex];
		}

		System.out.println(n);
		sc.close();
	}
}