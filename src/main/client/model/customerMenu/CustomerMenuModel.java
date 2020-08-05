package main.client.model.customerMenu;

import main.client.clientNetworking.customerMenu.ICustomerMenuClient;
import main.shared.Ingredient;
import main.shared.Item;

import java.util.ArrayList;

public class CustomerMenuModel implements ICustomerMenuModel{
    private ICustomerMenuClient client;
    private ArrayList<Item> cart;
    private ArrayList<Item> menu;

    public CustomerMenuModel(ICustomerMenuClient receptionistMenuClient) {
        client = receptionistMenuClient;
        cart = new ArrayList<Item>();
    }

    @Override
    public void addToCart(int id) {
        cart.add(new Item(menu.get(id)));
        System.out.println(menu.get(id).toString());
    }

    @Override
    public ArrayList<Item> getMenu() {
        ArrayList<Item> list = client.getMenu();
        ArrayList<String> groupNames = new ArrayList<>();
        ArrayList<Item> listSortedByGroupName = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!groupNames.contains(list.get(i).getGroupName()))
                groupNames.add(list.get(i).getGroupName());
        }

        for (int i = 0; i < groupNames.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(groupNames.get(i).equals(list.get(j).getGroupName()))
                    listSortedByGroupName.add(list.get(j));
            }
        }
        menu = listSortedByGroupName;
        return menu;
    }

    @Override
    public ArrayList<Item> getCart() {
        return cart;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public void removeItemFromCart(int id) {
        cart.remove(id);
    }
}
