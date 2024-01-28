# 개발 환경
1. IDE : IntelliJ IDEA Community
2. Spring Boot 3.2.1
3. JDK 17
4. Thymeleaf

# 게시판 주요 기능
1. 글쓰기
2. 글목록
3. 글조회
4. 글수정
5. 글삭제
6. 페이징처리

# API Document
1. 글쓰기 : /board/save
2. 글목록 : /board/list
3. 글조회 : /board/{id}
4. 글수정 : /board/update/{id}
5. 글삭제 : /board/delete/{id}
6. 페이징처리 : /board/paging
   - /board/list/paging?page=2
   - /board/list/paging/2

# 개선할 점
1. 로컬 저장소 -> JPA, MySQL 등 DB 활용
2. 회원 로그인 및 회원가입 기능
3. ERD 및 API Document 작성