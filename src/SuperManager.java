import java.io.IOException;

import com.sm.http.ManagerLoader;


public class SuperManager {

	public static void main(String[] args) throws IOException {
		
		ManagerLoader ml = new ManagerLoader();
		ml.load("yaguito", "papoce");
	}
}
