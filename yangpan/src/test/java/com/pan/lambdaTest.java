package com.pan;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class lambdaTest {
		
	@Test
	public void  ForEachTest() {
		
		List<String> list = Arrays.asList("yangpan","哈哈","谁先","我先");
		//list.forEach(n-> System.out.println(n));
		//list.forEach(System.out::println);
		list.stream()
		.filter(n-> n.equals("哈哈"))
		.forEach(System.out::println);
	}
	@Test
	public  void reduceTest(){
		List<Integer> lists  = Arrays.asList(100, 200, 300, 400, 500);
		Double integer = lists.stream().map((list)->list + .12*list).reduce((sum,list)->sum+list).get();
		System.out.println(integer);
		
	}
	
	@Test
	public void  CEachTest() {
		
		List<String> list = Arrays.asList("yangpan","哈哈","谁先","我先");
		//list.forEach(n-> System.out.println(n));
		//list.forEach(System.out::println);
		Set<Integer> collect = list.stream()
		.map(n -> n.codePointCount(1, 2))
		.collect(Collectors.toSet());
		
		System.out.println(collect);
	}
	
	
}
