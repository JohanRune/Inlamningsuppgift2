import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Johan Rune
 * Date: 2020-10-09
 * Time: 15:23
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class CustomerManagement {

    final String customerPath = "customers.txt";


    public List<String> createDataArray(String customerPath) {
        List<String> customerDataList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(customerPath));

            while (scanner.hasNextLine()){
                customerDataList.add(scanner.nextLine());
            }
        }
        catch (Exception e){
            System.out.println("Ett fel uppstod.");
        }

        return customerDataList;


    }

    public List<Customer> createCustomerList(List<String> customerData) {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();

        for (int i = 0; i < customerData.size(); i++) {
            if (customerData.get(i).contains(",")) {
                customer = new Customer();
                customer.setIdNumber(customerData.get(i).substring(0, 10)); //kanske ändra "9" till mindre hårdkodad lösning.   ;
                customer.setName(customerData.get(i).substring(12)); //kanske ändra "13" till mindre hårdkodad lösning.   ;
            }
            else {
                customer.setJoinDate(customerData.get(i)); //kanske ändra "9" till mindre hårdkodad lösning.   ;
                customerList.add(customer);
            }
        }
        return customerList;
    }


    public void mainProgram(){
        List<String> customerDataArray = createDataArray(customerPath);
        createCustomerList(customerDataArray);
    }

}

