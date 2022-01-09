public class test {
    public static void main(String[] args) {
        GradeSystem g = new GradeSystem();
        Course c= new Course("JavaA");
        Student s = new Student("Zhangwh");
        g.addStudent(s);
        g.addCourse(c);
        g.addGrade(new Grade(c,s,99));
        System.out.println(c);
        System.out.println(s);
        System.out.println(g.getGradeList().get(0));
    }
}
public class Student {
    private int sID;
    private String name;
    private static int student_Cnt;
    public Student(String name){

    }
    public int getsID(){
        return sID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setsID(int sID){

    }
    public static int getStudent_Cnt(){
        return student_Cnt;
    }
    public String toString(){
        System.out.printf("Student: %s, sID:%d",name,sID);
    }
}
