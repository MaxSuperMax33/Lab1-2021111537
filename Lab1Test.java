import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class Lab1Test {

    private Lab1 lab1;

    @Before
    public void setUp() {
        lab1 = new Lab1();
    }

    @Test
    public void testWordsNotInGraph() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lab1.calcShortestPath("abc", "def");

        System.setOut(System.out);
        String output = outContent.toString().trim();

        assertEquals("No word1 or word2 in the graph!", output);
    }

    @Test
    public void testShortestPathOneStep() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lab1.calcShortestPath("with", "its");

        System.setOut(System.out);
        String output = outContent.toString().trim();

        assertEquals("Shortest Path: 1", output);
    }

    @Test
    public void testShortestPathSeveralSteps() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lab1.calcShortestPath("and", "it");

        System.setOut(System.out);
        String output = outContent.toString().trim();

        assertEquals("Shortest Path: 7", output);
    }

    @Test
    public void testUnreachableWord2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lab1.calcShortestPath("melody", "its");

        System.setOut(System.out);
        String output = outContent.toString().trim();

        assertEquals("Word2 unreachable from word1!", output);
    }
}