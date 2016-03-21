
package sdevca;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Graham
 */
@Entity
public class Shift {
    @Id
    @Column(name="shift_id")
    private int shiftID;
    @Column(name="shift_type")
    private String shiftType;
    
    @OneToMany
    private List<Staff> staff;
    
    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i=0;i<5;i++){
            s+="        |\n";
        }
        s+= "shiftType|" + shiftType + staff;
        for(int i=0;i<5;i++){
            s+="        |\n";
        }
        return s;
    }
    
    
    
}
