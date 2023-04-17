package utils;

public class DemoHilos extends Thread {
	private String nombre;
	public DemoHilos(String nombre) {
		this.nombre=nombre;
	}
	
	public void run() {
		try {
			int x = (int)(Math.random()*5000);
			Thread.sleep(x);
			System.out.println("Soy: "+nombre+"("+x+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		DemoHilos t1= new DemoHilos("Pedro");
		DemoHilos t2 = new DemoHilos("Pablo");
		DemoHilos t3 = new DemoHilos("Juan");
		
		t1.start();
		t2.start();
		t3.start();

	}

}
