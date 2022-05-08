create table USER
(
    ID                  BIGINT               not null
        primary key,
    CREATED_DATE        TIMESTAMP,
    LAST_MODIFIED_DATE  TIMESTAMP,
    EMAIL               VARCHAR(255)         not null
        constraint UK_OB8KQYQQGMEFL0ACO34AKDTPE
            unique,
    ENABLED             BOOLEAN default TRUE not null,
    NAME                VARCHAR(255)         not null,
    PASSWORD            CHAR(60),
    ROLE                INTEGER              not null,
    CREATED_BY_ID       BIGINT,
    LAST_MODIFIED_BY_ID BIGINT,
    constraint FK2W3DBYYQWJ8HB3LWK5NXQ444Q
        foreign key (ID) references USER (ID),
    constraint FK9O7R2QPTRH93DEVPOB11VEIDJ
        foreign key (ID) references USER (ID)
);

INSERT INTO PUBLIC.USER (ID, CREATED_DATE, LAST_MODIFIED_DATE, EMAIL, ENABLED, NAME, PASSWORD, ROLE, CREATED_BY_ID, LAST_MODIFIED_BY_ID) VALUES (1, null, null, 'warren.trent@hotel.com', true, 'Warren Trent', '$2a$10$9dqIWWz9ztrPdCuj2Yyjwu/IVTHj1BDrAuJ8IecXrHZPLBit0nMGS', 0, null, null);
INSERT INTO PUBLIC.USER (ID, CREATED_DATE, LAST_MODIFIED_DATE, EMAIL, ENABLED, NAME, PASSWORD, ROLE, CREATED_BY_ID, LAST_MODIFIED_BY_ID) VALUES (2, null, null, 'marsha@guest.com', true, 'Marsha Preyscott', '$2a$12$8McfmVfXudaHjfHAvWinQOF24DFYMbuqH2uthPSfmC7GSODsu/MUC', 1, null, null);
