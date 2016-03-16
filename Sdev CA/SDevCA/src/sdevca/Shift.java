
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
    private String shiftTime;
    
    public Shift(int shiftID,String shiftType,String shiftTime){
        this.shiftID=shiftID;
        this.shiftType=shiftType;
        this.shiftTime=shiftTime;
    }

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
    
    
    
}
