package com.fzh.ecommerce;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MethodTest {

    public static List<String> strList= Arrays.asList("hello","world","tom","Jerry");

    @Test
    public void testPredicate(){

        Predicate<String> length = s-> s.length()>=5;
        List<String> newStr = strList.stream().filter(length).collect(Collectors.toList());
        System.out.println(newStr);

    }
}
