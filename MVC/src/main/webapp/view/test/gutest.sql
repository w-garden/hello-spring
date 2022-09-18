--gutest 테이블 생성

create table gutest(
	no number(38) primary key,
	title varchar2(100) not null,
	cont varchar2(4000) not null,
	regDate date
	
	
);

select * from gutest order by no desc; 

--g_no_seq  시퀀스 생성
create sequence g_no_seq
start with 1
increment by 1
nocache;

--g_no_seq 시퀀스 다음 번호값 확인

select g_no_seq.nextval from dual;