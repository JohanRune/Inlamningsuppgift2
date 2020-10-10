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
        //en metod som får in "promt", dvs ett meddelande till kunden,

    //detta testar om data läses in ok och läggs ok i en arrayList.
    @Test
    public final void createDataArrayTest(){
        List<String> customerData = cm.createDataArray("customers.txt");
        assertTrue(customerData.size()== 29);
        assertTrue(customerData.get(0).startsWith("7603021234"));
        assertTrue(customerData.get(1).startsWith("2019-07-01"));
    }

    @Test
    public final void createCustomerArrayTest(){
        List<String> customerData = cm.createDataArray("customers.txt");
        List<Customer> customerList = cm.createCustomerList(customerData);
        assertTrue(customerList.get(0).getIdNumber().equals("7603021234"));
        assertTrue(customerList.get(0).getName().equals("Alhambra Aromes"));
        assertTrue(customerList.get(0).getJoinDate().equals("2019-07-01")); //formatet i LocalDate?
        assertTrue(customerList.get(1).getIdNumber().equals("8104021234"));
        assertTrue(customerList.get(4).getIdNumber().equals("7605021234"));
        assertTrue(customerList.get(4).getName().equals("Elmer Ekorrsson"));
        assertTrue(customerList.get(4).getJoinDate().equals("2010-04-07"));
        assertTrue(customerList.get(13).getIdNumber().equals("7805211234"));
        assertTrue(customerList.get(13).getName().equals("Nahema Ninsson"));
        assertTrue(customerList.get(13).getJoinDate().equals("2020-08-04"));
    }


}
