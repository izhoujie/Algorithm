package leetcode;

import java.util.concurrent.Semaphore;

/**
 * @author ZhouJie
 * @date 2019年11月24日 下午5:25:32 
 * @Description: 1115.交替打印FooBar
 * 
	我们提供一个类：
	
	class FooBar {
	  public void foo() {
	    for (int i = 0; i < n; i++) {
	      print("foo");
	    }
	  }
	
	  public void bar() {
	    for (int i = 0; i < n; i++) {
	      print("bar");
	    }
	  }
	}
	两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
	
	请设计修改程序，以确保 "foobar" 被输出 n 次。
	
	 
	
	示例 1:
	
	输入: n = 1
	输出: "foobar"
	解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
	示例 2:
	
	输入: n = 2
	输出: "foobarfoobar"
	解释: "foobar" 将被输出两次。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/print-foobar-alternately
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 	思路：1-使用Semaphore信号量；
 		2-使用1114题中的volatile或者AtomicInteger然后配合Thread.yield()，避免CAS高频竞争导致超时；
 */
public class LeetCode_1115 {

}

/**
 * @author ZhouJie
 * @date 2019年11月24日 下午5:26:46 
 * @Description: TODO(类简述) 
 *
 */
class FooBar1 {
	private int n;

	private Semaphore s1 = new Semaphore(0);
	private Semaphore s2 = new Semaphore(1);

	public FooBar1(int n) {
		this.n = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {

		for (int i = 0; i < n; i++) {

			// printFoo.run() outputs "foo". Do not change or remove this line.
			s2.acquire();
			printFoo.run();
			s1.release();
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {

		for (int i = 0; i < n; i++) {
			s1.acquire();
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			s2.release();
		}
	}
}
