package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Calendar;

public class CustomDate implements Serializable {
    private int year;
    private int month;
    private int day;
    private final int[] daysOfMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Constructor for a new CustomDate object.
     * This method will throw an IllegalArgumentException if the date received is null or empty,
     * not in the correct format (YYYY/MM/DD, with leading zeros), the year/month/day values are
     * below 0, the month value exceeds 12, the specified month does not include the specified day,
     * or if a February 29th date is specified for a non-leap year.
     * @param date The String representation of this date.
     */
    public CustomDate(String date) {
        if(date == null){
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if(date.isBlank()){
            throw new IllegalArgumentException("Date cannot be blank.");
        }
        date = date.trim();
        String datePattern = "\\d{4}/\\d{2}/\\d{2}";
        if(!date.matches(datePattern)){
            throw new IllegalArgumentException("Invalid date format. Please use 'YYYY/MM/DD', with leading zeros.");
        }
        String[] components = date.split("/");
        int year = Integer.parseInt(components[0]);
        int month = Integer.parseInt(components[1]);
        int day = Integer.parseInt(components[2]);
        if(year <= 0 || month <= 0 || day <= 0) {
            throw new IllegalArgumentException("Year, month, and day must be above 0.");
        }
        if(month > 12){
            throw new IllegalArgumentException("Month must not exceed 12.");
        }
        if(((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31)
                || ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) || (month == 2 && day > 29)){
            throw new IllegalArgumentException("Specified month doesn't have specified day.");
        }
        if(!(year % 4 == 0 || (year % 100 == 0 && year % 400 == 0)) && month == 2 && day == 29){
            throw new IllegalArgumentException("February 29th on a non-leap year.");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Checks if this date is equal to another date.
     * Two dates are considered equal if they share the same year, month, and day.
     * @param o The CustomDate object to compare against.
     * @return A boolean value representing if the dates are equal.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof CustomDate)) {
            return false;
        }
        CustomDate other = (CustomDate) o;
        if(other.getYear() != year || other.getMonth() != month || other.getDay() != day){
            return false;
        }
        return true;
    }

    /**
     * The String representation of this date, in 'YYYY/MM/DDD' (leading zeros apply).
     * @return A String representing this date.
     */
    @Override
    public String toString() {
        String yearText = String.valueOf(year);
        int yearTextLength = yearText.length();
        for(int i = 0; i < 4 - yearTextLength; i++){
            yearText = "0" + yearText;
        }
        String monthText = String.valueOf(month);
        if(monthText.length() == 1){
            monthText = "0" + monthText;
        }
        String dayText = String.valueOf(day);
        if(dayText.length() == 1){
            dayText = "0" + dayText;
        }
        return yearText + "/" + monthText + "/" + dayText;
    }

