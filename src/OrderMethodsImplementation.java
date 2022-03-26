import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderMethodsImplementation implements OrderManagement
{
    static List<Order> list = new ArrayList<>();
    static List<Order> delivered = new ArrayList<>();
    static List<Object> orders = new ArrayList<>();
    static File file = new File("C:\\ordermanagementsystem\\ordermanagementtext.txt");



    public OrderMethodsImplementation() {
    }

    public static void listLoad()
    {
        if(file.length() != 0)
        {
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
        }
        else
        {
            System.out.println("List is empty.....");
        }
    }

    public static void fileUpdate()
    {
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(list);
            oo.flush();
            oo.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void addOrder(String orderId, String orderDescription, String deliveryAddress, LocalDateTime orderDate, String deliveryDatetime, double amount)
    {
//        List<Order> listOfOrders= new ArrayList<>();
        listLoad();
        list.add(new Order(orderId,orderDescription,deliveryAddress,orderDate,deliveryDatetime,amount));
        System.out.println("Order added successfully");
       /* for(Order i:list)
        {
            System.out.println(i.getOrderId() + " " + i.getOrderDate());
        }*/
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(list);
            oo.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }




    @Override
    public void  viewOrder()
    {
        listLoad();
        if(file.length()!=0) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);
                list = (ArrayList<Order>) oi.readObject();
                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");
                for (Order i : list) {
                    System.out.println(i.getOrderId() + " " + i.getOrderDescription() + " " + i.getDeliveryAddress() + " " +i.getOrderDate() + " " + i.getAmount()+ " " +i.getDeliveryDatetime());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else
            System.out.println("List is empty.....");
    }

    @Override
    public void viewByOrderId(String OrderId)
    {
        listLoad();
        System.out.println("----------------------------");
        System.out.println("Order Detail");
        System.out.println("----------------------------");
        for (Order i : list)
        {
            if (OrderId.equals(i.getOrderId()))
            {
                System.out.println("Order Id : " + i.getOrderId());
                System.out.println("Order Description : " + i.getOrderDescription());
                System.out.println("Delivery Address : " + i.getDeliveryAddress());
                System.out.println("Amount : " + i.getAmount());
                System.out.println("Order Date : " + i.getOrderDate());
                System.out.println("Delivery Date Time : " + i.getDeliveryDatetime());
            }
            else
            {
                System.out.println("Enter Correct Order ID");
            }

        }

    }

    @Override
    public void sortOrder()
    {
        System.out.println("\n *******Choose Sort Order Property******* \n 1.Order Id \n 2.Order Description \n 3.Delivery Address \n 4.Order Date \n 5.Amount \n 6.Delivery Datetime \n 7.Exit");
        System.out.println("Enter Your Choice : \n");
        int Choice;
        Scanner sc = new Scanner(System.in);
        Choice = sc.nextInt();
        switch(Choice)
        {
            case 1://by orderId
                Collections.sort(list, new Comparator<Order>() {

                    @Override
                    public int compare(Order o1, Order o2) {
                        return Integer.parseInt(o1.getOrderId()) - Integer.parseInt(o2.getOrderId());
                    }
                });

                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");

                //printing the list
                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getOrderId() + " " + list.get(i).getOrderDescription() + " " +list.get(i).getDeliveryAddress()+ " " +list.get(i).getOrderDate()+ " " +list.get(i).getAmount()+ " " +list.get(i).getDeliveryDatetime());
                }
                System.out.println("Successfully sorted by OrderId.....");
                break;
            case 2://by orderDescription
                Collections.sort(list, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getOrderDescription().compareToIgnoreCase(o2.getOrderDescription());
                    }
                });
                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");

                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getOrderId() + " " + list.get(i).getOrderDescription() + " " +list.get(i).getDeliveryAddress()+ " " +list.get(i).getOrderDate()+ " " +list.get(i).getAmount()+ " " +list.get(i).getDeliveryDatetime());
                }
                System.out.println("Successfully Sorted By OrderDescription....");
                break;
            case 3://by deliveryAddress
                Collections.sort(list, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getDeliveryAddress().compareTo(o2.getDeliveryAddress());
                    }
                });
                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");

                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getOrderId() + " " + list.get(i).getOrderDescription() + " " +list.get(i).getDeliveryAddress()+ " " +list.get(i).getOrderDate()+ " " +list.get(i).getAmount()+ " " +list.get(i).getDeliveryDatetime());
                }
                System.out.println("Successfully sorted on delivery Address.....");
                break;
            case 4://by orderDate
                Collections.sort(list, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getOrderDate().compareTo(o2.getOrderDate());
                    }
                });
                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");

                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getOrderId() + " " + list.get(i).getOrderDescription() + " " +list.get(i).getDeliveryAddress()+ " " +list.get(i).getOrderDate()+ " " +list.get(i).getAmount()+ " " +list.get(i).getDeliveryDatetime());
                }
                System.out.println("Successfully sorted on order Date....");
                break;
            case 5://by Amount
                Collections.sort(list, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return (int) (o1.getAmount() - o2.getAmount());
                    }
                });
                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");

                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getOrderId() + " " + list.get(i).getOrderDescription() + " " +list.get(i).getDeliveryAddress()+ " " +list.get(i).getOrderDate()+ " " +list.get(i).getAmount()+ " " +list.get(i).getDeliveryDatetime());
                }
                System.out.println("Successfully sorted on Amount.....");
                break;
            case 6://by Delivery
                Collections.sort(list, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getDeliveryDatetime().compareTo(o2.getDeliveryDatetime());
                    }
                });
                System.out.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");

                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getOrderId() + " " + list.get(i).getOrderDescription() + " " +list.get(i).getDeliveryAddress()+ " " +list.get(i).getOrderDate()+ " " +list.get(i).getAmount()+ " " +list.get(i).getDeliveryDatetime());
                }
                System.out.println("Successfully sorted on Delivery Date Time....");
                break;
            default:
                System.out.println("Enter Proper Choice !!!!");

        }
    }

    @Override
    public void deleteOrderById(String OrderID)
    {
        listLoad();
        for (int i = 0; i < list.size(); i++)
        {
            if (OrderID.equals(list.get(i).getOrderId()))
            {
                list.remove(i);
                break;
            }
        }
        System.out.println("Your order has been removed Successfully.....");
        //updating the file
        fileUpdate();
    }

    @Override
    public void markAsDelivered(String OrderID)
    {
        listLoad();

        String dateFormat = "yyyy-MM-dd hh:mm:ss a";
        LocalDateTime now = LocalDateTime.now();
        String format = (now.format(DateTimeFormatter.ofPattern(dateFormat)));

        for (int i = 0; i < list.size(); i++) {
            if (OrderID.equals(list.get(i).getOrderId())) {
                list.get(i).setDeliveryDatetime(format);
                System.out.println("Order Delivered Successfully...");
                break;
            }
        }

        fileUpdate();
    }

    @Override
    public void generateReport()
    {
        GenerateReport();
        System.out.println("Generated Report Successfully...");
    }

    @Override
    public void exit()
    {
        fileUpdate();
        System.out.println("Exited Thank you.....");
    }

    @Override
    public void GenerateReport()
    {
        listLoad();
        if(delivered.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDeliveryDatetime() != null) {
                    delivered.add(list.get(i));
                }
            }
        }
        else
        {
            for(int j=0;j<list.size();j++) {
                if (delivered.get(j).getOrderId().contains(list.get(j).getOrderId()))
                {

                }else
                {
                    delivered.add(list.get(j));
                }
            }
        }
        System.out.println("The size of Delivered List is : " +delivered.size());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");

        File file = new File("Delivered_" +dateFormat.format(date) + ".txt");
        try
        {
            file.createNewFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            if(file.length() == 0)
            {
                pw.println(" OrderId \t" + " OrderDescription \t" + " DeliveryAddress \t" + " OrderDateTime \t" + " \t\tAmount \t\t\t" + " DeliveryDateTime \t");
            }

            for(int i=0;i<delivered.size();i++)
            {
                pw.println(delivered.get(i).toString());
            }
            pw.close();
            delivered.clear();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

