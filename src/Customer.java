import java.time.LocalDate;

/**
 * Created by Johan Rune
 * Date: 2020-10-09
 * Time: 16:47
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class Customer {
    private String name;
    private String idNumber;
    private String joinDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }


}
