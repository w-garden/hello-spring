package net.daum.vo;

import lombok.Data;

/*댓글관련 자바 저장빈 클래스 : tbl_reply 테이블의 컬럼명과 빈클래스 변수명을 같게 한다.
 */
@Data //lombok 라이브러이의 @Data를 사용하면 setter(), getter()메서드를 자동제공

public class ReplyVO {
    private int rno; //댓글번호
    private int bno; //외래키로 설정된 게시판 번호
    private String replyer;  // 댓글 작성자
    private String replytext;  //댓글내용
    private String regdate;  //댓글 등록날짜
    private String updatedate;  //댓글 수정날짜
    
}
