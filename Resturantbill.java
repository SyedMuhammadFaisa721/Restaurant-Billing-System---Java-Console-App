package resturantbill;
//import all required packages/
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//Item class to represent each order item
class Item{  
    int quantity;
    String item;
    int price;
    
    // Parameterized constructor to initialize item
    Item(int quantity,String item,int price ){
        this.quantity = quantity;
        this.item = item;
        this.price = price;

    }
    
//  non parameterized method to give the final amount
    public int finalamount(){
    return(price*quantity);
    }   
}

// Receipt class to manage order details and printing
class Receipt{
    String bill_type;
    String User;
    String Waiter;
    
//  create list to store multiple items
    List<Item> items =new ArrayList<>();
    String date ,time;
    
     // Parameterized constructor to initialize receipt details
    Receipt(String bill_type, String User , String Waiter){
        this.bill_type= bill_type;
        this.User=User;
        this.Waiter = Waiter;
        LocalDate currentdate = LocalDate.now();
        LocalTime currenttime  = LocalTime.now();
        DateTimeFormatter dateformate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeformate = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        this.date = dateformate.format(currentdate);
        this.time = timeformate.format(currenttime);
    }
//  create a parameterized mehtod to add item to the receipt
    void addItem(Item item){
        items.add(item);
    }
    
//  non-parameterized method to print the receipt
    public void printreceipt(){
        System.out.println("\n               ROYAL TAJ GULSHAN");
        System.out.println("Address: B FL_7 Block 7 Gulshan-E-Iqbal karachi East");
        System.out.println("    Email: rt.gulshan@tajcorporation.com");
        System.out.println("......................................................");
        System.out.println("                Bill type : " + bill_type);
        System.out.println("Date:                                      "+ date);
        System.out.println("Time: "+time +"                         User: " +User);
        System.out.println("Waiter: " + Waiter);
        System.out.println("=========================================================");
        System.out.printf("%-8s%-32s%-10s%-10s\n","Qty","Item(s)","Price","Total");
        System.out.println("=========================================================");
        double grand_total=0;
        
        // Loop to display each item in receipt
        for(Item item: items){
            System.out.printf("%-8s%-32s%-12s%-12s\n",item.quantity,item.item,item.price,item.finalamount());
            grand_total+=item.finalamount();
        } 
        double salestax= grand_total*0.15;
        double grandfinalamount = grand_total+salestax;
        System.out.println("=========================================================");
        System.out.printf("Total: %48.0f\n",grand_total);
        System.out.printf("\nSales Tax (15%%): %38.0f\n", salestax);
        System.out.printf("Total Amount: %41.0f\n" , grandfinalamount);
        System.out.println("=========================================================");
    
    }
}
// main class
public class Resturantbill {

    public static void main(String[] args) {
        try(Scanner s =new Scanner(System.in);){
            System.out.print("Enter the Bill type: ");
            String type = s.nextLine();
            System.out.print("Enter the User name: ");
            String name = s.nextLine();
            System.out.print("Enter the Waiter name: ");
            String waitername = s.nextLine();
            Receipt r = new Receipt(type,name,waitername);
         while(true){
             System.out.println("\nIf you want to Exit enter 0 in quantity");
            System.out.print("Enter the Quantity: ");
            int quan=s.nextInt();
            s.nextLine();
            // Exit loop if quantity is 0
            if(quan==0){
            break;
            }
            System.out.print("Enter the Items name: ");
            String nam = s.nextLine();
            System.out.print("Enter the price: ");
            int pri = s.nextInt();
            
            // Creating item object and adding to receipt
            Item item = new Item(quan,nam,pri);
            r.addItem(item);
            
         }
         s.close();
         r.printreceipt();
        }catch(Exception e){
            System.out.println("Please enter the correct details "+e.getMessage());
        }
    }
}

