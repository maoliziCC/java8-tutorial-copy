import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @package:PACKAGE_NAME
 * @author: lizhang
 * @date: 2018-03-16 16:58
 */
public class RandomizedSet {

    private List<Integer> vals;
    private HashMap<Integer, Integer> val2idx; //key: val , value: array's index.

    /** Initialize your data structure here. */
    public RandomizedSet() {
        val2idx = new HashMap<>();
        vals = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(val2idx.containsKey(val)){
            return false;
        }else {
            val2idx.put(val, val2idx.size());
            vals.add(val);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!val2idx.containsKey(val)){
            return false;
        }else{
            int idx = val2idx.remove(val);
            if(idx < vals.size() - 1 ){// 若删除的不是链表最后的元素
                // 交换末尾元素和被删除元素
                vals.set(idx, vals.get(vals.size() -1));
                val2idx.put(vals.get(vals.size() -1), idx);
            }
            vals.remove(vals.size() - 1);
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return vals.get((new Random().nextInt(vals.size())) );
    }

}
