INSERT INTO `Users` (id, email, password, username, image_address, register_date, is_active) VALUES(1, 'ulfgir@gmail.com', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Przemek', 'www.google.pl', '2022-06-22', 1)
INSERT INTO User_Role (id, user_id, role) VALUES(1, 1, 'USER')
COMMIT