package main.server.model.orders;

import main.server.rmi.RmiHandler;
import main.shared.Order;

import java.util.ArrayList;

public class OrderModel implements IOrderModel {
    private RmiHandler rmiHandler;
    private ArrayList<Order> orders;
    private int count;

    public OrderModel() {
        count = 0;
        this.orders = new ArrayList<>();
    }

    public void setRmiHandler(RmiHandler rmiHandler) {
        this.rmiHandler = rmiHandler;
    }

    @Override
    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public ArrayList<Order> getUnfinishedOrders() {
        ArrayList<Order> unFinishedOrders = new ArrayList<>();
        for (int i = 0; i < orders.size() ; i++) {

        }
        for (int i = 0; i < orders.size() ; i++){
            if(orders.get(i).isFinished() == false){
                unFinishedOrders.add(orders.get(i));
            }
        }
        return unFinishedOrders;
    }

    @Override
    public int getIdForOrder() {
        count++;
        if(count < 100) {
            return count;
        } else {
            count = 1;
            return count;
        }
    }

    @Override
    public void makeOrder(Order order) {
        orders.add(order);
        rmiHandler.sendUpdateToOrderScreens();
        rmiHandler.sendUpdateToReceptionists();
    }

    @Override
    public void completeID(int ID) {
        for (int i = 0; i < orders.size(); i++){
            if (orders.get(i).getID() == ID){
                orders.get(i).setFinished(true);
                rmiHandler.sendUpdateToOrderScreens();
                rmiHandler.sendUpdateToReceptionists();
                break;
            }
        }
    }
}