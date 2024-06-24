# 브릿지톡(BridgeTalk) - 이주민 가정을 위한 다문화 소통 플랫폼
<img src="https://github.com/hyun0-25/SSAFY-BRIDGETALK/assets/95136913/54ed3830-f93b-46c4-90a2-9b4fefb4b497" width="800px" height="450px">

<br>

# ⏱️ 프로젝트 진행 기간

### SSAFY 10기 2학기 자율프로젝트

<summary><strong>전체 일정 : 2024.04.08 ~ 2024.05.20 (6주)</strong></summary>
<div>
    <ul>
        <li>기획: 2024.04.08 ~ 2024.04.19</li>
        <li>설계: 2024.04.20 ~ 2024.04.24</li>
        <li>구현: 2024.04.25 ~ 2024.05.16</li>
        <li>배포: 2024.05.17</li>
        <li>버그 수정: 2024.05.19 ~ 2024.05.20</li>
    </ul>
</div>
</details>
<br>

# 목차

[1. 프로젝트 개요](#1️⃣-프로젝트-개요)<br>
[2. 주요 기능](#2️⃣-주요-기능)<br>
[3. 기술 소개](#3️⃣-기술-소개)<br>
[4. 시스템 아키텍처](#4️⃣-시스템-아키텍처)<br>
[5. 파일 구조](#5️⃣-파일-구조)<br>
[6. 기술 스택](#6️⃣-기술-스택)<br>
[7. 설계 문서](#7️⃣-문서)<br>
[8. 팀 소개](#8️⃣-팀-소개)<br>
[9. 실행 화면](#8️9️⃣-실행 화면)<br>
<br><br><br>



# 1️⃣ 프로젝트 개요
## 💡 서비스 소개


아이가 한국어를 배우면서 계속 이야기를 하지만, 이 이야기를 이해하기 어려우시다면!

아이의 속마음에 대해 들어보고 싶다면!

아이와 같이 모국의 문화에 대해 알아가고 싶다면!

이주민 가정의 아이와 부모의 소통을 서포트하는 플랫폼 브릿지톡을 추천드립니다.

<br>

## ⭐ 기획 배경

국내에 다문화 가정이 몇 가구 인 지 혹시 아시나요?

약 40만 가구로 매년 꾸준하게 증가하고 있는데요. 이 과정에서 한국어가 서툰 어머니와 한국에서 자라게 되어 한국어가 능숙한 아이 간 소통에 대한 문제가 있다고 합니다.

저희는 뉴스를 보고 그 사실에 주목했습니다. 한 쪽에 치우쳐진 사실은 아닌 지 추가 조사도 진행하였습니다.

| 총 30여 곳의 다문화 센터에 연락 및 관련 영상 조사 |
| --- |
|<img src="https://github.com/hyun0-25/SSAFY-BRIDGETALK/assets/95136913/e62fca12-cbc3-4fad-8b7d-971928ec9279" width="650px" height="400px">|

→ 
| 대전 외국인 주민 지원통합 센터장님의 인터뷰 진행 |
| --- |
|<img src="https://github.com/hyun0-25/SSAFY-BRIDGETALK/assets/95136913/0fa9df6b-c5bb-4768-b8f2-011d5af4b8a7" width="650px" height="400px">|
```
- 조사 결과
    - 실제로 소통 문제 존재 확인
    - 기존의 언어 학습 위주의 프로그램에서 언어와 소통을 같이 하는 방향으로 바뀌어 나가고 있음
    - 지원 프로그램 예시로 상담사를 한 사람씩 가정에 배치하고 있음을 확인
```
### 지원 프로그램에서 시간적, 공간적인 제한의 아쉬움 발견 -> 아이와의 소통에 도움을 주는 서비스 기획

<br>

> #### ❓ 왜 언어 학습 위주가 아닌 소통 중심의 서비스를 기획하나요?

- **언어 학습으로 인한 문제는** 아이와 함께 어머니가 한국에서 계속 지내게 되면서, **시간이 흐르면 자연스레 소통에 큰 문제가 없을 정도로 개선되는 경우가 많음**
- **하지만 엄마와 아이와 소통하면서 유대감 형성을 하는 시기는 지나게 됩니다!**
    
    → **저희는 이 시기에 소통을 돕고자 했습니다.** 그렇게 나온 서비스! 
    

### 이주민 가정을 위한 다문화 소통 플랫폼 '브릿지톡' 입니다.

<br>

# 2️⃣ 주요 기능

## 💡 로그인 / 회원가입
- 자체 회원가입 구현 
    - 자유로운 닉네임 설정
    - 공룡 캐릭터 선택이 필요했기에 자체 회원가입 구현
- 이메일 인증 진행
    - 인증번호를 통해 부모 회원가입 승인
- 인증번호 및 리프레시토큰은 특정 기간 이후 바로 날리는 데이터이기에 redis를 사용

## 💡 멀티 프로필
- ott의 멀티 프로필 형태를 참고하여 구현
- 부모의 프로필은 오른쪽 상단의 톱니바퀴 클릭 후 접속 가능
- 아이의 프로필은 리스트 형태로 보여줌
- 부모와 아이 모두 비밀번호 입력 후 본인의 프로필로 접속 가능
## 💡 아이 
### 공룡친구와 대화하기 (STT → 생성형 AI → TTS)
- 아이가 음성으로 말하면 이를 텍스트로 변환
- 생성형 AI를 통해 감정 및 답변 추출
- CLOVA API를 통해 캐릭터 목소리로 답변 음성 전달
- 감정에 따른 공룡 캐릭터 표정변화
### 게임하기
- 퍼즐
    - 부모 나라의 랜드마크 퍼즐 맞추기
    - 퍼즐 완성 시 장소에 대한 설명 제공
- 전통복 입기, 사진찍기
    - Mediapipe를 통해 전통복 상하의 입기
    - 사진찍기 버튼을 통해 이미지 저장 가능
### 편지함
- 부모가 모국어(베트남어/필리핀어)로 보낸 음성 편지
- 익명의 공룡 친구에게 온 편지처럼 한국어 음성 파일과 자막 제공
- 편지내용의 키워드에 해당하는 이모지 제공
## 💡 부모
### 레포트 및 솔루션
- 아이 대화내용 기반 레포트 생성
- 대화 내용 요약 및 핵심 키워드 추출
- 대화 내용에 따른 솔루션 제시
- 번역본 제공
- 아이에게 모국어로 편지보내기
### 정보 제공
- 여성가족부 ‘자녀연령별 육아정보 제공
- 한국 학부모들의 줄임말 및 신조어 제공
- 베트남어/필리핀어 번역본 제공
### 커뮤니티
- 다른 부모들과 소통하는 공간
- 아이 레포트 기반 커뮤니티 고민글 작성
- 고민글에 대한 댓글 작성

<br><br>

# 3️⃣ 기술 소개
|브릿지톡 핵심 기술 소개|
|---|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/0ab80df6-bd3a-4bcf-9e03-cd6a36547613" width="650px" height="400px">|

### 서비스 워커 활용 -> 정적 데이터 캐싱
|서비스 워커 활용 -> 정적 데이터 캐싱|
|---|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/98dc0428-a982-4bd1-a82b-0093cb02aa06" width="650px" height="400px">|
### Unity & Three.js & 미디어 파이프 활용
|Unity & Three.js|
|---|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/a0f89041-4148-46f4-b3ba-0ffcad8d0d4c" width="650px" height="400px">|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/c2b9fb29-b014-4772-b850-4a9cefa3acdc" width="650px" height="400px">|
|미디어파이프 활용|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/422d4dc4-4570-412c-b77c-16f97d47ee9a" width="650px" height="400px">|
### 백엔드 API 성능 개선
|STS 성능 개선|
|---|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/650d6d4f-a11a-42ba-bae3-9a4fc1041c21" width="650px" height="400px">|
|백엔드 API 응답 속도 개선|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/95e8ca4f-4887-4e73-b6a7-3a4aa0fb99ef" width="650px" height="400px">|
### 테스트코드 작성 (통합 테스트 & 단위 테스트)
|테스트코드 작성|
|---|
|<img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/bbbf6361-379f-4d26-9132-53d8587f1afd" width="650px" height="400px">|

<br><br>
# 4️⃣ 시스템 아키텍처
<img src="https://github.com/hyun0-25/SSAFY-BRIDGETALK/assets/95136913/87e32f80-2ccc-4194-a46a-25d83886dbba" width="650px" height="400px">

<br><br>

# 5️⃣ 파일 구조
<details>
<summary>Front 폴더 구조</summary>

```bash
📦bridgetalk-front
 ┣ 📂public
 ┃ ┣ 📂@ffmpeg
 ┃ ┃ ┗ 📂core
 ┃ ┃ ┃ ┣ 📂dist
 ┃ ┃ ┃ ┃ ┣ 📂esm
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ffmpeg-core.js
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ffmpeg-core.wasm
 ┃ ┃ ┃ ┃ ┗ 📂umd
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ffmpeg-core.js
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ffmpeg-core.wasm
 ┃ ┃ ┃ ┗ 📜package.json
 ┃ ┣ 📂assets
 ┃ ┃ ┣ 📂dino
 ┃ ┃ ┃ ┣ 📂D1
 ┃ ┃ ┃ ┃ ┣ 📜cute.glb
 ┃ ┃ ┃ ┃ ┣ 📜D1.png
 ┃ ┃ ┃ ┃ ┣ 📜happy.glb
 ┃ ┃ ┃ ┃ ┣ 📜hello.glb
 ┃ ┃ ┃ ┃ ┣ 📜idle.glb
 ┃ ┃ ┃ ┃ ┣ 📜no.glb
 ┃ ┃ ┃ ┃ ┣ 📜sick.glb
 ┃ ┃ ┃ ┃ ┗ 📜yes.glb
 ┃ ┃ ┃ ┣ 📂D2
 ┃ ┃ ┃ ┃ ┣ 📜cute.glb
 ┃ ┃ ┃ ┃ ┣ 📜D2.png
 ┃ ┃ ┃ ┃ ┣ 📜happy.glb
 ┃ ┃ ┃ ┃ ┣ 📜hello.glb
 ┃ ┃ ┃ ┃ ┣ 📜idle.glb
 ┃ ┃ ┃ ┃ ┣ 📜no.glb
 ┃ ┃ ┃ ┃ ┣ 📜sick.glb
 ┃ ┃ ┃ ┃ ┗ 📜yes.glb
 ┃ ┃ ┃ ┣ 📂D3
 ┃ ┃ ┃ ┃ ┣ 📜cute.glb
 ┃ ┃ ┃ ┃ ┣ 📜D3.png
 ┃ ┃ ┃ ┃ ┣ 📜happy.glb
 ┃ ┃ ┃ ┃ ┣ 📜hello.glb
 ┃ ┃ ┃ ┃ ┣ 📜idle.glb
 ┃ ┃ ┃ ┃ ┣ 📜no.glb
 ┃ ┃ ┃ ┃ ┣ 📜sick.glb
 ┃ ┃ ┃ ┃ ┗ 📜yes.glb
 ┃ ┃ ┃ ┣ 📂D4
 ┃ ┃ ┃ ┃ ┣ 📜cute.glb
 ┃ ┃ ┃ ┃ ┣ 📜D4.png
 ┃ ┃ ┃ ┃ ┣ 📜happy.glb
 ┃ ┃ ┃ ┃ ┣ 📜hello.glb
 ┃ ┃ ┃ ┃ ┣ 📜idle.glb
 ┃ ┃ ┃ ┃ ┣ 📜no.glb
 ┃ ┃ ┃ ┃ ┣ 📜sick.glb
 ┃ ┃ ┃ ┃ ┗ 📜yes.glb
 ┃ ┃ ┃ ┣ 📂D5
 ┃ ┃ ┃ ┃ ┣ 📜cute.glb
 ┃ ┃ ┃ ┃ ┣ 📜D5.png
 ┃ ┃ ┃ ┃ ┣ 📜happy.glb
 ┃ ┃ ┃ ┃ ┣ 📜hello.glb
 ┃ ┃ ┃ ┃ ┣ 📜idle.glb
 ┃ ┃ ┃ ┃ ┣ 📜no.glb
 ┃ ┃ ┃ ┃ ┣ 📜sick.glb
 ┃ ┃ ┃ ┃ ┗ 📜yes.glb
 ┃ ┃ ┃ ┣ 📂D6
 ┃ ┃ ┃ ┃ ┣ 📜cute.glb
 ┃ ┃ ┃ ┃ ┣ 📜D6.png
 ┃ ┃ ┃ ┃ ┣ 📜happy.glb
 ┃ ┃ ┃ ┃ ┣ 📜hello.glb
 ┃ ┃ ┃ ┃ ┣ 📜idle.glb
 ┃ ┃ ┃ ┃ ┣ 📜no.glb
 ┃ ┃ ┃ ┃ ┣ 📜sick.glb
 ┃ ┃ ┃ ┃ ┗ 📜yes.glb
 ┃ ┃ ┃ ┣ 📜inCom.glb
 ┃ ┃ ┃ ┣ 📜inCom2.glb
 ┃ ┃ ┃ ┣ 📜mycom.glb
 ┃ ┃ ┃ ┣ 📜otherPos.glb
 ┃ ┃ ┃ ┗ 📜pinkSick.glb
 ┃ ┃ ┣ 📂favicon
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-114x114.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-120x120.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-144x144.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-152x152.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-57x57.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-60x60.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-72x72.png
 ┃ ┃ ┃ ┣ 📜apple-touch-icon-76x76.png
 ┃ ┃ ┃ ┣ 📜code.txt
 ┃ ┃ ┃ ┣ 📜favicon-128.png
 ┃ ┃ ┃ ┣ 📜favicon-16x16.png
 ┃ ┃ ┃ ┣ 📜favicon-196x196.png
 ┃ ┃ ┃ ┣ 📜favicon-32x32.png
 ┃ ┃ ┃ ┣ 📜favicon-96x96.png
 ┃ ┃ ┃ ┣ 📜favicon.ico
 ┃ ┃ ┃ ┣ 📜logo.png
 ┃ ┃ ┃ ┣ 📜mstile-144x144.png
 ┃ ┃ ┃ ┣ 📜mstile-150x150.png
 ┃ ┃ ┃ ┣ 📜mstile-310x150.png
 ┃ ┃ ┃ ┣ 📜mstile-310x310.png
 ┃ ┃ ┃ ┗ 📜mstile-70x70.png
 ┃ ┃ ┣ 📂flag
 ┃ ┃ ┃ ┣ 📜kor.png
 ┃ ┃ ┃ ┣ 📜ph.png
 ┃ ┃ ┃ ┗ 📜viet.png
 ┃ ┃ ┣ 📂img
 ┃ ┃ ┃ ┣ 📂child
 ┃ ┃ ┃ ┃ ┣ 📂game
 ┃ ┃ ┃ ┃ ┃ ┣ 📂drawing
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜eraser.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_arrowdown.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_arrowleft.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_arrowright.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_arrowtop.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_circle.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_fivestar.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_fourstar.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_line.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_line2.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_pentagon.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_rect.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_rhombus.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_sexangle.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜shape_triangle.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thickline1.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thickline2.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thickline3.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜thickline4.svg
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜thickness.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜barotSaya.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜bottom.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜candyBackground.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜capture.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜finishTitle.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜gameBackground.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜gameTitle.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜leftForearm.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜rightForearm.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜toDress.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜toHelp.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜toHome.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜top.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜toPuzzle.svg
 ┃ ┃ ┃ ┃ ┣ 📜back.svg
 ┃ ┃ ┃ ┃ ┣ 📜homeIcon.svg
 ┃ ┃ ┃ ┃ ┣ 📜save.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot1.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot2.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot3.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot4.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot5.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot6.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot7.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot8.svg
 ┃ ┃ ┃ ┃ ┣ 📜toGame.svg
 ┃ ┃ ┃ ┃ ┣ 📜toMessage.svg
 ┃ ┃ ┃ ┃ ┗ 📜toTalk.svg
 ┃ ┃ ┃ ┣ 📂icon
 ┃ ┃ ┃ ┃ ┗ 📜toBack.svg
 ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┣ 📜addProfile.svg
 ┃ ┃ ┃ ┃ ┣ 📜backIcon.svg
 ┃ ┃ ┃ ┃ ┣ 📜commingSoon.svg
 ┃ ┃ ┃ ┃ ┣ 📜deleteicon.svg
 ┃ ┃ ┃ ┃ ┣ 📜editProfile.svg
 ┃ ┃ ┃ ┃ ┣ 📜editProfileIcon.svg
 ┃ ┃ ┃ ┃ ┣ 📜emailicon.svg
 ┃ ┃ ┃ ┃ ┣ 📜emailverifynumbericon.svg
 ┃ ┃ ┃ ┃ ┣ 📜login.svg
 ┃ ┃ ┃ ┃ ┣ 📜logout.svg
 ┃ ┃ ┃ ┃ ┣ 📜mainBackground.png
 ┃ ┃ ┃ ┃ ┣ 📜nameicon.svg
 ┃ ┃ ┃ ┃ ┣ 📜newProfile.svg
 ┃ ┃ ┃ ┃ ┣ 📜nicknameicon.svg
 ┃ ┃ ┃ ┃ ┣ 📜passwordcheckicon.svg
 ┃ ┃ ┃ ┃ ┣ 📜passwordicon.svg
 ┃ ┃ ┃ ┃ ┣ 📜profile.svg
 ┃ ┃ ┃ ┃ ┣ 📜saveIcon.svg
 ┃ ┃ ┃ ┃ ┣ 📜selectCharacter.svg
 ┃ ┃ ┃ ┃ ┣ 📜selectYourCountry.svg
 ┃ ┃ ┃ ┃ ┣ 📜setting.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot1.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot2.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot3.svg
 ┃ ┃ ┃ ┃ ┣ 📜shot4.svg
 ┃ ┃ ┃ ┃ ┣ 📜star.svg
 ┃ ┃ ┃ ┃ ┗ 📜titleLogo.svg
 ┃ ┃ ┃ ┣ 📂parent
 ┃ ┃ ┃ ┃ ┣ 📂community
 ┃ ┃ ┃ ┃ ┃ ┣ 📜back.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜favor_empty.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜favor_solid.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜like_empty.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜like_solid.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜like_solid.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜search_empty.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜search_solid.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜share.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜write_empty.svg
 ┃ ┃ ┃ ┃ ┃ ┗ 📜write_solid.svg
 ┃ ┃ ┃ ┃ ┣ 📂navbar
 ┃ ┃ ┃ ┃ ┃ ┣ 📜community_off.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜community_on.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜home_off.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜home_on.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜info_off.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜info_on.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜message_off.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜message_on.svg
 ┃ ┃ ┃ ┃ ┃ ┣ 📜nurture_off.svg
 ┃ ┃ ┃ ┃ ┃ ┗ 📜nurture_on.svg
 ┃ ┃ ┃ ┃ ┣ 📜bridgeTalkLogo.svg
 ┃ ┃ ┃ ┃ ┣ 📜dino.svg
 ┃ ┃ ┃ ┃ ┣ 📜edu.svg
 ┃ ┃ ┃ ┃ ┣ 📜empty.svg
 ┃ ┃ ┃ ┃ ┣ 📜heart.svg
 ┃ ┃ ┃ ┃ ┣ 📜homeIcon.svg
 ┃ ┃ ┃ ┃ ┣ 📜noReports.svg
 ┃ ┃ ┃ ┃ ┗ 📜nurture.svg
 ┃ ┃ ┃ ┣ 📂pic
 ┃ ┃ ┃ ┃ ┣ 📜blue.svg
 ┃ ┃ ┃ ┃ ┣ 📜Cercle.png
 ┃ ┃ ┃ ┃ ┣ 📜childBackground.png
 ┃ ┃ ┃ ┃ ┣ 📜childMenu.svg
 ┃ ┃ ┃ ┃ ┣ 📜end.svg
 ┃ ┃ ┃ ┃ ┣ 📜envelop.svg
 ┃ ┃ ┃ ┃ ┣ 📜gameMenu.svg
 ┃ ┃ ┃ ┃ ┣ 📜halongbay.jpg
 ┃ ┃ ┃ ┃ ┣ 📜home3D.svg
 ┃ ┃ ┃ ┃ ┣ 📜mailbox.svg
 ┃ ┃ ┃ ┃ ┣ 📜menu1.png
 ┃ ┃ ┃ ┃ ┣ 📜menu2.png
 ┃ ┃ ┃ ┃ ┣ 📜message.svg
 ┃ ┃ ┃ ┃ ┣ 📜pink.svg
 ┃ ┃ ┃ ┃ ┣ 📜talkBackground.png
 ┃ ┃ ┃ ┃ ┣ 📜talkLogo.svg
 ┃ ┃ ┃ ┃ ┣ 📜talkMenu.svg
 ┃ ┃ ┃ ┃ ┗ 📜주니토니_베트남.png
 ┃ ┃ ┃ ┣ 📜backButton.svg
 ┃ ┃ ┃ ┣ 📜bridgetalk_red.svg
 ┃ ┃ ┃ ┣ 📜cloth.png
 ┃ ┃ ┃ ┣ 📜D1.svg
 ┃ ┃ ┃ ┣ 📜D2.svg
 ┃ ┃ ┃ ┣ 📜D3.svg
 ┃ ┃ ┃ ┣ 📜D4.svg
 ┃ ┃ ┃ ┣ 📜D5.svg
 ┃ ┃ ┃ ┣ 📜D6.svg
 ┃ ┃ ┃ ┣ 📜dino.svg
 ┃ ┃ ┃ ┣ 📜homeIcon.svg
 ┃ ┃ ┃ ┣ 📜ktv.svg
 ┃ ┃ ┃ ┣ 📜letter.svg
 ┃ ┃ ┃ ┣ 📜main_bg.png
 ┃ ┃ ┃ ┣ 📜nexticon.svg
 ┃ ┃ ┃ ┣ 📜nextTriangle.svg
 ┃ ┃ ┃ ┣ 📜parent_bg.png
 ┃ ┃ ┃ ┣ 📜previcon.svg
 ┃ ┃ ┃ ┣ 📜prevTriangle.svg
 ┃ ┃ ┃ ┣ 📜signinicon.svg
 ┃ ┃ ┃ ┣ 📜signupicon.svg
 ┃ ┃ ┃ ┗ 📜vtk.svg
 ┃ ┃ ┣ 📂three
 ┃ ┃ ┃ ┣ 📜armchair_007(Clone).glb
 ┃ ┃ ┃ ┣ 📜Backyard_Tree.glb
 ┃ ┃ ┃ ┣ 📜Backyard_Watering_Can.glb
 ┃ ┃ ┃ ┣ 📜Bathroom2_duck.glb
 ┃ ┃ ┃ ┣ 📜Bedroom_2_Cactus.glb
 ┃ ┃ ┃ ┣ 📜Bedroom_2_Vase.glb
 ┃ ┃ ┃ ┣ 📜Childrens_Room_Horse.glb
 ┃ ┃ ┃ ┣ 📜Childrens_Room_Teddy.glb
 ┃ ┃ ┃ ┣ 📜Childroom2_skateboard.glb
 ┃ ┃ ┃ ┣ 📜closet_003(Clone).glb
 ┃ ┃ ┃ ┣ 📜Dinosaur.glb
 ┃ ┃ ┃ ┣ 📜Duck.glb
 ┃ ┃ ┃ ┣ 📜entertainment_003(Clone).glb
 ┃ ┃ ┃ ┣ 📜entertainment_019(Clone).glb
 ┃ ┃ ┃ ┣ 📜Garage_Car.glb
 ┃ ┃ ┃ ┣ 📜Gnome.glb
 ┃ ┃ ┃ ┣ 📜handheld_01.glb
 ┃ ┃ ┃ ┣ 📜handheld_03.glb
 ┃ ┃ ┃ ┣ 📜Headphones1.glb
 ┃ ┃ ┃ ┣ 📜Joystick.glb
 ┃ ┃ ┃ ┣ 📜Kitchen_Plant3.glb
 ┃ ┃ ┃ ┣ 📜Kitchen_Plant4.glb
 ┃ ┃ ┃ ┣ 📜Kitchen_Vase.glb
 ┃ ┃ ┃ ┣ 📜Livingroom2_Ball.glb
 ┃ ┃ ┃ ┣ 📜Livingroom2_Toy.glb
 ┃ ┃ ┃ ┣ 📜Mailbox.glb
 ┃ ┃ ┃ ┣ 📜Panda_Toy.glb
 ┃ ┃ ┃ ┣ 📜Patio_Bush_1.glb
 ┃ ┃ ┃ ┣ 📜Patio_Bush_2.glb
 ┃ ┃ ┃ ┣ 📜Patio_Gnome.glb
 ┃ ┃ ┃ ┣ 📜Patio_Tea_set.glb
 ┃ ┃ ┃ ┣ 📜Playground_Ball.glb
 ┃ ┃ ┃ ┣ 📜Playground_bucket2.glb
 ┃ ┃ ┃ ┣ 📜Playground_chil_car1.glb
 ┃ ┃ ┃ ┣ 📜Playground_Chil_Car2.glb
 ┃ ┃ ┃ ┣ 📜Playground_figurine_circles2.glb
 ┃ ┃ ┃ ┣ 📜Playground_figurine_cubes.glb
 ┃ ┃ ┃ ┣ 📜Playground_figurine_triangles.glb
 ┃ ┃ ┃ ┣ 📜Playground_rakes.glb
 ┃ ┃ ┃ ┣ 📜Playground_Tree.glb
 ┃ ┃ ┃ ┣ 📜Teddy_bear.glb
 ┃ ┃ ┃ ┣ 📜toy_016(Clone).glb
 ┃ ┃ ┃ ┣ 📜toy_017(Clone).glb
 ┃ ┃ ┃ ┗ 📜toy_036(Clone).glb
 ┃ ┃ ┣ 📜android-chrome-144x144 copy.png
 ┃ ┃ ┣ 📜android-chrome-144x144.png
 ┃ ┃ ┣ 📜android-chrome-192x192.png
 ┃ ┃ ┣ 📜android-chrome-256x256.png
 ┃ ┃ ┣ 📜android-chrome-36x36.png
 ┃ ┃ ┣ 📜android-chrome-384x384.png
 ┃ ┃ ┣ 📜android-chrome-48x48.png
 ┃ ┃ ┣ 📜android-chrome-512x512.png
 ┃ ┃ ┣ 📜android-chrome-72x72.png
 ┃ ┃ ┣ 📜android-chrome-96x96.png
 ┃ ┃ ┣ 📜apple-touch-icon-1024x1024.png
 ┃ ┃ ┣ 📜apple-touch-icon-114x114.png
 ┃ ┃ ┣ 📜apple-touch-icon-120x120.png
 ┃ ┃ ┣ 📜apple-touch-icon-144x144.png
 ┃ ┃ ┣ 📜apple-touch-icon-152x152.png
 ┃ ┃ ┣ 📜apple-touch-icon-167x167.png
 ┃ ┃ ┣ 📜apple-touch-icon-180x180.png
 ┃ ┃ ┣ 📜apple-touch-icon-57x57.png
 ┃ ┃ ┣ 📜apple-touch-icon-60x60.png
 ┃ ┃ ┣ 📜apple-touch-icon-72x72.png
 ┃ ┃ ┣ 📜apple-touch-icon-76x76.png
 ┃ ┃ ┣ 📜apple-touch-icon-precomposed.png
 ┃ ┃ ┣ 📜apple-touch-icon.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1125x2436.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1136x640.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1170x2532.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1179x2556.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1242x2208.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1242x2688.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1284x2778.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1290x2796.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1334x750.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1488x2266.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1536x2048.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1620x2160.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1640x2160.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1668x2224.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1668x2388.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-1792x828.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2048x1536.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2048x2732.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2160x1620.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2160x1640.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2208x1242.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2224x1668.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2266x1488.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2388x1668.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2436x1125.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2532x1170.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2556x1179.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2688x1242.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2732x2048.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2778x1284.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-2796x1290.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-640x1136.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-750x1334.png
 ┃ ┃ ┣ 📜apple-touch-startup-image-828x1792.png
 ┃ ┃ ┣ 📜appLogo.png
 ┃ ┃ ┣ 📜browserconfig.xml
 ┃ ┃ ┣ 📜favicon-16x16.png
 ┃ ┃ ┣ 📜favicon-32x32.png
 ┃ ┃ ┣ 📜favicon-48x48.png
 ┃ ┃ ┣ 📜favicon.ico
 ┃ ┃ ┣ 📜manifest.webmanifest
 ┃ ┃ ┣ 📜mstile-144x144.png
 ┃ ┃ ┣ 📜mstile-150x150.png
 ┃ ┃ ┣ 📜mstile-310x150.png
 ┃ ┃ ┣ 📜mstile-310x310.png
 ┃ ┃ ┣ 📜mstile-70x70.png
 ┃ ┃ ┣ 📜yandex-browser-50x50.png
 ┃ ┃ ┗ 📜yandex-browser-manifest.json
 ┃ ┣ 📜814.ffmpeg.js
 ┃ ┣ 📜blender.png
 ┃ ┣ 📜index.html
 ┃ ┣ 📜manifest.json
 ┃ ┗ 📜maskable_icon_x192.png
 ┣ 📂src
 ┃ ┣ 📂app
 ┃ ┃ ┣ 📜app.tsx
 ┃ ┃ ┣ 📜appPreloader.tsx
 ┃ ┃ ┣ 📜appRoutes.tsx
 ┃ ┃ ┗ 📜serviceWorker.js
 ┃ ┣ 📂assets
 ┃ ┃ ┗ 📂fonts
 ┃ ┃ ┃ ┣ 📜CherryBombOne-Regular.ttf
 ┃ ┃ ┃ ┣ 📜DNFBitBitv2.ttf
 ┃ ┃ ┃ ┣ 📜Pretendard-Black.otf
 ┃ ┃ ┃ ┗ 📜Pretendard-Medium.ttf
 ┃ ┣ 📂pages
 ┃ ┃ ┣ 📂child
 ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┣ 📂decodeFormData
 ┃ ┃ ┃ ┃ ┃ ┗ 📜decodeFormData.ts
 ┃ ┃ ┃ ┃ ┣ 📂getAvgVolume
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getAvgVolume.tsx
 ┃ ┃ ┃ ┃ ┣ 📂getDinoEmotion
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getDinoEmotion.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleTalkEnd
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleTalkEnd.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleTalkSend
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleTalkSend.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleTalkStart
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleTalkStart.ts
 ┃ ┃ ┃ ┃ ┣ 📂webmToMp3
 ┃ ┃ ┃ ┃ ┃ ┗ 📜webmToMp3.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┣ 📂getTalkStart
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getTalkStart.ts
 ┃ ┃ ┃ ┃ ┣ 📂getTalkStop
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getTalkStop.ts
 ┃ ┃ ┃ ┃ ┣ 📂getTalkUpdate
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getTalkUpdate.ts
 ┃ ┃ ┃ ┃ ┣ 📂postMakeReport
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postMakeReport.ts
 ┃ ┃ ┃ ┃ ┣ 📂postSendTalk
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postSendTalk.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂store
 ┃ ┃ ┃ ┃ ┣ 📂useTalkStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useTalkStore.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂ui
 ┃ ┃ ┃ ┃ ┣ 📂game
 ┃ ┃ ┃ ┃ ┃ ┣ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜game.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂draw
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜canvas.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜drawingPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜toolBar.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dress
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜drawParts.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜coloringPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜dressingPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂puzzle
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜stageItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜finishPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜puzzlePage.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜stagePage.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜gamingPage.tsx
 ┃ ┃ ┃ ┃ ┣ 📂talk
 ┃ ┃ ┃ ┃ ┃ ┣ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜messageListItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜cameraControl.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜dino.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜index.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜message.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜messageList.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜talkingComponents.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜messagePage.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜talkingPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜warningPage.tsx
 ┃ ┃ ┃ ┃ ┣ 📜childPage.tsx
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂errorPage
 ┃ ┃ ┃ ┣ 📂ui
 ┃ ┃ ┃ ┃ ┣ 📂errorPage
 ┃ ┃ ┃ ┃ ┃ ┗ 📜errorPage.tsx
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┣ 📂handleFetchProfileList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleFetchProfileList.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleNicknameCheck
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleNIcknameCheck.ts
 ┃ ┃ ┃ ┃ ┣ 📂handlePasswordCheck
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handlePasswordCheck.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleProfileLogin
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleProfileLogin.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleSignin
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleSignin.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleSignup
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleSignup.ts
 ┃ ┃ ┃ ┃ ┣ 📂validateEmail
 ┃ ┃ ┃ ┃ ┃ ┗ 📜validateEmail.ts
 ┃ ┃ ┃ ┃ ┣ 📂validateName
 ┃ ┃ ┃ ┃ ┃ ┗ 📜validateName.ts
 ┃ ┃ ┃ ┃ ┣ 📂validateNickname
 ┃ ┃ ┃ ┃ ┃ ┗ 📜validateNickname.ts
 ┃ ┃ ┃ ┃ ┣ 📂validatePassword
 ┃ ┃ ┃ ┃ ┃ ┗ 📜validatePassword.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┣ 📂deleteDeleteProfile
 ┃ ┃ ┃ ┃ ┃ ┗ 📜deleteDeleteProfile.tsx
 ┃ ┃ ┃ ┃ ┣ 📂getNicknameCheck
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getNicknameCheck.ts
 ┃ ┃ ┃ ┃ ┣ 📂getProfileList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getProfileList.ts
 ┃ ┃ ┃ ┃ ┣ 📂patchEditProfile
 ┃ ┃ ┃ ┃ ┃ ┗ 📜patchEditProfile.tsx
 ┃ ┃ ┃ ┃ ┣ 📂postAddProfile
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postAddProfile.tsx
 ┃ ┃ ┃ ┃ ┣ 📂postProfileLogin
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postProfileLogin.ts
 ┃ ┃ ┃ ┃ ┣ 📂postSignin
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postSignin.ts
 ┃ ┃ ┃ ┃ ┣ 📂postSignup
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postSignup.tsx
 ┃ ┃ ┃ ┃ ┣ 📜index.ts
 ┃ ┃ ┃ ┃ ┣ 📜login.ts
 ┃ ┃ ┃ ┃ ┗ 📜register.ts
 ┃ ┃ ┃ ┣ 📂store
 ┃ ┃ ┃ ┃ ┣ 📂useProfileStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useProfileStore.ts
 ┃ ┃ ┃ ┃ ┣ 📂useSignupStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useSignupStore.ts
 ┃ ┃ ┃ ┃ ┣ 📂useUserStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useUserStore.ts
 ┃ ┃ ┃ ┃ ┣ 📜index.ts
 ┃ ┃ ┃ ┃ ┣ 📜profile.ts
 ┃ ┃ ┃ ┃ ┗ 📜user.ts
 ┃ ┃ ┃ ┣ 📂ui
 ┃ ┃ ┃ ┃ ┣ 📂guard
 ┃ ┃ ┃ ┃ ┃ ┗ 📜loginGuard.tsx
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┗ 📜main.tsx
 ┃ ┃ ┃ ┃ ┣ 📂profile
 ┃ ┃ ┃ ┃ ┃ ┣ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜character.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜dinoSelect.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜profile.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜inputName.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜selectCharacter.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜confirmAccessPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜confirmPassword.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜deleteProfilePage.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜editProfilePage.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜profilePage.tsx
 ┃ ┃ ┃ ┃ ┣ 📂sign
 ┃ ┃ ┃ ┃ ┃ ┣ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜inputEmail.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜inputName.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜selectCountry.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜selectDino.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜signInPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜signUpPage.tsx
 ┃ ┃ ┃ ┃ ┣ 📂start
 ┃ ┃ ┃ ┃ ┃ ┗ 📜startPage.tsx
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂parent
 ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┣ 📂handleNurtureInfoDetail
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleNurtureInfoDetail.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleNurtureInfoList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleNurtureInfoList.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleSearchBoard
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleSearchBoard.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┣ 📂deleteBoardLike
 ┃ ┃ ┃ ┃ ┃ ┗ 📜deleteBoardLike.ts
 ┃ ┃ ┃ ┃ ┣ 📂deleteCommentLike
 ┃ ┃ ┃ ┃ ┃ ┗ 📜deleteCommentLike.ts
 ┃ ┃ ┃ ┃ ┣ 📂getBoardDetail
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getBoardDetail.ts
 ┃ ┃ ┃ ┃ ┣ 📂getBoardLikeCheck
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getBoardLikeCheck.ts
 ┃ ┃ ┃ ┃ ┣ 📂getBoardList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getBoardList.ts
 ┃ ┃ ┃ ┃ ┣ 📂getCommentLikeCheck
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getCommentLikeCheck.ts
 ┃ ┃ ┃ ┃ ┣ 📂getCommentList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getCommentList.ts
 ┃ ┃ ┃ ┃ ┣ 📂getMyBoardList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getMyBoardList.ts
 ┃ ┃ ┃ ┃ ┣ 📂getNurtureInfoDetail
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getNurtureInfoDetail.ts
 ┃ ┃ ┃ ┃ ┣ 📂getNurtureInfoList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getNurtureInfoList.ts
 ┃ ┃ ┃ ┃ ┣ 📂getProfile
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getProfile.ts
 ┃ ┃ ┃ ┃ ┣ 📂getReportDetail
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getReportDetail.tsx
 ┃ ┃ ┃ ┃ ┣ 📂getReportList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getReportList.ts
 ┃ ┃ ┃ ┃ ┣ 📂getReportsReplyList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getReportsReplyList.ts
 ┃ ┃ ┃ ┃ ┣ 📂getSlang
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getSlang.ts
 ┃ ┃ ┃ ┃ ┣ 📂postBoardCreate
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postBoardCreate.ts
 ┃ ┃ ┃ ┃ ┣ 📂postBoardLike
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postBoardLike.ts
 ┃ ┃ ┃ ┃ ┣ 📂postCommentCreate
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postCommentCreate.ts
 ┃ ┃ ┃ ┃ ┣ 📂postCommentLike
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postCommentLike.ts
 ┃ ┃ ┃ ┃ ┣ 📂postVoiceBlob
 ┃ ┃ ┃ ┃ ┃ ┗ 📜postVoiceBlob.tsx
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂store
 ┃ ┃ ┃ ┃ ┣ 📂useBoardStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useBoardStore.ts
 ┃ ┃ ┃ ┃ ┣ 📂useReportStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useReportStore.ts
 ┃ ┃ ┃ ┃ ┣ 📂useVoiceStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜useVoiceStore.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂ui
 ┃ ┃ ┃ ┃ ┣ 📂community
 ┃ ┃ ┃ ┃ ┃ ┣ 📂boardPage
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜boardList.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜boardListItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜input.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜pagenation.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜replyRegist.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜searchTypes.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂items
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜articleListItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜replyListItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜articleList.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜replyCreate.tsx
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜replyList.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜boardDetailPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜boardPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📜createPage.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜updatePage.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parent
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parent.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentInformation
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentInformation.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentInformationMain
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentInformationMain.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentInformationNurture
 ┃ ┃ ┃ ┃ ┃ ┣ 📜parentInformationNurture.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentInformationNurtureDetail.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentInformationWord
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentInformationWordListItem
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentInformationWordListItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentInformationWord.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentMain
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentMain.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentReportDetail
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportDetailRecorder
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportDetailRecorder.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportDetailRecorderButton
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportDetailRecorderButton.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportDetailVolumeChecker
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportDetailVolumeChecker.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportDetail.tsx
 ┃ ┃ ┃ ┃ ┣ 📂parentReportList
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportListItem
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportListItem.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportListLeft
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportListLeft.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportListRight
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportListRight.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂parentReportListWordcloud
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportListWordcloud.tsx
 ┃ ┃ ┃ ┃ ┃ ┗ 📜parentReportList.tsx
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂Test
 ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┣ 📂capturePicture
 ┃ ┃ ┃ ┃ ┃ ┗ 📜capturePicture.ts
 ┃ ┃ ┃ ┃ ┣ 📂coloring
 ┃ ┃ ┃ ┃ ┃ ┣ 📜canvas.ts
 ┃ ┃ ┃ ┃ ┃ ┣ 📜color.ts
 ┃ ┃ ┃ ┃ ┃ ┗ 📜fill.ts
 ┃ ┃ ┃ ┃ ┣ 📂connectCamera
 ┃ ┃ ┃ ┃ ┃ ┗ 📜connectCamera.ts
 ┃ ┃ ┃ ┃ ┣ 📂connectMedia
 ┃ ┃ ┃ ┃ ┃ ┗ 📜connectMedia.ts
 ┃ ┃ ┃ ┃ ┣ 📂connectMicrophone
 ┃ ┃ ┃ ┃ ┃ ┗ 📜connectMicrophone.ts
 ┃ ┃ ┃ ┃ ┣ 📂generateAudioContext
 ┃ ┃ ┃ ┃ ┃ ┗ 📜generateAudioContext.ts
 ┃ ┃ ┃ ┃ ┣ 📂getAudioFrequency
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getAudioFrequency.ts
 ┃ ┃ ┃ ┃ ┣ 📂getAudioInputList
 ┃ ┃ ┃ ┃ ┃ ┗ 📜getAudioInputList.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleStartRecordAudio
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleStartRecordAudio.ts
 ┃ ┃ ┃ ┃ ┣ 📂handleStopRecordAudio
 ┃ ┃ ┃ ┃ ┃ ┗ 📜handleStopRecordAudio.ts
 ┃ ┃ ┃ ┃ ┣ 📂setCharacter
 ┃ ┃ ┃ ┃ ┃ ┣ 📜cube.ts
 ┃ ┃ ┃ ┃ ┃ ┣ 📜sphere.ts
 ┃ ┃ ┃ ┃ ┃ ┗ 📜test.jpg
 ┃ ┃ ┃ ┃ ┣ 📂startRecordVideo
 ┃ ┃ ┃ ┃ ┃ ┗ 📜startRecordVideo.ts
 ┃ ┃ ┃ ┃ ┣ 📂stopRecordVideo
 ┃ ┃ ┃ ┃ ┃ ┗ 📜stopRecordVideo.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┣ 📂examAPI
 ┃ ┃ ┃ ┃ ┃ ┗ 📜examAPI.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂store
 ┃ ┃ ┃ ┃ ┣ 📂countStore
 ┃ ┃ ┃ ┃ ┃ ┗ 📜countStore.ts
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┣ 📂ui
 ┃ ┃ ┃ ┃ ┣ 📂test
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Test.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testCamera
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜drawCloth.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜drawDress.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜drawParts.ts
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testCamera.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testCharacter
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜drawCanvas.ts
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testCharacter.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testColoring
 ┃ ┃ ┃ ┃ ┃ ┣ 📂components
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜canvas.tsx
 ┃ ┃ ┃ ┃ ┃ ┣ 📂util
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜context.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜dispatcher.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜event.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜fill.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜snapshot.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜tool.ts
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜toolType.ts
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testColoring.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testDraw
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testDraw.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testMenuButtons
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testMenuButtons.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testPage
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testPage.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testPuzzle
 ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜drawPose.ts
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testPuzzle.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testVoice
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testVoice.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testWordcloud
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testWordcloud.tsx
 ┃ ┃ ┃ ┃ ┣ 📂testZustand
 ┃ ┃ ┃ ┃ ┃ ┗ 📜testZustand.tsx
 ┃ ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┗ 📜index.ts
 ┃ ┣ 📂shared
 ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┣ 📂connectAudioStream
 ┃ ┃ ┃ ┃ ┗ 📜connectAudioStream.tsx
 ┃ ┃ ┃ ┣ 📂decodeToken
 ┃ ┃ ┃ ┃ ┗ 📜decodeToken.ts
 ┃ ┃ ┃ ┣ 📂errorCatch
 ┃ ┃ ┃ ┃ ┗ 📜errorCatch.ts
 ┃ ┃ ┃ ┣ 📂generateAudioContext
 ┃ ┃ ┃ ┃ ┗ 📜generateAudioContext.ts
 ┃ ┃ ┃ ┣ 📂generateVolumeCheckInterval
 ┃ ┃ ┃ ┃ ┗ 📜generateVolumeCheckInterval.ts
 ┃ ┃ ┃ ┣ 📂getUUIDbyToken
 ┃ ┃ ┃ ┃ ┗ 📜getUUIDbyToken.ts
 ┃ ┃ ┃ ┣ 📂registServiceWorker
 ┃ ┃ ┃ ┃ ┗ 📜registServiceWorker.ts
 ┃ ┃ ┃ ┣ 📂setToken
 ┃ ┃ ┃ ┃ ┗ 📜setToken.ts
 ┃ ┃ ┃ ┣ 📂startRecordVoice
 ┃ ┃ ┃ ┃ ┗ 📜startRecordVoice.ts
 ┃ ┃ ┃ ┣ 📂stopRecordVoice
 ┃ ┃ ┃ ┃ ┗ 📜stopRecordVoice.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂store
 ┃ ┃ ┃ ┣ 📂useErrorStore
 ┃ ┃ ┃ ┃ ┗ 📜useErrorStore.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂types
 ┃ ┃ ┃ ┣ 📂ChildrenProps
 ┃ ┃ ┃ ┃ ┗ 📜ChildrenProps.ts
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂ui
 ┃ ┃ ┃ ┣ 📂backButton
 ┃ ┃ ┃ ┃ ┗ 📜backButton.tsx
 ┃ ┃ ┃ ┣ 📂examUi
 ┃ ┃ ┃ ┃ ┗ 📜examUi.tsx
 ┃ ┃ ┃ ┣ 📂homeButton
 ┃ ┃ ┃ ┃ ┗ 📜homeButton.tsx
 ┃ ┃ ┃ ┣ 📂homeButton2
 ┃ ┃ ┃ ┃ ┗ 📜homeButton2.tsx
 ┃ ┃ ┃ ┣ 📂loading
 ┃ ┃ ┃ ┃ ┣ 📜loadingScreen.tsx
 ┃ ┃ ┃ ┃ ┣ 📜movingScreen.tsx
 ┃ ┃ ┃ ┃ ┗ 📜welcomeScreen.tsx
 ┃ ┃ ┃ ┣ 📂modalSpace
 ┃ ┃ ┃ ┃ ┗ 📜modalSpace.tsx
 ┃ ┃ ┃ ┣ 📂timer
 ┃ ┃ ┃ ┃ ┗ 📜timer.tsx
 ┃ ┃ ┃ ┗ 📜index.ts
 ┃ ┃ ┣ 📂util
 ┃ ┃ ┃ ┣ 📜cookie.ts
 ┃ ┃ ┃ ┣ 📜customAxios.ts
 ┃ ┃ ┃ ┣ 📜dateToString.ts
 ┃ ┃ ┃ ┣ 📜index.ts
 ┃ ┃ ┃ ┗ 📜preload.ts
 ┃ ┃ ┗ 📜index.ts
 ┃ ┣ 📂styles
 ┃ ┃ ┣ 📂app
 ┃ ┃ ┃ ┗ 📜app.style.ts
 ┃ ┃ ┣ 📂child
 ┃ ┃ ┃ ┣ 📂game
 ┃ ┃ ┃ ┃ ┣ 📜dress.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜finish.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜game.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜paint.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜puzzle.style.ts
 ┃ ┃ ┃ ┃ ┗ 📜stage.style.ts
 ┃ ┃ ┃ ┣ 📂talk
 ┃ ┃ ┃ ┃ ┣ 📜message.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜messageList.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜messageListItem.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜messagePage.style.ts
 ┃ ┃ ┃ ┃ ┣ 📜talk.style.ts
 ┃ ┃ ┃ ┃ ┗ 📜talkingPage.style.ts
 ┃ ┃ ┃ ┣ 📜childMain.style.ts
 ┃ ┃ ┃ ┗ 📜common.style.ts
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📜common.style.ts
 ┃ ┃ ┃ ┣ 📜dinoSelect.style.ts
 ┃ ┃ ┃ ┣ 📜editProfilePage.style.ts
 ┃ ┃ ┃ ┣ 📜inputEmail.style.ts
 ┃ ┃ ┃ ┣ 📜inputName.style.ts
 ┃ ┃ ┃ ┣ 📜main.style.ts
 ┃ ┃ ┃ ┣ 📜profilePage.style.ts
 ┃ ┃ ┃ ┣ 📜selectCountry.style.ts
 ┃ ┃ ┃ ┣ 📜selectDino.style.ts
 ┃ ┃ ┃ ┣ 📜signIn.style.ts
 ┃ ┃ ┃ ┣ 📜signup.style.ts
 ┃ ┃ ┃ ┗ 📜start.style.ts
 ┃ ┃ ┣ 📂parent
 ┃ ┃ ┃ ┣ 📜articleListItem.style.ts
 ┃ ┃ ┃ ┣ 📜boardDetailPage.style.ts
 ┃ ┃ ┃ ┣ 📜boardPage.style.ts
 ┃ ┃ ┃ ┣ 📜common.style.ts
 ┃ ┃ ┃ ┣ 📜createPage.style.ts
 ┃ ┃ ┃ ┣ 📜parent.style.ts
 ┃ ┃ ┃ ┣ 📜parentInformationMain.style.ts
 ┃ ┃ ┃ ┣ 📜parentInformationNews.style.ts
 ┃ ┃ ┃ ┣ 📜parentInformationNurturDetail.style.ts
 ┃ ┃ ┃ ┣ 📜parentInformationWord.style.ts
 ┃ ┃ ┃ ┣ 📜parentMain.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportDetail.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportDetailRecorder.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportList.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportListGraph.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportListItem.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportListLeft.style.ts
 ┃ ┃ ┃ ┣ 📜parentReportListRight.style.ts
 ┃ ┃ ┃ ┗ 📜parentReportListWordcloud.style.ts
 ┃ ┃ ┣ 📂shared
 ┃ ┃ ┃ ┣ 📜backButton.style.ts
 ┃ ┃ ┃ ┣ 📜homeButton.style.ts
 ┃ ┃ ┃ ┣ 📜loading.style.ts
 ┃ ┃ ┃ ┣ 📜modalSpace.style.ts
 ┃ ┃ ┃ ┗ 📜timer.style.ts
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┣ 📜test.style.ts
 ┃ ┃ ┃ ┣ 📜testCamera.style.ts
 ┃ ┃ ┃ ┣ 📜testDraw.style.ts
 ┃ ┃ ┃ ┣ 📜testMenuButtons.style.ts
 ┃ ┃ ┃ ┣ 📜testPage.style.ts
 ┃ ┃ ┃ ┣ 📜testVoice.style.ts
 ┃ ┃ ┃ ┣ 📜testWordcloud.style.ts
 ┃ ┃ ┃ ┗ 📜testZustand.style.ts
 ┃ ┣ 📜main.css
 ┃ ┗ 📜main.tsx
 ┣ 📜.gitignore
 ┣ 📜.prettierrc
 ┣ 📜global.d.ts
 ┣ 📜package.json
 ┣ 📜tsconfig.json
 ┣ 📜webpack.common.js
 ┣ 📜webpack.dev.js
 ┣ 📜webpack.prod.js
 ┗ 📜yarn.lock
```

</details>
<details>
<summary> Back 폴더 구조</summary>

```bash
📦bridgetalk-back
 ┣ 📂.gradle
 ┣ 📂.idea
 ┣ 📂build
 ┣ 📂gradle
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂ssafy
 ┃ ┃ ┃ ┃ ┃ ┗ 📂bridgetalkback
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂auth
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenReissueController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefreshToken.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KidsSignupRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentsSignupRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProfileLoginRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AuthErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefreshTokenRedisRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TokenReissueService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthorizationExtractor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtProvider.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂boards
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Boards.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsLike.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsSearchType.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsSortCondition.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsUpdateRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomBoardsListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsListDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsListQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsListQueryRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsLikeRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsLikeService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsListService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂chatgpt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatGptConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatGptRequestCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatGptRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatGptResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Choice.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatGptErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatGptService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂comments
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Comments.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsSortCondition.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsUpdateRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomCommentsListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsListDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsListQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsListQueryRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsListService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂files
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜S3FileController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜S3FileErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜S3FileService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂global
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂annotation
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExtractPayload.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExtractPayloadArgumentResolver.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExtractToken.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExtractTokenArgumentResolver.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AsyncConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CacheConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JpaAuditingConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QueryDslConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RedisConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜S3Config.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SwaggerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiGlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GlobalErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetails.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAccessDeniedHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationEntryPoint.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDetailDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂utils
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EnumConverter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EnumStandard.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordEncoderUtils.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StringListConverter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Language.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂kids
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Kids.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsPassword.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂letters
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Letters.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersImg.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LettersImgResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LettersResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TranscriptionDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TranslationResultsDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LettersErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TranslateBadRequestException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LettersImgRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ClovaSpeechService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LettersService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersTranscribeService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂notification
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationListener.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotificationController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SseController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Notification.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationType.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotificationRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SseRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SseRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotificationService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SseService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂parentingInfo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoCrawlingController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Category.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoBoardNum.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomParentingInfoListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoCrawlingDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂query
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoListDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoQueryRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoBoardNumRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoCrawlingService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoListService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂parents
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProfileController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProfileListController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Email.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Parents.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Password.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Role.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteProfileRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateProfileRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProfileListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProfileResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentsErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentsRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentsFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProfileListService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProfileService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂puzzle
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Puzzle.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PuzzleListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PuzzleFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reports
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TalkController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Reports.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsDetailResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TalkResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TranscriptionDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜VideoResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReportsCreateResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReportsErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReportsRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsFindService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsTranscribeService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsUpdateService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsVideoService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TalkFastApiService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TalkService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂slang
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Slang.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangListResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂translation
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TranslationService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂tts
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TtsErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TtsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BridgetalkBackApplication.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┣ 📜application-secret.yml
 ┃ ┃ ┃ ┗ 📜application.yml
 ┃ ┗ 📂test
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂ssafy
 ┃ ┃ ┃ ┃ ┃ ┗ 📂bridgetalkback
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂auth
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenReissueControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RefreshTokenRedisRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TokenReissueServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂boards
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsLikeServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsListServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardsServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂chatgpt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatGptServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂comment
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsListServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentsServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂comments
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DatabaseCleaner.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂files
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜S3MockConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜S3FileServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂fixture
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardsFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KidsFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentsFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PuzzleFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenFixture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂kids
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜KidsFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂letters
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ClovaSpeechServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LettersServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂notification
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SseControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SseServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂parentingInfo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoCrawlingControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoCrawlingServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentingInfoListServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentingInfoServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂parents
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProfileControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProfileListControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentsTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PasswordTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ParentsRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ParentsFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProfileListServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProfileServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂puzzle
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PuzzleFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PuzzleServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂reports
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TalkControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsFindServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsUpdateServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReportsVideoServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TalkServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂slang
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SlangServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂tts
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TtsServiceTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BridgetalkBackApplicationTests.java
 ┃ ┃ ┃ ┗ 📂global
 ┃ ┃ ┃ ┃ ┗ 📂config
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EmbeddedRedisConfig.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RedisCleaner.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┣ 📂files
 ┃ ┃ ┃ ┃ ┗ 📜test.mp3
 ┃ ┃ ┃ ┗ 📜application.yml
 ┣ 📜.gitignore
 ┣ 📜build.gradle
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┗ 📜settings.gradle
```

</detailS>
<br><br>

# 6️⃣ 기술 스택

<div style="display:flex; flex-direction:column; align-items:flex-start;">
    <p><strong>⚡ Management Tool</stron-g></p>
    <div>
        <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white"> 
        <img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white">  
        <img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white"> 
        <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> 
       <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
       <img src="https://img.shields.io/badge/termius-000000?style=for-the-badge&logo=termius&logoColor=white">
    </div>
    <br>
    <p><strong>⚡ IDE</strong></p>
    <div>
        <img src="https://img.shields.io/badge/vscode 1.85.1-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"> 
        <img src="https://img.shields.io/badge/intellij 2023.3.1-000000?style=for-the-badge&logo=intellijidea&logoColor=white">  
    </div>
    <br>
    <!-- Server -->
    <p><strong>⚡ Server</strong></p>
    <div>
        <img src="https://img.shields.io/badge/ubuntu 20.04.6-E95420?style=for-the-badge&logo=ubuntu&logoColor=white">    
        <img src="https://img.shields.io/badge/nginx 1.18.0-009639?style=for-the-badge&logo=nginx&logoColor=white">
        <img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
        <img src="https://img.shields.io/badge/amazon s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
    </div>
    <br>
    <p><strong>⚡ Infra</strong></p>
    <div>
        <img src="https://img.shields.io/badge/docker 25.04.6-2496ED?style=for-the-badge&logo=docker&logoColor=white">
        <img src="https://img.shields.io/badge/jenkins 2.455-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
        <img src="https://img.shields.io/badge/sonarqube 5.1.16-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white">
    </div>
    <br>
    <!-- Frontend -->
    <p><strong>⚡ Frontend</strong></p>
    <div> 
        <img src="https://img.shields.io/badge/react 18.2.0-61DAFB?style=for-the-badge&logo=react&logoColor=white">
        <img src="https://img.shields.io/badge/typescript 5.4.4-3178C6?style=for-the-badge&logo=typescript&logoColor=white">
        <img src="https://img.shields.io/badge/Zustand 4.5.2-DD0700?style=for-the-badge&logo=Zustand&logoColor=white">
        <img src="https://img.shields.io/badge/webpack 5.91.0-8DD6F9?style=for-the-badge&logo=webpack&logoColor=white">
        <img src="https://img.shields.io/badge/unity 2022.3.28f1-FFFFFF?style=for-the-badge&logo=unity&logoColor=black">
        <img src="https://img.shields.io/badge/react_three_fiber 6.0.13-000000?style=for-the-badge&logo=three.js&logoColor=white">
        <img src="https://img.shields.io/badge/styled_components 6.1.9-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=white">
    </div>
    <br>
    <!-- Backend -->
    <p><strong>⚡ Backend</strong></p>
    <div>
        <img src="https://img.shields.io/badge/Java 17-007396?style=for-the-badge&logo=Java&logoColor=white"> 
        <img src="https://img.shields.io/badge/Spring Boot 3.2.5-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
        <img src="https://img.shields.io/badge/gralde 8.7-02303A?style=for-the-badge&logo=gradle&logoColor=white">
       <img src="https://img.shields.io/badge/spring jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
       <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
       <img src="https://img.shields.io/badge/jwt-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
       <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white">
    </div>
    <br>
    <p><strong>⚡ Database</strong></p>
    <div>
        <img src="https://img.shields.io/badge/mysql 8.0.35-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
        <img src="https://img.shields.io/badge/redis 7.2.4-DC382D?style=for-the-badge&logo=redis&logoColor=white">
    </div>
    <br>
    <!--Alarm-->
    <p><strong>⚡ Alarm</strong></p>
    <div>
        <img src="https://img.shields.io/badge/SSE-FFD500?style=for-the-badge&logo=SSE&logoColor=white">
    </div>
    <br>
    <!--외부 서비스-->
    <p><strong>⚡ 외부 서비스</strong></p>
    <div>
        <img src="https://img.shields.io/badge/CHATGPT-179287?style=for-the-badge&logo=chatbot&logoColor=white">
        <img src="https://img.shields.io/badge/google cloud-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white">
        <img src="https://img.shields.io/badge/CLOVA-00DC82?style=for-the-badge&logo=x&logoColor=white">
        <img src="https://img.shields.io/badge/PAPAGO-50B0E9?style=for-the-badge&logo=parrotsecurity&logoColor=white">
        <img src="https://img.shields.io/badge/youtube-FF0000?style=for-the-badge&logo=youtube&logoColor=white">
        <img src="https://img.shields.io/badge/MEDIAPIPE-005571?style=for-the-badge&logo=mural&logoColor=white">
    </div>
    <br>
</div>
<br><br>

## 협업 환경

```markdown
- Gitlab
    - 코드 버전 관리
    - 기능별, 이슈별 브랜치를 생성해서 진행
    - MergeRequest를 통해 서로 코드 리뷰를 해서 merge 진행
	    - dto에 필요한 추가 변수, 테스트코드 오류, css 깨짐, typescript 오류 부분 등 확인하며 코드 리뷰 진행
- JIRA
    - 매주 월요일 오전 목표량을 정해 스프린트 진행
    - 이슈 별 해야하는 업무를 자세히 작성하고, Story Point와 Epic을 설정하여 작업
- 데일리 스크럼
    - 매주 오전 데일리 스크럼을 진행하여 서로 간의 진행 상황 공유 및 그날 진행할 업무 브리핑
    - 매일 소통하면서 새로 생기는 이슈에 빠른 대응 가능해짐
- Notion
    - 회고록 및 회의록이 있을 때마다 매번 기록하여 보관
    - 참고할 기술, 자료 확보 시 모두가 볼 수 있도록 공유
    - 컨벤션 정리 (git 등)
    - 문서 정리 (요구사항 명세서, ERD 명세서, API 명세서)
    - 백엔드, 프론트엔드 각각 페이지를 구성해서 실행 과정 정리, secret.yml 파일, .env 파일 등 공유
```

<br><br>

# 7️⃣ 문서

## 📂[ERD](https://www.notion.so/ERD-f84db9c62aa049b69c3f5ed78cd5d0ed)

## 📂[와이어프레임](https://www.figma.com/files/project/222942611/Team-project?fuid=958981197957446535)

## 📂[기능명세서](https://www.notion.so/2024-04-23-10-40-1fba7cf99bec45409630948c8ae28392)

## 📂[API 명세서](https://www.notion.so/API-2024-05-19-09-00-6a44ff0fdc354754b975bcfff039a3f8)

## 📂포팅메뉴얼
> exec/bridgetalk_포팅 메뉴얼.pdf

<br><br>

# 8️⃣ 팀 소개
| 이름 | 역할 |
| --- | --- |
| 윤선경 (팀장) |- BackEnd<br> - 전역  예외 처리  및 **테스트 코드 틀 구성** 및 작성<br> - 스프링시큐리티, **JWT를 사용한 회원 인가/인증 구현**<br> - 리프레시토큰 및 이메일 인증번호 등 일정 **휘발성 데이터 redis를 활용해서 처리**<br> - **생성형 AI를 활용**한 캐릭터 대화 구현<br> - **querydsl을 활용**한 조회 및 부모 커뮤니티 기능 구현<br> - 육아 정보 크롤링 및 번역 구현<br> - **ssl 인증** 및 프록시로 **nginx 분기 처리** ( / , /api ) |
| 방소영 |- BackEnd<br> - **텍스트 음성 변환** 시스템 구현<br> - **대화 내용 관리를 위한 Redis 및 MySQL 활용**<br> - **실시간 대화 내용을 Redis에 임시 저장**하고, 검증된 내용만을 MySQL 데이터베이스에 영구적으로 저장하는 프로세스 구현<br> - Spring Boot 프레임워크 내에서 **캐싱 기능을 활용**하여 한국어 줄임말 목록의 **조회 속도개선**<br> - 편지 내용 및 관련 이미지 반환 기능 구현<br> - UCC 촬영 및 편집|
| 이현영 |- BackEnd<br> - **EC2 서버 및 Jenkins CI/CD 구축**<br> - 아이 **대화내용 요약 및 키워드 추출, 솔루션 제시**, **2개 국어 번역**<br> - 비동기처리를 통해 응답속도 개선<br> - 아이 대화내용 기반 커뮤니티 글 CRUD, 커뮤니티 게시글 댓글 CRUD 구현<br> - **Mattermost 알림채널 개설**(Gitlab Merge Request, Jenkins Build 결과 알림기능)|
| 이지영 |- **음성 텍스트 변환** 시스템 구현<br> - 캐릭터와의 **대화시간 최적화를 위한 api 테스트 진행** (Amazon Transcribe, Clova, Whisper AI)<br> - 번역 API 구현(Papago api 및 생성형 AI 활용)<br> - **캐릭터 답장 음성 파일 및 자막 생성**<br> - **SSE** 알림 설정 |
| 조한빈 |- 메인 페이지, 부모 페이지 화면 및 기능<br> - manifest 작성을 통한 **PWA** 환경 구현<br> - webpack 설정을 통한 프로젝트 **번들링**<br> - 서비스 워커를 활용한 **정적 데이터 캐싱**<br> - 에셋 **프리로드**를 통한 초기 사용성 개선|
|박시연|- 3D 캐릭터 구현부 전반<br> - **Unity Editor, Blender, Three.js 사용하여 Animated 3D Asset 출력 및 상호작용 구현**<br> - 아이 페이지 디자인 전반<br> - [받은 편지함] : 편지 리스트, 편지 음성, 편지 아이콘, 편지 내용 받아오기<br> -  [퍼즐 게임]: 나라별 퍼즐 리스트 및 퍼즐 상세 받아오기, 퍼즐 로직 구현<br> -  [옷 입히기 게임 필터]: **mediapipe 사용하여 카메라 의상 필터 구현**<br>|
    
<br><br>

# 9️⃣ 실행 화면
- 로그인/회원가입
    - 이메일 인증 후 회원 가입 진행
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/4c6a8188-7135-4a52-b9ce-6195600ace2e" width="650px" height="400px">
        
    - 로그인
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/4ea1242f-ba78-4142-b589-362d460042bc" width="650px" height="400px">
        
    - 아이 프로필 생성
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/cad27ba9-b69c-4d2d-85a7-07417a183190" width="650px" height="400px">
        
    - ott의 멀티 프로필 형태로 프로필 제공
    - 각자의 프로필은 비밀번호로 입력 후 접속 가능
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/1b88c772-b0b3-40e7-9244-75bf55732d63" width="650px" height="400px">
        
    - 부모 프로필은 우상단 설정 메뉴를 통해 접속 가능
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/3677a56f-61a9-415c-a0eb-8854bc0b1884" width="650px" height="400px">
        
- 아이
    - 공룡 친구와 대화하기
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/be0656c5-7497-465c-b625-fdc9d50be945" width="650px" height="400px">
        
        - 음성을 인식하여 음성에 대답이 없을 때, 자동으로 서버에 답장 요청
        - stt → 생성형AI를 활용한 답변 생성 → tts
        - 답변에서 감정을 추출하여 공룡이 감정을 표현하도록 구현
    - 게임하기
        - 퍼즐
            
            <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/1f921679-b8d4-482e-b6d9-4a0627dbabc1" height="400px">
            
            - 어머니의 모국에 있는 랜드마크들을 퍼즐 형태로 제공
            - 퍼즐 성공 시 해당 랜드마크에 대한 설명 제공
        - 전통복 입기, 사진찍기
            
            <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/d29b2b79-e1fc-4212-9ff8-52e9364026dd" width="650px" height="400px">
            
            - 어머니의 모국에 있는 전통복 입기
            - on-device AI 기술인 미디어 파이프를 활용하여 사용자의 신체를 실시간으로 인식하여 옷 입기 진행
            - 인증샷 촬영 가능
    - 편지함
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/8a7be531-ce54-4ad8-861b-1e420c2eb187" width="650px" height="400px">
        
        - 부모님이 모국어로 남긴 편지가 아이에게 한국어로 공룡 친구의 목소리로 들려줌
- 부모 (한국어, 베트남어, 필리핀어 지원)
    - 레포트 및 솔루션
        
        <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/dc665b8c-b23c-4f6b-beb7-c3b6cd06c941" width="650px" height="400px">
        
        - 아이가 대화하기를 마치면 생성형 AI를 활용 → 대화 요약 & 키워드 추출해 제공
        - 아이의 이야기에 대한 답변을 추천
        - 모국어로 아이에게 편지 남기기
    - 육아 정보 제공
        - 실제로 여성 가족부에 올라온 자녀연령대별 육아 정보 제공
            
            <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/1a8c85c9-3758-41c0-801b-1f7d9f48acc5" width="650px" height="400px">
            
    - 한국어 줄임말 제공
        - 한국 부모가 사용하는 신조어 및 줄임말 제공
            
            <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/01fb3fc1-4ff3-4a3a-b632-ac23b74bab63" width="650px" height="400px">
            
    - 커뮤니티
        - 이주민 가정의 어머니들을 위한 소통 공간
            
            <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/83395092-bf70-4690-b2e5-66964b2d8398" width="650px" height="400px">
            
        - 레포트를 기반으로 게시글 작성
            
            <img src="https://github.com/Sunkyoung-Yoon/SunKyoung-Yoon/assets/97610532/d84cb9fb-b8f3-4238-a682-25526b1a4f39" width="650px" height="400px">