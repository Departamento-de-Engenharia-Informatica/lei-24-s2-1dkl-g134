package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceTest {
    @Test
    void ensureGreenSpaceIsCreatedSuccessfully() {
        new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
    }

    @Test
    void ensureNullFieldsNotAllowed(){
        assertThrows(IllegalArgumentException.class, () ->
                new GreenSpace(null, null, 50, null));
    }

    @Test
    void ensureNonPositiveAreaNotAllowed(){
        assertThrows(IllegalArgumentException.class, () ->
                new GreenSpace("Hello", "Somewhere", 0, GreenSpaceType.GARDEN));
    }

    @Test
    void testEqualsSameObject() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertEquals(greenSpace, greenSpace);
    }

    @Test
    void testEqualsDifferentClass() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertNotEquals(greenSpace, new Object());
    }

    @Test
    void testEqualsNull() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertNotEquals(greenSpace, null);
    }

    @Test
    void testEqualsDifferentObject() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        GreenSpace greenSpace1 = new GreenSpace("Bye", "Nowhere", 55, GreenSpaceType.LARGE_SIZE);

        assertNotEquals(greenSpace, greenSpace1);
    }

    @Test
    void testEqualsSameNameAddress() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        GreenSpace greenSpace1 = new GreenSpace("Hello", "Somewhere", 55, GreenSpaceType.LARGE_SIZE);

        assertEquals(greenSpace, greenSpace1);
    }

    @Test
    void testToString(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertEquals("Hello", greenSpace.toString());
    }

    @Test
    void testGetName(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertEquals("Hello", greenSpace.getName());
    }

    @Test
    void testGetAddress(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertEquals("Somewhere", greenSpace.getAddress());
    }

    @Test
    void testGetArea(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertEquals(50, greenSpace.getArea());
    }

    @Test
    void testGetType(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        assertEquals(GreenSpaceType.GARDEN, greenSpace.getType());
    }
}
