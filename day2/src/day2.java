import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] elements = null;

            int res = 0;

            while ((line = bufferedReader.readLine()) != null) {
                elements = line.split(" ");
                
                int[] curr = new int[elements.length];

                for (int i = 0; i < curr.length; i++) {
                    curr[i] = Integer.parseInt(elements[i]);
                }

                boolean any = false;
                for(int i = 0; i < curr.length; i++){
                    int[] newarr = new int[curr.length - 1];
                    System.arraycopy(curr, 0, newarr, 0, i);
                    if (curr.length >= i + 1)
                        System.arraycopy(curr, i + 1, newarr, i + 1 - 1, curr.length - (i + 1));
                    if(safe(newarr)){
                        any = true;
                    }
                }
                if(any){
                    res++;
                }
            }

            System.out.println(res);
        }
    }

    private static boolean safe(int[] nums){
        boolean decreasing = nums[0] > nums[1];
        if(nums[0] == nums[1]) return false;
        
        for(int i = 1; i < nums.length; i++){
            if (decreasing) {
                if (nums[i] > nums[i - 1] || nums[i] == nums[i-1] || nums[i - 1] - nums[i] > 3) return false;
            } else {
                if (nums[i] < nums[i - 1] || nums[i] == nums[i-1] || nums[i] - nums[i - 1] > 3) return false;
            }
        }
        
        return true;
    }
}
