import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by Johan Rune
 * Date: 2020-10-09
 * Time: 15:23
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class CustomerManagementTest {

CustomerManagement cm = new CustomerManagement();

    //börja med att göra test för att läsa in data från filen,
    // sedan skapa objekt för personer och lägga personobjekten i ny lista.
    //därefter skriva program som kollar om personer har betalat eller inte.
        //en metod som får in "promt", dvs ett meddelande till kunden. readInputData. två sorter. id-nummer eller namn: kommer senare ev.
        //metod som skickar promt till dataskärmen. med fält för svar. läser in svar.
        //kolla input data mot kundregister. Här kolla antal dagar sedan betalade, och här kolla metoden jag hittade?



    //detta testar om data läses in ok och läggs ok i en arrayList.
    @Test
    public final void createDataArrayTest(){
        List<String> customerData = cm.createDataArray("customers.txt");
        assertTrue(customerData.size()== 28);
        assertTrue(customerData.get(0).startsWith("7603021234"));
        assertTrue(customerData.get(1).startsWith("2019-07-01"));
    }

    @Test
    public final void createCustomerArrayTest(){
        List<String> customerData = cm.createDataArray("customers.txt");
        List<Customer> customerList = cm.createCustomerList(customerData);
        assertTrue(customerList.get(0).getIdNumber().equals("7603021234"));
        assertTrue(customerList.get(0).getName().equals("Alhambra Aromes"));
        assertTrue(customerList.get(0).getJoinDate().equals("2019-07-01"));
        assertTrue(customerList.get(1).getIdNumber().equals("8104021234"));
        assertTrue(customerList.get(4).getIdNumber().equals("7605021234"));
        assertTrue(customerList.get(4).getName().equals("Elmer Ekorrsson"));
        assertTrue(customerList.get(4).getJoinDate().equals("2010-04-07"));
        assertTrue(customerList.get(13).getIdNumber().equals("7805211234"));
        assertTrue(customerList.get(13).getName().equals("Nahema Ninsson"));
        assertTrue(customerList.get(13).getJoinDate().equals("2020-08-04"));
        assertTrue(customerList.get(3).getName().equals("Diamanda Djedi"));
        assertTrue(customerList.get(3).getJoinDate().equals("2020-01-30"));

    }

    @Test
    public final void readInputDataTest(){
        cm.test=true;
        String ok = "23";
        assertTrue(cm.readInputData("Hej", ok).equals("23"));
    }


    @Test
    public final void customerOrNotOrHasBeenTest(){
        List<String> customerData = cm.createDataArray("customers.txt");
        List<Customer> customerList = cm.createCustomerList(customerData);
        assertTrue(cm.customerOrNotOrHasBeen(customerList, "Alhambra Aromes").equals("Personen har varit kund förut men är inte kund nu"));
        assertTrue(cm.customerOrNotOrHasBeen(customerList, "7603021234").equals("Personen har varit kund förut men är inte kund nu"));
        assertTrue(cm.customerOrNotOrHasBeen(customerList, "Bear Belle").equals("Personen har varit kund förut men är inte kund nu"));
        assertTrue(cm.customerOrNotOrHasBeen(customerList, "Diamanda Djedi").equals("Personen är kund nu"));
        assertTrue(cm.customerOrNotOrHasBeen(customerList,"7608021234").equals("Personen är kund nu"));
        assertTrue(cm.customerOrNotOrHasBeen(customerList,"Greger Ganache").equals("Personen är kund nu"));
        assertFalse(cm.customerOrNotOrHasBeen(customerList, "Hej hej").equals("Personen är kund nu"));
        assertTrue(cm.customerOrNotOrHasBeen(customerList, "0000000000").equals("Personen har aldrig varit kund"));

    }

    @Test
    public final void nowOrExCustomerTest(){
        String date1 = "2015-08-03";
        String date2 = "2020-03-15";
        String date3 = "2020-01-30";
        assertTrue(cm.presentOrExCustomer(date1).equals("Personen har varit kund förut men är inte kund nu"));
        assertTrue(cm.presentOrExCustomer(date2).equals("Personen är kund nu"));
        assertTrue(cm.presentOrExCustomer(date3).equals("Personen är kund nu"));
    }

    //nu behövs dialogrutor. Den frågar efter namn eller nummer och väntar på svar. Den ger tillbaka info om kunden.




}
