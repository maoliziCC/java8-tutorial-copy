import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @package:PACKAGE_NAME
 * @author: lizhang
 * @date: 2018-03-16 11:51
 */
class Solution01 {
    public int removeDuplicates(int[] nums) {
        Integer length = nums.length;
        Integer r = null;
        int i = 1;
        int result = 0;

        if(null != length  && length > 0){
            r = nums[0];
            result = 1;

            for(;i < length; i++){
                if(nums[i] != r ){
                    result ++;
                    r = nums[i];
                    nums[result-1] = nums[i];//
                }
            }
        }
        return result;
    }
}

public class MainClass01 {
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

            int ret = new Solution01().removeDuplicates(nums);
            String out = integerArrayToString(nums, ret);

            System.out.print(out);
        }
    }
}