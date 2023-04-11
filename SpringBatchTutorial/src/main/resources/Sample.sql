/*주문 테이블 작성*/
CREATE TABLE study.orders (
                              ID INT NOT NULL AUTO_INCREMENT,
                              ORDER_ITEM VARCHAR(45) NULL,
                              PRICE INT NULL,
                              ORDER_DATE DATE NULL,
                              PRIMARY KEY (id)

);
/*정산 테이블 작성 */
CREATE TABLE study.accounts (
                                ID INT NOT NULL AUTO_INCREMENT,
                                ORDER_ITEM VARCHAR(45) NULL,
                                PRICE INT NULL,
                                ORDER_DATE DATE NULL,
                                ACCOUNT_DATE DATE NULL,
                                primary key(id)
);


INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('카카오 선물', 15000, '2022-03-01');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('배달주문', 18000, '2022-03-01');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('교보문고', 14000, '2022-03-02');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('아이스크림', 3000, '2022-03-03');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('치킨', 21000, '2022-03-04');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('커피', 4000, '2022-03-04');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('교보문고', 13800, '2022-03-05');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('카카오 선물', 5500, '2022-03-06');
INSERT INTO orders(ORDER_ITEM, PRICE, ORDER_DATE) VALUES ('카카오 선물', 7000, '2022-03-02');

SELECT * FROM orders;

SELECT * FROM accounts;

DROP TABLE orders;