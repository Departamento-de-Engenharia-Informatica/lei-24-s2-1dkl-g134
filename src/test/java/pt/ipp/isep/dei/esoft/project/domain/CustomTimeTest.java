package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import static org.junit.jupiter.api.Assertions.*;

class CustomTimeTest {

    @Test
    void ensureCustomTimeIsCreatedSuccessfully() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");
    }

    @Test
    void ensureCustomTimeNameIsNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime(null));
    }

    @Test
    void ensureCustomTimeNameIsNotBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime(""));
    }

    @Test
    void ensureCustomTimeHasCorrectFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime("4:0"));
    }

    @Test
    void ensureCustomTimeOnlyAcceptsNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime("HI:HI"));
    }

    @Test
    void ensureCustomTimeOnlyAcceptsPositiveNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime("-10:00"));
    }

    @Test
    void ensureCustomTimeHourDoesNotExceed23() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime("24:00"));
    }

    @Test
    void ensureCustomTimeHourIsInWorkHours() {
        String startHour;
        String endHour;
        if(String.valueOf(Bootstrap.workHoursStart - 1).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart - 1);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart - 1);
        }
        if(String.valueOf(Bootstrap.workHoursEnd + 1).length() == 1) {
            endHour = "0" + String.valueOf(Bootstrap.workHoursEnd + 1);
        } else {
            endHour = String.valueOf(Bootstrap.workHoursEnd + 1);
        }
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime(startHour+":00"));
        assertThrows(IllegalArgumentException.class,
                () -> new CustomTime(endHour+":00"));
    }

    @Test
    void testEqualsSameObject() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");

        assertEquals(customTime, customTime);
    }

    @Test
    void testEqualsDifferentClass() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");

        assertNotEquals(customTime, new Object());
    }

    @Test
    void testEqualsNull() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");

        assertNotEquals(customTime, null);
    }

    @Test
    void testEqualsDifferentObject() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        String endHour;
        if(String.valueOf(Bootstrap.workHoursEnd).length() == 1) {
            endHour = "0" + String.valueOf(Bootstrap.workHoursEnd);
        } else {
            endHour = String.valueOf(Bootstrap.workHoursEnd);
        }
        CustomTime customTime1 = new CustomTime(endHour+":00");

        assertNotEquals(customTime, customTime1);
    }

    @Test
    void testEqualsSameTime() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        CustomTime customTime1 = new CustomTime(startHour+":00");

        assertEquals(customTime, customTime1);
    }

    @Test
    void testHashCodeSameObject() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");

        assertEquals(customTime.hashCode(), customTime.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        String endHour;
        if(String.valueOf(Bootstrap.workHoursEnd).length() == 1) {
            endHour = "0" + String.valueOf(Bootstrap.workHoursEnd);
        } else {
            endHour = String.valueOf(Bootstrap.workHoursEnd);
        }
        CustomTime customTime1 = new CustomTime(endHour+":00");

        assertNotEquals(customTime.hashCode(), customTime1.hashCode());
    }

    @Test
    void testToString(){
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");

        assertEquals(startHour+":00", customTime.toString());
    }

    @Test
    void testGetHour(){
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");

        assertEquals(Bootstrap.workHoursStart, customTime.getHour());
    }

    @Test
    void testAdjustSameDay(){
        String startHour = String.valueOf(Bootstrap.workHoursStart);
        String endHour = String.valueOf(Bootstrap.workHoursEnd);
        if(startHour.length() == 1){
            startHour = "0"+startHour;
        }
        if(endHour.length() == 1){
            endHour = "0"+endHour;
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        CustomTime customTime1 = new CustomTime(endHour+":00");

        assertEquals(customTime1, customTime.adjust(Bootstrap.dailyWorkHours));
    }

    @Test
    void testAdjustDifferentDays(){
        String startHour = String.valueOf(Bootstrap.workHoursStart);
        String endHour = String.valueOf(Bootstrap.workHoursStart+1);
        if(startHour.length() == 1){
            startHour = "0"+startHour;
        }
        if(endHour.length() == 1){
            endHour = "0"+endHour;
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        CustomTime customTime1 = new CustomTime(endHour+":00");

        assertEquals(customTime1, customTime.adjust(Bootstrap.dailyWorkHours+1));
    }

    @Test
    void testAdjustDifferentDaysNotSequential(){
        String startHour = String.valueOf(Bootstrap.workHoursStart);
        String endHour = String.valueOf(Bootstrap.workHoursStart+1);
        if(startHour.length() == 1){
            startHour = "0"+startHour;
        }
        if(endHour.length() == 1){
            endHour = "0"+endHour;
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        CustomTime customTime1 = new CustomTime(endHour+":00");

        assertEquals(customTime1, customTime.adjust(Bootstrap.dailyWorkHours+(Bootstrap.dailyWorkHours*3)+1));
    }

    @Test
    void testAdjustFailsWithNegatives(){
        String startHour;
        if(String.valueOf(Bootstrap.workHoursStart).length() == 1) {
            startHour = "0" + String.valueOf(Bootstrap.workHoursStart);
        } else {
            startHour = String.valueOf(Bootstrap.workHoursStart);
        }
        CustomTime customTime = new CustomTime(startHour+":00");
        assertThrows(IllegalArgumentException.class,
                () -> customTime.adjust(-1));
    }
}