USE scheduler;

INSERT INTO employees(surname, initials) VALUES('Иванов', 'И.И.');
INSERT INTO employees(surname, initials) VALUES('Smith', 'W.');
INSERT INTO employees(surname, initials) VALUES('Иванов', 'И.И.');
INSERT INTO employees(surname, initials) VALUES('Иванов', 'И.П.');
INSERT INTO employees(surname, initials) VALUES('Иванов', 'И.И.');
INSERT INTO employees(surname, initials) VALUES('Fiorentino', 'L.');
INSERT INTO employees(surname, initials) VALUES('Smith', 'W.');

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('1',FALSE,'Test 1',CAST('2009-01-14' AS datetime),CAST('2009-02-14' AS datetime),
CAST('2009-02-10' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('2',TRUE,'Test one',CAST('2012-01-21' AS datetime),CAST('2012-02-01' AS datetime),
CAST('2012-02-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('2',FALSE,'Test 2',CAST('2012-01-17' AS datetime),CAST('2012-02-18' AS datetime),
CAST('2012-02-18' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('2',TRUE,'Test 3',CAST('2012-01-19' AS datetime),CAST('2012-02-07' AS datetime),
CAST('2012-01-31' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('3',TRUE,'Test one',CAST('2009-01-04' AS datetime),CAST('2009-01-26' AS datetime),
CAST('2009-01-20' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('3',FALSE,'Test two',CAST('2009-01-05' AS datetime),CAST('2009-02-05' AS datetime),
CAST('2009-02-04' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',FALSE,'Test 1',CAST('2012-01-08' AS datetime),CAST('2012-02-17' AS datetime),
CAST('2012-02-17' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',TRUE,'Test 2',CAST('2012-01-15' AS datetime),CAST('2012-02-21' AS datetime),
CAST('2012-02-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',FALSE,'Test 3',CAST('2012-01-19' AS datetime),CAST('2012-03-18' AS datetime),
CAST('2012-03-10' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',TRUE,'Test 4',CAST('2012-02-16' AS datetime),CAST('2012-03-02' AS datetime),
CAST('2012-03-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',FALSE,'Test 5',CAST('2012-01-15' AS datetime),CAST('2012-02-18' AS datetime),
CAST('2012-02-14' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',TRUE,'Test 6',CAST('2012-01-23' AS datetime),CAST('2012-02-03' AS datetime),
CAST('2012-01-31' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',FALSE,'Test 7',CAST('2012-01-28' AS datetime),CAST('2012-03-28' AS datetime),
CAST('2012-03-28' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',TRUE,'Test 8',CAST('2012-02-01' AS datetime),CAST('2012-02-11' AS datetime),
CAST('2012-02-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',FALSE,'Test 9',CAST('2012-01-04' AS datetime),CAST('2012-01-18' AS datetime),
CAST('2012-01-17' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',TRUE,'Test 10',CAST('2012-01-21' AS datetime),CAST('2012-02-01' AS datetime),
CAST('2012-02-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',FALSE,'Test 11',CAST('2012-03-04' AS datetime),CAST('2012-03-18' AS datetime),
CAST('2012-03-10' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('4',TRUE,'Test 12',CAST('2012-01-17' AS datetime),CAST('2012-02-17' AS datetime),
CAST('2012-02-17' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('5',TRUE,'Test one',CAST('2009-01-20' AS datetime),CAST('2009-03-02' AS datetime),
CAST('2009-03-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('5',FALSE,'Test two',CAST('2009-01-20' AS datetime),CAST('2009-02-28' AS datetime),
CAST('2009-02-08' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('5',TRUE,'Test three',CAST('2009-01-20' AS datetime),CAST('2009-01-30' AS datetime),
CAST('2009-01-30' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('6',TRUE,'Test 1',CAST('2012-01-24' AS datetime),CAST('2012-04-07' AS datetime),
CAST('2012-04-01' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('7',TRUE,'Test one',CAST('2009-01-12' AS datetime),CAST('2009-01-22' AS datetime),
CAST('2009-01-18' AS datetime));

INSERT INTO tasks(employee_id, is_finished, description, start_date, finish_date, completion_date)
VALUES('7',FALSE,'Test two',CAST('2009-01-14' AS datetime),CAST('2009-05-17' AS datetime),
CAST('2009-05-10' AS datetime));
