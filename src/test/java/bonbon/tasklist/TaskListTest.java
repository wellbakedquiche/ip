package bonbon.tasklist;

import bonbon.exception.BonBonException;
import bonbon.exception.BonBonOutOfBoundsException;
import bonbon.exception.BonBonTaskListFullException;
import bonbon.tasklist.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(10);
    }

    // Task Addition Tests

    @Test
    public void addToDo_validInput_returnsCorrectIndexAndIncrementsSize() throws BonBonTaskListFullException {
        int index1 = taskList.addToDo("read book");
        int index2 = taskList.addToDo("buy groceries");

        assertEquals(0, index1);
        assertEquals(1, index2);
        assertEquals(2, taskList.size());
    }

    @Test
    public void addDeadline_validInput_addsDeadlineTask() throws BonBonTaskListFullException {
        taskList.addDeadline("submit report", "2026-09-20 2359");

        assertEquals(1, taskList.size());
        assertEquals("submit report", taskList.get(0).getName());
    }

    @Test
    public void addEvent_validInput_addsEventTask() throws BonBonException {
        taskList.addEvent("orientation", "2026-09-20 1000", "2026-09-20 1200", "location");

        assertEquals(1, taskList.size());
        assertEquals("orientation", taskList.get(0).getName());
    }

    @Test
    public void addToDo_exceedCapacity_returnsNegativeOne() throws BonBonTaskListFullException {
        TaskList smallList = new TaskList(1);
        smallList.addToDo("task 1");

        int result = smallList.addToDo("task 2");
        assertEquals(-1, result);
        assertEquals(1, smallList.size());
    }

    // Mark & Unmark Tests

    @Test
    public void mark_validIndex_marksTaskAsDone() throws BonBonTaskListFullException, BonBonOutOfBoundsException {
        taskList.addToDo("read book");
        taskList.mark(0);

        assertTrue(taskList.get(0).isDone());
    }

    @Test
    public void unmark_markedTask_unmarksTask() throws BonBonOutOfBoundsException, BonBonTaskListFullException {
        taskList.addToDo("read book");
        taskList.mark(0);
        taskList.unmark(0);

        assertFalse(taskList.get(0).isDone());
    }

    // Task Removal Tests

    @Test
    public void removeTask_validIndex_removesTaskAndDecrementsSize() throws BonBonTaskListFullException, BonBonOutOfBoundsException {
        taskList.addToDo("read book");
        taskList.addToDo("buy groceries");

        int status = taskList.removeTask(0);

        assertEquals(1, status);
        assertEquals(1, taskList.size());
        assertEquals("buy groceries", taskList.get(0).getName());
    }

    @Test
    public void removeTask_outOfBoundsIndex_returnsNegativeOne() throws BonBonTaskListFullException, BonBonOutOfBoundsException {
        taskList.addToDo("read book");

        int statusUpper = taskList.removeTask(5);
        int statusNegative = taskList.removeTask(-1);

        assertEquals(-1, statusUpper);
        assertEquals(-1, statusNegative);
        assertEquals(1, taskList.size());
    }

    // Search / Find Tests

    @Test
    public void find_matchingKeyword_returnsMatchingTasksString() throws BonBonTaskListFullException {
        taskList.addToDo("read book");
        taskList.addToDo("buy groceries");

        String result = taskList.find("book");

        assertTrue(result.contains("read book"));
        assertFalse(result.contains("buy groceries"));
    }
}