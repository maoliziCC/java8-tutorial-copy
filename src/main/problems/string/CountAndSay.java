package string;

/**
 * Created by Christina on 18/03/2018.
 *
 * The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"

 */
public class CountAndSay {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println(new CountAndSay().countAndSay(4));
    }

    public String countAndSay(int n) {
        String result = "1";
        for(int i = 1; i < n; i ++){
            int count = 1;
            char num = result.charAt(0);
            StringBuilder temp = new StringBuilder();
            for(int j = 1, l = result.length(); j < l; j++){
                if(result.charAt(j) == num){
                    count++;
                } else{
                    temp = temp.append(String.valueOf(count)).append(String.valueOf(num));
                    num = result.charAt(j);
                    count = 1;
                }
            }
            temp = temp.append(String.valueOf(count)).append(String.valueOf(num));
            result = temp.toString();
        }
        return result;
    }
}

/**
 *  解释一下就是，输入n，那么我就打出第n行的字符串。
 怎么确定第n行字符串呢？他的这个是有规律的。
 n = 1时，打印一个1。
 n = 2时，看n=1那一行，念：1个1，所以打印：11。
 n = 3时，看n=2那一行，念：2个1，所以打印：21。
 n = 4时，看n=3那一行，念：一个2一个1，所以打印：1211。
 以此类推。(注意这里n是从1开始的）

 所以构建当前行的字符串要依据上一行的字符串。“小陷阱就是跑完循环之后记得把最后一个字符也加上，因为之前只是计数而已。”
 */
