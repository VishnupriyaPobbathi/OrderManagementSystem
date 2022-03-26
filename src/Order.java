import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order implements Serializable {

    static File file = new File("C:\\ordermanagementsystem\\ordermanagementtext.txt");

    static List<Order> list = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    private String orderId;
    private String orderDescription;
    private String deliveryAddress;
    private static LocalDateTime orderDate;
    private String deliveryDatetime;
    private double Amount;

//    public Order(String orderId, String orderDescription, double amount, String deliveryAddress, LocalDateTime orderDate) {
//    }


    public Order(String orderId, String orderDescription, String deliveryAddress, LocalDateTime orderDate, String deliveryDatetime, double amount) {
        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.deliveryDatetime = deliveryDatetime;
        this.Amount = amount;
    }


    public Order() {

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDatetime() {
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(String deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public static void listLoad() {
        if (file.length() != 0) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);
                list = (ArrayList<Order>) oi.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("List is empty.....");
        }
    }


    private static final long serialVersionUID = -6503517380979316252L;

    public static void main(String... args) {
        //System.out.println("Hello");

        Scanner sc = new Scanner(System.in);

        OrderMethodsImplementation o = new OrderMethodsImplementation();


        boolean flag = false;
        int choice = 0;

        while (choice != 8) {
            System.out.println("\n *******Order Management System******* \n 1.Add Order \n 2.View Order List \n 3.View By Order Id \n 4.Sort Order \n 5.Delete Order By Id \n 6.Mark As Delivered \n 7.Generate Report \n 8.Exit ");
            System.out.println("Choose Option: \n");

            listLoad();

            if (flag) {
                choice = 1;
                flag = false;
            } else {
                choice = sc.nextInt();
            }

            switch (choice) {
                case 1:
                    int count = 0;
                    System.out.println("Enter orderId : ");
                    String orderId = sc.next();
                    for (Order i : list) {
                        if (orderId.equalsIgnoreCase(i.getOrderId())) {
                            System.out.println("Duplicate Order Id... Please Enter the order again");
                            count = 1;
                            break;
                        }
                    }
                    if (count == 1) {
                        flag = true;
                        break;
                    }
                    Pattern p = Pattern.compile("[a-zA-Z]");
                    Matcher matcher = p.matcher(orderId);
                    if (matcher.find()) {
                        System.out.println("Invalid Order Id entered..... Please Enter the Order Details Again...");
                        count = 1;
                    }
                    if (count == 1) {
                        flag = true;
                        break;
                    }
                    sc.skip("\n");
                    System.out.println("Enter orderDescription: ");
                    String orderDescription = sc.nextLine();
                    p = Pattern.compile("\\W");
                    Matcher matcher1 = p.matcher(orderDescription);
                    if (matcher1.find()) {
                        System.out.println("Invalid Order Description....Please re-enter :");
                        count = 1;
                    }
                    if (count == 1) {
                        flag = true;
                        break;
                    }

                    System.out.println("Enter deliveryAddress: ");
                    String deliveryAddress = sc.nextLine();
                    System.out.println("Enter amount: ");
                    double amount = sc.nextDouble();

                    String dateFormat = "yyyy-MM-dd hh:mm:ss a";
                    LocalDateTime orderDate = LocalDateTime.now();
                    // String format = (deliveryDatetime.format(DateTimeFormatter.ofPattern(dateFormat)));


                    //function add order is called to add all these details.
                    o.addOrder(orderId, orderDescription, deliveryAddress, orderDate, null, amount);

                    System.out.println("Do you want to Place another order... Press Y to place.. N to return to menu");
                    char ch = sc.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') {
                        flag = true;
                        break;
                    }
                    break;
                case 2:
                    listLoad();
                   /* System.out.println(list.size());
                    System.out.println(o);*/
                    if (list.isEmpty()) {
                        System.out.println("there are no orders available to view.....");
                    } else {
                        o.viewOrder();
                    }
                    break;
                case 3:
                    if (list.isEmpty()) {
                        System.out.println("There are no orders to view....");
                    } else {
                        System.out.println("Enter OrderId :");
                        String ordID = sc.next();
                        o.viewByOrderId(ordID);
                    }
                    break;
                case 4:
                    if (list.isEmpty()) {
                        System.out.println("There are no orders to sort....");
                    } else {
                        o.sortOrder();
                    }
                    break;
                case 5:
                    if (list.isEmpty()) {
                        System.out.println("Orders List is Empty.... No Orders Available....");
                    } else {
                        System.out.println("Enter OrderId : ");
                        String oID = sc.next();
                        o.deleteOrderById(oID);
                    }
                    break;
                case 6:
                    if (list.isEmpty()) {
                        System.out.println("No more Orders Available......");
                    } else {
                        System.out.println("Enter orderId : ");
                        String id = sc.next();

                        for (int i = 0; i < orders.size(); i++) {
                            if (id.equals(orders.get(i).getOrderId()) && orders.get(i).getDeliveryDatetime() != null) {
                                System.out.println("Order has been already delivered at : " + orders.get(i).getDeliveryDatetime());
                            }
                        }

                        o.markAsDelivered(id);
                    }
                    break;
                case 7:
                    o.generateReport();
                    break;
                case 8:
                    o.exit();
                    break;
                default:
                    System.out.println("Enter Proper Choice");
            }
        }
    }

}


