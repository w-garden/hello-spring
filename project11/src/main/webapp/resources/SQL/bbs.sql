create table bbs(
  bbs_no number(38) primary key --자료실 번호값
  , bbs_name varchar2(100) not null -- 글쓴이
  , bbs_title varchar2(200) not null -- 글제목
  , bbs_pwd varchar2(20) not null --비밀번호
  , bbs_cont varchar2(4000) not null -- 글내용
  , bbs_file varchar2(200) --첨부한 파일명
  , bbs_hit number(38) default 0 --조회수
  , bbs_ref number(38) --글 그룹번호 : 원본글과 답변글을 묶어주는 역할
  , bbs_step number(38) -- 첫번째 답변글이면 1, 두번째 답변글이면 2,원본글이면 0=>몇번째 답변글인가를 알려주고, 원본글과 답변글을 구분하는 값
  , bbs_level number(38) --답변글 정렬순서
  , bbs_date date --글 등록날짜
);

select * from bbs order by bbs_no desc;

--bbs_no_seq라는 시퀀스 생성
create sequence bbs_no_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임시 메모리를 사용하지 않겠다는 의미.

-- 시퀀스 다음번호값 확인
select bbs_no_seq.nextval from dual;





