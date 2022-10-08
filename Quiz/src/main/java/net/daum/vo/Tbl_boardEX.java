package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tbl_boardEX {
	private int b_no;
	private String b_writer;
	private String b_title;
	private int b_hit;
	private String regdate;
	
	private int startrow;
	private int endrow;
}
