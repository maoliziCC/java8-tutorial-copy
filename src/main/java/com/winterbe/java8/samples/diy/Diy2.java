package com.winterbe.java8.samples.diy;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @package:com.winterbe.java8.samples.diy
 * @author: lizhang
 * @date: 2018-02-11 15:04
 */
public class Diy2 {

    @Test
    public void test01(){
    //不使用Lambda表达式的基本排序

        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        Collections.sort(humans, new Comparator<Human>() {
            @Override
            public int compare(Human h1, Human h2) {
                return h1.getName().compareTo(h2.getName());
            }
        });
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }
}
