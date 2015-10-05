select booknum,bookname,genre,borrowday,returnday,writer
from member
where bookname LIKE '%' | #{bookname} | '%';

도서검색 쿼리

select *
from blacklist;

블랙리스트 검색


등록일자순
select * from bulletinboard order by registerday;
제목순
select * from bulletinboard order by title;




Cookie[] cookies = request.getCookies();

if(cookie.getName().equals("쿠키이름아이디"));

return cookie.getValue();

상태정보 변경 빈칸일때 null exception없이 완성되게 하는방법 ! 아니면 빈칸이있으면 빈칸을 채우라고 써주기!