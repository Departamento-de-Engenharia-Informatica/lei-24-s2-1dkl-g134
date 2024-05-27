package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.Serializable;

public class CustomTime implements Serializable {
    private int hour;

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

    @Override
    public String toString() {
        String hour = String.valueOf(this.hour);
        if(hour.length() == 1){
            hour = "0" + hour;
        }
        return hour + ":00";
    }

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

    public int getHour(){
        return hour;
    }

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
