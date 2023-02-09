--memberT 테이블 생성(회원관리)
create table memberT(
 mem_id varchar2(50) primary key --회원아이디
 ,mem_pwd varchar2(50) not null --회원비번
 ,mem_name varchar2(20) not null --회원이름
 ,mem_zip varchar2(10) not null --우편번호
 ,mem_zip2 varchar2(10) not null --우편번호
 ,mem_addr varchar2(100) not null --주소
 ,mem_addr2 varchar2(100) --나머지 주소
 ,mem_phone01 varchar2(10) --폰번호
 ,mem_phone02 varchar2(10) 
 ,mem_phone03 varchar2(10)
 ,mail_id varchar2(30) --메일 아이디
 ,mail_domain varchar2(50) --메일 도메인
 ,mem_file varchar2(100) --프로필 첨부 사진파일명
 ,mem_date date --가입날짜
 ,mem_state number(38) --가입회원 1,탈퇴 회원 2
 ,mem_delcont varchar2(4000) --탈퇴사유
 ,mem_deldate date --탈퇴날짜
);

-- 아이디 중복검색 때문에 샘플 레코드 저장
insert into memberT (mem_id,mem_pwd,mem_name,mem_zip,mem_zip2,mem_addr,mem_addr2,
mem_phone01,mem_phone02,mem_phone03,mail_id,mail_domain,mem_date,mem_state)
values('aaaaa','77777','홍길동','123','789','서울시 강남구 역삼동 00빌딩','00호','010','8888',
'9999','aaaaa','daum.net',sysdate,1);

select * from memberT order by mem_id;

--우편/주소 테이블 (zipcode)
create table zipcode(
 no number(38) primary key
 ,zipcode varchar2(20) --우편번호
 ,sido varchar2(100) --시,도
 ,gugun varchar2(50) --구,군
 ,dong varchar2(50) --읖면동(길주소)
 ,bunji varchar2(100) --번지
);

insert into zipcode values(1,'123-456','서울시','강남구','역삼동','센터필드 빌딩 2001호');

select * from zipcode;









