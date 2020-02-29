package basic.effective;

public class InsensitiveString {

	private String s;

	public InsensitiveString(String s) {
		super();
		this.s = s;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof InsensitiveString) {
			return s.equalsIgnoreCase(((InsensitiveString)obj).s) ;
		}
		if(obj instanceof String) {
			return s.equalsIgnoreCase((String) obj);
		}
		return false;	
	}
	
	public static void main(String[] args) {
		InsensitiveString o = new  InsensitiveString("Polish");
		String s= "Polish";
		if(o instanceof Object) {
			
		}
		System.out.println(o.equals(s));
		System.out.println(s.equals(o));
	}
}
