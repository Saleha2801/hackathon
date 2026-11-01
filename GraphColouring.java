import java.util.*;

public class GraphColouring{

	static int colour[];

	static boolean isSafe(int graph[][],int c,int vertex,int n){
		for(int i=0;i<n;i++){
			if(graph[vertex][i]==1 && colour[i]==c){
				return false;
			}
		}
		return true;
	}

	static boolean GraphColour(int graph[][],int m,int vertex,int n){
	
		if(vertex==n){
			return true;
		}	
		
		for(int c=1;c<=m;c++){
			if(isSafe(graph,c,vertex,n)){
				colour[vertex]=c;

				if(GraphColour(graph,m,vertex+1,n)){
					return true;
				}
				
				colour[vertex]=0;
			}
		}
		return false;
	}

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
		System.out.println("Enter the number of colours: ");
		int m=sc.nextInt();

		colour=new int[n];

		if(GraphColour(graph,m,0,n)){
			System.out.println("Coloured Graph is: ");
			for(int i=0;i<n;i++){
				System.out.println("Vertex "+i+" Colour "+colour[i]);
			}
		}
		else{
			System.out.println("No Valid Found...");
		}
	}

}