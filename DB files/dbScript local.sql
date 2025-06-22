Create table users(
	user_id bigint generated always as identity,
	first_name varchar,
	last_name varchar,
	email varchar,
	password varchar,
	is_active boolean,
	is_verified boolean,
	date_created date,
	created_by varchar
)
select * from users

drop table mail_configuration

Create table mail_configuration(
	configuration_id bigint generated always as identity,
	sender varchar,
	reciever varchar,
	username varchar,
	password varchar,
	mail_server varchar,
	is_active boolean
)

INSERT INTO mail_configuration (sender, reciever, username, password, mail_server, is_active)
VALUES ('sherazakhtarmalik@gmail.com', 'sheraz321089@gmail.com', 'sheraz', 'alsjflkjads', 'Gmail', true);

update mail_configuration set username = 'sherazakhtarmalik@gmail.com',password = 'gmph ildd dfpr dnyo'


update mail_configuration set sender = 'devbotsolutions.com'


