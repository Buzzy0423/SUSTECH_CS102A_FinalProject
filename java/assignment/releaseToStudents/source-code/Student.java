/**
 * - Do not modify or remove any methods or fields that have been already defined.
 * - You can add other methods or attributes that you think are necessary (Like getting and setting methods).
 */
public abstract class Student {
    private int number;
    private int college;

    public abstract boolean checkGraduate();

    @Override
    public String toString() {
        return String.format("%d-%d", this.number, this.college);
    }
}
