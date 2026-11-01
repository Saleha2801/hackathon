
import java.util.*;
import java.io.*;

public class MergeSort {

    static String[] parseCSV(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

    static int findColumn(String header, String name) {
        String h[] = parseCSV(header);

        for (int i = 0; i < h.length; i++) {
            if (h[i].replace("\"", "").trim().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    static String getValue(String row, int col) {
        return parseCSV(row)[col].replace("\"", "").trim();
    }

    static void merge(String arr[], int low, int mid, int high, int col) {

        String temp[] = new String[high - low + 1];

        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {

            if (getValue(arr[i], col).compareTo(getValue(arr[j], col)) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= high) temp[k++] = arr[j++];

        for (i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }
    }

    static void mergeSort(String arr[], int low, int high, int col) {

        if (low < high) {

            int mid = (low + high) / 2;

            mergeSort(arr, low, mid, col);
            mergeSort(arr, mid + 1, high, col);

            merge(arr, low, mid, high, col);
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(
            new FileReader("olist_orders_dataset.csv"));

        String header = br.readLine();

        int col = findColumn(header, "order_purchase_timestamp");

        if (col == -1) {
            System.out.println("Timestamp column not found.");
            br.close();
            return;
        }

        ArrayList<String> list = new ArrayList<>();

        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        br.close();

        String arr[] = list.toArray(new String[0]);

        mergeSort(arr, 0, arr.length - 1, col);

        PrintWriter pw = new PrintWriter("sorted_orders.csv");

        pw.println(header);

        for (String row : arr) {
            pw.println(row);
        }

        pw.close();

        System.out.println("Orders sorted successfully!");
        System.out.println("Total orders: " + arr.length);
        System.out.println("Saved in sorted_orders.csv");
    }
}
