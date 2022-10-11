create table admin(
    admin_no number(38),
    admin_id varchar2(100) primary key, --관리자 아이디
    admin_pwd varchar2(200) not null, --관리자 비번
    admin_name varchar2(50) not null, --관리자 이름
    admin_date date --등록날짜
);

commit;
delete from admin;
select * from admin;

select * from bbs;




create table test (
    id varchar2(20),
    pw varchar2(30)
);

select * from test;


insert into test values('aa' ,'bb');
insert into test values('bb' ,'dd');

commit;
