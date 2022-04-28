-- System Administrator account
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (0, 'admin@hotel.com', 'System Administrator', '$2a$12$04uiFMs6NZoleeuCD64eWOkrrlh2PmL0crO/LlYPe7xmpU2eBYrfy', 0);

-- Employees of St.Gregory Hotel

-- Warren Trent, proprietor
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (1, 'warren.trent@hotel.com', 'Warren Trent', '$2a$10$9dqIWWz9ztrPdCuj2Yyjwu/IVTHj1BDrAuJ8IecXrHZPLBit0nMGS', 1);

-- Peter McDermott, manager
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (2, 'peter.mcdermott@hotel.com', 'Peter McDermott',
        '$2a$10$Jf8.ABITV/47JIJXbCNgA.8WJ9otBpccdk7nxa7iG8KDJ6AJEMvB.', 1);

-- Aloysius Royce, administrative assistant to Warren Trent
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (3, 'aloysius.royce@hotel.com', 'Aloysius Royce', '$2a$10$GpbffHhqheSIBQEChP6nO.Bg57TP0hDfmbH1p8U1PVXG0X0ZEG.hK', 1);

-- Christine Francis, secretary to Warren Trent
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (4, 'christine.francis@hotel.com', 'Christine Francis','$2a$10$litNHpLmQCD4WfVFij3zSO1vvINRdCeR5nQ6d4bvSupMd0U3uEb4u', 1);

-- Populate user table with customers

-- Marsha Preyscott, one night guest in the hotel. Password: MarPrey!09
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (5, 'marsha@guest.com', 'Marsha Preyscott', '$2a$12$8McfmVfXudaHjfHAvWinQOF24DFYMbuqH2uthPSfmC7GSODsu/MUC', 2);

