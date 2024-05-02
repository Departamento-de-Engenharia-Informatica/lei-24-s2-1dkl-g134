package pt.ipp.isep.dei.esoft.project.domain;

public class Job{
    private String name;


    @Override
    public boolean equals(Object o){
        Job s = (Job) o;
        if (s.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

