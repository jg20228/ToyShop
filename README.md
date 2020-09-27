# toyshop 프로젝트 약 2주

- Spring Web
- Spring Devtool
- Lombok
- MySQL
- MyBatis
- OAuth2 Client
- JSTL
- Tomcat-Embed-Jasper(JSP)

#

- 회원가입
- 로그인
- 상품 목록
- 장바구니 목록
- 장바구니 추가
- 장바구니 삭제
- 장바구니 수정
- 상품 등록(admin)
- 상품 삭제(admin)
- 결제
- 주문 목록
- 주문 상세 내용

# ------------------------------------

- Security의 로그인 부분을 커스터마이징
- Annotation을 생성해서 session 관리
- role(admin, user)에 따라서 달라지는 접근 권한
- OAuth2를 이용한 로그인(구글,페이스북,네이버,카카오톡)
- 아임포트를 이용한 결제 후 받는 값을 DB에 입력

# 미리보기

- 로그인 화면
  ![Screenshot_6](https://user-images.githubusercontent.com/62128942/94368222-f4f04b00-011d-11eb-8427-bdb95a7e8072.png)
- 상품 등록 화면
  ![Screenshot_7](https://user-images.githubusercontent.com/62128942/94368235-fd488600-011d-11eb-97e9-fe888a438e67.png)
- 상품 목록 화면
  ![Screenshot_8](https://user-images.githubusercontent.com/62128942/94368239-fe79b300-011d-11eb-9915-094c4d8dd9cd.png)
- 주문 내역 화면
  ![Screenshot_9](https://user-images.githubusercontent.com/62128942/94368241-ff124980-011d-11eb-8adb-1689d6b54ab8.png)

# Youtube 링크

- https://youtu.be/8UFGcxmCDzo

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
