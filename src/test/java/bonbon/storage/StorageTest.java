package bonbon.storage;

import bonbon.exception.BonBonException;
import bonbon.tasklist.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @TempDir
    Path tempDir;

    private Path testFilePath;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        testFilePath = tempDir.resolve("test_bonbon.txt");
        taskList = new TaskList(10);
    }

    // writeFile Tests

    @Test
    public void writeFile_tasksInList_createsFileAndWritesFormattedData() throws IOException, BonBonException {
        taskList.addToDo("read book");
        Storage.writeFile(testFilePath, taskList);

        assertTrue(Files.exists(testFilePath));
        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(1, lines.size());
        assertEquals("T | 0 | read book", lines.get(0));
    }

    @Test
    public void writeFile_multipleTasks_overwritesWithCurrentListState() throws IOException, BonBonException {
        taskList.addToDo("buy groceries");
        taskList.addDeadline("return book", "2026-09-01 1800");
        Storage.writeFile(testFilePath, taskList);

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(2, lines.size());
        assertEquals("T | 0 | buy groceries", lines.get(0));
        assertEquals("D | 0 | return book | 2026-09-01 1800", lines.get(1));
    }

    // loadFile Tests

    @Test
    public void loadFile_nonExistentFile_printsMessageAndDoesNotThrow() {
        Path nonExistentPath = tempDir.resolve("does_not_exist.txt");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            Storage.loadFile(nonExistentPath, taskList);
            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("No saved data found!"));
            assertEquals(0, taskList.size());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void loadFile_validTasksInFile_populatesTaskList() throws Exception {
        Files.writeString(testFilePath, "T | 0 | read book\nD | 0 | assignment | 2026-09-01 1800\n");

        Storage.loadFile(testFilePath, taskList);

        assertEquals(2, taskList.size());
        assertEquals("read book", taskList.get(0).getName());
    }

    @Test
    public void loadFile_markedTaskInFile_loadsTaskAsDone() throws Exception {
        Files.writeString(testFilePath, "T | 1 | submit paper\n");

        Storage.loadFile(testFilePath, taskList);

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0).isDone());
    }
}