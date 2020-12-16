package managers;

import entities.OrderToArrive;
import entities.OrderToShip;

import java.util.List;

public class OrdersManager {
    private List<OrderToShip> ordersToShip;
    private List<OrderToArrive> ordersToArrive;

    public List<OrderToShip> getOrdersToShip() {
        return ordersToShip;
    }

    public void setOrdersToShip(List<OrderToShip> ordersToShip) {
        this.ordersToShip = ordersToShip;
    }

    public List<OrderToArrive> getOrdersToArrive() {
        return ordersToArrive;
    }

    public void setOrdersToArrive(List<OrderToArrive> ordersToArrive) {
        this.ordersToArrive = ordersToArrive;
    }

    public void setOrderToArrive(OrderToArrive orderToArrive) {
        this.ordersToArrive.add(orderToArrive);
    }

    public void setOrderToShip(OrderToShip orderToShip) {
        this.ordersToShip.add(orderToShip);
    }

    @Override
    public String toString() {
        return "OrdersManager{" +
                "ordersToShip=" + ordersToShip +
                ", ordersToArrive=" + ordersToArrive +
                '}';
    }
}
