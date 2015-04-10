/**
 * 
 */
package com.idea.tools.demo;

/**
 * 算法
 * @author zhangyh
 *
 */
public class Arithmetic {
	/**
	 * 若有一只免子每个月生一只小免子，一个月后小免子也开始生产。
	 * 起初只有一只免子，一个月后就有两只免子，二个月后有三只免子，三个月后有五只免子（小免子投入生产）
	 */
	public static void demo1(){
		int bigRabbit=1;//大兔子个数
		int smallRabbit=0;//小兔子个数
		int yearCount=3;//月数
		for(int i=0 ;i<yearCount;i++){
			int big = bigRabbit;
			int small = smallRabbit;
			bigRabbit = big + small;
			smallRabbit = big;
		}
		System.out.println("总共有"+(bigRabbit+smallRabbit)+"只兔子");
	}
	public static void main(String[] args) {
		demo1();
	}
}
