# 프로젝트 소개
매년 수많은 반려동물이 여러 이유로 우리 곁을 떠나고 있습니다. 사랑받는 존재들이 길을 잃고 고통받는 것은 우리 모두가 마음 아프게 느끼는 문제입니다. 우리 프로젝트는 반려동물이 주인에게 돌아갈 수 있도록 하는 부분에 보탬이 되고자 합니다.

### Technology Stacks
+ Backend
  + Java 17 (OpenJDK)
  + Spring Boot 3.2.5
  + Spring Data JPA
  + Hibernate
  + Spring Web
  + Spring Security
  + Validation
  + Lombok
  + Querydsl
  + JWT
  + Swagger
  + Github
  
+ Frontend
  + TypeScript
  + JavaScript
  +  CSS

<br><br>

### 주요 기능
+ 회원 관리
  + 회원가입
  + 로그인/로그아웃
+ 커뮤니티
  + 재회 후기 글 (등록/수정/삭제/조회)
+ 보호소 페이지
+ `프론트 담당` : 공공 API를 활용하여 등록된 실종 동물 정보 조회
+ 실종/제보 페이지
  + 실종 글 (등록/수정/삭제/조회)
  + 제보 글 (등록/수정/삭제/조회)

대부분의 주요 기능은 백엔드 파트에서 구현되었으나, 보호소 페이지는 프론트 파트에서 구현하였으며, 공공 API를 사용했습니다.
> [공공 API -  구조동물 조회 서비스](https://www.data.go.kr/data/15098931/openapi.do)

<br><br>

### API Docs
[Notion > API DOCS 바로가기](https://fierce-baryonyx-006.notion.site/API-Doc-2188e8aba8074a87918f7ee95d57ee6b)

 <br><br>
 
### Branches
+ test, master, main : (원래 개발 단계별로 사용하려고 생성한 브랜치)
+ add-swagger : 개발 및 배포용으로 사용됨

원래는 각각 개발단계에서 사용하려고 생성한 브랜치였지만,
add-swagger 브랜치만 사용하게 되었다.

# ERD
![Copy of abandoned animals (1)](https://github.com/user-attachments/assets/119317aa-d912-43fb-8b38-d056249aa28f)


# Architecture
![팀풀 drawio](https://github.com/user-attachments/assets/6c3f5132-fc9f-4588-8c9a-ee1b20d011d3) <br>
플랫폼 (NCP)으로 서버를 생성하고 프로젝트를 배포하기로 했습니다. <br>
+ 서버로 Ubuntu  Micro 타입으로 무료버전을 사용했으며, 데이터베이스는 MySQL Standard 8GB 타입을 사용했습니다.
+ 포스팅/프로필 등록되는 이미지는 GCP로 관리했습니다.

# Troubleshooting
## CORS 문제 해결
### 문제 원인
![image](https://github.com/user-attachments/assets/27e5664b-f433-4672-9921-cfba9e903196) <br><br>
프론트엔드에서 회원가입 API 요청을 보낼 때 CORS(Cross-Origin Resource Sharing) 문제가 발생했습니다. <br>
이유는 브라우저의 보안 정책 때문입니다. CORS는 서로 다른 도메인 간의 요청에 대해 보안을 강화하기 위해 적용됩니다. <br>
즉, 프론트엔드와 백엔드가 서로 다른 도메인에서 실행될 경우, 브라우저는 기본적으로 이를 차단하고 CORS 에러가 발생합니다.

### 해결 과정
#### 1.  CORS 설정을 수정한다.
![image](https://github.com/user-attachments/assets/a40bac4a-0283-4a2f-9db5-f60c027816a4)
이렇게 CORS 설정을 수정한 후, 프론트팀원에게 확인을 요청했으나, 프론트 팀원은 '회원가입은 해결되었지만, <br>
게시글 등록 시 401 권한 오류가 발생했다'고 피드백을 주었습니다.

#### 2. CORS 문제 외에도 JWT 관련 오류 발견됨
프론트팀원은 CORS 문제 외에도 JWT 관련 오류가 발생했다고 했습니다. <br>
이를 해결하기 위해 JWT 검증 부분의 로그를 확인하여 오류가 발생하는 위치를 파악하려고 했습니다. <br>
![image](https://github.com/user-attachments/assets/ecffa109-ce52-4a98-8282-a01d7b156d79)  <br>
![image](https://github.com/user-attachments/assets/5213ecbf-b762-4b4e-88ff-436681b1b151)  <br> <br>

로그를 확인해보니, 프론트 팀원이 게시글 등록요청을 했을 때 요청 데이터 타입이 맞지 않아 요청이 실패했다는 로그가 찍혔다. <br>
결과적으로 요청 데이터 타입이 맞지 않아 요청이 실패한 것이었다. <br>
이 문제를 확인하고 해결함으로써, CORS와 JWT 관련 오류의 원인을 명확히 알 수 있었다.
