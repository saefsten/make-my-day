INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (1, 'SPORT')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (2, 'CULTURE')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (3, 'CUISINE')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (4, 'MUSIC')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (5, 'HISTORY')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (6, 'NIGHTLIFE')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (7, 'SHOPPING')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (8, 'RELAXATION')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (9, 'ADVENTURE')
INSERT INTO CATEGORY (ID, CATEGORYNAME) VALUES (10, 'FAMILY')

INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (1L, 'Fotbollsmatch', 3,199.99, 'Djurgården - AIK', 'www.friendsarena.se', 'Friends arena')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (2L, 'Chess', 1, 299.99, 'Se Tommy Körberg', 'www.kungligaoperan.se','Kungliga Operan')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (3L, 'Gröna Lund', 2, 150.00, 'Ha riktigt kul och må illa', 'www.gronalund.se', 'Djurgården')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (4L, 'Ulla Winblad', 2, 800.90, 'Unna dig en sagolik middag', 'www.ullawinblad.se', 'Djurgården')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (5L, 'Operakällaren', 1, 1999.90, 'Upplev en dekadent middag', 'www.operakallaren.se', 'Norrmalm')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (6L, 'Cykla runt Stockholm', 1, 100.00, 'Upplev huvudstaden på cykel', 'www.cykeluthyrning.se', 'Vasastan')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (7L, 'Beirut Bistro', 1, 500.00, 'Smaka på det libanesiska köket', 'www.beirutbistro.se', 'Kungsholmen')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (8L, 'Boulebar Rålis', 1, 200.00, 'Spela boule med hela familjen', 'www.boulebar.se', 'Kungsholmen')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (9L, 'Historiska museet', 1, 100.00, 'Se föremål från stenåldern och framåt', 'www.historiskamuseet.se', 'Östermalm')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (10L, 'Sturecompagniet', 1, 300.90, 'Dansa hela natten', 'www.sturecompagniet.se', 'Östermalm')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (11L, 'Nordiska Kompaniet', 1, 1000.90, 'Shoppa loss i huvudstaden', 'www.nk.se', 'Vasastan')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (12L, 'Konsert på Globen', 1, 700.00, 'Se Håkan Hellström i Globen', 'www.globen.se', 'Globen')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (13L, 'Selma Spa', 4, 500.00, 'Unna dig en dag på spa', 'www.selmaspa.se', 'Norrmalm')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (14L, 'Skansen', 1, 200.00, 'Gulliga djur, god mat och historia', 'www.skansen.se', 'Djurgården')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (15L, 'Tak', 2, 500.00, 'God drinkar och fin utsikt på Tak', 'www.tak.se', 'Norrmalm')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (16L, 'Yasuragi', 5, 700.00, 'Njut av härliga bad och massage', 'www.yasuragi.se', 'Nacka')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (17L, 'Eriksdalsbadet', 1, 100.00, 'Simma och lek med hela familjen', 'www.eriksdalsbadet.se', 'Södermalm')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (18L, 'Åhléns', 1, 500.00, 'Shopping för alla', 'www.ahlens.se', 'City')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (19L, 'Padel Zenter Årsta', 1, 500.00, 'Rolig racketsport', 'www.padelzenter.se', 'Årsta')
INSERT INTO ACTIVITY (ID, NAME, HOURS, PRICE, DESCRIPTION, URL, ADDRESS) VALUES (20L, 'Kungliga operan', 1, 1000.00, 'Njut till vackra toner', 'www.kungligaoperan.se', 'City')


