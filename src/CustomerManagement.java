import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.time.temporal.ChronoUnit;


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


    public static Boolean test = false;
    private Scanner scanner;

    public String readInputData (String promt, String optionalTestParameter) {
        if (test) {
            scanner = new Scanner(optionalTestParameter);
        }
        else{
            scanner = new Scanner(System.in);
        }

        while (true) {
            try {
                System.out.println(promt);
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                // TODO: 2020-10-10  tweeka. ta bort?
                System.out.println("Du skrev något annat än siffror. Försök igen.");
                scanner.next();
            }
            catch (NoSuchElementException e) {
                System.out.println("Indata saknas!");
                scanner.next();
            }
            catch (Exception e) {
                System.out.println("Något gick fel. Försök igen.");
                scanner.next();
                e.getStackTrace();
            }
        }
    }


    public String communication(){
        String input = readInputData("Skriv namn eller personnummer!", null);
        return input;

    }



    //nu ta input och söka in kundregistret.
    public String customerOrNotOrHasBeen(List<Customer> customerList, String s) {

        for (Customer c : customerList) {
            if (c.getName().equals(s) || c.getIdNumber().equals(s))
                return presentOrExCustomer(c.getJoinDate());

        }
        return "Personen har aldrig varit kund";
    }


    public String presentOrExCustomer(String date) {

        LocalDate firstDate = LocalDate.parse(date);
        LocalDate dateToday = LocalDate.now();

        if (ChronoUnit.DAYS.between(firstDate, dateToday) <= 365)
            return "Personen är kund nu";
        else
            return "Personen har varit kund förut men är inte kund nu";
    }



    public void mainProgram(){
        List<String> customerDataArray = createDataArray(customerPath);
        List<Customer> customerList = createCustomerList(customerDataArray);
        String input = communication();
        String customerOrNotOrHasBeen = customerOrNotOrHasBeen(customerList, input);
        System.out.println(customerOrNotOrHasBeen);
    }


}

