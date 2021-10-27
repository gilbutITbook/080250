public class MyUtilsMain {
    public static void main(String[] args) {
        Person person = MyUtils.readPerson();
        if (person == null) return;
        System.out.println(MyUtils.getFullName(person));
    }
}
