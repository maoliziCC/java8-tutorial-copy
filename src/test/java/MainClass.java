/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution04 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode sum = new ListNode((l1.val + l2.val) %10);
        ListNode node = null;
        sum.next = node;
        boolean inc = (l1.val + l2.val)>= 10 ? true : false;
        ListNode tmp1 = l1.next, tmp2 = l2.next, stmp = sum.next, lstmp = sum;


        for(; tmp1 !=null && tmp2 != null;
            tmp1 = tmp1.next, tmp2 = tmp2.next, lstmp = stmp){
            node = new ListNode((tmp1.val + tmp2.val + (inc == true? 1: 0)) % 10 );
            inc = (tmp1.val + tmp2.val + (inc == true? 1: 0)) >= 10;
            stmp = node;
            lstmp.next = stmp;
            node.next = null;
        }
        if(tmp1 != null){
            for(;tmp1!=null; tmp1 = tmp1.next, lstmp = stmp){
                node = new ListNode((tmp1.val+ (inc == true?1:0) )% 10);
                inc  = (tmp1.val+ (inc == true?1:0) ) >= 10;
                stmp = node;
                lstmp.next = stmp;
                node.next = null;
            }
        }
        if(tmp2 != null){
            for(;tmp2!=null; tmp2 = tmp2.next, lstmp = stmp){
                node = new ListNode((tmp2.val+ (inc == true?1:0) )% 10);
                inc  = (tmp2.val + (inc == true?1:0)) >= 10;
                stmp = node;
                lstmp.next = stmp;
                node.next = null;
            }
        }
        if(inc){
            node = new ListNode(1);
            inc = false;
            stmp = node;
            lstmp.next = stmp;
            node.next = null;
        }
        return sum;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = new Solution04().addTwoNumbers(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}