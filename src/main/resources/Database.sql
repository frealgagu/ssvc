DROP TABLE IF EXISTS configuration;
DROP TABLE IF EXISTS users;

CREATE TABLE users 
(
  u_id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  u_username VARCHAR(20) NOT NULL,
  u_password VARCHAR(32) NOT NULL,
  UNIQUE KEY id (u_id)
);

INSERT INTO users (u_id, u_username, u_password) VALUES (1, 'frealgagu', '123456');

CREATE TABLE configuration 
(
  c_key VARCHAR(50) NOT NULL,
  c_value VARCHAR(100) NOT NULL,
  c_last_modification TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (c_key)
);

INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.port', '/dev/ttyUSB0', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.baud.rate', '19200', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.databits', '8', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.parity', 'even', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.stopbits', '1', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.encoding', 'RTU', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.receive.timeout', '1000', '2014-01-01 00:00:00');

INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.pressure.register.number', '4000', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('plc.temperature.register.number', '3000', '2014-01-01 00:00:00');

INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.pressure.advice.threshold', '90', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.pressure.alarm.threshold', '100', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.temperature.advice.threshold', '130', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.temperature.alarm.threshold', '140', '2014-01-01 00:00:00');

INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.advice.time.before.sending', '20', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.alarm.time.before.sending', '10', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.advice.time.before.reply', '60', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.alarm.time.before.reply', '60', '2014-01-01 00:00:00');

INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.send.email.on.advice', 'frealgagu@gmail.com', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.send.email.on.alarm', 'frealgagu@gmail.com', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.send.sms.on.advice', '3012345678', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('notification.send.sms.on.alarm', '3012345678', '2014-01-01 00:00:00');


INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.host', 'smtp.gmail.com', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.starttls.enable', 'true', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.port', '587', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.user', 'ssvc.udistrital@gmail.com', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.auth', 'true', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.from', 'ssvc.udistrital@gmail.com', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.username', 'ssvc.udistrital@gmail.com', '2014-01-01 00:00:00');
INSERT INTO configuration (c_key, c_value, c_last_modification) VALUES ('mail.smtp.password', 'sistema.udistrital', '2014-01-01 00:00:00');

