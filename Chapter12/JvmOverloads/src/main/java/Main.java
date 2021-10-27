/* // 디폴트 값을 호출시 제공하지 않아 생기는 오류
public class Main {
    public static void main(String[] args) {
        System.out.println(UtilKt.restrictToRange(100, 1, 10));
        System.out.println(UtilKt.restrictToRange(100, 1)); // Error
        System.out.println(UtilKt.restrictToRange(100));    // Error
    }
}
*/

public class Main {
    public static void main(String[] args) {
        System.out.println(UtilKt.restrictToRange2(100, 1, 10)); // 10
        System.out.println(UtilKt.restrictToRange2(100, 1));     // 100
        System.out.println(UtilKt.restrictToRange2(100));        // 100
    }
}
