import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class CourseLoader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CourseLoader extends DataLoader
{
    private List<Course> courses = new ArrayList<Course>();

    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */
    CourseLoader(String file){
        load(file);
    }
    CourseLoader() {
        //empty constructor for testing purposes
    }

    /**
     * Parse a single line of CSV in the form:
     * dept, courseNum, section, name, credits, maxEnrol, days, startTime, EndTime, Duration, String time, room, professor
     * CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309,Gomerschdat Anna
     * 
     * Method should create a new Course Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     */
    public void parseAndLoadLine(String data){
        try{
            String[] info = data.split(",");
            String dept = info[0];
            int courseNum = Integer.parseInt(info[1]);
            int section = Integer.parseInt(info[2]);
            String name = info[3];
            double credits = Double.parseDouble(info[4]);
            int maxEnrol = Integer.parseInt(info[5]);
            String days = info[6];
            int startTime = Integer.parseInt(info[7]);
            int endTime = Integer.parseInt(info[8]);
            int duration = Integer.parseInt(info[9]);
            String time = info[10];
            String room = info[11];
            String prof = info[12];
            
            courses.add(new Course(dept, courseNum, section, name, credits, maxEnrol, days, 
            startTime, endTime, duration, time, room, prof));
        }
        catch(Exception e){}
    }

    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Course>
     */
    public List<Course> getCourses(){
        return courses;
    }
}
