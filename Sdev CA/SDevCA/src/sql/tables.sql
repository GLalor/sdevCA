Drop Table Roster;
Drop Table Shift;
Drop Table Staff;

CREATE TABLE  Staff(
	staff_id NUMBER NOT NULL,
	fName    VARCHAR2(255) NOT NULL,
        LName    VARCHAR2(255) NOT NULL,
	PRIMARY KEY (staff_id)
);

CREATE TABLE Shift(
    shift_id NUMBER NOT NULL,
    shift_type VARCHAR(5),
    PRIMARY KEY(shift_id)
);

CREATE TABLE  Roster (
	week_num NUMBER,
	staff_needed Number,
        staff_id Number,
	PRIMARY KEY (week_num),
        Foreign Key (staff_id) referces Staff(staff_id)
);


