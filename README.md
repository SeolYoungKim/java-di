# 만들면서 배우는 스프링
[Next Step - 과정 소개](https://edu.nextstep.camp/c/4YUvqn9V)

## DI 컨테이너 구현하기

### 학습목표
- DI 컨테이너 구현을 통해 내부 동작 원리를 이해한다.
- IoC와 DI의 이해도를 높인다.

### 시작 가이드
1. 이전 미션에서 진행한 코드를 사용하고 싶다면, 마이그레이션 작업을 진행합니다.
2. 학습 테스트는 강의 시간에 풀어봅시다.
3. LMS의 1단계 미션부터 진행합니다.

### 준비 사항
- IntelliJ에 Kotest 플러그인 설치

## 학습 테스트
- 스프링 IoC 컨테이너에 대해 좀 더 자세히 알아봅시다.
- 실패하는 학습 테스트를 통과시키시면 됩니다.
- 학습 테스트는 ioc 패키지 또는 클래스 단위로 실행하세요.

1. [스프링 IoC 컨테이너와 Bean 소개](study/src/test/kotlin/ioc/Introduction.kt)
2. [컨테이너 개요](study/src/test/kotlin/ioc/Container.kt)
3. [Bean 개요](study/src/test/kotlin/ioc/Bean.kt)
4. [의존성(Dependencies)](study/src/test/kotlin/ioc/Dependencies.kt)
5. [Bean 스코프](study/src/test/kotlin/ioc/BeanScopes.kt)
6. [Bean의 라이프 사이클](study/src/test/kotlin/ioc/Lifecycle.kt)
7. [어노테이션 기반 컨테이너 구성](study/src/test/kotlin/ioc/AnnotationBasedConfiguration.kt)
8. [자바 기반 컨테이 구성](study/src/test/kotlin/ioc/JavaBasedConfiguration.kt)

### 싱글톤 스코프
<img src="docs/images/singleton.png" alt="singleton">

### 프로토타입 스코프
<img src="docs/images/prototype.png" alt="prototype">


## 요구사항 정리 
### 🚀 1단계 - DI 컨테이너 구현하기
- [x] @Controller, @Service, @Repository 를 스캔해서 인스턴스를 생성하고 등록한다 
- [x] 의존관계 주입에는 @Autowired 를 사용한다 
  - 위 세 개의 설정으로 생성된 인스턴스 간의 의존관계를 설정해야 한다. 
- [x] DefaultListableBeanFactoryTest 를 통과해야 한다 
- [x] MVC 프레임워크와 통합 
  - [x] @Controller, @Service, @Repository에 대해 지원이 가능하도록 개선한다
  - 아래 요구사항은 HandlerExecutionFactory가 어울린다고 생각해서 제 기준대로 변경하였습니다. 
  - [x] ControllerScanner를 DI 컨테이너가 있는 패키지로 옮긴다
    - 기존 ControllerScanner를 HandlerExecutionFactory로 변경하고 이를 생성하는 책임 부여 
  - [x] 클래스 이름을 BeanScanner로 변경한다 
    - Bean을 스캔하는 BeanScanner는 따로 구현하였음 
  - [x] AnnotationHandlerMapping이 BeanFactory와 BeanScanner를 활용해 동작하도록 변경한다
    - ApplicationContext를 활용해 동작하도록 변경하였음 

