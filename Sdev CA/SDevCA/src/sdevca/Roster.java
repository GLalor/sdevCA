package sdevca;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 *
 * @author Graham
 */
@Entity
public class Roster {

    @Id
    @Column(name = "week_num")
    private int weekNum;
    @Column(name = "staff_needed")
    private int staffNeeded;

    @ManyToMany
    @JoinTable(name = "Roster_Staff",
            joinColumns = @JoinColumn(name = "week_num"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<Staff> staffList;



    //Default Constructor
    public Roster() {
    }

    //Getters and Setters
    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public int getStaffNeeded() {
        return staffNeeded;
    }

    public void setStaffNeeded(int staffNeeded) {
        this.staffNeeded = staffNeeded;
    }

   public void getStaff() {
        for (Staff s : staffList) {
            System.out.printf("Name: %s",
                    s.getName());
        }
    }
    
    

    public void removeStaff(int empNum)
    {
        Staff s = new Staff();
        for(int i=0;i<staffList.size();i++)
        {
            if (staffList.get(i).getEmployeeNum()==empNum)
            {
                s = (Staff)staffList.get(i);
                staffList.remove(i);
            }
        }
        staffNeeded++;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void addStaff(Staff staff) {
        this.staffList.add(staff);
        staffNeeded--;
    }

    public String toString() {

        return "Roster for week: " + weekNum + " staff needed: " + staffNeeded + "\n";

    }
}
