package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class Job implements Serializable {
    private String name;

    /**
     * Checks if this job is equal to another.
     * Two jobs are considered equal if they share the same name.
     * This comparison is not case-sensitive.
     * @param o The job to compare against.
     * @return A boolean value representing if this job is equal to the other.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Job)) {
            return false;
        }
        Job s = (Job) o;
        if (s.getName().equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the String representation of this job.
     * @return A String representing this job's name.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Constructor for a new Job object.
     * This method will throw an IllegalArgumentException if the job's name is null/blank
     * or contains anything other than letters and whitespaces.
     * @param name A String representing new job's name.
     */
    public Job(String name) {
        if(name == null){
            throw new IllegalArgumentException("Job name cannot be null");
        }
        if(name.isBlank()){
            throw new IllegalArgumentException("Job name cannot be blank");
        }
        name = name.trim();
        char[] nameCharacters = name.toCharArray();
        for(char c : nameCharacters){
            if(!Character.isLetter(c) && !Character.isWhitespace(c)){
                throw new IllegalArgumentException("Job name can only contain letters and whitespaces.");
            }
        }
        this.name = name;
    }

    /**
     * Returns this job's name.
     * @return A String object representing this job's name.
     */
    public String getName() {
        return name;
    }
}

