public class Course implements Comparable {
    String dept;
    int courseNum;
    int section;
    String title;
    double credits;
    int maxEnrollment;
    String daysOfTheWeek;
    int startTime;
    int endTime;
    int duration;
    String timeString;
    String loc;
    String instructor;

    /**
     * Constructor takes in all values from the CSV.
     */
    public Course(String dept, int courseNum, int section, String title, double credits, int maxEnrollment, 
    String daysOfTheWeek, int startTime,int endTime,int duration,String timeString, String loc, String instructor){
        //AFRS,100,51,Intro to Africana Studies,1,20,TR,810,885,75,0130PM-0245PM,BH 305,"Harriford, Diane"
        this.dept = dept;
        this.courseNum = courseNum;
        this.section = section;
        this.title = title;
        this.credits = credits;
        this.maxEnrollment = maxEnrollment;
        this.daysOfTheWeek = daysOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.timeString = timeString;
        this.loc = loc;
        this.instructor = instructor;
    }

    /**
     * Key is a string representation of department-courseNumber-section.
     * Example: CMPU-102-51
     */
    public String getKey(){
        return dept + "-" + courseNum + "-" + section;
    }


    /**
     * Returns true if key is the same.  (Department, Course Number, and Section).
     * 
     */
    public boolean equals(Object o){
        if(!(o instanceof  Course)){
            return false;
        }
        Course c = (Course) o;
        return this.getKey().equals(c.getKey());
    }

    /**
     * String representation as it might appear on askBanner.
     */
    public String toString(){
        return getKey() + " " + title + "\t" + credits + "\t" + daysOfTheWeek + " " + timeString;
    }

    /**
     * Should sort classes by department, then course number, then section. (just like
     * askBanner)
     */
    public int compareTo(Object o){
        Course compare = (Course)o;
        if (this.dept.compareTo(compare.dept) < 0) {
            return -1;
        }
        else if (this.dept.compareTo(compare.dept) > 0) {
            return 1;
        }
        else {
            if (this.courseNum < compare.courseNum) {
                return -1;
            }
            else if (this.courseNum > compare.courseNum) {
                return 1;
            }
            else {
                if (this.section < compare.section) {
                    return -1;
                }
                else if (this.section < compare.section) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    /**
     * A method that checks times and days to determine whether or not they overlap.
     */
    public boolean conflictsWith(Course maybe){
        boolean dayOverlap = false;
        for (int i = 0; i < daysOfTheWeek.length(); i++) {
            for (int j = 0; j < maybe.daysOfTheWeek.length(); j++) {
                if (daysOfTheWeek.charAt(i) == maybe.daysOfTheWeek.charAt(j)) {
                    dayOverlap = true;
                }
            }
        }
        if (dayOverlap) {
            if (this.startTime <= maybe.startTime && this.endTime > maybe.startTime) {
                return true;
            }
            else if (this.startTime > maybe.startTime && this.endTime < maybe.startTime) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
