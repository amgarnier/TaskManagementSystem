insert into employees(first_name, last_name, hire_date, user_name, employee_password,email, phone_number, manager_id)
values("Aaron", "Garnier", "22-01-05", "agarnier", "password", "aaron.garnier@email.com", "+6301245678",1),
      ("Brian", "Dawn", "08-02-01", "bdawn","password","brian.dawn@email.com","+1234567890",2),
      ("Angela", "First", "03-02-05", "afirst","password","angela.first@email.com","+7894563567",2),
      ("Sponge", "Bob", "01-01-01", "sbob","password","sponge.bob@email.com","+3474567890",1),
      ('Krusty', 'Krab', '98-05-01', 'kkrab', 'password', 'krusty.krab@email.com', '+0987865678',1),
      ('Sandy', 'Patty', '01-05-08', 'spatty', 'password', 'sandy.patty@email.com', '+8796577788',2);




select * from employees;

insert into manager(division,employee_id)
values("mechanical",1),
      ("electrical",2);


insert into project(start_date,description,manager_id, title)
values("18-01-05","heating system wallmart",1 ,"Wallmart"),
      ("05-05-05","potable water system cvs",1, "Water" ),
      ("19-01-05","flooring carpet cvs bolingbrook",3, "CVS" );

select * from project;

insert into task(team_member, description_task, creation_date, due_date, complete, project,title_task)
values(3,"order pipes", "22-09-15", "23-10-30", false, 3,"Pipes"),
      (2,"install items", "22-09-20", "23-09-30", false, 3,"install"),
      (1,"fix contract", "22-01-05", "23-05-30", false, 2,"contract"),
      (3,"redo things that were done", "19-09-02", "23-01-30", false, 1,"redo");

select * from task;

insert into note(creation_date, task_id, note)
values("22-09-24", 1, "This is my first thing done"),
      ("22-09-23", 1, "This is my second thing done"),
      ("22-09-23", 2, "This is my note thing done");

insert into document(creation_date, task_id, description,title)
values("23-09-24", 2, "plan of system","plan"),
      ("23-09-24", 2, "plan of item","item"),
      ("23-09-24", 1, "plan of things","things");

select * from project;





