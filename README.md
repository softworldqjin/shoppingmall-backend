# 🛒 Spring Boot 쇼핑몰 프로젝트

Spring Boot 기반으로 구현한 쇼핑몰 웹 애플리케이션입니다.  
강의를 따라 기능을 구현하였으며, 일부 기능은 직접 추가 및 수정했습니다.  
클라우드 환경(AWS, Azure)을 연동하여 실제 배포까지 완료했습니다.

---

## 📚 프로젝트 소개

본 프로젝트는 Spring Boot 강의를 보며 구현한 첫 백엔드 쇼핑몰 프로젝트입니다.  
강의를 통해 CRUD, 인증 처리, Pagination, 검색 기능, 댓글 기능, AWS S3 이미지 업로드 등의  
구현 과정을 학습했습니다.

기능 구현 자체는 강의를 참고하여 진행했지만,  
각 기능의 동작 원리와 전체 애플리케이션 구조를 이해하는 데 집중하며 개발했습니다.

또한 Member, Item, Notice 도메인 단위로 패키지를 분리하면서  
도메인 중심 설계(DDD) 구조를 처음 경험한 프로젝트입니다.

---

## 🚀 배포

- Platform: AWS Elastic Beanstalk
- Database: Azure MySQL
- Image Storage: AWS S3
- <a href="http://gyujinshop.ap-northeast-2.elasticbeanstalk.com" target="_blank">서비스 바로가기</a>

AWS Elastic Beanstalk에 Spring Boot 애플리케이션을 배포하고,  
Azure MySQL과 AWS S3를 연동하여 실제 서비스 환경으로 구성했습니다.

---

## ⚙️ 기술 스택

- Java 17
- Spring Boot 3.5.10
- Spring MVC
- Spring Data JPA
- MySQL (Azure Database for MySQL)
- Thymeleaf
- AWS S3
- AWS Elastic Beanstalk

---

## 🏗 프로젝트 구조 (DDD 기반)

각 기능을 Member, Item, Notice 도메인 단위로 분리하여  
Controller, Service, Repository, Entity 구조로 설계했습니다.

---

## ✨ 주요 기능

### 👤 회원 기능
- 회원가입
- 로그인 / 로그아웃
- 세션 기반 인증 처리
  
> 세션 타임아웃: 6분
> 쿠키 유지시간: 6분
> 브라우저 종료 시 세션은 소멸되지만 쿠키는 유지되며,  
> 서버에서 해당 쿠키를 6분 동안 인증에 사용하도록 설정했습니다.

---

### 🛍 상품 기능
- 상품 등록 / 수정 / 삭제
- Pagination 적용
- 키워드 기반 상품 검색 기능
- AWS S3 이미지 업로드 후 URL을 DB에 저장
- 저장된 URL을 통해 이미지 출력

---

### 🔍 검색 기능
- 상품 제목 키워드 검색
- 검색 결과 Pagination 처리
- Spring Data JPA의 `findByTitleContains` 방식으로 구현

---

### 📝 게시글 / 댓글 기능
- 게시글 작성 (로그인 필요)
- 댓글 작성 (로그인 필요)
- 댓글 작성 시 닉네임과 내용 표시
- 게시글 수정 기능 (로그인 불필요)

---

## 🔐 인증 및 접근 제어

- 회원가입 후 로그인 가능
- BCrypt 기반 비밀번호 암호화 후 저장
- 로그인 상태에서만 게시글 작성 가능
- 로그인 상태에서만 댓글 작성 가능
- 세션 기반 인증 방식 적용

<img width="424" height="111" alt="Image" src="https://github.com/user-attachments/assets/ba08d2bd-22e2-4108-b1a2-00708f5333ea" />

---

## ☁️ 인프라 연동

### Azure MySQL
- Azure에서 MySQL 서버 생성 후 외부 DB 연결
- Spring Boot와 MySQL 연동

### AWS S3
- 이미지 파일 업로드
- 업로드된 파일 URL을 DB에 저장
- 저장된 URL을 통해 웹 페이지에서 이미지 출력

---

## 🔐 환경 변수 설정

보안을 위해 민감한 정보(DB 계정, AWS Key)는 환경 변수로 관리합니다.
``
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
AWS_ACCESS_KEY=your_access_key
AWS_SECRET_KEY=your_secret_key
``
