import java.util.*;

class Item{
	double weight,value,ratio;
	boolean divisible;
	
	Item(double weight,double value,boolean divisible){
		this.weight=weight;
		this.value=value;
		this.divisible=divisible;
		this.ratio=value/weight;
	}
}

public class Main{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the number of item: ");
		int n=sc.nextInt();
		
		Item items[]=new Item[n];

		System.out.println("Enter the utility values for the items: ");
		for(int i=0;i<n;i++){
			double w=sc.nextDouble();
			double v=sc.nextDouble();
			boolean d=sc.nextBoolean();
			items[i]=new Item(w,v,d);
		}

		System.out.println("Enter the total capacity: ");
		double W=sc.nextDouble();
		
		Arrays.sort(items,(a,b)->
			Double.compare(b.ratio,a.ratio)
		);
		
		double TotalValue=0;
		
		for(int i=0;i<n;i++){
			if(W==0){
				break;
			}
			if(items[i].weight<=W){
				TotalValue=TotalValue+items[i].value;
				W=W-items[i].weight;
			}
			else if(items[i].divisible==true){
				TotalValue=TotalValue+items[i].ratio*W;
				W=0;
			}
			else{
				continue;
			}
		}

		System.out.println("Maximum Value is: "+TotalValue);
		sc.close();
	}
}