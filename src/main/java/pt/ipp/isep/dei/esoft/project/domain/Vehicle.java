package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {
    private String brand;
    private String model;
    private int tare;
    private double grossWeight;
    private int currentKM;
    private CustomDate registerDate;
    private CustomDate acquisitionDate;
    private int checkUpFrequency;
    private String plateNumber;
    private String type;

    /**
     * Constructor for a new Vehicle object.
     * This method will throw an IllegalArgumentExcpetion if any field is blank, any numerical field
     * is below or equal to 0, the register date occurs before the acquisition date, any dates are
     * in the future, or for any reason outlined in the CustomDate constructor.
     * @param brand A String representing this vehicle's brand.
     * @param model A String representing this vehicle's model.
     * @param tare An int representing this vehicle's tare weight.
     * @param grossWeight A double representing this vehicle's gross weight.
     * @param currentKM An int representing this vehicle's current kms.
     * @param registerDate A String representing this vehicle's registration date.
     * @param acquisitionDate A String representing this vehicle's acquisition date.
     * @param checkUpFrequency An int representing this vehicle's check up frequency.
     * @param plateNumber A String representing this vehicle's plate number.
     * @param type A String representing this vehicle's type.
     */
    public Vehicle(String brand, String model, int tare, double grossWeight,
                   int currentKM, String registerDate, String acquisitionDate,
                   int checkUpFrequency, String plateNumber, String type) {
        if(brand == null || model == null || registerDate == null ||
                acquisitionDate == null || plateNumber == null || type == null){
            throw new IllegalArgumentException("Null parameters not allowed");
        }
        if (plateNumber.isBlank() || brand.isBlank() || model.isBlank() || type.isBlank() ||
        acquisitionDate.isBlank() || registerDate.isBlank()) {
            throw new IllegalArgumentException("All fields must be filled.");
        }
        if (grossWeight <= 0 || tare <= 0 || currentKM <= 0 || checkUpFrequency <= 0) {
            throw new IllegalArgumentException("Values must be greater than zero.");
        }
        brand = brand.trim();
        model = model.trim();
        registerDate = registerDate.trim();
        acquisitionDate = acquisitionDate.trim();
        plateNumber = plateNumber.trim();
        type = type.trim();
        this.brand = brand;
        this.model = model;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKM = currentKM;
        this.registerDate = new CustomDate(registerDate);
        this.acquisitionDate = new CustomDate(acquisitionDate);
        if(!this.registerDate.isAfterDate(this.acquisitionDate)) {
            throw new IllegalArgumentException("Registration cannot be done before acquisition.");
        }
        if(this.registerDate.isFuture() || this.acquisitionDate.isFuture()){
            throw new IllegalArgumentException("Dates must not be in the future.");
        }
        this.checkUpFrequency = checkUpFrequency;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    /**
     * Checks if this vehicle is equal to another.
     * Two vehicles are considered the same if their plate numbers are the same.
     * This method is not case-sensitive.
     * @param o The Vehicle object to compare against.
     * @return A boolean value representing if the vehicles are the same.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle v = (Vehicle) o;
        if (v.getPlateNumber().equalsIgnoreCase(plateNumber)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the String representation of this vehicle.
     * @return The String representing this vehicle's plate number.
     */
    @Override
    public String toString(){
        return plateNumber;
    }

    /**
     * Gets the vehicle's brand.
     * @return A String representing this vehicle's brand.
     */
    public String getBrand() { return brand; }

    /**
     * Gets the vehicle's model.
     * @return A String representing this vehicle's model.
     */
    public String getModel() { return model; }

    /**
     * Gets the vehicle's plate number.
     * @return A String representing this vehicle's plate number.
     */
    public String getPlateNumber() { return plateNumber; }

    /**
     * Gets the vehicle's checkup frequency.
     * @return An int representing this vehicle's checkup frequency.
     */
    public int getCheckUpFrequency() { return checkUpFrequency; }

    /**
     * Gets the vehicle's current kms.
     * @return An int representing this vehicle's current km.
     */
    public int getCurrentKM() { return currentKM; }

    /**
     * Changes the vehicle's current kms.
     * This method will do nothing if the new current km value is below or equal to 0.
     * @param currentKM The int representation of the new current km value.
     */
    public void setCurrentKM(int currentKM) {
        if(currentKM <= 0){return;}
        this.currentKM = currentKM;
    }
}




