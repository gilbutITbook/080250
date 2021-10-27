public class ApplicationMain {
    public static void main(String[] args) {
        System.out.println(Application.name); // 정적 필드
        System.out.println(Application.INSTANCE.getName2()); // 인스턴스 필드
        System.out.println(Application.otherName); // 상수는 자동으로 정적필드
        System.out.println(Application.lateName); // lateinit 필드는 자동으로 정적필드를 생성해줌. 초기화되지 않았으므로 null 출력
        Application.INSTANCE.setLateName("Haha");
        System.out.println(Application.INSTANCE.getLateName()); // Haha
        System.out.println(Application.lateName); // Haha
    }
}

