package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

import java.io.Serializable;

public class GreenSpace implements Serializable {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private String creatorName, creatorEmail;



    public GreenSpace(String name, String address, int area, GreenSpaceType type) {
        this.name = name;
        this.address=address;
        this.area = area;
        this.type = type;
        creatorName = ApplicationSession.getInstance().getCurrentSession().getUserName();
        creatorEmail = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

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

    public GreenSpaceType getType() {
        return type;
    }

    public int getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isCreatedBy(String name, String email) {
        if(name.equalsIgnoreCase(creatorName) && email.equalsIgnoreCase(creatorEmail)){
            return true;
        }
        return false;
    }
}

