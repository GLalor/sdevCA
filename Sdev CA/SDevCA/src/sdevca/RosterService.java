
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
    // Create a Staff entity
    public Staff createStaff(int empNum, String fName, String lName){
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
    
    public List<Staff> findAllStaff() {
        Query query = em.createQuery("SELECT s FROM Staff s");
        return (List<Staff>) query.getResultList();
    }
    
    public Roster createRoster(int weekNum, int staffNeeded){
        em.getTransaction().begin();
        Roster roster = new Roster();
        roster.setWeekNum(weekNum);
        roster.setStaffNeeded(staffNeeded);
        rosterList.add(roster);
        em.persist(roster);
        em.getTransaction().commit();
        return roster;
    }
    
    public List<Roster> findAllRosters() {
        Query query = em.createQuery("SELECT r FROM Roster r");
        return (List<Roster>) query.getResultList();
    }
    
    public void updateEmployeeSalary(int id, double newSal) {

        em.getTransaction().begin();
        Employee e = em.find(Employee.class, id);
        e.setSalary(newSal);
        em.merge(e);
        em.getTransaction().commit();

    }
    public void updateDepName(int id, String newName)
    {
        em.getTransaction().begin();
        Department d = em.find(Department.class, id);
        d.setName(newName);
        em.getTransaction().commit();
        
    }

    public void removeStaff(int id) {
        em.getTransaction().begin();
        Staff s = em.find(Staff.class, id);
        s.getDepartment().removeEmployee(id);
        em.getTransaction().commit();
    }
    
        public void removeDepartment(int did) {
        em.getTransaction().begin();
        Department d = em.find(Department.class, did);
        Query query = em.createQuery("SELECT e FROM Employee e where e.department.id = "+did);
        List<Employee> aCollection = (List<Employee>) query.getResultList();
        for(Employee e:aCollection ){
            d.removeEmployee(e);
        }
        d.getListEmployees().clear();
        em.remove(d); // removes department from department table
        em.getTransaction().commit();
    }
}
