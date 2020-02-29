package basic.effective;

public class ExtenedTest implements AutoCloseable{
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println("AutoBox " + sumAutoBox());
		measure(start);
		
		start = System.nanoTime();
		System.out.println("Only primitive " + sum());
		measure(start);
	}

	private static long sumAutoBox() {
		Long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
	
	private static long sum() {
		long sum = 0;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
	
	private static void measure(long nanoTime) {
		System.out.println((float)((System.nanoTime() - nanoTime) / 1000000));
	}

	public void close() throws Exception {
		
	}
}
