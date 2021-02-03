package app;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class CommandsTest {
    private Commands commands;

    @Before
    public void setUp() throws Exception {
        loadWordXMLfile();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleCommand() {

    }

    @Test
    public void wordMatch() {
        commands.loadWordXMLfile();
        List<String> test1 = Arrays.asList(new String[]{"locate", "ramdom", "word"});
        List<String> test2 = Arrays.asList(new String[]{"test", "ramdom", "word"});

        assertEquals("find", commands.wordMatch(test1));
        assertEquals("no match", commands.wordMatch((test2)));
    }

    @Test
    public void loadWordXMLfile() {
        assertNotNull(commands.loadWordXMLfile());
        assertEquals(4,commands.loadWordXMLfile().keySet().size());
    }
}