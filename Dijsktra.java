import java.util.*;

public class Dijsktra{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of vertices: ");
		int n=sc.nextInt();
		
		int graph[][]=new int[n][n];

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				graph[i][j]=sc.nextInt();
			}
		}

		int dist[]=new int[n];
		boolean visited[]=new boolean[n];
		int parent[]=new int[n];
		Arrays.fill(parent,-1);
		Arrays.fill(dist,Integer.MAX_VALUE);

		System.out.println("Enter the source: ");
		int source=sc.nextInt();

		dist[source]=0;
		
		for(int i=0;i<n-1;i++){
			int min=Integer.MAX_VALUE;
			int u=-1;

			for(int j=0;j<n;j++){
				if(!visited[j] && dist[j]<min){
					min=dist[j];
					u=j;
				}
			}
			if(u==-1){
				break;
			}
			visited[u]=true;

			for(int v=0;v<n;v++){
				if(!visited[v] && graph[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+graph[u][v]<dist[v]){
					dist[v]=dist[u]+graph[u][v];
					parent[v]=u;
				}
			}
		}
		
		System.out.println("Distance Table is: ");
		for(int i=0;i<n;i++){
			if(dist[i]==Integer.MAX_VALUE){
				System.out.println("Unreachable...!!!");
			}
			else{
			System.out.println(i+" : "+dist[i]);	
			}
		}
		
		System.out.println("Enter the destination: ");
		int dest=sc.nextInt();
		
		Stack<Integer> path=new Stack<>();
		int current=dest;
		while(current!=-1){
			if(dist[dest]==Integer.MAX_VALUE){
				System.out.println("Unreachable...!!!");
			}
			else{
			path.push(current);
			current=parent[current];
			}
		}
		while(!path.isEmpty()){
			System.out.print(path.pop());
			if(!path.isEmpty()){
				System.out.print(" -> ");
			}
		}
		sc.close();
	}
}