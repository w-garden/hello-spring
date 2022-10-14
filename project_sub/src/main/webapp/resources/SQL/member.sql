create table member ( --회원관리 테이블 생성 
    mem_id varchar2(50) primary key, --회원 아이디
    mem_pwd varchar2(200) not null, --비밀 번호
    mem_name varchar2(50) not null, --회원 이름
    mem_zip varchar2(10) not null, --우편 번호
    mem_zip2 varchar2(10) not null, --우편 번호
    mem_addr varchar2(100) not null, --주소
    mem_addr2 varchar2(100) not null, --나머지 주소
    mem_phone01 varchar2(10), --첫번째 자리 폰번호
    mem_phone02 varchar2(10), --두번째 자리 폰번호
    mem_phone03 varchar2(10), --세번째 자리 폰번호
    mail_id varchar2(100), --메일 아이디
    mail_domain varchar2(100), --메일 도메인
    mem_date date, --가입날짜
    mem_state number(30), --가입회원이면 1, 탈퇴회원이면 2
    mem_delcont varchar2(4000), --탈퇴사유
    mem_deldate date -- 탈퇴날짜
);

select * from member order by mem_id asc;

--아이디 중복검색을 위한 sample member insert
insert into member (mem_id, mem_pwd, mem_name, mem_zip, mem_zip2, mem_addr, mem_addr2, mem_phone01, mem_phone02, mem_phone03, mail_id, mail_domain, mem_date, mem_state)  
             values('aaaaa', '99999', '홍길동', '145', '950', '서울시 강남구 역삼1동', '제이스 타워 1101호', '010', '8888', '9999', 'aaaaa', 'gmail.com', sysdate, 1);

commit;
drop table member;
select * from member;


select * from zipcode;