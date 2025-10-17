package diaz;

import java.util.ArrayList;

public class SalesSlip {
	private ArrayList<SalesItem> list;
	
	public SalesSlip() {
		list = new ArrayList<SalesItem>();
	}
	
	public void addItem(SalesItem item) {
		list.add(item);
	}
	
	public double getTotal() {
	    double total = 0;
	    for (int i = 0; i < list.size(); i++) {
	        SalesItem item = list.get(i); 
	        total = total + item.getTotal();
	    }
	    return total;
	}
	
	public String toString() {
	    String all = "";
	    for (int i = 0; i < list.size(); i++) {
	        SalesItem item = list.get(i);
	        all = all + item.toString() + "\n";
	    }
	    return all;
	}
	

}
