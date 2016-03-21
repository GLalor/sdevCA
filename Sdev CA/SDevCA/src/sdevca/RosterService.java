package sdevca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RosterService {

    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SDevCAPU");
    EntityManager em = emf.createEntityManager();

    List<Roster> rosterList = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();
    List<Shift> shiftList = new ArrayList<>();

    // Create a Staff entity
    public Staff createStaff(int empNum, String name) {
        em.getTransaction().begin();
        Staff staff = new Staff();
        staff.setEmployeeNum(empNum);
        staff.setName(name);
        staffList.add(staff);
        em.persist(staff);
        em.getTransaction().commit();
        return staff;
    }

    // find all staff entitys and store them in  an List
    public List<Staff> findAllStaff() {
        Query query = em.createQuery("SELECT s FROM Staff s");
        List<Staff> allStaff = (List<Staff>) query.getResultList();
        return allStaff;
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

    public Shift createShift(int id, String shiftType) {
        em.getTransaction().begin();
        Shift shift = new Shift();
        shift.setShiftID(id);
        shift.setShiftType(shiftType);
        shiftList.add(shift);
        em.persist(shift);
        em.getTransaction().commit();
        return shift;
    }


    public void setStaffShift(int empNum, int newShiftType) {

        em.getTransaction().begin();
        Staff s = em.find(Staff.class, empNum);
        Shift shiftType = em.find(Shift.class, newShiftType);
        s.setShift(shiftType);

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
        em.remove(s);
        em.getTransaction().commit();
    }

    public void addStaffRoster(int empNum, int weekNum) {
        Staff s = em.find(Staff.class, empNum);
        Roster r = em.find(Roster.class, weekNum);
        if(r.getStaffNeeded() > 0){
        r.addStaff(s);
            System.out.println("Staff needed for this roster: "+ r.getStaffNeeded());
        }
        else{
            System.out.println("No more staff needed for this roster!");
        }
    }

    public void removeStaffFromRoster(int empNum, int weekNum) {
        Roster r = em.find(Roster.class, weekNum);
        r.removeStaff(empNum);
        if(r.getStaffNeeded() == 1){
            System.out.println("One staff member is needed to fill this roster!");
        }
        else{
           System.out.println("More staff member are needed to fill this roster!");
        }
    }
    
    public String printRoster(int weekNum) {
        String s = "";
        s += rosterList.get(weekNum - 1).toString();
        for (int x = 0; x < days.length; x++) {
            s += "\n|\t " + days[x]+" \t|";
            for (int i = 0; i < shiftList.size(); i++) {
                s += "\n" + shiftList.get(i).getShiftType() + " Staff :";
                for (int y = 0; y < rosterList.get(weekNum-1).getStaffList().size(); y++) {
                    if (staffList.get(y).getShift().getShiftType().equals(shiftList.get(i).getShiftType())) {
                        s += "\t\t" + rosterList.get(weekNum-1).getStaffList().get(y).getName()+"\n";
                    }
                }
            }
        }
        return s;
    }

    public void saveAndClear() throws IOException {
        File outFile = new File("files", "data.txt");

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter((outFile)))) {
            bWriter.write("********************************* NEW SET OF ROSTERS *********************************\n");
            for (int i = 0; i < rosterList.size(); i++) {
                bWriter.write(printRoster(i + 1));
                em.remove(rosterList.get(i));
            }

        }
    }

}
