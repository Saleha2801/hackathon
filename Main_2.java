import java.util.*;

class Item{
	int weight,value;
	Item(int weight,int value){
		this.weight=weight;
		this.value=value;
	}
}

public class Main_2{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the number of item: ");
		int n=sc.nextInt();

		Item items[]=new Item[n];
		
		System.out.println("Enter the weights and values: ");
		for(int i=0;i<n;i++){
			int w=sc.nextInt();
			int v=sc.nextInt();
			items[i]=new Item(w,v);
		}

		System.out.println("Enter the capacity: ");
		int W=sc.nextInt();

		int dp[][]=new int[n+1][W+1];

		for(int i=1;i<=n;i++){
			for(int w=0;w<=W;w++){
				if(items[i-1].weight<=w){
					dp[i][w]=Math.max(
						items[i-1].value+dp[i-1][w-items[i-1].weight],
						dp[i-1][w]
					);
				}
				else{
					dp[i][w]=dp[i-1][w];
				}
			}
		}
		
		System.out.println("Max utility is: "+dp[n][W]);

int w = W;

System.out.println("Selected items:");

for (int i = n; i > 0; i--) {

    if (dp[i][w] != dp[i-1][w]) {

        System.out.println("Item " + i +
            " Weight: " + items[i-1].weight +
            " Value: " + items[i-1].value);

        w = w - items[i-1].weight;
    }
}
		sc.close();
	}
}