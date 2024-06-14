import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

public class Lab1Test {

    private Lab1 lab1;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws FileNotFoundException {
        lab1 = new Lab1();
        String filePath = "src/input.txt";
        lab1.buildGraphFromFile(filePath);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut); // 恢复标准输出
        outContent.reset(); // 清空输出流
    }

    @Test
    public void testWordsNotInGraph() {
        lab1.calcShortestPath("abc", "def");
        assertEquals("No word1 or word2 in the graph!", outContent.toString().trim());
    }

    @Test
    public void testShortestPathOneStep() {
        lab1.calcShortestPath("with", "its");
        String[] lines = outContent.toString().trim().split("\\r?\\n");
        // 确保第一行是"Shortest Path: 1"
        assertEquals("Shortest Path: 1", lines[0]);
        // 确保有两行输出，并且第二行是"Write to output success."
        if (lines.length > 1) {
            assertEquals("Write to output success.", lines[1]);
        } else {
            fail("Expected two lines of output, but only one was found.");
        }
    }

    @Test
    public void testShortestPathSeveralSteps() {
        lab1.calcShortestPath("and", "it");
        String[] lines = outContent.toString().trim().split("\\r?\\n");
        // 确保第一行是"Shortest Path:7"
        assertEquals("Shortest Path: 7", lines[0]);
        // 确保有两行输出，并且第二行是"Write to output success."
        if (lines.length > 1) {
            assertEquals("Write to output success.", lines[1]);
        } else {
            fail("Expected two lines of output, but only one was found.");
        }
    }

    @Test
    public void testUnreachableWord2() {
        lab1.calcShortestPath("melody", "its");
        assertEquals("Word2 unreachable from word1!", outContent.toString().trim());
    }
}