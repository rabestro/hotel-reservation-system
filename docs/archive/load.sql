-- Employees of St.Gregory Hotel

-- Warren Trent, proprietor
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, ROLE, ENABLED, PASSWORD)
VALUES (1, 'warren.trent@hotel.com', 'Warren Trent', 0, true, '$2a$10$9dqIWWz9ztrPdCuj2Yyjwu/IVTHj1BDrAuJ8IecXrHZPLBit0nMGS');

-- Guests of the hotel (customers)

-- Marsha Preyscott, one night guest in the hotel. Password: MarPrey!09
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, ROLE, ENABLED, PASSWORD)
VALUES (5, 'marsha@guest.com', 'Marsha Preyscott', 1, true, '$2a$12$8McfmVfXudaHjfHAvWinQOF24DFYMbuqH2uthPSfmC7GSODsu/MUC');

