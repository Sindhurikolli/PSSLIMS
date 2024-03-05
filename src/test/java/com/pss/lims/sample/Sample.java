package com.pss.lims.sample;

import java.util.HashSet;
import java.util.Set;

public class Sample {

	public static void main(String[] args) {

		
		int arr[] = {10,20,30,40,20,10};
		
		Set<Integer> set = new HashSet<Integer>();
		for(int num : arr) {
		set.add(num);
		}
		
		System.out.println(set);
	}
}
