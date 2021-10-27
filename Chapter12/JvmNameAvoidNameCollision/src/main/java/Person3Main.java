public class Person3Main {
    public static void main(String[] args) {
        Person3 person = new Person3("John", "Doe");
        Person3 daughter = new Person3("Jane", "Doe");
        person.visit(daughter);
    }
}
