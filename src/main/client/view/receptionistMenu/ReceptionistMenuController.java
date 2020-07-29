package main.client.view.receptionistMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.client.view.ViewHandler;
import main.client.viewModel.ReceptionistMenuViewModel;
import main.shared.Item;
import main.shared.Order;

public class ReceptionistMenuController {
    @FXML
    private TableView<Item> menuTable;
    @FXML
    private TableColumn<Item, String> menuName;
    @FXML
    private TableColumn<Item, String> menuIngredients;
    @FXML
    private TableColumn<Item, Double> menuPrice;
    @FXML
    private TableColumn<Item, Boolean> menuCustomizable;
    @FXML
    private TableColumn<Item, String> menuGroupName;

    @FXML
    private TableView<Order> incompleteOrderTable;
    @FXML
    private TableColumn<Order, String> tableIdOrder;
    @FXML
    private TableColumn<Order, String> tableItemsOrder;


    private ViewHandler viewHandler;
    private ReceptionistMenuViewModel vm;
    private ObservableList<Item> menuItems = FXCollections.observableArrayList();
    private ObservableList<Order> orderItems = FXCollections.observableArrayList();

    public void init(ViewHandler viewHandler, ReceptionistMenuViewModel vm) {
        this.viewHandler = viewHandler;
        this.vm = vm;
        vm.loadReceptionist();
        vm.addPropertyChangeListener(evt -> updateTable());
        menuName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        menuIngredients.setCellValueFactory(new PropertyValueFactory<Item, String>("ingredients"));
        menuPrice.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        menuCustomizable.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("customizable"));
        menuGroupName.setCellValueFactory(new PropertyValueFactory<Item, String>("groupName"));
        menuItems.addAll(vm.getMenu());
        menuTable.setItems(menuItems);

        tableIdOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("ID"));
        tableItemsOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("items"));
        orderItems.addAll(vm.getOrders());
        incompleteOrderTable.setItems(orderItems);
    }

    public void updateTable(){
        orderItems.addAll(vm.getOrders());
        incompleteOrderTable.setItems(orderItems);
    }
}