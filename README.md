# Project 📰

👉🏻 여러 언론사가 제공하는 뉴스에 관련된 여러 기능을 제공하는 웹 애플리케이션 프로젝트

- [프로젝트 소개](#프로젝트-소개-)
- [기술 스택](#기술-스택-)
- [Browser Support](#browser-support-)
- [화면 구성](#화면-구성)
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

## 화면 구성 🖥️

- [인덱스 페이지](#인덱스-페이지)
- [검색한 기사 목록](#검색한-기사-목록)
- [조회수, 인기순, 댓글순 상위 기사 목록](#조회수-인기순-댓글순-상위-기사-목록)
- [커뮤니티 게시물 목록](#커뮤니티-게시물-목록)
- [공지사항 목록](#공지사항-목록)
- [마이페이지](#마이페이지)

### 인덱스 페이지

### 검색한 기사 목록

### 조회수, 인기순, 댓글순 상위 기사 목록

### 커뮤니티 게시물 목록

### 공지사항 목록

### 마이페이지

### 로그인

### 회원가입

## 구현 기능 🔥

- [뉴스 검색](#뉴스-검색-)
- [회원 관리](#회원-관리-)
- [커뮤니티(게시판)](#커뮤니티게시판)

### 뉴스 검색 🔎

#### Naver news-search API

### 회원 관리 👩🏻‍💼

### 커뮤니티(게시판) 📋
