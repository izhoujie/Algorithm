package leetcode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author ZhouJie
 * @date 2019年11月24日 下午4:21:50 
 * @Description: 1114.按序打印 
 *
	三个不同的线程将会共用一个 Foo 实例。
	
	线程 A 将会调用 one() 方法
	线程 B 将会调用 two() 方法
	线程 C 将会调用 three() 方法
	请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
	
	示例 1:
	
	输入: [1,2,3]
	输出: "onetwothree"
	解释: 
	有三个线程会被异步启动。
	输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
	正确的输出是 "onetwothree"。
	示例 2:
	
	输入: [1,3,2]
	输出: "onetwothree"
	解释: 
	输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
	正确的输出是 "onetwothree"。
	 
	
	注意:
	
	尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
	
	你看到的输入格式主要是为了确保测试的全面性。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/print-in-order
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	
	思路：1-设置volatile变量，以值控序；类似的也可以用Atomic原子类的AtomicInteger的值或者两个boolean变量控制；
		2-使用semaphore信号量；
		3-使用CountDownLatch；
 */
public class LeetCode_1114 {

}

/**
 * @author ZhouJie
 * @date 2019年11月24日 下午4:34:42 
 * @Description: 思路1：定义volatile变量，内存屏障；
 *
 */
class Foo1 {
	private volatile int flag = 1;

	public Foo1() {

	}

	public void first(Runnable printFirst) throws InterruptedException {
		while (flag != 1)
			;
		// printFirst.run() outputs "first". Do not change or remove this line.
		printFirst.run();
		flag = 2;
	}

	public void second(Runnable printSecond) throws InterruptedException {
		while (flag != 2)
			;
		// printSecond.run() outputs "second". Do not change or remove this line.
		printSecond.run();
		flag = 3;
	}

	public void third(Runnable printThird) throws InterruptedException {
		while (flag != 3)
			;
		// printThird.run() outputs "third". Do not change or remove this line.
		printThird.run();
		flag = 1;
	}

	/**
	 * @author ZhouJie
	 * @date 2019年11月24日 下午4:48:27 
	 * @Description: 思路2：使用信号量Semaphore
	 *
	 */
	class Foo2 {
		public Semaphore S1_2 = new Semaphore(0);
		public Semaphore S2_3 = new Semaphore(0);

		public Foo2() {

		}

		public void first(Runnable printFirst) throws InterruptedException {

			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
			S1_2.release();
		}

		public void second(Runnable printSecond) throws InterruptedException {
			S1_2.acquire();
			// printSecond.run() outputs "second". Do not change or remove this line.
			printSecond.run();
			S2_3.release();
		}

		public void third(Runnable printThird) throws InterruptedException {
			S2_3.acquire();
			// printThird.run() outputs "third". Do not change or remove this line.
			printThird.run();
		}
	}

	/**
	 * @author ZhouJie
	 * @date 2019年11月24日 下午5:00:51 
	 * @Description: 思路3：使用CountDownLatch
	 *
	 */
	class Foo3 {
		public CountDownLatch c1_2 = new CountDownLatch(1);
		public CountDownLatch c2_3 = new CountDownLatch(1);

		public Foo3() {

		}

		public void first(Runnable printFirst) throws InterruptedException {

			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
			c1_2.countDown();
		}

		public void second(Runnable printSecond) throws InterruptedException {
			// printSecond.run() outputs "second". Do not change or remove this line.
			c1_2.await();
			printSecond.run();
			c2_3.countDown();
		}

		public void third(Runnable printThird) throws InterruptedException {
			// printThird.run() outputs "third". Do not change or remove this line.
			c2_3.await();
			printThird.run();
		}
	}
}
