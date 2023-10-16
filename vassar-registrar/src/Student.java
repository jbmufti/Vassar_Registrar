import java.util.List;
import java.util.ArrayList;

public class Student implements Comparable{
    String name;
    int idNum;
    int gradYear;
    int drawNumber;
    public List<Course> requests;
    public List<Course> schedule;
    
    double totalCredits = 0.0;
    /**
     * @param name: string the student's first and last name;
     * @param idNum: int the student's 999 number.
     * @param gradYear: 4 digit graduation year.
     * @param drawNumber: the draw number determines the student's place in the algorithm.
     */
    public Student(String name, int idNum, int gradYear,int drawNumber){
        this.name = name;
        this.idNum = idNum;
        this.gradYear = gradYear;
        this.drawNumber = drawNumber;
        this.requests = new ArrayList<Course>();
        this.schedule = new ArrayList<Course>();
    }
    
    /**
     * Returns true if idNumbers are the same;
     * @param Object: any possible object.  
     * 
     * @return boolean: true if idNumbers are the same.
     */
   public boolean equals(Object o){
        if((o instanceof Student)){
            return idNum == ((Student)(o)).idNum;
        }
        return false;
    }

    /**
     * ToString returns a string representation including 
     * name, graduation year and draw number.
     */
    public String toString(){
        return name + " " + gradYear + " " + drawNumber;
    }

    /**
     * Sorts the student by draw number and
     * class year.  
     * The first person should be a 4th year with draw number 1.
     * The last person should be a 1st year with a the largest draw number.
     * All 4th years come before all 3rd years, etc.
     * 
     * @return retval: 
     *    1 if the first student comes first, 
     *    0 if they are equal
     *    -1 if the second student comes firt.
     */
    public int compareTo(Object s){
        Student compare = (Student)s;
        if (this.gradYear < compare.gradYear) {
            return -1;
        }
        else if (this.gradYear > compare.gradYear) {
            return 1;
        }
        else {
            if (this.drawNumber < compare.drawNumber) {
                return -1;
            }
            else if (this.drawNumber > compare.drawNumber) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    /**
     * Check to see if the student is registered for any section of a course.
     * @param maybe: Course.  The potential course to register for.
     * 
     * @return boolean: true if the student is registered for any section of the course.
     */
    public boolean isRegisteredFor(Course maybe){
        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).title.equals(maybe.title)) 
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the total registered credits (limit is 4.5)
     */
    public double totalRegisteredCredits(){
        double total = 0.0;
        for (int i = 0; i < schedule.size(); i++) {
            total += schedule.get(i).credits;
        }
        return total;
    }

    /**
     * @param maybe: Course the potential course
     * 
     * @return true if the student already has something at that time.
     */
    public boolean hasAConflict(Course maybe){
        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).conflictsWith(maybe)) {
                return true;
            }
        }
        return false;
    }

}
