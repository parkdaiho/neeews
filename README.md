# Project 📰

👉🏻 여러 언론사가 제공하는 뉴스에 관련된 여러 기능을 제공하는 웹 애플리케이션 프로젝트

- [프로젝트 소개](#프로젝트-소개-)
- [기술 스택](#기술-스택-)
- [Browser Support](#browser-support-)
- [Information Architecture](#information-architecture-)
- [구현 기능](#구현-기능-)
- [화면 구성](#화면-구성-)

## 프로젝트 소개 👐🏻

**Project**는 뉴스 기사 제공 기능에서 파생되는 여러 기능을 구현한 웹 애플리케이션입니다.

해당 프로젝트에서 제공하고 있는 핵심 기능은 다음과 같습니다.

- 특정 키워드를 포함하는 기사 검색
- 토큰 기반의 로그인
- 기본적인 CRUD 게시판 구현(검색, 댓글, 페이지네이션, 이미지 업로드) 

## 기술 스택 💻
- Java 17 
- Spring Boot (3.0.2)
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

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/2f47229a-103a-41c8-bf18-32eb71e541d6">

- 로그인 상태일 때

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/60328900-dc19-4eca-80c4-f210917ba117">


### 인덱스 페이지

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/56ca68a2-20f9-4aad-9ba2-eeca81996c5d">

- 기사 또는 게시물 검색 영역

<img width="1110" alt="image" src="https://github.com/user-attachments/assets/67da3e22-d466-43ac-8e1b-1afdba7ba5d9">

- 특정 기준(조회수, 인기순, 댓글순)에 따른 상위 기사, 게시물 영역

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/82676a9c-a8eb-413f-8706-6dbfaa5be6ae">

### 검색한 기사 목록

<img width="1208" alt="image" src="https://github.com/user-attachments/assets/615c1aa6-8e4d-44e5-8835-611a2c63da2d">

- 페이지 소개 및 기사 검색 영역

<img width="1199" alt="image" src="https://github.com/user-attachments/assets/de3bcebe-9d3d-466c-83a6-e3b8a390e860">

- 기사 목록

<img width="1204" alt="image" src="https://github.com/user-attachments/assets/ed5f6cfd-55be-41b3-97d0-c6486a781898">

### 기사 뷰

- 기사 내용

<img width="1210" alt="image" src="https://github.com/user-attachments/assets/7bc77da1-edf9-4488-9596-f13473e0eb82">

- 기사 관련 버튼(좋아요, 싫어요, 기사 관련 게시물 작성하기, 기사와 관련된 게시물 목록)

<img width="1202" alt="image" src="https://github.com/user-attachments/assets/a3bbb500-89a3-4c46-aef7-a8f5ce353e1b">

- 댓글

<img width="1181" alt="image" src="https://github.com/user-attachments/assets/c67ac18e-d6b9-4dd9-ac40-94eea6844f9c">

### 특정 기준(조회수, 인기순, 댓글순)에 따른 상위 기사 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/571f826f-16ba-4019-a50a-7cc97910a47e">

- 페이지 소개 및 기사 검색 영역

<img width="1200" alt="image" src="https://github.com/user-attachments/assets/af0a1c16-090b-46ef-8563-5418309bbef4">

- 상위 기사 목록

<img width="1210" alt="image" src="https://github.com/user-attachments/assets/938cbfeb-cf00-44a8-8899-5029a256a659">

### 스크랩 기사 목록

<img width="1219" alt="image" src="https://github.com/user-attachments/assets/058ebfff-331c-41c0-b910-99d5800c3d48">

### 커뮤니티 게시물 목록

<img width="1220" alt="image" src="https://github.com/user-attachments/assets/cf9fbf5e-45a6-4219-9720-5d9fc23c8ea9">

- 페이지 소개 및 게시물 검색 영역

<img width="1211" alt="image" src="https://github.com/user-attachments/assets/15953f41-52f0-4015-8817-814d2aa27525">

- 게시물 목록 영역

<img width="1214" alt="image" src="https://github.com/user-attachments/assets/c83a8aa2-75fa-45a2-818e-d849b62f3787">

### 게시물 작성

<img width="1203" alt="image" src="https://github.com/user-attachments/assets/6d1a933d-94cb-4318-9355-9a28ad40b96d">

- 이미지 업로드(미리보기)

<img width="1205" alt="image" src="https://github.com/user-attachments/assets/b8781df6-f5e5-4c2a-b3d0-1561113a3bff">

### 게시물 뷰

<img width="1211" alt="image" src="https://github.com/user-attachments/assets/e2eeb74e-d62e-4ffa-adb5-cffbd7d33359">

### 공지사항 목록

<img width="1226" alt="image" src="https://github.com/user-attachments/assets/9b483e5d-d386-46dd-a77c-e8fe087760c6">

- 페이지 소개 및 공지사항 검색 영역

<img width="1203" alt="image" src="https://github.com/user-attachments/assets/53f8ff38-7e51-4278-a5ef-3f0a29aa6d99">

- 공지사항 목록 영역

<img width="1210" alt="image" src="https://github.com/user-attachments/assets/f32d1a0f-7d28-46ae-872c-b227c52c2dee">


### 마이페이지

<img width="1219" alt="image" src="https://github.com/user-attachments/assets/22d1c26c-08fa-4bc4-9096-b083eca5e944">

- 마이페이지 메뉴 영역

<img width="276" alt="image" src="https://github.com/user-attachments/assets/cdeb7f6c-05f4-474f-96bb-0280e801982b">

- 회원 정보 수정 메뉴

<img width="965" alt="image" src="https://github.com/user-attachments/assets/9185db8f-0491-4e9b-96ff-91f8b19356fc">

- 회원 목록 메뉴(관리자, 매니저)

<img width="978" alt="image" src="https://github.com/user-attachments/assets/23b91404-8cad-488f-8cad-0944f2956710">

- 내가 쓴 게시물 메뉴

<img width="976" alt="image" src="https://github.com/user-attachments/assets/6f79487c-0289-4853-ada6-3fb92c1185c1">

- 내가 쓴 댓글 메뉴

<img width="966" alt="image" src="https://github.com/user-attachments/assets/a61abdb5-650a-4505-a717-edccc849ea63">

- 회원 탈퇴

<img width="968" alt="image" src="https://github.com/user-attachments/assets/85ed830a-be37-4e56-a328-e0de3db3ff4c">

### 로그인

<img width="761" alt="image" src="https://github.com/user-attachments/assets/93b21275-6600-4af8-a2f2-49843cc4fe35">

### 회원가입

<img width="873" alt="image" src="https://github.com/user-attachments/assets/bfd4a70e-984f-4688-948c-008d9b66ad90">

