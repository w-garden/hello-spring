--boardT mvc 계층형 게시판
create table boardT( --boardT테이블 생성
	board_no number(38) primary key, --게시글 번호
	board_name varchar2(100) not null, -- 글쓴이
	board_title varchar2(200) not null, -- 글제목
	board_pwd varchar2(50) not null, --비번
	board_cont varchar2(4000) not null, --글 내용
	board_hit number(38) default 0, --조회수
	board_ref number(38), --원본글과 관리자 답변글을 묶어주는 글 그룹 번호값
	board_step number(38), --원본글이면 0, 첫번째 답변글이면 1, 두번째 답변글이면 2, 원본글과 답변글을 구분하는 번호값이면서 몇번째 답변글인가를 알려줌
	board_level number(38), --답변글 정렬 순서
	board_date date -- 등록날짜
	

);

select * from boardT order by board_no desc;

--boardT_no_seq 시퀀스 생성
create sequence boardT_no_seq
start with 1 
increment by 1
nocache;

select boardT_no_seq.nextval from dual;

