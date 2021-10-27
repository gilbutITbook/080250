public class PersonMain {
    public static void main(String[] args) {
        Person person = new Person("John", "Doe");
        System.out.println(person.getFamilyName());
        System.out.println(person.getFirstName());
        System.out.println(PersonKt.getFullName(person));            // 확장 메서드는 정적 함수로 호출해야 한다
        System.out.println(PersonKt.getFullNameFamilyFirst(person)); // 최상위 메서드도 정적 함수로 호출해야 한다
        System.out.println(PersonKt.getFullNameFamilyLast(person));  // 확장 메서드는 정적 함수로 호출해야 한다
        System.out.println(PersonKt.getFullNameFamilyLast2(person)); // 확장 메서드는 정적 함수로 호출해야 한다 
    }
}
