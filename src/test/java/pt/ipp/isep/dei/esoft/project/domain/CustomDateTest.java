package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomDateTest {

    @Test
    void ensureCustomDateIsCreatedSuccessfully() {
        CustomDate CustomDate = new CustomDate("1974/04/25");
    }

    @Test
    void ensureCustomDateNameIsNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate(null));
    }

    @Test
    void ensureCustomDateHasCorrectFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate("25/04/1975"));
    }

    @Test
    void ensureCustomDateOnlyAcceptsNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate("OOOO/HE/LL"));
    }

    @Test
    void ensureCustomDateOnlyAcceptsPositiveNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate("0000/00/00"));
    }

    @Test
    void ensureCustomDateMonthDoesNotExceedTwelve() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate("1975/13/25"));
    }

    @Test
    void ensureCustomDateDayValidForMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate("1975/11/31"));
    }

    @Test
    void ensureCustomDateNoLeapDayOnNonLeapYear() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomDate("1975/02/29"));
    }

    @Test
    void ensureCustomDateLeapDayOnLeapYear() {
        CustomDate customDate = new CustomDate("2020/02/29");
    }

    @Test
    void testEqualsSameObject() {
        CustomDate CustomDate = new CustomDate("1974/04/25");

        assertEquals(CustomDate, CustomDate);
    }

    @Test
    void testEqualsDifferentClass() {
        CustomDate CustomDate = new CustomDate("1974/04/25");

        assertNotEquals(CustomDate, new Object());
    }

    @Test
    void testEqualsNull() {
        CustomDate CustomDate = new CustomDate("1974/04/25");

        assertNotEquals(CustomDate, null);
    }

    @Test
    void testEqualsDifferentObject() {
        CustomDate CustomDate = new CustomDate("1974/04/25");
        CustomDate CustomDate1 = new CustomDate("1974/04/24");

        assertNotEquals(CustomDate, CustomDate1);
    }

    @Test
    void testEqualsSameObjectSameDate() {
        CustomDate CustomDate = new CustomDate("1974/04/25");
        CustomDate CustomDate1 = new CustomDate("1974/04/25");

        assertEquals(CustomDate, CustomDate1);
    }

    @Test
    void testHashCodeSameObject() {
        CustomDate customDate = new CustomDate("1974/04/25");

        assertEquals(customDate.hashCode(), customDate.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        CustomDate customDate = new CustomDate("1974/04/25");
        CustomDate customDate1 = new CustomDate("1974/04/24");

        assertNotEquals(customDate.hashCode(), customDate1.hashCode());
    }

    @Test
    void testToString(){
        CustomDate customDate = new CustomDate("1974/04/25");

        assertEquals("1974/04/25", customDate.toString());
    }

    @Test
    void testIsAfterDateFailsForFutureDate() {
        CustomDate customDate = new CustomDate("1974/04/25");
        CustomDate customDate1 = new CustomDate("1975/04/24");

        assertFalse(customDate.isAfterDate(customDate1));
    }

    @Test
    void testIsAfterDateWorksForCurrentDate() {
        CustomDate customDate = new CustomDate("1974/04/25");
        CustomDate customDate1 = new CustomDate("1974/04/24");

        assertTrue(customDate.isAfterDate(customDate1));
    }

    @Test
    void testIsAfterDateWorksForPastDate() {
        CustomDate customDate = new CustomDate("1974/04/25");
        CustomDate customDate1 = new CustomDate("1973/04/24");

        assertTrue(customDate.isAfterDate(customDate1));
    }

    @Test
    void testIsFutureWorksForFutureDate() {
        CustomDate customDate = new CustomDate("9999/04/25");

        assertTrue(customDate.isFuture());
    }

    @Test
    void testIsFutureFailsForPastDate() {
        CustomDate customDate = new CustomDate("1000/04/25");

        assertFalse(customDate.isFuture());
    }

    @Test
    void testIsLegalAgeFailsForMinorBirthdate() {
        CustomDate customDate = new CustomDate("2024/04/25");

        assertFalse(customDate.isLegalAge());
    }

    @Test
    void testIsLegalAgeWorksForAdultBirthdate() {
        CustomDate customDate = new CustomDate("2000/04/25");

        assertTrue(customDate.isLegalAge());
    }

    @Test
    void testYearDifference(){
        CustomDate customDate = new CustomDate("1974/04/25");
        CustomDate customDate1 = new CustomDate("2024/04/25");

        assertEquals(50, customDate1.yearDifference(customDate));
    }

    @Test
    void testGetYear(){
        CustomDate customDate = new CustomDate("1974/04/25");

        assertEquals(1974, customDate.getYear());
    }

    @Test
    void testGetMonth(){
        CustomDate customDate = new CustomDate("1974/04/25");

        assertEquals(4, customDate.getMonth());
    }

    @Test
    void testGetDay(){
        CustomDate customDate = new CustomDate("1974/04/25");

        assertEquals(25, customDate.getDay());
    }
}