create table  users(   --users 테이블
	username nvarchar2(50) PRIMARY KEY, --회원 아이디
    password nvarchar2(50) not NULL, --비밀번호
    enabled char(1) default '1' 
    );
    
    
select * from users;


--회원 아이디와 권한할당할 테이블 생성
create table authorities(
    username nvarchar2(50) NOT NULL, --아이디
    authority nvarchar2(50) NOT NULL, --아이디 권한 부여 컬럼
    constraint authorities_username_fk FOREIGN KEY(username) references users(username) --외래키 설정
    );
    
create unique index ix_auth_username on authorities(username,authority); --인덱스 설정

--users 테이블에 레코드 저장
insert into users (username, password) values('user00','pw00');
insert into users (username, password) values('member','pw00');
insert into users (username, password) values('admin00','pw00');


update users set username='member00' where username='member';
select * from users;

commit;

insert into authorities (username, authority) values('user00','ROLE_USER');
insert into authorities (username, authority) values('member00','ROLE_MANAGER');
insert into authorities (username, authority) values('admin00','ROLE_MANAGER');
insert into authorities (username, authority) values('admin00','ROLE_ADMIN');

select * from authorities order by authority asc;






