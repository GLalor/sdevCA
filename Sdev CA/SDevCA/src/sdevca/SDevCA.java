package sdevca;

/**
 * Graham Lalor x00122026 Ruairí Keogh
 */
public class SDevCA {

    public static void main(String[] args) {
        boolean exit = false;

        RosterService rs = new RosterService();

        rs.createRoster(1, 5);

        System.out.println(rs.rosterList.get(0).toString());

    }

}
