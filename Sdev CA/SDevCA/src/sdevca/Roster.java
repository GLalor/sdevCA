
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
    
    @OneToMany(mappedBy="roster")
    @MapKeyColumn(name="staff_id")
    private Map<String, Staff> roster;
    private List<Staff> staff;
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
        for (Map.Entry<String, Staff> entry : roster.entrySet()) {
            System.out.printf("Key : %s and Value: %s %n",
                    entry.getKey(), entry.getValue().getFName(),entry.getValue().getLName());
        }
    }   
    
    public void addStaff(String staffId, Staff staffIn) {
        roster.put(staffId, staffIn);
        this.staff.add(staffIn);
        staff.setShift(this);
    }
    
    
    public void removeEmployee(int empId)
    {
        Employee e = new Employee();
        for(int i=0;i<employees.size();i++)
        {
            if (employees.get(i).getId()==empId)
            {
                e = (Employee)employees.get(i);
                employees.remove(i);
            }
        }
        removeEmployee(e);
        
    }
    
    public void removeEmployee(Employee employee) {
        Iterator iter = employeesByCubicle.entrySet().iterator();
        while (iter.hasNext()) {
            Employee current = ((Map.Entry<String,Employee>) iter.next()).getValue();
            if (current.getId() == employee.getId()) {
                iter.remove();
            }
        }
    }

    public List<Employee> getListEmployees() {
        return employees;
    }

    public void setListEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
