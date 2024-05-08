package pt.ipp.isep.dei.esoft.project.domain;

public class    Skill {
    private String name;


    @Override
    public boolean equals(Object o){
        Skill s = (Skill) o;
        if (s.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public Skill(String name) {
        if(name.isBlank() || name == null){
            throw new IllegalArgumentException("Skill name cannot be blank");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

