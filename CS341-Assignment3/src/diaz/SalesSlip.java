package diaz;

import java.util.ArrayList;

public class SalesSlip {
    private ArrayList<SalesItem> items;

    public SalesSlip() {
        items = new ArrayList<>();
    }

    public void addItem(SalesItem item) {
        items.add(item);
    }

    public double getTotalSales() {
        double total = 0;
        for (SalesItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SalesItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}

