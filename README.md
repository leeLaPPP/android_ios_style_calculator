# 안드로이드 계산기 앱

## 프로젝트 소개
이 프로젝트는 안드로이드 스튜디오를 사용하여 개발된 기본적인 계산기 애플리케이션입니다. iOS 스타일의 디자인을 적용하여 깔끔하고 직관적인 사용자 인터페이스를 제공합니다.

## 주요 기능
- 기본 사칙연산 (덧셈, 뺄셈, 곱셈, 나눗셈)
- 소수점 계산 지원
- 양수/음수 전환 (+/-)
- 퍼센트 계산
- 최대 12자리 숫자 입력 제한
- 결과값 천 단위 구분자 표시

## 기술 스택
- Language: Java
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 34 (Android 14)
- UI Framework: Android Material Design

## 화면 구성
- 상단: 계산 결과 표시 영역
- 하단: 숫자 및 연산자 버튼
  - 숫자 버튼 (0-9)
  - 연산자 버튼 (+, -, ×, ÷)
  - 기능 버튼 (AC, +/-, %, .)

## 사용된 주요 컴포넌트
- ConstraintLayout: 전체 레이아웃 구성
- TableLayout: 버튼 그리드 배치
- EditText: 계산 결과 표시
- Material Design Button: 계산기 버튼

## 코드 구조
- `MainActivity.java`: 메인 액티비티 및 계산기 로직 구현
- `activity_main.xml`: UI 레이아웃 정의
- `themes.xml`: 앱 테마 및 스타일 정의
- `colors.xml`: 색상 리소스 정의

## 주요 기능 구현
1. 숫자 입력 처리
   - 최대 12자리 제한
   - 소수점 입력 지원
2. 연산 처리
   - 사칙연산 구현
   - 0으로 나누기 예외 처리
3. 결과 표시
   - 천 단위 구분자 적용
   - 소수점 10자리까지 표시

## 빌드 및 실행
1. Android Studio에서 프로젝트를 엽니다.
2. Gradle 동기화를 완료합니다.
3. 에뮬레이터 또는 실제 기기에서 실행합니다.

## 라이선스
이 프로젝트는 MIT 라이선스를 따릅니다. 

5/15 Renamed several variables for better readability.