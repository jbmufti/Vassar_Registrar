import java.util.*;
import java.util.Arrays;

/**
 * Write a description of class Algorithm here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Algorithm
{
    HashMap<Course, Integer> enrollment;
    HashMap<Integer, PriorityQueue<Student>> queue = new HashMap<Integer, PriorityQueue<Student>>();
    HashMap<Integer, Stack<Student>> stack = new HashMap<Integer, Stack<Student>>();
    
    int freshman;
    int sophomore;
    int junior;
    int senior;

    public Algorithm(List<Student> mapStudents,  HashMap<Course,Integer> enrollment){
        this.enrollment = enrollment;
        
        //Creates the queue
        List<Integer> keys = new ArrayList<Integer>();
        for (int i = 0; i < mapStudents.size(); i++) {
            int year = mapStudents.get(i).gradYear;
            if (queue.containsKey(year) == false) {
                PriorityQueue<Student> pq = new PriorityQueue<Student>();
                pq.add(mapStudents.get(i));
                queue.put(year, pq);
                keys.add(year);
                stack.put(year, new Stack<Student>());
            }
            else {
                //PriorityQueue<Student> pq = queue.get(year);
                queue.get(year).add(mapStudents.get(i));
                //queue.put(year, pq);
            }
        }
        //Creates the stack
        
        Collections.sort(keys);
        
        this.senior = keys.get(0);
        this.junior = keys.get(1);
        this.sophomore = keys.get(2);
        this.freshman = keys.get(3);
    }


    public void run(){
        /* Description of the algorithm from the Registrar's website:
         * 
         * Entry into a section is determined by the combination of your class year, the priority you give each section, and your draw number.
         * Seniors’ requests are processed first followed sequentially by juniors’, sophomores’, and first-years requests.
         * Your requests are considered in the order that you list them on the registration screen, with the first item having the highest priority. 
         * If one of your requests cannot be filled, then the next item in your list will be considered instead.
         * For your class year, your draw number determines when one of your requests is considered. 
         * Your top request is considered immediately after the top requests of all of the students in your class with lower draw numbers. 
         * As mentioned above, if your top request cannot be granted you will be enrolled in the first request on your list that can be.
         * 
         * In a second pass through the requests from your class, your top request among your remaining requests will be considered immediately 
         * before all of the students in your class with lower draw numbers. 
         * That is, the draw numbers work in reverse compared to the first pass. The remaining passes through the requests from your class 
         * continue the pattern of the first two passes, switching direction through the draw numbers on each pass.
         * You may list multiple sections of the same course among your requests but you will be enrolled only in the first one on your list 
         * that is available. You will not be enrolled in multiple sections of the same course.
         * You may also list sections of different courses that meet at the same time but you will be enrolled only in the 
         * first one on your list that is available. You will not be enrolled in sections with time conflicts.
         * 
         */
        int pass = 0;
        while (pass < 4) {
            forwards(senior);
            forwards(junior);
            forwards(sophomore);
            forwards(freshman);
            
            if (pass < 3) {
                backwards(senior);
                backwards(junior);
                backwards(sophomore);
                backwards(freshman);
            }
            pass++;
        }
    }

    /*
     * Enrolls one student in the next class on their request list
     * Removes all selected/inelligible classes from the request list
     */
    public void enroll(Student s) {
        while (s.requests.size() > 0) {
            Course req = s.requests.get(0);
            if (s.totalCredits + req.credits <= 4.5 
            && enrollment.get(req) < req.maxEnrollment 
            && !s.isRegisteredFor(req) 
            && !s.hasAConflict(req)) {
                int enrollOne = enrollment.get(req) + 1;
                enrollment.put(req, enrollOne);
                s.totalCredits += req.credits;
                s.schedule.add(req);
                s.requests.remove(req);
                break;
            }
            // else if (enrollment.get(req) >= req.maxEnrollment) {
                // s.requests.remove(req); 
                // //continue;
            // }
            // else if (s.isRegisteredFor(req)) {
                // s.requests.remove(req);
                // //continue;
            // }
            // else if (s.hasAConflict(req)) {
                // s.requests.remove(req);
                // //continue;
            // }
            else {
                s.requests.remove(req);
            }
        }
    }
    public void forwards (int grade) {
        for (int i = 0; i <= queue.get(grade).size()+1; i++) {
            Student s = queue.get(grade).poll(); //remove from queue
            enroll(s);
            stack.get(grade).push(s); //add to stack
        }
    }
    public void backwards(int grade) {
        for (int i = 0; i <= stack.get(grade).size()+1; i++) {
            Student s = stack.get(grade).pop(); //remove from stack
            enroll(s);
            queue.get(grade).add(s); //add to queue
        }
    }
   
    public void printEnrollment(){
        //Print the toString of the student, followed by their schedule (using course toString).
        /*
         * Hector Tran 2023 1
         * CMPU-145-51 Foundations/Computer Science    1.0    TRF 1200PM-0115PM
         * EDUC-361-51 Sem: Math/Science/Elem Classrm    1.0    R 0310PM-0610PM
         * ECON-235-51 Sports Economics    1.0    TR 1030AM-1145AM
         * PHED-105-51 Foundations of Wellness    0.5    TR 0900AM-1015AM
         * --------------------
         * Chace Sanford 2023 2
         * GNCS-355-51 Childhood/Childrn 19C Britain    1.0    R 0310PM-0510PM
         * ART-318-51 Building the Museum    1.0    T 0100PM-0300PM
         * CHEM-352-51 Phys Chem-Molec Structr    1.0    MW 1030AM-1145AM
         * INTL-109-51 A Lexicon of Forced Migration    1.0    TR 1030AM-1145AM
         * --------------------
         * etc...
         */
        backwards(senior);
        backwards(junior);
        backwards(sophomore);
        backwards(freshman);
        for (int i = 0; i <= queue.get(senior).size()+1; i++) {
            Student s = queue.get(senior).poll();
            System.out.println(s);
            for (int j = 0; j < s.schedule.size(); j++) {
                System.out.println(s.schedule.get(j));
            }
            System.out.print("--------------------\n");
        }
        for (int i = 0; i <= queue.get(junior).size()+1; i++) {
            Student s = queue.get(junior).poll();
            System.out.println(s);
            for (int j = 0; j < s.schedule.size(); j++) {
                System.out.println(s.schedule.get(j));
            }
            System.out.print("--------------------\n");
        }
        for (int i = 0; i <= queue.get(sophomore).size()+1; i++) {
            Student s = queue.get(sophomore).poll();
            System.out.println(s);
            for (int j = 0; j < s.schedule.size(); j++) {
                System.out.println(s.schedule.get(j));
            }
            System.out.print("--------------------\n");
        }
        for (int i = 0; i <= queue.get(freshman).size()+1; i++) {
            Student s = queue.get(freshman).poll();
            System.out.println(s);
            for (int j = 0; j < s.schedule.size(); j++) {
                System.out.println(s.schedule.get(j));
            }
            System.out.print("--------------------\n");
        }
    }
}
