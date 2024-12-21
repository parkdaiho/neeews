# neeews 📰

👉🏻 여러 언론사가 제공하는 뉴스에 관련된 여러 기능을 제공하는 웹 애플리케이션 프로젝트

- [프로젝트 소개](#프로젝트-소개-)
- [기술 스택](#기술-스택-)
- [Browser Support](#browser-support-)
- [Information Architecture](#information-architecture-)
- [구현 기능](#핵심-구현-기능-)
- [화면 구성](#화면-구성-)

## 프로젝트 소개 👐🏻

**neeews**는 뉴스 기사 제공 기능에서 파생되는 여러 기능을 구현한 웹 애플리케이션입니다.

해당 프로젝트에서 제공하고 있는 핵심 기능은 다음과 같습니다.

- 특정 키워드를 포함하는 기사 검색
- 토큰 기반의 로그인
- 기본적인 CRUD 게시판 구현(검색, 댓글, 페이지네이션, 이미지 업로드) 

## 기술 스택 💻
- Java 17 
- Spring Boot 
    - jpa
    - security 
    - mail
- JSP
- HTML, CSS
- JS
- H2 (개발)
- redis

### Browser support 👀

- Firefox-Developer-Edition (개발)
- Safari
- Microsoft-Edge
- Chrome (OAuth2 로그인 문제)

## Information Architecture 🕸️

<img width="1551" alt="image" src="https://github.com/user-attachments/assets/87d448eb-ed5b-47e4-b9a3-2fe1608d9cdb">

## 핵심 구현 기능 🔥

- [뉴스 검색](#뉴스-검색-)
- [회원 관리](#회원-관리-)
- [게시판](#게시판특정-기사-관련-게시판-커뮤니티-공지사항-)

### 뉴스 검색 🔎

- 네이버에서 제공하는 *뉴스 검색 API* 를 이용해 검색어와 관련된 기사의 제목, 요약 등을 제공하는 기능입니다.
- 검색은 유사도, 최신순 기준으로 이루어지며, 기준에 부합하는 상위 1000개의 기사를 제공합니다. 
- 사용자가 기사를 클릭했을 때, 데이터베이스에 해당 기사가 저장되는데,
  - 해당 기사가 네이버에 제공되는 기사인 경우에는 *jsoup* 을 이용한 크롤링을 통해 기사 관련 이미지, 내용 등이 저장됩니다.
  - 네이버에 제공되지 않는 경우에는 기사의 내용은 저장되지 않으며, 기사 원문의 url을 통해 해당 기사를 볼 수 있게 합니다.
- 다른 사용자에 의해 데이터베이스에 저장된 기사를 클릭하면 이미 저장된 데이터를 불러와 기사를 보여줍니다.
- 데이터베이스에 저장된 기사는 모두 각각의 게시판이 존재합니다.
- 회원의 경우,
  - 좋아요, 싫어요 버튼 또는 댓글을 통해 기사에 대해 본인의 의사표현이 가능합니다.
  - 스크랩 기능을 통해 스크랩한 기사를 이후에 쉽게 확인할 수 있습니다.
  - 기사와 관련된 게시물을 작성할 수 있습니다. 해당 게시물은 일반 게시물과 완전히 동일한 기능을 갖습니다.

### 회원 관리 👩🏻‍💼

- *jwt* 를 이용한 토큰 방식의 인증을 이용합니다.
- 로그인은 아이디, 비밀번호를 통한 로그인 방식과 소셜 인증(구글, 네이버)을 통한 로그인 방식으로 구분됩니다.
- 회원가입을 위해 사용자는 미리 정해진 기준에 맞는 아이디, 비밀번호, 닉네임을 정하고 인증해야 하는데, 이는 모두 *비동기 통신(js: fetch)* 으로 행해집니다.
  - 아이디는 알파벳 소문자, 숫자로 이루어진 5 ~ 20 길이를 가지는 문자열로 중복될 수 없습니다.
  - 비밀번호는 알파벳 소문자, 대문자, 숫자, 문자가 최소한 하나 이상 포함되는 8 ~ 20 길이의 문자열이어야 합니다.
  - 닉네임은 2 ~ 16 길이의 문자열로 중복될 수 없습니다.
- 한 개의 이메일로 중복가입은 불가능하며, 메일로 보내진 회원가입 코드 인증까지 완료해야 회원가입이 완료됩니다.
- 소셜 로그인 인증을 통해 최초 회원가입을 하는 경우, 이메일 인증을 제외한 과정을 모두 수행해야 합니다.
- 아이디, 비밀번호를 통한 로그인 시에 아이디 저장을 할 수 있습니다.
- 로그인 시에 필요한 아이디, 비밀번호를 잊어버린 경우 메일을 이용한 코드 인증을 통해 찾을 수 있습니다. 
  - 아이디를 잊어버린 경우, 인증한 메일을 이용한 코드 인증을 통해 아이디를 찾을 수 있습니다.
  - 비밀번호를 잊어버린 경우, 아이디와 인증한 메일을 이용한 코드 인증을 통해 새로운 비밀번호로 변경할 수 있습니다.
- 회원은 모두 각자의 마이페이지가 있고, 하위 메뉴에서 정보 수정, 내가 쓴 게시물, 댓글 확인, 회원 탈퇴 페이지를 불러올 수 있습니다.
- 매니저는 관리자를 통해 임명되며, 관리자, 매니저는 앞서 설명한 마이페이지에 회원 목록을 확인할 수 있습니다.
  - 관리자는 회원 목록 페이지를 통해 매니저를 임명할 수 있습니다.
  - 관리자와 매니저는 회원 목록 페이지를 통해 특정 회원을 검색하거나 탈퇴시킬 수 있습니다.
- 관리자, 매니저는 모든 게시물, 댓글에 대해 작성자와 동등한 권한을 가집니다.

### 게시판(특정 기사 관련 게시판, 커뮤니티, 공지사항) 📋

- 기본적으로 *REST API* 방식을 따르고 있습니다. 
- 제목, 내용, 닉네임을 기준으로 관련된 게시물을 검색할 수 있습니다.
- 게시물 목록은 최신순, 인기순, 댓글순으로 정렬될 수 있습니다.
- 회원이 아닌 사용자는 게시물을 보는 것만 가능합니다.
- 게시물 작성을 할 때, 이미지 업로드가 가능합니다.
  - 업로드 할 이미지는 미리보기를 통해 보여집니다.
  - 업로드 된 이미지는 미리 정해진 파일 경로에 생성되는 해당 게시물 관련 폴더에 저장됩니다.
- 게시물 작성자는 해당 게시물에 대해 수정, 삭제의 권한을 가집니다.
- 회원은 좋아요, 싫어요 버튼 또는 댓글(답글)을 통해 게시물(댓글)에 대해 본인의 의사표현이 가능합니다.
- 댓글 목록은 최신순, 좋아요 순으로 정렬될 수 있습니다.
- 댓글은 작성자에 의해서 삭제될 수 있으며, 수정은 불가능합니다.
- 답글은 댓글과 완전 동일한 기능을 갖습니다.
- 공지사항은 관리자, 매니저만 작성이 가능하고, 공지사항 목록 상위에 고정될 수 있는 것을 제외하면 게시물과 완전 동일한 기능을 갖습니다.

## 화면 구성 🖼️

- [헤더](#헤더)
- [인덱스 페이지](#인덱스-페이지)
- [검색한 기사 목록](#검색한-기사-목록)
- [특정 기준(조회수, 인가순, 댓글순)에 따른 상위 기사 목록](#특정-기준조회수-인기순-댓글순에-따른-상위-기사-목록)
- [커뮤니티 게시물 목록](#커뮤니티-게시물-목록)
- [공지사항 목록](#공지사항-목록)
- [마이페이지](#마이페이지)

### 헤더

- 모든 페이지에 적용된다.

- 로그인 상태가 아닐 때

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/fba27a34-802e-46b5-bd84-ba9ff196da8b" />

- 로그인 상태일 때

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/48e1369a-dcc9-4d22-ace9-796a6f1edbc2" />

### 인덱스 페이지

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/d81fc603-e026-40db-b62c-e233cefd3651" />

- 기사 또는 게시물 검색 영역

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/5512b9e3-a401-4540-8a08-4a0cb88a12fd" />

- 특정 기준(조회수, 인기순, 댓글순)에 따른 상위 기사, 게시물 영역

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/9de97dda-40d8-42e0-941f-5224f30ec95b" />

### 검색한 기사 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/76211107-f092-445b-b94a-d5d55ec89e9f" />

### 기사 뷰

- 기사 내용

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/60488763-d5a6-4588-9dde-931b0a4b3754" />

- 기사 관련 버튼(좋아요, 싫어요, 기사 관련 게시물 작성하기, 기사와 관련된 게시물 목록)

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/729ad0df-b7b6-4431-bd48-aebe645344f8" />

- 댓글

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/dfef93e8-7e28-4b83-ab7c-a314483600ee" />

### 특정 기준(조회수, 인기순, 댓글순)에 따른 상위 기사 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/af1e7499-c025-4287-abce-0a6f42f8469c" />

### 스크랩 기사 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/a57c1ebc-8678-489d-8326-b43c08e567f7" />

### 커뮤니티 게시물 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/ae04effe-6e6a-47bd-9b6f-0f8eb73302b0" />

### 게시물 작성

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/96d0856a-8c33-4055-a17e-0b94f218ea84" />

- 이미지 업로드(미리보기)

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/ea78a072-8ed5-47cf-a8ca-435c8c526e8c" />

### 게시물 뷰

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/faebda7d-f95b-4bfc-a799-c64622e44db8" />

### 공지사항 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/5f62f409-d486-4031-92d0-847e31d7e484" />

### 마이페이지

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/d9ad9a2c-56bc-49e9-9b57-d5022facfa2f" />

- 회원 정보 수정 메뉴

<img width="900" alt="image" src="https://github.com/user-attachments/assets/2f3b49d5-d3b6-4541-993f-7d5d8f6824de" />

- 회원 목록 메뉴(관리자, 매니저)

<img width="971" alt="image" src="https://github.com/user-attachments/assets/5bb2bca1-cb49-4a9c-bb7a-a40c386be71c" />

- 내가 쓴 게시물 메뉴

<img width="958" alt="image" src="https://github.com/user-attachments/assets/271fceef-b682-406e-b635-a2f24a682d7a" />

- 내가 쓴 댓글 메뉴

<img width="957" alt="image" src="https://github.com/user-attachments/assets/802a1fe0-ff0b-404f-86a7-d4b45977f7b0" />

- 회원 탈퇴

<img width="953" alt="image" src="https://github.com/user-attachments/assets/c8f3af06-94b4-4f8c-b312-98bc46a956b4" />

### 로그인

<img width="752" alt="image" src="https://github.com/user-attachments/assets/94013b79-e2a3-48df-a407-7ff0c912c7b4" />

### 회원가입

<img width="865" alt="image" src="https://github.com/user-attachments/assets/385f4f46-a9fa-40bc-b36a-ad6a4be39e60" />

### 아이디, 비밀번호 찾기

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/442302cc-6ca0-43d9-ba3c-de39ac60851f" />
