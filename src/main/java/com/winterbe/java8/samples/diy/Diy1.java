package com.winterbe.java8.samples.diy;


import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * https://zhuanlan.zhihu.com/p/33477686
 *
 * @package:com.winterbe.java8.samples.diy
 * @author: lizhang
 * @date: 2018-02-11 9:13
 */
public class Diy1 {
    public static void main(String[] args) {

    }

    @Test
    public void test01(){
        //1、用lambda表达式实现Runnable
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }

    @Test
    public void test03(){
        //2、使用Java 8 lambda表达式进行事件处理
        //3、使用lambda表达式对列表进行迭代
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }
        // Java 8之后：
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
        System.out.println("----------------------------------------------");
    }
    @Test
    public void test04(){
        //4使用lambda表达式和函数式接口Predicate
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
/*
        System.out.println("Languages which starts with J :");
        filter(languages, (str)->((String)str).startsWith("J"));
        System.out.println("------------------");

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->((String)str).endsWith("a"));
        System.out.println("------------------");

        System.out.println("Print all languages :");
        filter(languages, (str)->true);
        System.out.println("------------------");

        System.out.println("Print no language : ");
        filter(languages, (str)->false);
        System.out.println("------------------");*/

        System.out.println("Print language whose length greater than 4:");
        filter02(languages, (str)->((String)str).length() > 4);
        System.out.println("------------------");
    }

    public static void filter(List<String> names, Predicate condition) {
        for(String  name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    //更好的办法
    public static void filter02(List<String> names, Predicate condition) {
        names.stream().filter((name)->(condition.test(name)))
                .forEach((name)->{
                    System.out.println(name + " ");
                });
    }

    @Test
    public void test05(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        //5、如何在lambda表达式中加入Predicate
        Predicate<String> startsWithJ = (n)->n.startsWith("J");
        Predicate<String> fourLetterLong = (n)->n.length()==4;

        languages.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.println("nName, which starts with 'J' and four letter long is : " + n));
    }


    @Test
    public void test06_1(){
        //Java 8中使用lambda表达式的Map和Reduce示例
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12*cost)
                .forEach(System.out::println);
    }


    @Test
    public void test06_2(){

        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

    // 新方法：
        List<Integer> costBeforeTax02 = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax02.stream()
                .map((cost) -> cost + .12*cost)
                .reduce((sum, cost) -> sum + cost)
                .get();
        System.out.println("Total : " + bill);
    }


    @Test
    public void test07(){
        //7、通过过滤创建一个String列表
        List<String> strList = Arrays.asList("bc", "", "bcd", "", "defg", "jk");

        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream()
                .filter(x -> x.length()> 2)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }


    @Test
    public void test08(){
        //8. 对列表的每个元素应用函数
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    @Test
    public void test09(){
        //复制不同的值，创建一个子列表
        //本例展示了如何利用流的 distinct() 方法来对集合进行去重。
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream()
                .map( i -> i*i)
                .distinct()
                .collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    @Test
    public void test10(){
        //10、计算集合元素的最大值、最小值、总和以及平均值
        // IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    @Test
    public void test11(){

        List<Integer> primes = Arrays.asList(new Integer[]{2, 3,5,7});
        int factor = 2;
        //lambda表达式有个限制，那就是只能引用 final 或 final 局部变量，这就是说不能在lambda内部修改定义在域外的变量。
//        primes.forEach(element -> { factor++; });//wrong

        //另外，只是访问它而不作修改是可以的
        primes.forEach(element -> { System.out.println(factor*element); });
    }


}
