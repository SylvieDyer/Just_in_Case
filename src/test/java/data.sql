insert into just_in_case.building values (1, 'Building1', 'Description1');
insert into just_in_case.building values (2, 'Building2', 'Description2');

insert into just_in_case.facility values (1, 'B1just_in_case.facility1', 'NOT_BUSY', NOW());
insert into just_in_case.facility values (2, 'B1just_in_case.facility2', 'CLOSED', NOW());
insert into just_in_case.facility values (3, 'B2just_in_case.facility1', 'NOT_BUSY', NOW());
insert into just_in_case.facility values (4, 'B3just_in_case.facility1', 'FAIRLY_BUSY', NOW());

insert into just_in_case.within values (1, 1);
insert into just_in_case.within values (1, 2);
insert into just_in_case.within values (2, 3);

insert into just_in_case.livealertpost values (1, 'DEFAULT', 'ADELBERT_HALL', NOW(), 1, 3);
insert into just_in_case.livealertpost values (2, 'EXCESSIVE_RAIN', 'DEFAULT', NOW(), 0, 0);
insert into just_in_case.livealertpost values (3, 'SUSPICIOUS_BEHAVIOR', 'BIOMEDICAL_RESEARCH_BUILDING', NOW(), 2, 3);
insert into just_in_case.livealertpost values (4, 'DEFAULT', 'DEFAULT', NOW(), 0, 3);

insert into just_in_case.app_users values ('abc123', 'Test1', 1, 0, 'abc123');
insert into just_in_case.app_users values ('def456', 'Test2', 0, 1, 'def456');

insert into just_in_case.posts values (1, 'abc123');
insert into just_in_case.posts values (2, 'abc123');
insert into just_in_case.posts values (3, 'abc123');
insert into just_in_case.posts values (4, 'def456');
