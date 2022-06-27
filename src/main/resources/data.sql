INSERT INTO `Users` (email, password, username, image_address, register_date, is_active) VALUES('ulfgir@gmail.com', '$2a$10$9YL8li3tMpF0.avaz3lAGegLCrT34xHpLuE47w53ZPPrAHfTcqJ.a', 'Przemek', 'przemek.jpg', '2022-06-22', 1);
INSERT INTO User_Role (user_id, role) VALUES(1, 'USER');
COMMIT;