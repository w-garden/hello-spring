create table tbl_boardEX(
	b_no number(10), --게시판 번호값
	b_writer varchar2(30), --작성자
	b_title varchar2(30), --제목
	b_hit number(38), --조회수
	regdate date --등록날짜
);