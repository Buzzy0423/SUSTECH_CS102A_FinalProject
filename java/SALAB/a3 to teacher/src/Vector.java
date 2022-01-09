public class Vector {
    private int x;
    private int y;
    private int z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * return the quadrant of vector
     *
     * @return the value of return should be 1 to 8
     */
    public int quadrant() {
        if (x == 0 || y == 0 || z == 0) {
            return -1;
        }
        if (x > 0) {
            if (y > 0) {
                return z > 0 ? 1 : 5;
            } else {
                return z > 0 ? 4 : 8;
            }
        } else {
            if (y > 0) {
                return z > 0 ? 2 : 6;
            } else {
                return z > 0 ? 3 : 7;
            }
        }
    }

    public double modulus() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Vector add(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    public double area(Vector vector) {
        return area(this, vector);
    }

    // by three sides. Only for testing the accuracy of the method area
    public static double area2(Vector a, Vector b) {
        double s1 = a.modulus();
        double s2 = b.modulus();
        double s3 = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
        double p = 0.5 * (s1 + s2 + s3);
        return Math.sqrt(p * (p - s1) * (p - s2) * (p - s3));
    }

    // by cross product
    public static double area(Vector a, Vector b) {
        Vector v = new Vector(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
        return 0.5 * v.modulus();
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public String toString() {
        return String.format("(%d,%d,%d)", x, y, z);
    }

    public static void main(String[] args) {
        Vector v1 = new Vector(1, -2, 3);
        Vector v2 = new Vector(-5, 2, 2);
        System.out.println("quadrant of v1 is " + v1.quadrant());
        System.out.println("quadrant of v2 is " + v2.quadrant());
        System.out.printf("Area %.5f\n", v1.area(v2));
        System.out.printf("Area %.5f\n", Vector.area(v1, v2));
        System.out.println(v1.add(v2));
        System.out.println(Vector.add(v1, v2));
    }
}
