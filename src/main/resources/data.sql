INSERT INTO `Users` (email, password, username, image_name, register_date, is_active) VALUES('ulfgir@gmail.com', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Przemek', 'przemek.jpg', '2022-06-22', 1);
INSERT INTO `Users` (email, password, username, image_name, register_date, is_active) VALUES('kajuhnke@wp.pl', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Karol', 'karol.jpg', '2022-06-28', 1);
INSERT INTO `Users` (email, password, username, image_name, register_date, is_active) VALUES('burek@gmail.com', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Burek', 'burek.jpg', '2022-06-30', 1);
INSERT INTO User_Role (user_id, role) VALUES(1, 'USER');
INSERT INTO User_Role (user_id, role) VALUES(2, 'USER');
INSERT INTO User_Role (user_id, role) VALUES(3, 'USER');
INSERT INTO Post (user_id, create_date, content, image_address) VALUES(1, '2022-06-28', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'przemek.jpg');
INSERT INTO Post (user_id, create_date, content, image_address) VALUES(2, '2022-06-28', 'Iga Świątek pokonała Chorwatkę Janę Fett 6:0, 6:3 i pewnie awansowała do II rundy Wimbledonu, w której również będzie zdecydowaną faworytką, gdyż zmierzy się w niej z 138. w światowym rankingu Holenderką Lesley Pattinamą Kerkhove.', 'karol.jpg');
COMMIT;