INSERT INTO ACTIVITY_CATEGORIES VALUES (1L, 1)
INSERT INTO ACTIVITY_CATEGORIES VALUES (2L, 2)
INSERT INTO ACTIVITY_CATEGORIES VALUES (2L, 4)
INSERT INTO ACTIVITY_CATEGORIES VALUES (3L, 9)
INSERT INTO ACTIVITY_CATEGORIES VALUES (3L, 10)
INSERT INTO ACTIVITY_CATEGORIES VALUES (4L, 3)
INSERT INTO ACTIVITY_CATEGORIES VALUES (5L, 3)
INSERT INTO ACTIVITY_CATEGORIES VALUES (6L, 1)
INSERT INTO ACTIVITY_CATEGORIES VALUES (6L, 8)
INSERT INTO ACTIVITY_CATEGORIES VALUES (7L, 3)
INSERT INTO ACTIVITY_CATEGORIES VALUES (8L, 10)
INSERT INTO ACTIVITY_CATEGORIES VALUES (8L, 1)
INSERT INTO ACTIVITY_CATEGORIES VALUES (9L, 5)
INSERT INTO ACTIVITY_CATEGORIES VALUES (9L, 2)
INSERT INTO ACTIVITY_CATEGORIES VALUES (10L, 6)
INSERT INTO ACTIVITY_CATEGORIES VALUES (11L, 7)
INSERT INTO ACTIVITY_CATEGORIES VALUES (12L, 4)
INSERT INTO ACTIVITY_CATEGORIES VALUES (13L, 8)
INSERT INTO ACTIVITY_CATEGORIES VALUES (14L, 10)
INSERT INTO ACTIVITY_CATEGORIES VALUES (14L, 5)
INSERT INTO ACTIVITY_CATEGORIES VALUES (15L, 6)
INSERT INTO ACTIVITY_CATEGORIES VALUES (16L, 8)
INSERT INTO ACTIVITY_CATEGORIES VALUES (17L, 1)
INSERT INTO ACTIVITY_CATEGORIES VALUES (18L, 7)
INSERT INTO ACTIVITY_CATEGORIES VALUES (19L, 1)
INSERT INTO ACTIVITY_CATEGORIES VALUES (20L, 2)
INSERT INTO ACTIVITY_CATEGORIES VALUES (20L, 4)

INSERT INTO PACKAGE (ID, PACKAGE_NAME, DESCRIPTION, START_TIMES, LONG_DESCRIPTION) VALUES (1, 'Morning Package', 'Description for morning package... Cykla runt Stockholm, Gröna Lund', '08:00;13:00;', 'I´m baby succulents fashion axe messenger bag, tilde biodiesel tumblr taiyaki hammock. Pour-over franzen pork belly forage sartorial asymmetrical raclette scenester XOXO gastropub mlkshk air plant copper mug thundercats. Shoreditch deep v cliche lo-fi.')
INSERT INTO PACKAGE (ID, PACKAGE_NAME, DESCRIPTION, START_TIMES, LONG_DESCRIPTION) VALUES (2, 'Afternoon Package', 'Description for afternoon package... Fotbollsmatch, Nordiska Kompaniet', '16:00;20:00;', 'Prow scuttle parrel provost Sail ho shrouds spirits boom mizzenmast yardarm. Pinnace holystone mizzenmast quarter crow´s nest nipperkin grog yardarm hempen halter furl. Swab barque interloper chantey doubloon starboard grog black jack gangway rutters.')
INSERT INTO PACKAGE (ID, PACKAGE_NAME, DESCRIPTION, START_TIMES, LONG_DESCRIPTION) VALUES (3, 'Evening Package', 'Description for evening package... Bar, restaurant, club', '18:00;20:00;23:00;', 'I love cheese, especially dolcelatte swiss. Cheeseburger cream cheese smelly cheese cheesy feet monterey jack port-salut monterey jack emmental. Cheesy grin stilton cheesy feet cheesy grin hard cheese cow brie cheese and wine.')

INSERT INTO PACKAGE_ACTIVITIES VALUES (2, 1L)
INSERT INTO PACKAGE_ACTIVITIES VALUES (1, 3L)
INSERT INTO PACKAGE_ACTIVITIES VALUES (1, 6L)
INSERT INTO PACKAGE_ACTIVITIES VALUES (3, 7L)
INSERT INTO PACKAGE_ACTIVITIES VALUES (3, 8L)
INSERT INTO PACKAGE_ACTIVITIES VALUES (3, 10L)
INSERT INTO PACKAGE_ACTIVITIES VALUES (2, 11L)

INSERT INTO USERS (FIRST_NAME, LAST_NAME, PASSWORD, USERNAME) VALUES ('Student', 'Duktig', '$2a$11$lVnw9DS4d6fcB17R/OwIwujo8xgk6iuHpmZMeRcKe7.rT1Xp3gaAq', 'aw@aw.com')
INSERT INTO USERS (FIRST_NAME, LAST_NAME, PASSWORD, USERNAME) VALUES ('Admin', 'Adminsson', '$2a$11$lVnw9DS4d6fcB17R/OwIwujo8xgk6iuHpmZMeRcKe7.rT1Xp3gaAq', 'admin@mmd.com')

INSERT INTO ROLE (NAME) VALUES ('USER')
INSERT INTO ROLE (NAME) VALUES ('ADMIN')

INSERT INTO USERS_ROLES VALUES (1L, 1L)
INSERT INTO USERS_ROLES VALUES (2L, 1L)
INSERT INTO USERS_ROLES VALUES (2L, 2L)
