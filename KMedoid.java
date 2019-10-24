import java.util.*;
import java.math.*;
class KMedoid{
  int distance1[];
  int distance2[];
  int cost[];
  int cluster_index[];
  int total_cost,c1,c2 ;

  public int clustering(int []x,int []y,int n,int c1, int c2)
  {
    int i;
    total_cost=0;
    cluster_index[c1] = 1;
    cluster_index[c2] = 2;
    for (i=0;i<n ;i++ ) {
      if(i == c1 || i == c2)
        distance1[i] = distance2[i]= -1;

      else{
        distance1[i] = (int)Math.abs(x[i]-x[c1])+ (int)Math.abs(y[i]-y[c1]);
        distance2[i] = (int)Math.abs(x[i]-x[c2])+ (int)Math.abs(y[i] - y[c2]);
        
        if(distance1[i]>distance2[i])
        {
          cost[i] = distance2[i];
          cluster_index[i] = 2;  
          total_cost+=cost[i];
        }
        else{
          cost[i] = distance1[i];
          cluster_index[i] = 1;
          total_cost+=cost[i];
        }
        
      }
      
    }
    
    return total_cost;
  }
  public static void main(String[] args) {
    Scanner sc =new Scanner(System.in);
    Random rand = new Random();
    Main main = new Main();
    int x[],y[],i,n,k=2,c1,c2;
    System.out.print("No. of Clusters = 2 \nEnter Number of data: ");
    n = sc.nextInt();
    x = new int[n];
    y = new int[n];
    main.distance1 = new int[n];
    main.distance2 = new int[n];
    main.cost =new int[n];
    main.cluster_index= new int[n];
    

    System.out.println("Enter X and Y values of data");
    for (i=0;i<n;i++ ) {
     x[i]=sc.nextInt();
     y[i]=sc.nextInt();
   }

   c1 = rand.nextInt(n-1)+0;
   c2 = rand.nextInt(n-1)+0;
   int cost1 = main.clustering(x,y,n,c1,c2);
   
   int c3,cost2;
   while(true){
    c3 = rand.nextInt(n-1)+0;
    if(c3 == c1 || c3 == c2)
      continue;
    else{
      cost2 = main.clustering(x,y,n,c1,c3);
      if(cost1<=cost2)
        break;
      else
      {
        cost1 = cost2;
        c2 = c3;
      }

    }
    
  } 
  System.out.println("The Total Minimum Cost is: " + cost1);
  for(i =0 ; i< n;i++)
    System.out.println("Element "+(i+1) + " Cluster: "+ main.cluster_index[i]);
}
}


/* Inputs
10
2 6
3 4 
3 8
4 7
6 2
6 4
7 3
7 4
8 5
7 6
*/