package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {
    private String brand;
    private String model;
    private int tare;
    private double grossWeight;
    private int currentKM;
    private String registerDate;
    private String acquisitionDate;
    private int checkUpFrequency;
    private String plateNumber;
    private String type;

    public Vehicle(String brand, String model, int tare, double grossWeight,
                   int currentKM, String registerDate, String acquisitionDate,
                   int checkUpFrequency, String plateNumber, String type) {
        if (brand == null || model == null || registerDate == null ||
                acquisitionDate == null || plateNumber == null || type == null ||
                plateNumber.isEmpty() || brand.isEmpty() || model.isEmpty() || type.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled");
        }
        if (grossWeight <= 0 || tare <= 0 || currentKM <= 0 || checkUpFrequency <= 0) {
            throw new IllegalArgumentException("Values must be greater than zero");
        }

        this.brand = brand;
        this.model = model;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKM = currentKM;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkUpFrequency = checkUpFrequency;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    @Override
    public boolean equals(Object o){
        Vehicle v = (Vehicle) o;
        if (v.getPlateNumber().equals(plateNumber)) {
            return true;
        }
        return false;
    }

    public String getBrand() { return brand; }

    public String getModel() { return model; }

    public String getPlateNumber() { return plateNumber; }

    public String getType() { return type; }

    public String getRegisterDate() { return registerDate; }

    public int getCheckUpFrequency() { return checkUpFrequency; }

    public String getAcquisitionDate() { return acquisitionDate; }

    public int getCurrentKM() { return currentKM; }

    public double getGrossWeight() { return grossWeight; }

    public int getTare() { return tare; }
}




