import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    Path outFilePath = Paths.get("ActivityLog3.txt");

    public List<String> createDataArray(String customerPath) {
        List<String> customerDataList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(customerPath)); //vad gör detta?

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
                customer.setIdNumber(customerData.get(i).substring(0, customerData.get(i).indexOf(",")));
                customer.setName(customerData.get(i).substring(customerData.get(i).indexOf(",")+2));
            }
            else {
                customer.setJoinDate(customerData.get(i)); //kanske ändra "9" till mindre hårdkodad lösning.   ;
                customerList.add(customer);
            }
        }
        return customerList;
    }

    /*

    public List<Customer> createCustomerList(List<String> customerData) {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();

        for (int i = 0; i < customerData.size(); i++) {
            if (customerData.get(i).contains(",")) {
                customer = new Customer();
                customer.setIdNumber(customerData.get(i).substring(0, 10)); //kanske ändra "10" till mindre hårdkodad lösning.   ;
                customer.setName(customerData.get(i).substring(12)); //kanske ändra "13" till mindre hårdkodad lösning.   ;
            }
            else {
                customer.setJoinDate(customerData.get(i)); //kanske ändra "9" till mindre hårdkodad lösning.   ;
                customerList.add(customer);
            }
        }
        return customerList;
    }
    */

    /*



    */




    public static Boolean test = false;
    private Scanner scanner;

    public String readInputData (String optionalTestParameter) {

        if (test) {
            scanner = new Scanner(optionalTestParameter);
        }
        else{
             scanner = new Scanner(System.in);
        }


        while (true) {
            try {
                System.out.println("Vad heter personen eller vad är hens personnummer (10 siffror)");
                String indata = scanner.nextLine();
                //String indata = JOptionPane.showInputDialog("Vad heter personen eller vad är hens personnummer (10 siffror)");
                //if (indata == null)
                //    System.exit(0);
                while (indata.isEmpty()) {
                    //indata = JOptionPane.showInputDialog("Du skrev inget. Försök igen.");
                    System.out.println("Du skrev inget. Försök igen!");
                    indata = scanner.nextLine();
                }
                return indata;
            }

            catch (Exception e) {
                //return JOptionPane.showInputDialog("Ett fel har uppstått. Försök igen.");
                //JOptionPane.showMessageDialog(null, "Ett fel har uppstått. Starta om programmet."); //obs, justera.
                System.out.println("Ett fel har uppstått.");
                e.getStackTrace();
                System.exit(0);
                //scanner.nextLine();
            }
        }
    }

    //nu ta input och söka in kundregistret.
    public String customerOrNotOrHasBeen(List<Customer> customerList, String s) {
        String s2;
        for (Customer c : customerList) {
            if (c.getName().equals(s) || c.getIdNumber().equals(s)) {
                s2 = presentOrExCustomer(c.getJoinDate());
                if (s2.equals("Personen är kund nu"))
                    writeToFile(c);
                return s2;
            }
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




    public void writeToFile(Customer customer){
            StringBuilder sb = new StringBuilder();

//        try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(outFilePath));){
            try (PrintWriter w = new PrintWriter(new FileWriter("ActivityLog3.txt",
                    true));) {
                w.println(sb.append(customer.getName()).append(", ").append(customer.getIdNumber())
                        .append(", ").append(LocalDate.now()));

            }
            catch (FileNotFoundException e) {
                System.out.println("Filen kunde inte hittas.");
                e.printStackTrace();
                System.exit(0);
            }
            catch (IOException e) {
                System.out.println("Det gick inte att skriva till fil.");
                e.printStackTrace();
                System.exit(0);
            }
            catch (Exception e) {
                System.out.println("Något gick fel.");
                e.printStackTrace();
                System.exit(0);
            }

        }







    public void mainProgram(){
        List<String> customerDataArray = createDataArray(customerPath);
        List<Customer> customerList = createCustomerList(customerDataArray);
       // String input = communication();
        String input = readInputData(null);
        String customerOrNotOrHasBeen = customerOrNotOrHasBeen(customerList, input);
        System.out.println(customerOrNotOrHasBeen); //ta bort

    }


}

