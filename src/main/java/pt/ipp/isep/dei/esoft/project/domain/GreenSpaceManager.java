package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpaceManager {
    private String name;


}

    }
    public GreenSpaceManager(String name){
        if(name==null) {
            throw new IllegalArgumentException("Manager name cannot be null");
        }
        name=name.trim();
        char[] nameCharacters=name.toCharArray();
        for (char c: nameCharacters){
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                throw new IllegalArgumentException("Manager name can only contain letters and whitespaces");

            }
            @Override
            public boolean equals(Object o) {
                if (!(o instanceof GreenSpaceManager)) {
                    return false;
                }
                GreenSpaceManager gsm = (GreenSpaceManager) o;
                return gsm.getName().equalsIgnoreCase(name);
            }
            public String toString(){
                return name;
        }this.name = name;


    }
    public String getName(){
        return name;
    }
}
        }