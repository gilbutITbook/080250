# 12장 예제

12장은 짜뱌-코틀린 상호 운용을 보여주기 위해 여러가지 인텔리J IDEA 프로젝트로 구성되어 있습니다. 각각은 다음과 같습니다.

- PersonProperty : 코틀린에서 자바 합성 프로퍼티 사용하기 (Person 클래스(자바)와 코틀린 main())
- PersonPropertyNull: 코틀린에서 자바 사용하기
    - PersonMain.kt : NotNull 애너테이션
	- Collection.kt : 자바 배열 입력 함수에게 코틀린 배열 전달할 때 공변성
	- SAM.kt : executor에게 Runnable SAM 인터페이스를 상속한 객체 역할을 하는 람다 넘기기
	- Callable.kt : Callable 자바 SAM 안터페이스 대신 람다를 사용하기
	- FunInterface.kt : 코틀린 함수형 인터페이스
- JavaPersonProperty : 자바에서 코틀린 사용하기
    - Main.java : 자바에서 접근자 메서드를 통해 코틀린 프로퍼티에 접근하기
    - MainJvmField.java : 코틀린 @JvmField가 붙은 프로퍼티를 자바에서 필드로 접근하기
	- Application.java : 코틀린 싱글턴 객체에 정의된 @JvmField
    - Util.kt와 UtilMain.java : 코틀린 최상위 수준 선언과 퍼사드 클래스 
- JvmName : 여러 코틀린 최상위 선언을 정해진 파일에 모으기
- JvmStatic : 객체 함수나 프로퍼티를 정적 메서드로 만들기
- JvmNameAvoidNameCollision : @JvmName을 사용한 이름 충돌 회피
    - Person.kt, PersonMain.java : @JvmName을 사용해 이름 충돌피하기
	- Person2.kt, Person2Main.java : @JvmName으로 세터 메서드 이름 바꾸기
	- Person3.kt, Person3Main.java : goto라는 이름을 자바에서는 다른 이름으로 사용하기
- JvmOverloads - JvmOverloads 애너테이션을 통해 디폴트값을 지정한 코틀린 함수에 대해 여러가지 자바 함수 오버로딩하기
- Type : 구체화한 타입이 있는 인라인 함수(컴파일 오류)와 타입 별명
    - Reified.kt, ReifiedMain.java
	- TypeAlias.kt, TypeAlias.java
	