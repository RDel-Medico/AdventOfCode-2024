import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class day1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] elements = null;

            int[] left = new int[1000];
            HashMap<Integer, Integer> right = new HashMap<>();
            int index = 0;

            while ((line = bufferedReader.readLine()) != null) {
                elements = line.split("-");
                left[index++] = Integer.parseInt(elements[0]);
                right.put(Integer.parseInt(elements[1]), right.getOrDefault(Integer.parseInt(elements[1]), 0) + 1);
            }

            int res = 0;

            for (int i = 0; i < left.length; i++) {
                res += left[i] * right.getOrDefault(left[i], 0);
            }

            System.out.println(res);
        }
    }
}
