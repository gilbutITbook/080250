public class UtilMail {
    public static void main(String[] args) {
        UtilPerson person = UtilKt.readPerson();
        if (person == null) return;
        System.out.println(UtilKt.getFullName(person));
    }
}
