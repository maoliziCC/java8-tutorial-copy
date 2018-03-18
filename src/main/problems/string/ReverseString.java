package string;

import java.util.Stack;

/**
 * Created by Christina on 2018/3/18.
 */
public class ReverseString {

        public String reverseString(String s) {
            Stack a = new Stack();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<s.length(); i++){
                a.push(s.charAt(i));
            }
            while (!a.empty()){
                sb.append(a.pop());

            }
            return sb.toString();

        }

    public static void main(String[] args) {

        System.out.println(new ReverseString().reverseString("abcde"));
    }

}
