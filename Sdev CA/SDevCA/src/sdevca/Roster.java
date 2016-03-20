
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
    @Column(name="week_num")
    private int weekNum;
    @Column(name="staff_needed")
    private int staffNeeded;
    
    @OneToMany(cascade=ALL,mappedBy="department",orphanRemoval=true)
    @OrderBy ("staffID")
    private List<Staff> staff;
    
    @OneToMany(mappedBy="roster")
    @MapKeyColumn(name="shift_type")
    private Map<String, Staff> staffByShift;
    
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
        for (Map.Entry<String, Staff> entry : staffByShift.entrySet()) {
            System.out.printf("Key : %s and Value: %s %n",
                    entry.getKey(), entry.getValue().getFName(), entry.getValue().getLName());
        }
    }

    public void addStaff(String shiftType, Staff staff) {
        staffByShift.put(shiftType, staff);
        this.staff.add(staff);
        staff.setRoster(this);
    }
    
    
    public void removeStaff(int empNum)
    {
        Staff s = new Staff();
        for(int i=0;i<staff.size();i++)
        {
            if (staff.get(i).getEmployeeNum()==empNum)
            {
                s = (Staff)staff.get(i);
                staff.remove(i);
            }
        }
        removeStaffByShift(s);
        
    }
    
    public void removeStaffByShift(Staff staff) {
        Iterator iter = staffByShift.entrySet().iterator();
        while (iter.hasNext()) {
            Staff currentStaff = ((Map.Entry<String,Staff>) iter.next()).getValue();
            if (currentStaff.getEmployeeNum() == staff.getEmployeeNum()) {
                iter.remove();
            }
        }
    }

    public List<Staff> getListOfStaff() {
        return staff;
    }

    public void setListOfStaff(List<Staff> staff) {
        this.staff = staff;
    }
    
    public String toString(){
        String s = "Roster for week: "+weekNum+" staff needed: "+staffNeeded;
        
        return s;
    }
}
