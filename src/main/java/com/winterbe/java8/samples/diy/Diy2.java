package com.winterbe.java8.samples.diy;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;


/**
 * https://zhuanlan.zhihu.com/p/33636690
 *
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






}
