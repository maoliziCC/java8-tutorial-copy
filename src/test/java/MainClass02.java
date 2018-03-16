import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @package:PACKAGE_NAME
 * @author: lizhang
 * @date: 2018-03-16 14:03
 */
class Solution {
    public void rotate01(int[] nums, int k) {
        int i = 1;
        if(nums.length > 0 && k > 0){
            k = k % nums.length;
            int temp = nums[nums.length - 1]; // last one
            for(;i <= k; i++){
                for(int j = nums.length - 1; j > 0 ; j -- ){
                    nums[j] = nums[j - 1];
                }
                nums[0] = temp;  //first one
                temp = nums[nums.length - 1];
            }
        }
    }//Low time efficiency

    public void rotate(int[] nums, int k) {
        int i = 1;

        if(nums.length > 0 && k > 0){
            k = k % nums.length;
            int[] knums = new int[k];
            for(int t = 0; t < k; t++ ){
                knums[k - t - 1] = nums[nums.length - 1- t];
            }
            for (int j = nums.length - k - 1; j >= 0; j--) {
                nums[j + k] = nums[j];
            }
            for(int t = 0; t < k; t++ ){
               nums[t] =  knums[t];
            }
           // System.out.println(Arrays.asList(nums).toString());
        }
    }
}

public class MainClass02 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            new Solution().rotate(nums, k);
            String out = integerArrayToString(nums);

            System.out.print(out);
        }
    }
}