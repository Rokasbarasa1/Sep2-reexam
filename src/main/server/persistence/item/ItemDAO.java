package main.server.persistence.item;

import main.server.persistence.database.DataConnectionException;
import main.server.persistence.database.IDBConnection;
import main.shared.Item;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO implements IItemDAO{
    private final IDBConnection databaseConnection;

    public ItemDAO(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<Item> getMenuItems() {
        ArrayList<Item> items = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Item;";
            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next()) {
                int id = resultSet.getInt("item_ID");
                String name = resultSet.getString("itemName");
                boolean customizable = resultSet.getBoolean("customizable");
                double price = resultSet.getDouble("price");
                String groupName = resultSet.getString("groupName");

                Item item = new Item(id, name,  customizable,  price,  groupName);
                items.add(item);
            }
        } catch (DataConnectionException | SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection();
        }
        return items;
    }

    @Override
    public boolean addItem(Item item) {
        try{
            String sql = "INSERT INTO Item VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setBoolean(2, item.isCustomizable());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getGroupName());
            int i = preparedStatement.executeUpdate();


            if (i == 1){
                return true;
            }
        } catch (DataConnectionException | SQLException e) {
        e.printStackTrace();
        } finally {
        databaseConnection.closeConnection();
        }
        return false;
    }

    public int getItemID(Item item) {
        int id = 0;
        try {
            PreparedStatement getID = databaseConnection.createPreparedStatement("SELECT OBJECT_ID('" + item.getName() + "')");
            ResultSet resultSet = getID.executeQuery();
            id = resultSet.getInt("item_ID");

        } catch (DataConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}

