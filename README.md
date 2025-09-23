# SSGPANG

Spring Boot 기반의 MVC 아키텍처와 MyBatis를 사용하여 **재고 관리의 기본적인 CRUD 기능**을 구현한 프로젝트입니다.  
처음으로 DB 연동과 웹 애플리케이션의 기본 동작 원리를 이해하기 위해 진행했습니다.  
또한 기본 CRUD 외에도 학습 범위를 확장하기 위해 **게시판, 외부 API 연동, 엑셀 데이터 입출력, 대시보드** 기능을 추가했습니다.

---

## 📌 목적
- Spring Boot + MyBatis 프레임워크 학습  
- 웹 애플리케이션에서 CRUD 동작 구현 경험  
- 데이터베이스와 자바를 연동하는 기본 로직 이해  

---

## 📆 개발 기간
- 2025.01.10(금) ~ 2025.01.15(수)

---

## 👨‍👩‍👧‍👦 멤버 및 역할 소개

| 이름 | 역할 |
|------|------|
| [강기호](https://github.com/kanggiho)  | Git 관리, 발주 관리, 엑셀 데이터 입출력 |
| [민소원](https://github.com/wishs2)   | 회원 관리 |
| [박헌우](https://github.com/heonwoo1315) | 스케줄 관리, 재고 관리 기능 |
| [이가영](https://github.com/GaYoung28)  | 대시보드 제작, 게시판 제작 |
| [이상우](https://github.com/sangwooLee1231) | 입고 관리, MAP API 연동 |

---

## 🛠 기술 스택

#### Frontend
- 언어  
  <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">

- 라이브러리  
  <img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white">

- 프레임워크  
  <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">

#### Backend
- 언어  
  <img src="https://img.shields.io/badge/Java17-007396?style=for-the-badge&logo=Java&logoColor=white">

- 프레임워크  
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-000000?style=for-the-badge">

- DB  
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">

---

## ✨ 주요 기능
- **재고 관리**: 상품 등록, 수정, 삭제, 조회  
- **입출고 관리**: 재고 입고/출고 처리  
- **발주 관리**: 발주 요청 등록 및 관리  
- **대시보드**: 창고 관리 현황 시각화  
- **게시판 & FAQ**: 사용자 커뮤니티 지원  
- **엑셀 데이터 입출력**: 대량 데이터 관리  

---

## 🗂 ERD
<img width="913" height="746" alt="ERD Diagram" src="https://github.com/user-attachments/assets/662dc928-615e-43c2-8b7e-0052e72ff4d9" />

---

## 📂 프로젝트 구조

```
📦 project-root
├─ .gradle
│ └─ 8.1.1
├─ .idea
├─ build
├─ gradle
├─ logs
├─ src
│ └─ main
│ ├─ java
│ │ └─ com
│ │ └─ shinsegae
│ │ └─ project
│ │ ├─ board
│ │ │ ├─ controller # MVC - Controller
│ │ │ ├─ mapper # DAO, MyBatis Mapper
│ │ │ ├─ service # Business Logic
│ │ │ └─ vo # DTO/VO
│ │ ├─ chatbot
│ │ ├─ common
│ │ ├─ config
│ │ ├─ dashboard
│ │ ├─ excel
│ │ ├─ incoming
│ │ ├─ inventory
│ │ ├─ map
│ │ ├─ member
│ │ ├─ order
│ │ └─ search
│ │
│ │ Project2Application.java
│ │ ServletInitializer.java
│ │
│ └─ resources
│ ├─ mapper
│ │ ├─ AdminMapper.xml
│ │ ├─ BoardMapper.xml
│ │ ├─ ChatBotMapper.xml
│ │ ├─ DashboardMapper.xml
│ │ ├─ ExcelMapper.xml
│ │ ├─ IncomingMapper.xml
│ │ ├─ InventoryMapper.xml
│ │ ├─ MapMapper.xml
│ │ ├─ OrderMapper.xml
│ │ └─ UserMapper.xml
│ │
│ ├─ static
│ └─ templates
│ ├─ admin
│ │ ├─ board
│ │ ├─ excel
│ │ ├─ fragments
│ │ ├─ incoming
│ │ ├─ inventory
│ │ ├─ map
│ │ ├─ order
│ │ └─ search
│ │ └─ home_admin.html
│ │
│ └─ user
│ ├─ board
│ ├─ chatbot
│ ├─ fragments
│ ├─ map
│ ├─ member
│ ├─ order
│ └─ search
│ └─ home.html
│
│ ├─ index.html
│ ├─ application.properties
│ └─ applicationContext.xml
```

---

## 📷 Screenshots

### 회원 관리
- 회원가입 / 로그인  
<img width="652" height="194" alt="Signup/Login" src="https://github.com/user-attachments/assets/7926720b-e2cb-486e-adfb-79b1af40c1ba" />

### 대시보드
- USER 대시보드  
<img width="659" height="295" alt="User Dashboard" src="https://github.com/user-attachments/assets/24017aac-7c95-4426-98df-c15401db2472" />

- ADMIN 대시보드  
<img width="633" height="288" alt="Admin Dashboard" src="https://github.com/user-attachments/assets/3e7741fc-c025-4019-b3e9-164cb156cb39" />

### 발주 관리
- 발주하기  
<img width="556" height="278" alt="Order Create" src="https://github.com/user-attachments/assets/737ce869-8d50-4cad-9afa-72bb37ac38b9" />

- 발주 내역 확인  
<img width="566" height="248" alt="Order List" src="https://github.com/user-attachments/assets/cd308b98-4184-4bc8-8d5e-793d3422ad2c" />

### 입고 관리
- 입고 신청  
<img width="530" height="265" alt="Incoming Request" src="https://github.com/user-attachments/assets/2dbcff2b-3198-4234-97a9-507ff81feb7e" />

- 입고 내역 확인  
<img width="526" height="266" alt="Incoming List" src="https://github.com/user-attachments/assets/fc3fea23-a02b-4a6c-bf98-b8c92c812979" />

### 재고 관리
- 재고 확인  
<img width="526" height="260" alt="Inventory Check" src="https://github.com/user-attachments/assets/60cb00d0-da16-41be-8134-e9c903518fff" />

- 재고 추가  
<img width="541" height="266" alt="Inventory Add" src="https://github.com/user-attachments/assets/c7eb2647-b0fb-4b68-8a35-20f4c9feb89a" />

### 기타 기능
- 발주 요청 관리  
<img width="547" height="271" alt="Order Request Manage" src="https://github.com/user-attachments/assets/ad575773-be77-49c2-a39f-d5ebf6a4392b" />

- 파일 입출력 (가져오기 / 내보내기)  
<img width="529" height="256" alt="Excel Import" src="https://github.com/user-attachments/assets/e8fec9f3-b37d-4403-bdfc-82e753ca5a9d" />  
<img width="643" height="258" alt="Excel Export" src="https://github.com/user-attachments/assets/b5033585-bb05-4370-b4b2-64c2a39f8669" />

- 창고별 위치  
<img width="526" height="260" alt="Warehouse Map" src="https://github.com/user-attachments/assets/a3e78681-22d9-4a63-8a01-5dc7a396fb0b" />

- 게시판  
<img width="648" height="281" alt="Board" src="https://github.com/user-attachments/assets/f67ff128-dc3b-49a1-ab56-841baced9ecd" />

- 사용자 FAQ  
<img width="581" height="282" alt="FAQ" src="https://github.com/user-attachments/assets/3601c9fe-0a95-4706-a5d1-b1b0118c313e" />

---

## 📖 성과 및 배운 점
- Spring Boot 기반 MVC 아키텍처와 MyBatis를 활용한 CRUD 구현 경험  
- 데이터베이스 구조 설계 및 ERD 작성 경험  
- GitHub를 통한 협업 및 버전 관리 학습  
- 단순 CRUD를 넘어 API 연동, 파일 입출력, 대시보드 등 기능 확장을 통해 서비스 범위를 넓힌 경험  

---

## 🎥 시연 영상
[https://youtu.be/x5z8BbT6-Bs?si=_JJ3G-jW_iN4cz2H]
