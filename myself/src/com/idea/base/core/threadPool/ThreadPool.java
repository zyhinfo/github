package com.idea.base.core.threadPool;import java.util.concurrent.ExecutorService;import java.util.concurrent.Executors;/** * @author zhangyh * */public class ThreadPool {	public static ExecutorService pool = Executors.newFixedThreadPool(10);	}