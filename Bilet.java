package model;

import java.util.ArrayList;
import java.util.List;

public class Bilet {
	
	List<Match> bilet;
	
	public Bilet() {
		bilet = new ArrayList<Match>();
	}

	public List<Match> getBilet() {
		return bilet;
	}

	public void setBilet(List<Match> bilet) {
		this.bilet = bilet;
	}
	
}
