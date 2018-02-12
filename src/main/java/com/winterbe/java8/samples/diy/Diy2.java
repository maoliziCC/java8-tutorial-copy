package com.winterbe.java8.samples.diy;

import com.google.common.cache.Weigher;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntBiFunction;

import static org.hamcrest.core.IsEqual.equalTo;


/**
 * https://zhuanlan.zhihu.com/p/33636690
 * https://github.com/eugenp/tutorials/tree/master/core-java-8#readme
 * Java 8: Lambda表达式增强版Comparator和排序
 * @package:com.winterbe.java8.samples.diy
 * @author: lizhang
 * @date: 2018-02-11 15:04
 */
public class Diy2 {

    @Test
    public void givenPreLambda_whenSortingEntitiesByName_thenCorrectlySorted(){
    //2.不使用Lambda表达式的基本排序

        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        Collections.sort(humans, new Comparator<Human>() {
            @Override
            public int compare(Human h1, Human h2) {
                return h1.getName().compareTo(h2.getName());
            }
        });
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public void whenSortingEntitiesByName_thenCorrectlySorted(){
        //3. 使用Lambda表达式的基本排序
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));

//        ToIntBiFunction<Human, Human> humanHumanWeigher =
        Comparator<Human> humanHumanWeigher = (Human h1, Human h2) -> h1.getName().compareTo(h2.getName());
        Comparator<Human> humanHumanWeigher02 = (Human h1, Human h2) -> h1.getAge().compareTo(h2.getAge());
        System.out.println(humanHumanWeigher.compare(humans.get(0),humans.get(1)));
        System.out.println(humanHumanWeigher02.compare(humans.get(1), humans.get(1)));//

    }


    @Test
    public void givenLambdaShortForm_whenSortingEntitiesByName_thenCorrectlySorted() {
        //4、没有类型定义（ Type Definitions）的基本排序
        //lambda表达式的 this 关键字指向包围lambda表达式的类
        //事实上，可以省略这里的lambda参数的类型声明，编译器可以从列表的类属性推测出来。
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }



    @Test
    public void givenMethodDefinition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        //5.使用静态方法的引用来排序
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort(Human::compareByNameThenAge);
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    //6.提取Comparator进行排序
    @Test
    public void givenInstanceMethod_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        Collections.sort(humans, Comparator.comparing(Human::getName));
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }


    @Test
    public void whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        //7.反转Comparator排序
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        Comparator<Human> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
        humans.sort(comparator.reversed());
        humans.sort(Collections.reverseOrder());
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }


    @Test
    public void whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        //8.多条件排序
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        humans.sort((lhs, rhs) ->{
            if(lhs.getName().equals(rhs.getName())){
                return lhs.getAge() - rhs.getAge();
            }else {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    //9.多条件组合排序
    @Test
    public void givenComposition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(
                new Human("Sarah", 12), new Human("Sarah", 10), new Human("Zack", 12));

        humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }



}
