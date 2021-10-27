public class ReifiedMain {
    public static void main(String[] args) {
        System.out.println(ReifiedKt.normalInlineFunction("Test"));
        //ReifiedKt.<Integer>cast(""); // error: Cannot resolve method 'cast' in 'ReifiedKt'
    }
}
