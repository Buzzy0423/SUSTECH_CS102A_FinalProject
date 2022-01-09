/**
 * - Do not modify or remove any methods or fields that have been already defined.
 * - You can add other methods or attributes that you think are necessary (Like getting and setting methods).
 */
public class Course {
    private String courseNumber;
    private CourseType courseType;
    private int credit;

    @Override
    public String toString() {
        return "Course{" +
                "courseNumber='" + courseNumber + '\'' +
                ", courseType=" + courseType +
                ", credit=" + credit +
                '}';
    }
}
