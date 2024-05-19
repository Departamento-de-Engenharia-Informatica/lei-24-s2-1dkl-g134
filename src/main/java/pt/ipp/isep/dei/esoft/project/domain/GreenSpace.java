package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {
private String name;

    public GreenSpace(String name, String household, int area, String type) {
    }

    @Override
    public boolean equals(Object o){
    if (!(o instanceof GreenSpace)){
        return false;

    }
    GreenSpace s =(GreenSpace) o;
    if (s.getName().equalsIgnoreCase(name)){
        return true;
    }return false;
}

    private String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "GreenSpace{" +
                "name='" + name + '\'' +
                '}';
    }

    }

