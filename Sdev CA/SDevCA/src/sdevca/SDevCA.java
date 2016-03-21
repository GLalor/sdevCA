package sdevca;

import java.io.IOException;
import java.util.Scanner;

/**
 * Graham Lalor x00122026 Ruairí Keogh
 */
public class SDevCA {

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        int weekCount = 0;
        RosterService rs = new RosterService();

        rs.createShift(1, "Day");
        rs.createShift(2, "Night");
        rs.createStaff(1, "Graham Lalor");
        rs.createStaff(2, "Ruairí Keogh");
        rs.createStaff(3, "Conor Griffin");
        rs.createStaff(4, "Lee Healy");
        rs.createStaff(5, "Imor Hartnett");
        rs.createStaff(6, "Eddy Ekofo");
        rs.createStaff(7, "Ray Ji");

        rs.setStaffShift(1, 1);
        rs.setStaffShift(2, 2);
        rs.setStaffShift(3, 1);
        rs.setStaffShift(4, 2);
        rs.setStaffShift(5, 1);
        rs.setStaffShift(6, 2);
        rs.setStaffShift(7, 1);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please press 1 to view all the staff members");
            System.out.println("Please press 2 to update a staff members shift type");
            System.out.println("Please press 3 to delete a staff member");
            System.out.println("Please press 4 to add a new staff member");
            System.out.println("Please press 5 to create a roster");
            System.out.println("Please press 6 to add a staff member to a roster");
            System.out.println("Please press 7 to remove a staff member from a roster");
            System.out.println("Please press 8 to view a roster");
            System.out.println("Press 9 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(rs.findAllStaff());
                    break;
                case 2:
                    System.out.println("please enter the id of the employee whose shift you wish to update");
                    int id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the new shift for employee " + id);
                    String newShiftType = in.next();
                    if (newShiftType.equalsIgnoreCase("Day")) {
                        rs.setStaffShift(id, 1);
                    } else if (newShiftType.equalsIgnoreCase("Night")) {
                        rs.setStaffShift(id, 2);
                    } else {
                        System.err.println("Invlaid Shift type! shift types are Day and Night!");
                    }
                    break;
                case 3:
                    System.out.println("please enter the id of the staff member you wish to delete");
                    int idRemove = in.nextInt();
                    rs.removeStaff(idRemove);
                    break;
                case 4:
                    System.out.println("please enter the ID of the staff member you wish to add");
                    int idNew = in.nextInt();
                    in.nextLine();
                    System.out.println("please enter the name of the staff member you wish to add");
                    String name = in.nextLine();
                    rs.createStaff(idNew, name);
                    System.out.println("Please enter the shift type you for this staff member");
                    String shiftT = in.next();
                    boolean shiftSet = false;
                    while (shiftSet == false) {
                        if (shiftT.equalsIgnoreCase("Day")) {
                            rs.setStaffShift(idNew, 1);
                            shiftSet = true;
                        } else if (shiftT.equalsIgnoreCase("Night")) {
                            rs.setStaffShift(idNew, 2);
                            shiftSet = true;
                        } else {
                            System.err.println("Invlaid Shift type! shift types are Day and Night!");
                        }
                    }
                    break;

                case 5:
                    if (weekCount == 52) {
                        System.out.println("You are about to start a new year! You will need to save all your rosters and start again!");
                        System.out.println("Dou you wish to save all rosters to a file? (y/n)");
                        String saveChoice = in.next();
                        if (saveChoice.equalsIgnoreCase("y")) {
                            rs.saveAndClear();
                            weekCount = 0;
                        } else if (saveChoice.equalsIgnoreCase("n")) {
                            break;
                        } else {
                            System.out.println("Invalid choice!");
                            break;
                        }
                    } else if (weekCount == 0) {
                        System.out.println("please enter week number for the roster you wish to create");

                    } else {
                        System.out.println("please enter week number for the roster you wish to create you are currently on week: " + weekCount);
                    }
                    int weekNum = in.nextInt();
                    System.out.println("Please enter the of staff needed each day");
                    int staffNeeded = in.nextInt();
                    rs.createRoster(weekNum, staffNeeded);
                    weekCount++;
                    break;
                case 6:

                    System.out.println("Please enter the employee number of the staff member you wish to add to the current roster week number: " + weekCount);
                    int staffMember = in.nextInt();
                    rs.addStaffRoster(staffMember, weekCount);
                    break;
                case 7:
                    System.out.println("Please enter the employee number of the staff member you wish to remove from the current roster week number: " + weekCount);
                    int staff = in.nextInt();
                    rs.removeStaffFromRoster(staff, weekCount);
                    break;
                case 8:
                    boolean selected = false;
                    while (selected == false) {

                        System.out.println("Please enter the week number you wish to view (week num between 0 and " + weekCount + " )");
                        int num = in.nextInt();
                        if (num > 0 && num <= weekCount) {
                            System.out.println(rs.printRoster(num));
                            selected = true;
                        } else {
                            System.out.println("Invalid choice! please try again or press n to exit!");
                            String done = in.next();
                            if (done.equalsIgnoreCase("n")) {
                                selected = true;
                            } else {
                            }
                        }
                    }
                    break;

                case 9:
                    System.exit(0);
                    break;
                case 10:
                    rs.saveAndClear();
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}
