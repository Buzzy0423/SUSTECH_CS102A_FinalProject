
public class Lab3_E2 {
    public static void main(String[] args) {
        float gpa;
        float score = Float.parseFloat(args[0]);

        if(score >= 60)
            System.out.println("You passed the exam.");
        else
            System.out.println("You failed in the exam.");

        if(score >= 90)
            gpa = (float) 4.0;
        else if(score >= 80)
            gpa = 3.0f;
        else if(score >= 70)
            gpa = 2.0f;
        else if(score >= 60)
            gpa = 1.0f;
        else if(score >  0)
            gpa = 0.0f;
        else
        {
            System.out.println("Invalide grade");
            return;
        }
        System.out.printf("Your score is %.1f, the GPA is %.1f\n", score, gpa);
    }
}
