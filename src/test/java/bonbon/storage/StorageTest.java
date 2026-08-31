package bonbon.storage;

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
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        testFilePath = tempDir.resolve("test_bonbon.txt");
        storage = new Storage();
        taskList = new TaskList(10);
    }

    // writeFile Tests

    @Test
    public void writeFile_singleLine_createsFileAndWritesContent() throws IOException {
        storage.writeFile(testFilePath, "todo read book", taskList);

        assertTrue(Files.exists(testFilePath));
        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(1, lines.size());
        assertEquals("todo read book", lines.get(0));
    }

    @Test
    public void writeFile_multipleLines_appendsInOrder() throws IOException {
        storage.writeFile(testFilePath, "todo buy groceries", taskList);
        storage.writeFile(testFilePath, "deadline return book /by 2026-09-01 1800", taskList);

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(2, lines.size());
        assertEquals("todo buy groceries", lines.get(0));
        assertEquals("deadline return book /by 2026-09-01 1800", lines.get(1));
    }

    // loadFile Tests

    @Test
    public void loadFile_nonExistentFile_printsMessageAndDoesNotThrow() {
        Path nonExistentPath = tempDir.resolve("does_not_exist.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        storage.loadFile(nonExistentPath, taskList);

        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("No saved data found!"));
    }

    @Test
    public void loadFile_validTasksInFile_executesWithoutErrors() throws IOException {
        Files.writeString(testFilePath, "todo read book\ndeadline assignment /by 2026-09-01 1800\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        storage.loadFile(testFilePath, taskList);

        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Task added: read book"));
        assertTrue(consoleOutput.contains("Task added:"));
    }

    @Test
    public void loadFile_withMarkCommand_executesMarkingFromConsoleOutput() throws IOException {
        Files.writeString(testFilePath, "todo submit paper\nmark 1\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        storage.loadFile(testFilePath, taskList);

        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Task marked as done:"));
        assertTrue(consoleOutput.contains("[X] submit paper"));
    }
}