package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.Serializable;

public class CustomTime implements Serializable {
    private int hour;

    /**
     * Constructor for a new CustomTime object.
     * This method will throw an IllegalArgumentException if the received time is null or blank,
     * doesn't follow the "HH:MM" format (with leading zeros), if the hour value isn't between
     * 0 and 23, or if the hour value isn't between the start and end of work hours defined in
     * the Bootstrap file.
     * @param time The String representation of the time.
     */
    public CustomTime(String time) {
        if(time == null){
            throw new IllegalArgumentException("Time cannot be null.");
        }
        if(time.isBlank()){
            throw new IllegalArgumentException("Time cannot be blank.");
        }
        time = time.trim();
        String timePattern = "\\d{2}:\\d{2}";
        if(!time.matches(timePattern)){
            throw new IllegalArgumentException("Invalid date format. Please use 'HH:MM', with leading zeros.");
        }
        String[] components = time.split(":");
        hour = Integer.parseInt(components[0]);
        if(hour < 0 || hour > 23){
            throw new IllegalArgumentException("Hour value must be between 0 and 23.");
        }
        if(hour < Bootstrap.workHoursStart || hour > Bootstrap.workHoursEnd){
            throw new IllegalArgumentException("Hour value not part of work hours.");
        }
    }

    /**
     * Gets the String representation of this time.
     * @return A String representing this time.
     */
    @Override
    public String toString() {
        String hour = String.valueOf(this.hour);
        if(hour.length() == 1){
            hour = "0" + hour;
        }
        return hour + ":00";
    }

    /**
     * Checks if two times are equal.
     * Two times are considered equal if their hour values are the same.
     * @param o The time to compare against.
     * @return A boolean value representing if the times are equal.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof CustomTime)) {
            return false;
        }
        CustomTime other = (CustomTime) o;
        if(other.getHour() != hour){
            return false;
        }
        return true;
    }

    /**
     * Gets this time's hour value.
     * @return An int representing this time's hour.
     */
    public int getHour(){
        return hour;
    }

    /**
     * Advances this CustomTime object forward by a non-negative number of hours.
     * This method does not update the CustomTime object it is performed on, will throw
     * an IllegalArgumentException if the number of hours to adjust by is negative,
     * and is limited to start/end work hour values defined in the Bootstrap file: If
     * the start hour was defined as 9, the end hour as 17, this time's hour was 16, and this
     * method advanced it by 4 hours, the return hour would be 12.
     * @param hour
     * @return
     */
    public CustomTime adjust(int hour){
        if(hour < 0){
            throw new IllegalArgumentException("Hour adjustment cannot be negative.");
        }
        int newHour = this.hour + hour;
        if(newHour > Bootstrap.workHoursEnd){
            int excess = newHour - Bootstrap.workHoursEnd;
            while(excess > Bootstrap.dailyWorkHours + (Bootstrap.workHoursEnd - this.hour)){
                excess -= Bootstrap.dailyWorkHours;
            }
            if(excess > Bootstrap.workHoursEnd - this.hour){
                excess -= Bootstrap.workHoursEnd - this.hour;
                newHour = Bootstrap.workHoursStart + excess;
            }else{
                newHour = this.hour + excess;
            }
        }
        String newHourText = String.valueOf(newHour);
        if(newHourText.length() == 1){
            newHourText = "0" + newHourText;
        }
        return new CustomTime(newHourText + ":00");
    }
}
