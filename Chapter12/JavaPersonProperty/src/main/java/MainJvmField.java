public class MainJvmField {
    public static void main(String[] args) {
        PersonJvmField person = new PersonJvmField("John", 25);
        System.out.println(person.age); // 25

        person.name = "Harry";
        System.out.println(person.name); // Harry
    }
}
