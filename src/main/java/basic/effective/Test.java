package basic.effective;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

public class Test {

	private static PrivCl privCl;
	
	public static PrivCl getInstance() {
		if(privCl == null) {
			privCl = new PrivCl();
		}
		
		System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1.00")));
		System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1.00")));
		return privCl;
	}
	public static void main(String[] args) {
		System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1.00")));
		System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1.00")));
	}
	
	private static class PrivCl implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -9145575529250184427L;
		
	}
	
	
	private Test() {
		Arrays.copyOf(new char[2], 2);
		String a = new String("ABC");
	}
}
