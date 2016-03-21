package sdevca;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RosterService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SDevCAPU");
    EntityManager em = emf.createEntityManager();

    List<Roster> rosterList = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();
    List<Shift> shiftList = new ArrayList<>();
    
    

    // Create a Staff entity
    public Staff createStaff(int empNum, String fName, String lName) {
        em.getTransaction().begin();
        Staff staff = new Staff();
        staff.setEmployeeNum(empNum);
        staff.setFName(fName);
        staff.setLName(lName);
        staffList.add(staff);
        em.persist(staff);
        em.getTransaction().commit();
        return staff;
    }
    // find all staff entitys and store them in  an List
    public List<Staff> findAllStaff() {
        Query query = em.createQuery("SELECT s FROM Staff s");
        return (List<Staff>) query.getResultList();
    }
    // create a roster entity
    public Roster createRoster(int weekNum, int staffNeeded) {
        em.getTransaction().begin();
        Roster roster = new Roster();
        roster.setWeekNum(weekNum);
        roster.setStaffNeeded(staffNeeded);
        rosterList.add(roster);
        em.persist(roster);
        em.getTransaction().commit();
        return roster;
    }
    // find all roster entitys and store them in  an List
    public List<Roster> findAllRosters() {
        Query query = em.createQuery("SELECT r FROM Roster r");
        return (List<Roster>) query.getResultList();
    }

    public void updateStaffShift(int empNum, String newShiftType) {

        em.getTransaction().begin();
        Staff s = em.find(Staff.class, empNum);
        s.setShiftType(newShiftType);
        em.merge(s);
        em.getTransaction().commit();

    }

    public void updateStaffNeeded(int id, int newStaffNeeded) {
        em.getTransaction().begin();
        Roster r = em.find(Roster.class, id);
        r.setStaffNeeded(newStaffNeeded);
        em.getTransaction().commit();

    }

    public void removeStaff(int id) {
        em.getTransaction().begin();
        Staff s = em.find(Staff.class, id);
        s.getRoster().removeStaff(id);
        em.getTransaction().commit();
    }

    
}
