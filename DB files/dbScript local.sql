drop  table users

Create table users(
	user_id bigint generated always as identity,
	first_name varchar(50),
	last_name varchar(50),
	email varchar(100),
	password varchar(200),
	is_active Boolean DEFAULT True,
	is_verified Boolean DEFAULT FALSE,
	verification_code varchar(6),
	date_created TIMESTAMP default CURRENT_TIMESTAMP,
	created_by varchar(100)
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

select * from users

alter table users 
add verification_code varchar

update mail_configuration set sender = 'devbotsolutions.com'

alter table mail_configuration add mail_template varchar

alter table users add verification_code varchar

 update mail_configuration set mail_template = '<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify Your Email</title>
    <style>
        body {
            font-family: ''Segoe UI'', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f7;
            margin: 0;
            padding: 0;
        }
        .email-container {
            max-width: 600px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .email-header {
            background-color: #4A90E2;
            color: white;
            text-align: center;
            padding: 24px;
        }
        .email-body {
            padding: 24px;
            color: #333;
        }
        .email-body h2 {
            margin-top: 0;
        }
        .verify-button {
            display: inline-block;
            margin-top: 24px;
            background-color: #4A90E2;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }
        .email-footer {
            font-size: 12px;
            text-align: center;
            padding: 16px;
            color: #888;
        }
        @media (max-width: 600px) {
            .email-container {
                margin: 20px;
            }
        }
    </style>
</head>
<body>

<div class="email-container">
    <div class="email-header">
        <h1>Verify Your Email</h1>
    </div>
    <div class="email-body">
        <h2>Hello 👋</h2>
        <p>Thanks for signing up! Please verify your email address to activate your account.</p>
        <p>Here is your Verification Code:</p>
        <h3>{verificationCode}</h3>
        <p>If you didn’t create this account, you can safely ignore this email.</p>
    </div>
    <div class="email-footer">
        &copy; 2025 DevbotSolutions. All rights reserved.
    </div>
</div>

</body>
</html>
'
delete from users where email = 'sheraz321089@gmail.com'











select * from users



update users set password = '$2a$10$qUi9VZ.AgvyHl36Ty/XYvOu00IRBKdtnam3/vVJtniqLhwNQvafR6'


CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
    category_id BIGINT,
    created_by BIGINT NOT NULL,
    is_group BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    CONSTRAINT fk_expense_user FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);





alter table mail_configuration
add mail_template varchar
update mail_configuration set mail_template = '
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify Your Email</title>
    <style>
        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f7;
            margin: 0;
            padding: 0;
        }
        .email-container {
            max-width: 600px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .email-header {
            background-color: #4A90E2;
            color: white;
            text-align: center;
            padding: 24px;
        }
        .email-body {
            padding: 24px;
            color: #333;
        }
        .email-body h2 {
            margin-top: 0;
        }
        .verify-button {
            display: inline-block;
            margin-top: 24px;
            background-color: #4A90E2;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }
        .email-footer {
            font-size: 12px;
            text-align: center;
            padding: 16px;
            color: #888;
        }
        @media (max-width: 600px) {
            .email-container {
                margin: 20px;
            }
        }
    </style>
</head>
<body>

<div class="email-container">
    <div class="email-header">
        <h1>Verify Your Email</h1>
    </div>
    <div class="email-body">
        <h2>Hello 👋</h2>
        <p>Thanks for signing up! Please verify your email address to activate your account.</p>
        <p>Here is your verification code:</p>
        <h3>{verificationCode}</h3>
        <p>If you didn’t create this account, you can safely ignore this email.</p>
    </div>
    <div class="email-footer">
        &copy; 2025 DevbotSolutions.com. All rights reserved.
    </div>
</div>

</body>
</html>
'


select * from users


update users set password = '$2a$10$nlK8P1aIgxvdFcDCR1VVS.dVDlrqi6HKq1AUSNtvOa1M28zfWeDTK'







Create table expense_main(
	expense_id bigint generated always as identity,
	amount money,
	details varchar,
	type varchar,
	payment_date varchar,
	mail_server varchar,
	is_active boolean
)


























