/*
 * @(#)IteratorTest.java 2020年4月22日
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.meishubao.collection;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 迭代器是一种模式、详细可见其设计模式，可以使得序列类型的数据结构的遍历行为与
 * 被遍历的对象分离，即我们无需关心该序列的底层结构是什么样子的。只要拿到这个对
 * 象,使用迭代器就可以遍历这个对象的内部。<br>
 * 
 * <pre>
 * Iterable：实现这个接口的集合对象支持迭代，是可以迭代的。实现了这个可以配合foreach使用。
 * Iterator：迭代器，提供迭代机制的对象，具体如何迭代是这个Iterator接口规范的。
 * <code>
 * public interface Iterator<E> {
 * 
 * 	boolean hasNext();    // 每次next之前，先调用此方法探测是否迭代到终点
 * 
 * 	E next();             // 返回当前迭代元素 ，同时，迭代游标后移
 * 
 * 	// 删除最近一次已近迭代出出去的那个元素。只有当next执行完后，才能调用remove函数。
 *	// 比如你要删除第一个元素，不能直接调用remove()而要先next一下( );在没有先调用next
 *	// 就调用remove方法是会抛出异常的。这个和MySQL中的ResultSet很类似
 * 	void remove() {
 * 		throw new UnsupportedOperationException("remove");
 * 	}
 * 
 * }
 * </code>
 * </pre>
 * 
 * @author lilu
 * @date 2020年4月22日
 * @since 1.0.0
 */
public class IteratorTest {

	/**
	 * 执行器主函数
	 *
	 * @author lilu
	 * @date 2020/5/11 9:48 下午
	 * @since 1.0.0
	 * 
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
//		testList();
//		testSet();
//		testMap();
		
		String  _str     = "LILU";
		byte    _byte    = 0;
		short   _short   = 1;
		int     _int     = 12;
		long    _long    = 1234567890;
		double  _double  = 11111111111111111111111110.2345D;
		float   _float   = 11111111111111111111111111.2345F;
		boolean _boolean = true;
		char    _char    = 'A';
		
		Console.log(StrUtil.format("_str:{}, _byte:{}, _short:{}, _int:{}, _long:{}, _double:{}, _float:{}, _boolean:{}, _char:{}, ", 
				_str, _byte, _short, _int, _long, _double, _float, _boolean, _char));
	}

	/**
	 * List迭代器测试
	 *
	 * @author lilu
	 * @date 2020/5/11 9:36 下午
	 * @since 1.0.0
	 *
	 * @param
	 * @return void
	 */
	public static void testList() {
		List<Integer> list = Lists.newArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			int n = iterator.next();
			if (2 == n) {
				iterator.remove();
			}
		}
		
		Console.log(list);
	}
	
	/**
	 * Set迭代器测试 
	 *
	 * @author lilu
	 * @date 2020/5/11 9:47 下午
	 * @since 1.0.0
	 * 
	 * @param 
	 * @return void
	 */
	public static void testSet() {
		Set<Integer> set = Sets.newHashSet();
		set.add(1);
		set.add(2);
		set.add(3);
		
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			int n = iterator.next();
			if (2 == n) {
				iterator.remove();
			}
		}
		
		Console.log(set);
	}
	
	/**
	 * Map迭代器测试 
	 *
	 * @author lilu
	 * @date 2020/5/11 9:47 下午
	 * @since 1.0.0
	 * 
	 * @param 
	 * @return void
	 */
	public static void testMap() {
		Map<Integer, String> map = Maps.newHashMap();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		
		Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			Console.log("key:{}, value:{}", entry.getKey(), entry.getValue());
			
			if ("b".equals(entry.getValue())) {
				iterator.remove();
			}
		}
		
		Console.log(map);
	}
	
}
