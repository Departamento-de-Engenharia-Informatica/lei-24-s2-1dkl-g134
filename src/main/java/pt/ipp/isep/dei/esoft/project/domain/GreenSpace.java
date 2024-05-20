package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;




    public GreenSpace(String name, String address, int area, GreenSpaceType type) {
        this.name = name;
        this.address=address;
        this.area = area;
        this.type = type;
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

}

