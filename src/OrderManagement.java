import java.time.LocalDateTime;

public interface OrderManagement
{
    public void addOrder(String orderId, String orderDescription, String deliveryAddress, LocalDateTime orderDate, String deliveryDatetime, double amount);
    public void viewOrder();

    public void viewByOrderId(String OrderId);

    public void sortOrder();
    public void deleteOrderById(String OrderID);

    public void markAsDelivered(String OrderID);

    public void generateReport();
    public void exit();


    void GenerateReport();
}

