-- Employees of St.Gregory Hotel

-- Warren Trent, proprietor
-- INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, ROLE, ENABLED, PASSWORD)
-- VALUES (1, 'warren.trent@hotel.com', 'Warren Trent', 0, true, '$2a$10$9dqIWWz9ztrPdCuj2Yyjwu/IVTHj1BDrAuJ8IecXrHZPLBit0nMGS');

-- Peter McDermott, manager
INSERT INTO USER (ID, EMAIL, NAME, ROLE, ENABLED, PASSWORD) VALUES (2, 'peter.mcdermott@hotel.com', 'Peter McDermott', 0, true, '$2a$10$Jf8.ABITV/47JIJXbCNgA.8WJ9otBpccdk7nxa7iG8KDJ6AJEMvB.');

-- Marsha Preyscott, guest in the hotel
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, ROLE, ENABLED, PASSWORD) VALUES (3, 'marsha@guest.com', 'Marsha Preyscott', 1, true, '$2a$12$8McfmVfXudaHjfHAvWinQOF24DFYMbuqH2uthPSfmC7GSODsu/MUC');
