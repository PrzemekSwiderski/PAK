INSERT INTO `Users` (email, password, username, image_address, register_date, is_active) VALUES('ulfgir@gmail.com', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Przemek', 'przemek.jpg', '2022-06-22', 1);
INSERT INTO `Users` (email, password, username, image_address, register_date, is_active) VALUES('kajuhnke@wp.pl', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Karol', 'karol.jpg', '2022-06-28', 1);
INSERT INTO `Users` (email, password, username, image_address, register_date, is_active) VALUES('burek@gmail.com', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Burek', 'burek.jpg', '2022-06-30', 1);
INSERT INTO User_Role (user_id, role) VALUES(1, 'USER');
INSERT INTO User_Role (user_id, role) VALUES(2, 'USER');
INSERT INTO User_Role (user_id, role) VALUES(3, 'USER');
COMMIT;