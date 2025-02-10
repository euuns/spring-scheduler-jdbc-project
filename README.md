# 일정 관리 앱 간단하게 구현하기
[작성 블로그 링크](https://rvrlo.tistory.com/entry/%EC%9D%BC%EC%A0%95-%EA%B4%80%EB%A6%AC-%EC%95%B1-%EA%B0%9C%EB%B0%9C-%EC%9D%BC%EC%A7%80) <br>

<br><br>

## 목차
[1. API 명세서 작성](#api-명세서) <br>
[2. ERD 작성](#erd) <br>
[3. POST - 일정 생성](#1-일정-생성) <br>
[4. GET - 일정 조회](#2-일정-조회) <br>
[5. PUT - 일정 수정](#3-일정-수정) <br>
[6. DELETE - 일정 삭제](#4-일정-삭제) <br>

<br><br><br>

## 설계

### API 명세서
![API 명세서](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbS3D6E%2FbtsLYs4wuIA%2FKqmEikBE2kupNomR7bT9F1%2Fimg.png)

<br>

☑️ 주요 기능
 - 일정 등록 : POST - schedule/crete
 - 전체 조회 : GET - schedule/
 - 선택 조회 : GET - schedule/{id}
 - 일정 수정 : PUT - schedule/{id}
 - 일정 삭제 : DELETE - schedule/{id}

<br>

🔐 인증: Basic Authentication <p>
🚨 400 Bad Request: 잘못된 접근(비밀번호, 이메일 형식 등)<br>
　　404 Not Fount: 찾을 수 없는 접근(id)



<br>

### ERD
![ERD](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbPVOlk%2FbtsL6LIutkw%2FY4DdeFolbicgEmc0ohCwvk%2Fimg.png)

<br>

schedule은 users에 종속적 <br>
users 1 : N schedule 관계

<br><br>


## 기능 구현

### 1. 일정 생성

|내용|&nbsp;&nbsp;&nbsp; Schedule &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; Users &nbsp;&nbsp;&nbsp;|
|:---:|:---:|:---:|
|**요청**|userId<br>title<br>content|password|
|**응답**|id<br>title<br>content<br>date|name|
|**변경**|id<br>user_id<br>title<br>content<br>date|-|

<br>

- schedule.user_id와 users.id로 JOIN하여 users.name 응답
- Body로 요청받은 내용을 schedule에 INSERT

<br><br>

### 2. 일정 조회

|내용|&nbsp;&nbsp;&nbsp; Schedule &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; Users &nbsp;&nbsp;&nbsp;|
|:---:|:---:|:---:|
|**요청**|-|-|
|**응답**|id<br>title<br>content<br>date|name|

<br>

- schedule.user_id = users.id 가 일치하는 쿼리 조회 → JOIN 이용
- ORDER BY를 이용해 schedule.date를 기준으로 내림차순 정렬 DESC

<br>

👩🏻 작성자 이름으로 조회하는 경우: users.name 조건 <br>
📅 날짜를 선택해 조회하는 경우: schedule.date 조건


<br><br>

### 3. 일정 수정

|내용|&nbsp;&nbsp;&nbsp; Schedule &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; Users &nbsp;&nbsp;&nbsp;|
|:---:|:---:|:---:|
|**요청**|/{id}<br>title<br>content|password|
|**응답**|id<br>title<br>content<br>date|name|
|**변경**|title<br>content<br>date|-|

<br>

- schedule.id로 해당 글을 조회
- schedule.user_id를 Schedule entity의 userId에 저장
- userId를 이용해 users.id로 작성자 조회
- users.password와 request password를 비교
- 일치하는 경우, schedule.id 에 해당하는 데이터를 UPDATE

<br><br>

### 4. 일정 삭제

|내용|&nbsp;&nbsp;&nbsp; Schedule &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; Users &nbsp;&nbsp;&nbsp;|
|:---:|:---:|:---:|
|**요청**|/{id}|password|
|**응답**|-|-|

<br>

- 수정과 같은 방식으로 user.password 비교
- 일치하는 경우, schedule.id에 해당하는 데이터를 DELETE

<br><br>
