
package sdevca;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> 10b4361700616f19b5a7a4afa9095a29198b5054
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
<<<<<<< HEAD
    
    @OneToMany(mappedBy="shift")
    private List<Staff> staffList;
=======
    private String shiftTime;
    
    public Shift(int shiftID,String shiftType,String shiftTime){
        this.shiftID=shiftID;
        this.shiftType=shiftType;
        this.shiftTime=shiftTime;
    }
>>>>>>> 10b4361700616f19b5a7a4afa9095a29198b5054

    
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
