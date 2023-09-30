DROP TABLE `taskmanagementsystem`.`document`, `taskmanagementsystem`.`employees`, `taskmanagementsystem`.`manager`, `taskmanagementsystem`.`note`, `taskmanagementsystem`.`project`, `taskmanagementsystem`.`task`;

Create table employees(
                          employee_id int not null auto_increment,
                          first_name varchar(255) not null,
                          last_name varchar(255) not null,
                          hire_date date,
                          user_name varchar(255) not null,
                          employee_password varchar(255) not null,
                          email varchar(255) not null,
                          manager_id int,
                          phone_number varchar(255) not null,
                          PRIMARY KEY (employee_id)
);

Create table manager(
                        manager_id int not null auto_increment,
                        employee_id int,
                        division varchar(255),
                        PRIMARY KEY (manager_id),
                        foreign key (employee_id) references employees(employee_id)
);

create table project(
                        project_id int not null auto_increment,
                        start_date Date,
                        completion_date Date,
                        manager_id int,
                        title varchar(255),
                        description varchar(255),
                        PRIMARY KEY (project_id)
);

create table task(
                     task_id int not null auto_increment,
                     team_member int,
                     description_task varchar(255),
                     title_task varchar(255),
                     creation_date Date,
                     end_date Date,
                     due_date Date,
                     complete boolean,
                     response_serial_number varchar(255),
                     project int not null,
                     PRIMARY KEY (task_id),
                     foreign key(project) references project(project_id),
                     foreign key(team_member) references employees(employee_id)
);

create table note(
                     note_id int not null auto_increment,
                     creation_date Date,
                     task_id int not null,
                     note varchar(255),
                     PRIMARY KEY (note_id),
                     foreign key(task_id) references task(task_id)
);

create table document(
                         document_id int not null auto_increment,
                         creation_date Date,
                         task_id int not null,
                         title varchar(255),
                         description varchar(255),
                         PRIMARY KEY (document_id),
                         foreign key(task_id) references task(task_id)
);
