package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

import java.io.Serializable;

public class GreenSpace implements Serializable {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private String creatorEmail;

    /**
     * Constructor for a new GreenSpace object.
     * This method will throw an IllegalArgumentException if any field is null or blank
     * or the area is below or equal to zero.
     * @param name The String representation of the green space's name.
     * @param address The String representation of the green space's address.
     * @param area An int representing the green space's area in hectares.
     * @param type The type of the green space, expressed with a GreenSpaceType enumerator.
     */
    public GreenSpace(String name, String address, int area, GreenSpaceType type) {
        if(name == null || address == null || type == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        name = name.trim();
        address = address.trim();
        if(name.isBlank() || address.isBlank()){
            throw new IllegalArgumentException("Blank fields not allowed.");
        }
        if(area <= 0){
            throw new IllegalArgumentException("Area value must be a number greater than 0.");
        }
        this.name = name;
        this.address=address;
        this.area = area;
        this.type = type;
        if(ApplicationSession.getInstance().getCurrentSession().getUserID() == null){
            creatorEmail = null;
        }else{
            creatorEmail = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        }
    }

    /**
     * Checks if this green space is equal to another.
     * Two green spaces are considered the same if their name or address is the same.
     * @param o The green space to compare against.
     * @return A boolean value representing if the green spaces are the same.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof GreenSpace)){
            return false;

        }
        GreenSpace s =(GreenSpace) o;
        if (s.getName().equalsIgnoreCase(name) ||s.getAddress().equalsIgnoreCase(address)){
            return true;
        }return false;
    }

    /**
     * Gets the green space's type.
     * @return A GreenSpaceType enumerator representing this green space's type.
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Gets the green space's area in hectares.
     * @return An int value representing this green space's area.
     */
    public int getArea() {
        return area;
    }

    /**
     * Gets the green space's name.
     * @return A String representing this green space's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the green space's address.
     * @return A String representing this green space's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retuns the String representation of this green space.
     * @return The String representation of this green space.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Checks if this green space was registered on the platform (and therefore is managed by)
     * the user identified using the email parameter.
     * @param email The email of the user to check for.
     * @return A boolean value representing if the green space is managed by the specified user.
     */
    public boolean isCreatedBy(String email) {
        if(email.equalsIgnoreCase(creatorEmail)){
            return true;
        }
        return false;
    }
}

