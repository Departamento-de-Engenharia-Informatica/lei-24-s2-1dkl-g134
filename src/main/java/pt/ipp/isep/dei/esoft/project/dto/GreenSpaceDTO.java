package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

public class GreenSpaceDTO {
    public String name;
    public String address;
    public int area;
    public GreenSpaceType type;
    public GreenSpace attachedGreenSpace;

    @Override
    public boolean equals(Object o) {
        return attachedGreenSpace.equals(o);
    }
}
