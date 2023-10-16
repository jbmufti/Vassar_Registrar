

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StudentLoaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StudentLoaderTest
{
    @Test
    public void parseAndLoadLineTest1() {
        StudentLoader s = new StudentLoader();
        String info = "Hector Tran,999248624,2023,1";
        s.parseAndLoadLine(info);
        
        assertEquals(1, s.getStudents().size());
    }
    @Test
    public void parseAndLoadLineTest2() {
        StudentLoader s = new StudentLoader();
        String badInfo = "h";
        s.parseAndLoadLine(badInfo);
        
        assertEquals(0, s.getStudents().size());
    }
}
