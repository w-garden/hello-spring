package net.daum.vo;

public class ZipCodeVO2 { //오라클로부터 가져온 주소레코드를 중간에서 한번더 가공해서 저장한다
	private String zipcode; //우편번호
	private String addr; //시도 구군 동
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
