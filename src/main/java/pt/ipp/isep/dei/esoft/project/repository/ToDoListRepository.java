package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {
    private List<ToDoList> toDoLists;


    public ToDoListRepository() {
        this.toDoLists = new ArrayList<>();
    }

    public void addToDoList(ToDoList toDoList) {
        toDoLists.add(toDoList);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoListRepository that = (ToDoListRepository) o;
        return toDoLists.equals(that.toDoLists);
    }
}
