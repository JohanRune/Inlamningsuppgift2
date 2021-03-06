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

    //detta testar om data läses in ok och läggs ok i en arrayList.
    @Test
    public final void testCreateDataArray(){
        List<String> customerData = cm.createDataArray("customers.txt");
        assertTrue(customerData.size()== 28);
        assertTrue(customerData.get(0).startsWith("7603021234"));
        assertTrue(customerData.get(1).startsWith("2019-07-01"));
        assertTrue(customerData.get(2).endsWith("Belle"));

    }

    @Test
    public final void testCreateCustomerArray(){
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
    }

    @Test
    public final void testReadInputData(){
        cm.test=true;
        String ok = "23";
        assertTrue(cm.readInputData(ok).equals("23"));
        assertFalse(cm.readInputData(ok).equals("24"));
    }


    @Test
    public final void testCustomerOrNotOrHasBeen(){
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
    public final void testNowOrExCustomer(){
        String date1 = "2015-08-03";
        String date2 = "2020-03-15";
        String date3 = "2020-01-30";
        assertTrue(cm.presentOrExCustomer(date1).equals("Personen har varit kund förut men är inte kund nu"));
        assertTrue(cm.presentOrExCustomer(date2).equals("Personen är kund nu"));
        assertTrue(cm.presentOrExCustomer(date3).equals("Personen är kund nu"));
    }
}