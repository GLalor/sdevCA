
package sdevca;

import java.util.ArrayList;
import java.util.List;
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
<<<<<<< HEAD
    private String name; // employee name
    @ManyToOne
    @JoinColumn(name="shift_id")
    private Shift shift;
    
    @ManyToMany(mappedBy = "staffList")
    private List<Roster> rosterList = new ArrayList<>();
=======
    private String fName; // employee first name
    private String lName; // employee last name
    @OneToMany(mappedBy="staff")
    @MapKey(name="shiftID")
    private Shift shift;

    @ManyToOne 
    private Roster roster;
>>>>>>> 10b4361700616f19b5a7a4afa9095a29198b5054
    
    // Getters and Setters
    public int getEmployeeNum() {
        return empNum;
    }

    public void setEmployeeNum(int empNum) {
        this.empNum = empNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
    
    public void setShift(Shift shift){
        this.shift=shift;
    }
    
     public List<Roster> getRosterList() {
        return rosterList;
    }

    public void setRosterList(List<Roster> rosterlist) {
        this.rosterList = rosterlist;
    }

    public void addRoster(Roster r) {
        rosterList.add(r);
        r.getStaffList().add(this);
    }

    public void removeStaff(Roster r) {
        rosterList.remove(r);
        r.getStaffList().remove(this);
    }

    @Override
    public String toString() {
        return "Employee Number: " + empNum + ", Name: " + name + " Shift type: " + shift.getShiftType()+"\n" ;
    }
    
}
