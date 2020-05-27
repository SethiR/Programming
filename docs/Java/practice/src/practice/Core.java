package practice;



class A{
	
	void funcA(String... args){
		for(String a: args) {
			System.out.println(a);
		}
	}
	
}



public class Core {
	public static void main(String[] args) {
		
		A a = new A();
		a.funcA("hello", "Hello again");
		
	}
}