    /**
     * Checks if this date is after <b>or equal</b> to another date.
     * @param other A CustomDate object representing another date.
     * @return A boolean value representing if this date is after <b>or equal</b> to the other date.
     */
    public boolean isAfterDate(CustomDate other){
        if(year > other.getYear()) {
            return true;
        }else if(year < other.getYear()) {
            return false;
        }
        if(month > other.getMonth()) {
            return true;
        }else if(month < other.getMonth()) {
            return false;
        }
        if(day >= other.getDay()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if this date is currently in the future.
     * @return A boolean value representing if this date is currently in the future.
     */
    public boolean isFuture(){
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
        String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
        if(month.length() == 1){
            month = "0" + month;
        }
        if(day.length() == 1){
            day = "0" + day;
        }
        CustomDate tomorrow = new CustomDate(year + "/" + month + "/" + day);
        return isAfterDate(tomorrow);
    }

    /**
     * Checks if someone born on this date would be of legal age.
     * @return A boolean value representing if this date indicates a birthdate of a legal adult.
     */
    public boolean isLegalAge(){
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
        String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        if(month.length() == 1){
            month = "0" + month;
        }
        if(day.length() == 1){
            day = "0" + day;
        }
        if(new CustomDate(year + "/" + month + "/" + day).yearDifference(this) < 18){
            return false;
        }
        return true;
    }

    /**
     * Gets the <b>relative</b> difference in years between this date and another.
     * This method will return a positive number if this date is higher and a negative number if
     * this date is lower.
     * @param other A CustomDate object representing the date to compare to.
     * @return An int value representing the <b>relative</b> difference in years between this date and another.
     */
    public int yearDifference(CustomDate other){
        int yearDifference = year - other.getYear();
        if(month < other.getMonth() || (month == other.getMonth() && day < other.getDay())){
            yearDifference--;
        }
        return yearDifference;
    }

    /**
     * Gets the year of this date.
     * @return An int value representing the year of this date.
     */
    public int getYear(){return year;}

    /**
     * Gets the month of this date.
     * @return An int value representing the month of this date.
     */
    public int getMonth(){return month;}

    /**
     * Gets the day of this date.
     * @return An int value representing the day of this date.
     */
    public int getDay(){return day;}

    /**
     * Adjusts the date by a number of days (does not update the object this is performed on).
     * This method will throw an IllegalArgumentException if the number of days to adjust
     * by is negative.
     * @param days The number of days (not negative) to adjust the date by.
     * @return The end date after the adjustment in question.
     */
    public CustomDate adjust(int days){
        //HOW THIS WORKS:
        //First we check for negative adjustments, then we create new month and year variables.
        //We do a frankly very bad way of checking for leap years: Before any place where it
        //matters, we check if the month is february and the year is a leap year, and if it is,
        //we modify the array daysOfMonth to account for that, making sure to modify it back
        //once the section where it matters is over.
        //The actual check consists of first checking if the change of days is greater than the
        //number of days remaining in the current month: If it is, remove that section and
        //advance the month, maybe advancing the year too if needed.
        //From there we simply continuously check if the number of days to change is still
        //greater than the number of days remaining in the month (in this case, all the days).
        //And if it is, we advance the month and remove that amount of days. If it isn't, we stop.
        //Finally, we check if we modified the month or year through all this. If we did, the
        //final day is equal to the days variable. If we didn't, it's equal to the sum of the
        //days variable to this object's day variable (seeing as no month change occurred).
        if(days < 0){
            throw new IllegalArgumentException("Date adjustment cannot be negative.");
        }
        int newMonth = month;
        int newYear = year;
        if(newMonth == 2 && (newYear % 4 == 0 || (newYear % 100 == 0 && newYear % 400 == 0))){
            daysOfMonth[2] = 29;
        }
        if(days > daysOfMonth[newMonth] - day){
            days -= daysOfMonth[newMonth] - day;
            newMonth++;
            if(newMonth == 13){
                newMonth = 1;
                newYear++;
            }
        }
        if(daysOfMonth[2] == 29){
            daysOfMonth[2] = 28;
        }
        while(days > daysOfMonth[newMonth]){
            days -= daysOfMonth[newMonth];
            newMonth++;
            if(newMonth == 13){
                newMonth = 1;
                newYear++;
            }
            if(newMonth == 2 && (newYear % 4 == 0 || (newYear % 100 == 0 && newYear % 400 == 0))){
                daysOfMonth[2] = 29;
            }else{
                daysOfMonth[2] = 28;
            }
        }
        if(daysOfMonth[2] == 29){
            daysOfMonth[2] = 28;
        }
        int newDay = 0;
        if(newMonth != month || newYear != year){
            newDay = days;
        }else{
            newDay = day+days;
        }
        String yearText = String.valueOf(newYear);
        int yearTextLength = yearText.length();
        for(int i = 0; i < 4 - yearTextLength; i++){
            yearText = "0" + yearText;
        }
        String monthText = String.valueOf(newMonth);
        if(monthText.length() == 1){
            monthText = "0" + monthText;
        }
        String dayText = String.valueOf(newDay);
        if(dayText.length() == 1){
            dayText = "0" + dayText;
        }
        return new CustomDate(yearText + "/" + monthText + "/" + dayText);
    }
}
