import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int k = 1;
    static int n;
    static int m;
    static int used[];
    static ArrayList<Integer>[]matrix;
    static ArrayList<Integer>[]matrixReversed;
    public static ArrayList<Integer> order = new ArrayList<>();
    public static ArrayList<Integer> component = new ArrayList<>();
    public static void main(String[] args) {

        Map<Integer,Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] split = string.split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        matrix = new ArrayList[n];
        matrixReversed = new ArrayList[n];
        used= new int[n];
        int[]output= new int[n];


        for (int i =0;i<n;i++){
            matrix[i]= new ArrayList<Integer>();
            matrixReversed[i] = new ArrayList<Integer>();
        }

        for (int i =0;i<m;i++){

            string = scanner.nextLine();
            split = string.split(" ");
            matrix[Integer.parseInt(split[0]) - 1].add(Integer.parseInt(split[1]) - 1);
            matrixReversed[Integer.parseInt(split[1]) - 1].add(Integer.parseInt(split[0]) - 1);
        }
        scanner.close();

        for(int i =0;i<n;i++){
            if(used[i]==0){
                dfs1(i);
            }
        }
        for (int i =0;i<n;i++){
            used[i]=0;
        }
        for(int i=0;i<n;i++)
        {
            int v = order.get(n - 1 - i);
            if(used[v] == 0){
                dfs2(v);
                if(component.size() != 0) {
                    for (int j = 0; j < component.size(); j++) {
                        map.put(component.get(j),k);
                    }
                }

                k++;
                component.clear();
            }
        }

        System.out.println(k - 1);
        for (int i=0;i< map.size();i++){
            System.out.print(map.get(i) + " ");
        }






    }
    static void dfs1(int v ){
        used[v]=1;
        for(int i =0;i<matrix[v].size();i++){
            if(used[matrix[v].get(i)]==0){
                dfs1(matrix[v].get(i));
            }
        }
        order.add(v);
    }
    static void dfs2(int v ){
        used[v]=1;
        component.add(v);
        for(int i=0 ;i<matrixReversed[v].size();i++){
            if(used[matrixReversed[v].get(i)]==0){
                dfs2(matrixReversed[v].get(i));
            }
        }

    }
}
