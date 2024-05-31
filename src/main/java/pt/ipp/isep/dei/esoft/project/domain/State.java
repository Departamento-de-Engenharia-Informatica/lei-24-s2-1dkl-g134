package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

public enum State implements Serializable {
    PENDING,
    PLANNED,
    POSTPONED,
    COMPLETED,
    CANCELED;
}
