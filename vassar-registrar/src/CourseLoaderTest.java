

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CourseLoaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CourseLoaderTest
{
    @Test
    public void parseAndLoadLineTest1() {
        CourseLoader c = new CourseLoader();
        String info = "CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309,Gomerschdat Anna";
        c.parseAndLoadLine(info);
        
        assertEquals(1, c.getCourses().size());
    }
    @Test
    public void parseAndLoadLineTest2() {
        CourseLoader c = new CourseLoader();
        String badInfo = "h";
        c.parseAndLoadLine(badInfo);
        
        assertEquals(0, c.getCourses().size());
    }
}

