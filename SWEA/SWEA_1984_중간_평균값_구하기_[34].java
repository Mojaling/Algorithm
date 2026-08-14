import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int times=1; times<=T;times++) {
			int[] nums = new int[10];
			int max=Integer.MIN_VALUE;
			int min=Integer.MAX_VALUE;
			int sum=0;
			for(int i=0; i<10;i++) {
				nums[i] = sc.nextInt();
				if(nums[i]>max) {
					max = nums[i];
				}
				
				if(nums[i]<min) {
					min = nums[i];
				}
				sum += nums[i];
			}
			
			double mean = (double)(sum-max-min) / (nums.length-2);
			System.out.printf("#%d %d%n",times, Math.round(mean));
			
			
			
			
		}
	}

}