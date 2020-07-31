package main.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private int ID;
    private ArrayList<Item> items;
    private boolean finished;

    public Order(int ID, ArrayList<Item> items) {
        this.ID = ID;
        this.items = items;
        this.finished = false;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Item> getItems() {
        String itemsString = "";
        for (int i = 0; i < items.size(); i++) {
            itemsString += items.get(i).getName()+ ", ";
        }
        return items;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
