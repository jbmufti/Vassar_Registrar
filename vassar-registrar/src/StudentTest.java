

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StudentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StudentTest
{
    @Test
    public void compareToTest() {
        Student s1 = new Student("John", 999000000, 2024, 10);
        Student s2 = new Student("Eric", 999111111, 2023, 10);
        Student s3 = new Student("Ron", 999222222, 2023, 5);
        
        assertEquals(-1, s1.compareTo((Object)s2));
        assertEquals(1, s3.compareTo((Object)s2));
        assertEquals(0, s3.compareTo((Object)s3));
    }
}
