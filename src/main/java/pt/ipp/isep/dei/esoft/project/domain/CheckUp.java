package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class CheckUp {
    private Vehicle vehicle;
    private int currentKM;
    private String date;

    public Vehicle getVehicle() {return vehicle;}

    public int getCurrentKM() {return currentKM;}

    public String getDate() {return date;}

    @Override
    public boolean equals(Object o){
        CheckUp c = (CheckUp) o;
        if (c.getVehicle().equals(vehicle) && c.getDate().compareTo(date) == 0) {
            return true;
        }
        return false;
    }

    public CheckUp(Vehicle vehicle, int currentKM, String date) {
        this.vehicle = vehicle;
        this.currentKM = currentKM;
        this.date = date;
        if(currentKM<=0){
            throw new IllegalArgumentException("value must be greater than 0");
        }
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Please use 'YYYY/MM/DD'.");
        }
    }

    private boolean isValidDate(String date) {
        String datePattern = "\\d{4}/\\d{2}/\\d{2}";
        return date.matches(datePattern);
    }

}
