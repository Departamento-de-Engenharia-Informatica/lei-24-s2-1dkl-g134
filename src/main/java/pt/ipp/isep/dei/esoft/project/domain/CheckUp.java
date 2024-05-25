package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;

public class CheckUp implements Serializable {
    private Vehicle vehicle;
    private int currentKM;
    private CustomDate date;

    /**
     * Returns the vehicle associated with this particular checkup.
     * @return The Vehicle object associated with this checkup
     */
    public Vehicle getVehicle() {return vehicle;}

    /**
     * Returns the KMs the vehicle had as of this checkup.
     * @return An int value representing the KMs of the vehicle as of this checkup
     */
    public int getCurrentKM() {return currentKM;}

    /**
     * Returns the date on which this checkup was performed
     * @return A CustomDate object describing the date on which this checkup was performed
     */
    public CustomDate getDate(){return date;}

    /**
     * Checks if this checkup is the same as another.
     * A checkup is considered the same as another if they were perfomred on the same date
     * and on the same vehicle.
     * @param o The checkup to compare against
     * @return A boolean value describing if the checkups are equal or not.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof CheckUp)) {
            return false;
        }
        CheckUp c = (CheckUp) o;
        if (c.getVehicle().equals(vehicle) && c.getDate().equals(date)) {
            return true;
        }
        return false;
    }

    /**
     * Constructor for a new CheckUp object.
     * This method will throw an IllegalArgumentException if the date value is in the future,
     * the currentKm valus is below or equal to 0, the date is null or blank, or for any reason
     * outlined in the CustomDate constructor.
     * @param vehicle The Vehicle object to be associated with this checkup
     * @param currentKM The KMs of the vehicle as of the time of this checkup
     * @param date The date on which this checkup was performed.
     */
    public CheckUp(Vehicle vehicle, int currentKM, String date) {
        if(date == null || vehicle == null){
            throw new IllegalArgumentException("Null parameters not allowed.");
        }
        if(date.isBlank()){
            throw new IllegalArgumentException("Date cannot be blank");
        }
        date = date.trim();
        this.vehicle = vehicle;
        this.currentKM = currentKM;
        this.date = new CustomDate(date);
        if(this.date.isFuture()){
            throw new IllegalArgumentException("Date must not be in the future.");
        }
        if(currentKM<=0){
            throw new IllegalArgumentException("Current KM value must be greater than 0");
        }
    }

}
