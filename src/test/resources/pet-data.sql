insert into Pet (petID, name, description) values (1, 'Judo', 'A mischievous airdale');
insert into Pet (petID, name, description) values (2, 'Vithu', 'An awesome parrot who can talk!');
insert into Pet (petID, name, description) values (3, 'Rock', 'A pet rock! Does nothing at all!');

insert into Hobby (hobbyID, name, description) values (1, 'trail-runner', 'trail runner');
insert into Hobby (hobbyID, name, description) values (2, 'bark', 'play with kids');
insert into Hobby (hobbyID, name, description) values (3, 'sing', 'Sweet notes!');


insert into PetHobby (petID, hobbyID, name, description) values (1, 1, 'Judo', 'trail-runner');
insert into PetHobby (petID, hobbyID, name, description) values (1, 2, 'Judo', 'player');
insert into PetHobby (petID, hobbyID, name, description) values (2, 3, 'Vithu', 'sing');
