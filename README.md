# Project 📰

👉🏻 여러 언론사가 제공하는 뉴스에 관련된 여러 기능을 제공하는 웹 애플리케이션 프로젝트

- [프로젝트 소개](#프로젝트-소개-)
- [기술 스택](#기술-스택-)
- [Browser Support](#browser-support-)
- [화면 구성](#화면-구성-)
- [구현 기능](#구현-기능-)

## 프로젝트 소개 👐🏻

**Project**는 기본적인 게시판 구현 및 회원관리, 외부 API를 사용해보고자 개발된 웹 애플리케이션입니다.

해당 프로젝트에서 제공하고 있는 기능은 다음과 같습니다.

- *Naver 뉴스 검색 API* 를 이용해 특정 키워드를 포함하는 기사 검색
  - *jsoup* 을 이용한 기사 크롤링
- *jwt* 를 기반으로 한 로그인
  - 기본적인 폼 로그인 
  - *OAuth2.0* 을 이용해 외부 인증을 통한 로그인(Google, Naver)
- 스프링 부트를 이용해 Rest API 방식의 게시판 구현(검색, 댓글, 페이지네이션, 이미지 업로드) 

## 기술 스택 💻
- Java 17 
- Spring Boot (3.0.2)
    - JPA
    - Security 
    - OAuth2.0
- JSP
- HTML, CSS
- JS
- H2 (개발)

### Browser support 👀

- Firefox-Developer-Edition (개발)
- Safari
- Microsoft-Edge
- Chrome (OAuth2 로그인 문제)

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

## 구현 기능 🔥

- [뉴스 검색](#뉴스-검색-)
- [회원 관리](#회원-관리-)
- [커뮤니티(게시판)](#커뮤니티게시판)

### 뉴스 검색 🔎

#### Naver news-search API

### 회원 관리 👩🏻‍💼

### 커뮤니티(게시판) 📋
