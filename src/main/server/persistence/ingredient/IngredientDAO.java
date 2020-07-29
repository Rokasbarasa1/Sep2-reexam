package main.server.persistence.ingredient;

import main.server.persistence.database.DataConnectionException;
import main.server.persistence.database.IDBConnection;
import main.shared.Ingredient;
import main.shared.Receptionist;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAO implements IIngredientDAO{
    private final IDBConnection databaseConnection;

    public IngredientDAO(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<Ingredient> getIngredientsByItemId(int id) {
        ArrayList<Ingredient> ingredients =  new ArrayList<>();
        try{
            String sql =    "SELECT i.name" +
                            "FROM Ingredient i, ItemIngredient ie" +
                            "WHERE ie.itemID = " + id + " AND i.ID = ie.ingredientID;";
            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next()) {
                String name = resultSet.getString("name");
                Ingredient ingredient = new Ingredient(name);
                ingredients.add(ingredient);
            }
        } catch (DataConnectionException | SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection();
        }
        return ingredients;
    }
}
