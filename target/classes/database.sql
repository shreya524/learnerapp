use learnerapp;

create table student (
            stu_id int(11) NOT NULL AUTO_INCREMENT,
            stu_name varchar(30) NOT NULL,
            stu_age int(11),
            stu_email varchar(30) NOT NULL,
            PRIMARY KEY(stu_id)
		    	
);

create table teacher (
            t_id int(11) NOT NULL AUTO_INCREMENT,
            t_name varchar(30) NOT NULL,
            t_age int(11),
            t_email varchar(30) NOT NULL,
            t_salary int(11),
            c_id int(11),
            PRIMARY KEY(t_id)
);

create table class (
            c_id int(11) NOT NULL AUTO_INCREMENT,
            c_name varchar(30) NOT NULL,
            PRIMARY KEY(c_id)
);

create table subject (
             sub_id int(11) NOT NULL AUTO_INCREMENT,
             sub_name varchar(30) NOT NULL,
             c_id int(11) NOT NULL,
             t_id int(11) NOT NULL,
             PRIMARY KEY(sub_id)
);


alter table subject add KEY class_fk(c_id);
alter table subject add CONSTRAINT c_subject_fk FOREIGN KEY(c_id) REFERENCES class (c_id);
alter table subject add KEY teacher_fk(t_id);
alter table subject add CONSTRAINT t_subject_fk FOREIGN KEY(t_id) REFERENCES teacher (t_id);
alter table student add c_id int(11);
alter table student add KEY class_fk(c_id);
alter table student add CONSTRAINT c_student_fk FOREIGN KEY(c_id) REFERENCES class (c_id);