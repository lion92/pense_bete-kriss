package lecture;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lire implements Runnable {
	private String path;
	private static String recup="";
	public Lire(String path) {
		this.path = path;

	}

	public String lecture() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.path));
			String lecture;
			try {
				while ((lecture = reader.readLine()) != null) {
					System.out.println(lecture);
					recup+=lecture;
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recup;
	}
	public static void main (String[]args) {
		Lire lir=new Lire("D:\\kri\\emploidutemps.txt");
  		
		System.out.println(lir.lecture());
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000);
				lecture();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
