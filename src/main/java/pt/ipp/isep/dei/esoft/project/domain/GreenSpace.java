package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

public class GreenSpace {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private UserSession creator;



    public GreenSpace(String name, String address, int area, GreenSpaceType type) {
        this.name = name;
        this.address=address;
        this.area = area;
        this.type = type;
        creator = ApplicationSession.getInstance().getCurrentSession();
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

    private String getName() {
        return null;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name;
    }

    public UserSession getCreator() {
        return creator;
    }
}

