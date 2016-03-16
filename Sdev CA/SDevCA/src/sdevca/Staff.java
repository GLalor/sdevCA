
package sdevca;

import java.util.Map;
import javax.persistence.*;


/**
 *
 * @author Graham
 */
@Entity
public class Staff {
    @Id
    @Column(name="staff_id")
    private int empNum;  //employee number
    private String fName; // employee first name
    private String lName; // employee last name
    @OneToMany(mappedBy="staff")
    @MapKey(name="shiftID")
    private Shift shift;

    @ManyToOne 
    private Roster roster;
    
    // Getters and Setters
    public int getEmployeeNum() {
        return empNum;
    }

    public void setEmployeeNum(int empNum) {
        this.empNum = empNum;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
    
    public void setShift(Shift shift){
        this.shift=shift;
    }
    
}
