public class Main {
    public static void main(String[] args) {
        Person person = new Person("John", 25, false);
        System.out.println(person.getAge()); // 25

        person.setName("Harry");
        System.out.println(person.getName()); // Harry

        person.setEmployed(true);
        System.out.println(person.isEmployed()); // true
    }
}
