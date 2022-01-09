import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class OfficialJudgeTest {

    public String message = "Wrong answer";

    @Test
    public void test01_class() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            findVector();
        });
    }

    @Test
    public void test02_attributes() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            findX();
            findY();
            findZ();
        });
    }

    @Test
    public void test03_constructor() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            findConstructor(findVector(), Modifier.PUBLIC, int.class, int.class, int.class);
        });
    }

    @Test
    public void test04_methods() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            findQuadrant();
            findModulus();
            findToString();
            findAdd();
            findArea();
            findStaticAdd();
            findStaticArea();
        });
    }

    @Test
    public void test05_quadrant_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

            assertEquals(1, callQuadrant(newVector(1, 2, 3)), message);
            assertEquals(2, callQuadrant(newVector(-1, 2, 3)), message);
            assertEquals(3, callQuadrant(newVector(-1, -2, 3)), message);
            assertEquals(4, callQuadrant(newVector(1, -2, 3)), message);
            assertEquals(5, callQuadrant(newVector(1, 2, -3)), message);
            assertEquals(6, callQuadrant(newVector(-1, 2, -3)), message);
            assertEquals(7, callQuadrant(newVector(-1, -2, -3)), message);
            assertEquals(8, callQuadrant(newVector(1, -2, -3)), message);

            assertEquals(-1, callQuadrant(newVector(0, 2, 3)), message);
            assertEquals(-1, callQuadrant(newVector(1, 0, 3)), message);
            assertEquals(-1, callQuadrant(newVector(1, 2, 0)), message);
            assertEquals(-1, callQuadrant(newVector(1, 0, 0)), message);
            assertEquals(-1, callQuadrant(newVector(0, 2, 0)), message);
            assertEquals(-1, callQuadrant(newVector(0, 0, 3)), message);
            assertEquals(-1, callQuadrant(newVector(0, 0, 0)), message);
        });
    }

    @Test
    public void test06_quadrant_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE;
            assertEquals(1, callQuadrant(newVector(max, max, max)), message);
            assertEquals(2, callQuadrant(newVector(min, max, max)), message);
            assertEquals(3, callQuadrant(newVector(min, min, 3)), message);
            assertEquals(4, callQuadrant(newVector(1, min, 3)), message);
            assertEquals(5, callQuadrant(newVector(1, max, min)), message);
            assertEquals(6, callQuadrant(newVector(min, max, min)), message);
            assertEquals(7, callQuadrant(newVector(min, min, min)), message);
            assertEquals(8, callQuadrant(newVector(1, min, min)), message);

            assertEquals(-1, callQuadrant(newVector(0, 2, min)), message);
            assertEquals(-1, callQuadrant(newVector(1, 0, 3)), message);
            assertEquals(-1, callQuadrant(newVector(1, max, 0)), message);
            assertEquals(-1, callQuadrant(newVector(1, min, 0)), message);
            assertEquals(-1, callQuadrant(newVector(0, 2, 0)), message);
            assertEquals(-1, callQuadrant(newVector(0, 0, min)), message);
            assertEquals(-1, callQuadrant(newVector(0, 0, 0)), message);
        });
    }

    @Test
    public void test07_modulus_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(0.0, callModulus(newVector(0, 0, 0)), 0.0001, message);
            assertEquals(1.0, callModulus(newVector(1, 0, 0)), 0.0001, message);
            assertEquals(1.41421356, callModulus(newVector(1, 1, 0)), 0.0001, message);
            assertEquals(1.73205080, callModulus(newVector(1, 1, 1)), 0.0001, message);
            assertEquals(3.74165738, callModulus(newVector(1, 2, -3)), 0.0001, message);
            assertEquals(3.74165738, callModulus(newVector(1, -2, -3)), 0.0001, message);
        });
    }

    @Test
    public void test08_modulus_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(6819.604533988756, callModulus(newVector(234, 645, -6785)), 0.0001, message);
            assertEquals(542.2416435501796, callModulus(newVector(535, 76, 45)), 0.0001, message);
            assertEquals(57.15767664977295, callModulus(newVector(33, 33, 33)), 0.0001, message);
            assertEquals(896.7212498876114, callModulus(newVector(-56, 867, 222)), 0.0001, message);
            assertEquals(2.147483647E9, callModulus(newVector(0, 0, Integer.MAX_VALUE)), 0.0001, message);
            assertEquals(5.676575736069014E8, callModulus(newVector(567657568, -867, -79780)), 0.0001, message);
        });
    }

    @Test
    public void test09_toString_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals("(0,0,0)", callToString(newVector(0, 0, 0)), message);
            assertEquals("(1,0,0)", callToString(newVector(1, 0, 0)), message);
            assertEquals("(1,1,0)", callToString(newVector(1, 1, 0)), message);
            assertEquals("(1,1,1)", callToString(newVector(1, 1, 1)), message);
            assertEquals("(1,2,-3)", callToString(newVector(1, 2, -3)), message);
            assertEquals("(1,-2,-3)", callToString(newVector(1, -2, -3)), message);
        });
    }

    @Test
    public void test10_toString_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals("(2147483647,2147483647,0)", callToString(newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, 0)), message);
            assertEquals("(1,-2147483648,0)", callToString(newVector(1, Integer.MIN_VALUE, 0)), message);
            assertEquals("(1,1,-2147483648)", callToString(newVector(1, 1, Integer.MIN_VALUE)), message);
            assertEquals("(1,1,124235)", callToString(newVector(1, 1, 124235)), message);
            assertEquals("(999,999,998)", callToString(newVector(999, 999, 998)), message);
            assertEquals("(123,-456,789)", callToString(newVector(123, -456, 789)), message);
        });
    }

    @Test
    public void test11_add_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertVectorEquals(
                    newVector(2, 4, 6),
                    callAdd(
                            newVector(1, 2, 3),
                            newVector(1, 2, 3)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(1, 2, 3),
                    callAdd(
                            newVector(0, 0, 0),
                            newVector(1, 2, 3)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(0, 0, 0),
                    callAdd(
                            newVector(1, 2, 3),
                            newVector(-1, -2, -3)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(5, 3, -3),
                    callAdd(
                            newVector(1, -2, 3),
                            newVector(4, 5, -6)
                    ),
                    message
            );
        });
    }

    @Test
    public void test12_add_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertVectorEquals(
                    newVector(10000, 10000, 10000),
                    callAdd(
                            newVector(5000, 5000, 5000),
                            newVector(5000, 5000, 5000)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
                    callAdd(
                            newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
                            newVector(0, 0, 0)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
                    callAdd(
                            newVector(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1),
                            newVector(1, 1, 1)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE),
                    callAdd(
                            newVector(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1),
                            newVector(-1, -1, -1)
                    ),
                    message
            );
        });
    }

    @Test
    public void test13_area_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(
                    0.5,
                    callArea(
                            newVector(1, 0, 0),
                            newVector(0, 1, 0)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    0.86602540,
                    callArea(
                            newVector(1, 1, 0),
                            newVector(0, 1, 1)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    0.0,
                    callArea(
                            newVector(0, 0, 0),
                            newVector(1, 1, 1)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    11.20267825,
                    callArea(
                            newVector(1, 2, -3),
                            newVector(4, -5, 6)
                    ),
                    0.0001,
                    message
            );
        });
    }

    @Test
    public void test14_area_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(
                    57087.5,
                    callArea(
                            newVector(25, 0, 0),
                            newVector(0, 4567, 0)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    50455.243852844476,
                    callArea(
                            newVector(678, -789, 0),
                            newVector(0, 1, -97)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    0.0,
                    callArea(
                            newVector(0, 0, 0),
                            newVector(789, 97, 5)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    356308.8198799603,
                    callArea(
                            newVector(897, 234, -6),
                            newVector(67, -5, 768)
                    ),
                    0.0001,
                    message
            );
        });

    }

    @Test
    public void test15_static_add_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertVectorEquals(
                    newVector(2, 4, 6),
                    callStaticAdd(
                            newVector(1, 2, 3),
                            newVector(1, 2, 3)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(1, 2, 3),
                    callStaticAdd(
                            newVector(0, 0, 0),
                            newVector(1, 2, 3)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(0, 0, 0),
                    callStaticAdd(
                            newVector(1, 2, 3),
                            newVector(-1, -2, -3)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(5, 3, -3),
                    callStaticAdd(
                            newVector(1, -2, 3),
                            newVector(4, 5, -6)
                    ),
                    message
            );
        });
    }

    @Test
    public void test16_static_add_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertVectorEquals(
                    newVector(10000, 10000, 10000),
                    callStaticAdd(
                            newVector(5000, 5000, 5000),
                            newVector(5000, 5000, 5000)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
                    callStaticAdd(
                            newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
                            newVector(0, 0, 0)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
                    callStaticAdd(
                            newVector(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1),
                            newVector(1, 1, 1)
                    ),
                    message
            );
            assertVectorEquals(
                    newVector(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE),
                    callStaticAdd(
                            newVector(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1),
                            newVector(-1, -1, -1)
                    ),
                    message
            );
        });
    }

    @Test
    public void test17_static_area_1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(
                    0.5,
                    callStaticArea(
                            newVector(1, 0, 0),
                            newVector(0, 1, 0)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    0.86602540,
                    callStaticArea(
                            newVector(1, 1, 0),
                            newVector(0, 1, 1)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    0.0,
                    callStaticArea(
                            newVector(0, 0, 0),
                            newVector(1, 1, 1)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    11.20267825,
                    callStaticArea(
                            newVector(1, 2, -3),
                            newVector(4, -5, 6)
                    ),
                    0.0001,
                    message
            );
        });

    }

    @Test
    public void test18_static_area_2() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(
                    57087.5,
                    callStaticArea(
                            newVector(25, 0, 0),
                            newVector(0, 4567, 0)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    50455.243852844476,
                    callStaticArea(
                            newVector(678, -789, 0),
                            newVector(0, 1, -97)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    0.0,
                    callStaticArea(
                            newVector(0, 0, 0),
                            newVector(789, 97, 5)
                    ),
                    0.0001,
                    message
            );
            assertEquals(
                    356308.8198799603,
                    callStaticArea(
                            newVector(897, 234, -6),
                            newVector(67, -5, 768)
                    ),
                    0.0001,
                    message
            );
        });

    }


    private Constructor<?> findConstructor(Class<?> clazz, int modifier, Class<?>... parameters) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameters);
            if (constructor.getModifiers() != modifier) {
                fail(String.format(
                        "Constructor '(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return constructor;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find constructor '(%s)' in class '%s'",
                    getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private Class<?> findVector() {
        try {
            return Class.forName("Vector");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'Vector'. Please check the class name. Class 'Vector' should not in a package");
            return null;
        }
    }

    private Field findField(Class<?> clazz, String name, Class<?> type, int modifier) {
        try {
            Field field = clazz.getDeclaredField(name);
            if (!field.getType().equals(type)) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong type, it should be '%s'",
                        name, clazz.getCanonicalName(), type.toGenericString()
                ));
            }
            if (field.getModifiers() != modifier) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong modifier, it should be '%s'",
                        name, clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return field;
        } catch (NoSuchFieldException e) {
            fail(String.format("Cannot find attribute '%s' in class '%s'", name, clazz.getCanonicalName()));
            return null;
        }
    }

    private Method findQuadrant() {
        return findMethod(findVector(), "quadrant", Modifier.PUBLIC, int.class);
    }

    private Method findModulus() {
        return findMethod(findVector(), "modulus", Modifier.PUBLIC, double.class);
    }

    private Method findToString() {
        return findMethod(findVector(), "toString", Modifier.PUBLIC, String.class);
    }

    private Method findAdd() {
        return findMethod(findVector(), "add", Modifier.PUBLIC, findVector(), findVector());
    }

    private Method findArea() {
        return findMethod(findVector(), "area", Modifier.PUBLIC, double.class, findVector());
    }

    private Method findStaticAdd() {
        return findMethod(findVector(), "add",
                Modifier.PUBLIC | Modifier.STATIC, findVector(),
                findVector(), findVector());
    }

    private Method findStaticArea() {
        return findMethod(findVector(), "area",
                Modifier.PUBLIC | Modifier.STATIC, double.class,
                findVector(), findVector());
    }

    private Method findMethod(Class<?> clazz, String name, int modifier, Class<?> returnType, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameters);
            if (method.getReturnType() != returnType) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong return type, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), returnType.toGenericString()
                ));
            }
            if (method.getModifiers() != modifier) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return method;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find method '%s(%s)' in class '%s'",
                    name, getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private String getParameterTypeString(Class<?>[] parameters) {
        return Arrays.stream(parameters)
                .map(Class::getCanonicalName)
                .collect(Collectors.joining(", "));
    }

    private int callQuadrant(Object vector) {
        try {
            return (int) findQuadrant().invoke(vector);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private double callModulus(Object vector) {
        try {
            return (double) findModulus().invoke(vector);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private String callToString(Object vector) {
        try {
            return (String) findToString().invoke(vector);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Object callAdd(Object v1, Object v2) {
        try {
            return findAdd().invoke(v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private double callArea(Object v1, Object v2) {
        try {
            return (double) findArea().invoke(v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private Object callStaticAdd(Object v1, Object v2) {
        try {
            return findStaticAdd().invoke(null, v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private double callStaticArea(Object v1, Object v2) {
        try {
            return (double) findStaticArea().invoke(null, v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private Object newVector(int x, int y, int z) {
        try {
            return findConstructor(findVector(), Modifier.PUBLIC, int.class, int.class, int.class)
                    .newInstance(x, y, z);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private void assertVectorEquals(Object v1, Object v2, String error) {
        if (getX(v1) != getX(v2) || getY(v1) != getY(v2) || getZ(v1) != getZ(v2)) {
            fail(error);
        }
    }

    private int getX(Object vector) {
        Field field = findX();
        field.setAccessible(true);
        try {
            return field.getInt(vector);
        } catch (IllegalAccessException e) {
            fail(e);
            return 0;
        }
    }

    private int getY(Object vector) {
        Field field = findY();
        field.setAccessible(true);
        try {
            return field.getInt(vector);
        } catch (IllegalAccessException e) {
            fail(e);
            return 0;
        }
    }

    private int getZ(Object vector) {
        Field field = findZ();
        field.setAccessible(true);
        try {
            return field.getInt(vector);
        } catch (IllegalAccessException e) {
            fail(e);
            return 0;
        }
    }

    private Field findX() {
        return findField(findVector(), "x", int.class, Modifier.PRIVATE);
    }

    private Field findY() {
        return findField(findVector(), "y", int.class, Modifier.PRIVATE);
    }

    private Field findZ() {
        return findField(findVector(), "z", int.class, Modifier.PRIVATE);
    }

}
