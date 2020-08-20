package item;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Item81_PreferConcurrencyUtilitiesToWaitAndNotify {
	
	public static void main(String[] args) throws InterruptedException {
		Executor ex = Executors.newFixedThreadPool(3);
		System.out.println(time(ex, 3, () -> System.out.println(Thread.currentThread().getName() + "a")));
//		testExecutor(ex, 10, () ->  System.out.println(Thread.currentThread().getName()));
	}
	
	private static void testExecutor(Executor executor, int threadNum, Runnable act) {
		for (int i = 0; i < threadNum; i++) {
			executor.execute(act);
		}
		System.out.println("ABC");
	}

	private static long time(Executor executor, int concurrency, Runnable act) throws InterruptedException {
		CountDownLatch ready = new CountDownLatch(concurrency);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(concurrency);
		for (int i = 0; i < concurrency; i++) {
			executor.execute(() -> {
				System.err.println("Ready.countDown()");
				ready.countDown(); // tell timer we're ready
				try {
					start.await(); // wail till peers are ready
					act.run();
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				} finally {
					done.countDown();// tell timer we're done
				}
			});
		}
		System.err.println("Wait for ready actions");
		ready.await();
		System.err.println("Already ready");
		long startNanos = System.nanoTime();
		start.countDown();
		done.await();
		return System.nanoTime() - startNanos;
	}
}
