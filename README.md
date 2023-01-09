# exception
스프링 MVC 2편 - 백엔드 웹 개발 활용 기술_04(에외처리와 오류 페이지 & API 오류처리)

### 2022-12-28 추가
1. 서블릿 예외 처리 (필터, 인터셉터)
2. 스프링 부트를 사용한 오류페이지 설정
3. API 오류처리
수정중

### 2023-01-09 추가
#### 스프링부트가 제공하는 ExceptionResolver (우선순위 순서대로)
1. ExceptionHandlerExceptionResolver
    - @ExceptionHandler을 처리. API 예외 처리는 대부분 이 기능으로 해결
2. ResponseStatusExceptionResolver
    - 예외에 따라 HTTP 상태코드를 지정해줌
3. DefaultHandlerExceptionResolver
    - 스프링 내부 기본 예외를 처리
