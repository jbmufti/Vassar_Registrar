

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RequestLoaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RequestLoaderTest
{
    @Test
    public void parseAndLoadLineTest() {
        StudentLoader s = new StudentLoader("../data/shortRoster.csv");
        CourseLoader c = new CourseLoader("../data/shortRequests.csv");
        RequestLoader r = new RequestLoader("../data/course_shortRequests.csv", s.getStudents(), c.getCourses());
        String info = "999248624,CMPU-145-51,CMPU-145-52,EDUC-361-51,ECON-235-51,PHED-105-51,ECON-235-51,COGS-311-51";
        
        r.parseAndLoadLine(info);
        assertEquals(7, r.mapStudents.get(999248624).requests.size());
    }
}
