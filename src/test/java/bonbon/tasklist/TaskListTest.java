package bonbon.tasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(10);
    }

    // addToDo Tests

    @Test
    public void addToDo_validInput_returnsCorrectIndex() {
        int index = taskList.addToDo("read book");
        assertEquals(0, index);
    }

    @Test
    public void addToDo_multipleTasks_incrementsIndex() {
        taskList.addToDo("read book");
        int secondIndex = taskList.addToDo("buy groceries");
        assertEquals(1, secondIndex);
    }

    @Test
    public void addToDo_exceedCapacity_returnsNegativeOne() {
        TaskList smallList = new TaskList(1);
        smallList.addToDo("task 1");
        int result = smallList.addToDo("task 2");
        assertEquals(-1, result);
    }

    // delete / removeTask Tests

    @Test
    public void removeTask_validIndex_success() {
        taskList.addToDo("read book");
        int status = taskList.removeTask(0);
        assertEquals(1, status);
    }

    @Test
    public void removeTask_outOfBoundsIndex_returnsNegativeOne() {
        taskList.addToDo("read book");
        int status = taskList.removeTask(5); // Invalid index
        assertEquals(-1, status);
    }

    @Test
    public void removeTask_negativeIndex_returnsNegativeOne() {
        taskList.addToDo("read book");
        int status = taskList.removeTask(-1);
        assertEquals(-1, status);
    }
}