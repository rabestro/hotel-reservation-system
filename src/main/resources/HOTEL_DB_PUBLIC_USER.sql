create table USER
(
    ID       BIGINT  not null
        primary key,
    EMAIL    VARCHAR(255),
    NAME     VARCHAR(255),
    PASSWORD VARCHAR(255),
    ROLE     INTEGER not null
);

-- System Administrator account
INSERT INTO PUBLIC.USER (ID, EMAIL, NAME, PASSWORD, ROLE)
VALUES (0, 'admin@hotel.com', 'System Administrator', '$2a$12$04uiFMs6NZoleeuCD64eWOkrrlh2PmL0crO/LlYPe7xmpU2eBYrfy', 0);

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

