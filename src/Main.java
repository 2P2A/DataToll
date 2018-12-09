import java.io.IOException;

import ctrl.Controller;
import gui.MyFrame;

public class Main {
	
	private final static double DELTA_T=0.1;
	
	public static void main(String[] args) throws IOException {
		Controller controller = new Controller(DELTA_T);
	}

}
