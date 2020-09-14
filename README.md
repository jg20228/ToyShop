# MYSQL 계정 설정
```sql
create user 'toyshop'@'%' identified by '1234';
GRANT ALL PRIVILEGES ON *.* TO 'toyshop'@'%';
create database toyshop;
use toyshop;
```