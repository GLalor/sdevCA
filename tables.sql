Drop Table Roster;
Drop Table Shift;
Drop Table Payslip;
Drop Table Staff_Holiday;
Drop Table Holiday;
Drop Table Users;

CREATE TABLE Users(
        user_id NUMBER NOT NULL PRIMARY KEY,
	username varchar2(20),
	password varchar2(20),
	flag varchar2(1),
	fname varchar2(255),
	lname varchar2(255),
	address1 varchar2(255),
	address2 varchar2(255),			
	email varchar2(255),
	pnumber varchar2(255),
        availability varchar2(255),
	contract varchar2(255),
        position varchar2(255)
);

CREATE TABLE  Holiday(
	holiday_id NUMBER NOT NULL,
	staff_id NUMBER NOT NULL,
	approved varchar2(255),
	startdate date,
        enddate date,
	information varchar2(255),
	PRIMARY KEY (holiday_id),
	FOREIGN KEY (staff_id) REFERENCES Users(user_id)
);

CREATE TABLE  Staff_Holiday(
	staff_id NUMBER NOT NULL,
	holiday_id NUMBER NOT NULL,
	PRIMARY KEY (staff_id,holiday_id),
	FOREIGN KEY (staff_id) REFERENCES Users(user_id),
	FOREIGN KEY (holiday_id) REFERENCES Holiday(holiday_id)
);

CREATE TABLE  Payslip(
	payslip_id NUMBER NOT NULL,
	staff_id NUMBER NOT NULL,
	week NUMBER,
	netpay decimal,
	grosspay decimal,
	tax decimal,
	PRIMARY KEY (payslip_id),
	FOREIGN KEY (staff_id) REFERENCES Users(user_id)
);

CREATE TABLE  Shift(
	shift_id NUMBER NOT NULL,
	staff_id NUMBER NOT NULL,
	shift_time varchar2(255),
	shift_type varchar2(255),
	PRIMARY KEY (shift_id),
	FOREIGN KEY (staff_id) REFERENCES Users(user_id)
);

CREATE TABLE  Roster (
	roster_id NUMBER NOT NULL,
	shift_id NUMBER NOT NULL,
	week_num NUMBER,
	staff_needed varchar2(255),
	PRIMARY KEY (roster_id),
	FOREIGN KEY (shift_id) REFERENCES Shift(shift_id)
);

INSERT INTO Users VALUES(301,'WayneCondron','24Mar90','M','Wayne','Condron',null,null,null,null,null,null,null);
INSERT INTO Users VALUES(302,'RuairiKeogh','29Jan96','S','Ruairi','Keogh',null,null,null,null,null,null,null);
