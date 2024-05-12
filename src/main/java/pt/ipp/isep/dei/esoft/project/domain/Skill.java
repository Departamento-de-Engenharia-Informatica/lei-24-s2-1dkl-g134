package pt.ipp.isep.dei.esoft.project.domain;

public class    Skill {
    private String name;

    /**
     * Checks if this skill is equal to another skill.
     * Two skills are considered equal if they have the same name.
     * This method is not case-sensitive.
     * @param o The Skill object to compare against.
     * @return A boolean value representing if this skill is equal to the other.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill s = (Skill) o;
        if (s.getName().equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the String representation of this skill.
     * @return The skill's name.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Constructor for a new Skill object.
     * This method will throw an IllegalArgumentException if the skill name is null/blank or contains
     * characters other than letters and whitespaces.
     * @param name
     */
    public Skill(String name) {
        if(name == null){
            throw new IllegalArgumentException("Skill name cannot be null");
        }
        if(name.isBlank()){
            throw new IllegalArgumentException("Skill name cannot be blank");
        }
        name = name.trim();
        char[] nameCharacters = name.toCharArray();
        for(char c : nameCharacters){
            if(!Character.isLetter(c) && !Character.isWhitespace(c)){
                throw new IllegalArgumentException("Skill name can only contain letters and whitespaces.");
            }
        }
        this.name = name;
    }

    /**
     * Gets the name of the skill.
     * @return The String representing the skill's name.
     */
    public String getName() {
        return name;
    }
}

