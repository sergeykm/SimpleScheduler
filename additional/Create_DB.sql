CREATE DATABASE scheduler;
USE scheduler;

CREATE TABLE EMPLOYEES
	(
		employee_id	INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
		surname		VARCHAR(64) NOT NULL,
		initials	VARCHAR(16) NOT NULL
	);

CREATE TABLE TASKS
	(
		task_id		INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
		employee_id	INT(11) NOT NULL,
		is_finished	BOOLEAN not null,
		description	VARCHAR(128) NOT NULL,
		start_date	DATE NOT NULL,
		finish_date	DATE not null,
		completion_date	DATE
	);
