--users 테이블 생성
create table users(
  username varchar2(50) primary key -- 회원 아이디
  ,password varchar2(50) not null -- 비밀번호
  ,enabled char(1) default '1' 
  )
  
  select * from users;
  
  --회원아이디와 권한 할당할 테이블 생성
  create table authorities(
    username varchar2(50) not null --아이디
    , authority varchar2(50) not null -- 아이디 권한 부여 컬럼
    , constraint authorities_username_fk foreign key(username) references users(username) --외래키 설정
  );
  
  create unique index ix_auth_username on authorities(username,authority); --인덱스 설정
  
  --users테이블에 레코드 저장
  insert into users (username,password) values('user00','pw00');
  insert into users (username,password) values('member00','pw00');
  insert into users (username,password) values('admin00','pw00');
  
  commit;
  
  select * from users;
  
  insert into authorities (username,authority) values('user00','ROLE_USER');
  insert into authorities (username,authority) values('member00','ROLE_MANAGER');
  insert into authorities (username,authority) values('admin00','ROLE_MANAGER');
  insert into authorities (username,authority) values('admin00','ROLE_ADMIN');
  
  select * from authorities order by authority; 
  
  commit;
  
  drop table tbl_member;
  
  create table tbl_member(--회원 테이블
    userid varchar2(50) primary key --회원 아이디
    ,userpw varchar2(100) not null --비번
    ,username varchar2(50) not null --회원이름
    ,regdate date default sysdate --가입날짜
    ,updatedate date default sysdate --수정날짜
    ,enabled char(1) default '1'
  );
  
  select * from tbl_member order by userid asc;
  commit;
  
  
  create table tbl_member_auth (--권한 부여 테이블
    userid varchar2(50) not null -- 아이디
    ,auth varchar2(50) not null -- 권한 부여
    ,constraint tbl_member_auth_userid_fk foreign key(userid) references tbl_member(userid) --외래키 설정. 이 컬럼 레코드 아이디는 tbl_member
    --테이블의 회원아이디값만 저장된다.주종 관계에서 종속테이블에 해당한다.
  );
  
  select * from tbl_member_auth order by userid asc; --asc문은 오름차순 정렬이고 생략가능함.
  
  
  
  
  
  
  
  
  
  
  
  
  