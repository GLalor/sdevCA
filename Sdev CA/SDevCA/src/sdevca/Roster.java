
package sdevca;

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
    
    @OneToMany(cascade=ALL,mappedBy="roster",orphanRemoval=true)
    @OrderBy ("weekNum")
    private Staff staff;
    
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
    
    
}
