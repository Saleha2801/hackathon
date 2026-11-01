
import java.util.*;
import java.io.*;

public class QuickSort {

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

    static double getRating(String row, int col) {
        String value = parseCSV(row)[col].replace("\"", "").trim();

        if (value.isEmpty()) return 0;

        return Double.parseDouble(value);
    }

    static int partition(String arr[], int low, int high, int col) {

        double pivot = getRating(arr[high], col);

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (getRating(arr[j], col) >= pivot) {

                i++;

                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(String arr[], int low, int high, int col) {

        if (low < high) {

            int pi = partition(arr, low, high, col);

            quickSort(arr, low, pi - 1, col);
            quickSort(arr, pi + 1, high, col);
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(
            new FileReader("movies_metadata.csv"));

        String header = br.readLine();

        int col = findColumn(header, "vote_average");

        if (col == -1) {
            System.out.println("Rating column not found.");
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

        quickSort(arr, 0, arr.length - 1, col);

        PrintWriter pw = new PrintWriter("sorted_movies.csv");

        pw.println(header);

        for (String row : arr) {
            pw.println(row);
        }

        pw.close();

        System.out.println("Movies sorted successfully!");
        System.out.println("Total movies: " + arr.length);
        System.out.println("Saved in sorted_movies.csv");
    }
}
