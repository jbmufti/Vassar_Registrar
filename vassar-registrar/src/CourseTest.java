

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CourseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CourseTest
{
    @Test
    public void compareToTest() {
        Course c1 = new Course("CMPU", 101, 1, "Intro to Computer Science", 1.0, 30, 
        "MWF", 810, 885, 75, "0130PM-0245PM", "SP100", "Alan Turing");
        
        Course c2 = new Course("PHYS", 101, 1, "Intro to Physics", 1.0, 30, 
        "MWF", 810, 885, 75, "0130PM-0245PM", "SC100", "Albert Einstein");
        
        Course c3 = new Course("CMPU", 102, 1, "Computer Science II", 1.0, 30, 
        "MWF", 810, 885, 75, "0130PM-0245PM", "SP100", "Bill Gates");
        
        Course c4 = new Course("CMPU", 102, 2, "Computer Science II", 1.0, 30, 
        "MWF", 810, 885, 75, "0130PM-0245PM", "SP100", "Bill Gates");
        
        assertEquals(1, c1.compareTo((Object)c2));
        assertEquals(1, c1.compareTo((Object)c3));
        assertEquals(1, c1.compareTo((Object)c3));
        assertEquals(0, c3.compareTo((Object)c3));
    }
    @Test
    public void conflictsWithTest() {
        Course c1 = new Course("CMPU", 101, 1, "Intro to Computer Science", 1.0, 30, 
        "MWF", 810, 885, 75, "0130PM-0245PM", "SP100", "Alan Turing");
        
        Course c2 = new Course("PHYS", 101, 1, "Intro to Physics", 1.0, 30, 
        "MWF", 810, 885, 75, "0130PM-0245PM", "SC100", "Albert Einstein");
        
        Course c3 = new Course("PHYS", 101, 1, "Intro to Physics", 1.0, 30, 
        "MWF", 750, 825, 75, "12:30PM-1:45PM", "SC100", "Albert Einstein");
        
        Course c4 = new Course("PHYS", 101, 1, "Intro to Physics", 1.0, 30, 
        "MWF", 540, 615, 75, "9:00AM-10:15AM", "SC100", "Albert Einstein");
        
        assertEquals(true, c1.conflictsWith(c2));
        assertEquals(true, c1.conflictsWith(c3));
        assertEquals(false, c1.conflictsWith(c4));
    }
}
