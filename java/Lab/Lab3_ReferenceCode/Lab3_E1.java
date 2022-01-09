

public class Lab3_E1 {
    public static void main(String[] args) {
        String name = args[0];
        int age = Integer.parseInt(args[1]);
        float weight = Float.parseFloat(args[2]);
        char grade = args[3].charAt(0);

        System.out.printf("You are %s.\nYou are %d years old.\n", name, age);
        System.out.printf("You weigh %.1f KG.\nThe highest grade you got is %c.\n", weight, grade);
    }
}
