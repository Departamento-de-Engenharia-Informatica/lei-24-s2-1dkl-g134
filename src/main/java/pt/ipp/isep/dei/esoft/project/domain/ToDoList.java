package pt.ipp.isep.dei.esoft.project.domain;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<TaskEntry> entries;

    public ToDoList() {
        this.entries = new ArrayList<>();
    }

    public void addTaskEntry(TaskEntry taskEntry) {
        entries.add(taskEntry);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList toDoList = (ToDoList) o;
        return entries.equals(toDoList.entries);
    }
}

