# MYSQL 계정 설정

```sql
create user 'toyshop'@'%' identified by '1234';
GRANT ALL PRIVILEGES ON *.* TO 'toyshop'@'%';
create database toyshop;
use toyshop;
```

# Model 설정

```sql
DROP table orders_detail;
DROP table orders;
DROP table stock;
DROP table basket;
DROP table product;
DROP table user;

##유저
CREATE TABLE user(
	id int auto_increment primary key,
    username varchar(100) unique not null,
    email varchar(100) unique not null,
    password varchar(100) not null,
    name varchar(100) not null,
    address varchar(100) not null,
    phone varchar(100) not null,
    gender varchar(100) not null,
    profileImage varchar(100),
    role varchar(100) not null,
	provider varchar(100),
	providerId varchar(100),
    createDate timestamp
) engine=InnoDB default charset=utf8;

#상품
CREATE TABLE product(
	id int auto_increment primary key,
    name varchar(100) not null,
    imgUrl varchar(200) not null,
    disc varchar(200) not null,
	price int not null,
    createDate timestamp
) engine=InnoDB default charset=utf8;

#장바구니
CREATE TABLE basket(
	id int auto_increment primary key,
    userId int not null,
    productId int not null,
	count int not null,
    createDate timestamp,
    FOREIGN KEY(userId) REFERENCES user(id),
    FOREIGN KEY(productId) REFERENCES product(id)
) engine=InnoDB default charset=utf8;

#재고량
CREATE TABLE stock(
	id int auto_increment primary key,
    productId int not null,
    count int not null,
    FOREIGN KEY(productId) REFERENCES product(id)
) engine=InnoDB default charset=utf8;

#결제내역
CREATE TABLE orders(
	id int auto_increment primary key,
    userId int not null,
    impId varchar(100) not null,
    merchantId varchar(100) not null,
    applyNum varchar(100) not null,
    totalPay int not null,
    createDate timestamp,
	FOREIGN KEY(userId) REFERENCES user(id)
) engine=InnoDB default charset=utf8;

#결제상세내역
CREATE TABLE orders_detail(
	id int auto_increment primary key,
    ordersId int not null,
    productId int not null,
    count int not null,
    status varchar(100) not null,
    createDate timestamp,
	FOREIGN KEY(ordersId) REFERENCES orders(id),
	FOREIGN KEY(productId) REFERENCES product(id)
) engine=InnoDB default charset=utf8;
```

# admin 계정

```sql
update user set role = 'ROLE_ADMIN' where id = ?;
```
