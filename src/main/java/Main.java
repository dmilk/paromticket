import security.DAO.Factory;
import security.domain.Role;
import security.domain.SecurityUser;
import security.domain.User;

import java.sql.SQLException;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws SQLException {

        SecurityUser securityUser = Factory.getInstance().getUserDAO().authUser("admin","12345");
        System.out.println("*************");
        System.out.println(securityUser.getUserName());
//        Set<Role> roles = Factory.getInstance().getUserDAO().getUserRolesById(userId);
        for(Role role : securityUser.getRoles())
            System.out.println("Role: " + role.getRoleName());




//        Student s1 = new Student();
//        Student s2 = new Student();
//        s1.setName("Ivanov Ivan");
//        s1.setAge(21);
//        s2.setName("Petrova Alisa");
//        s2.setAge(22);
//
//        Factory.getInstance().getStudentDAO().addStudent(s1);
//        Factory.getInstance().getStudentDAO().addStudent(s2);

//        List<Student> students = Factory.getInstance().getStudentDAO().getAllStudents();
//        System.out.println("---All students---");
//        for (int i = 0; i < students.size(); i++) {
//            System.out.println("Name: " + students.get(i).getName() +
//                    " , Age: " + students.get(i).getAge() +
//                    ", id: " + students.get(i).getId());
//            System.out.println("---");
//        }

//        Test t1 = new Test();
//        Test t2 = new Test();
//        t1.setTname("Math test");
//        t2.setTname("Phys test");
//
//        Factory.getInstance().getTestDAO().addTest(t1);
//        Factory.getInstance().getTestDAO().addTest(t1);

//        List<Test> tests = Factory.getInstance().getTestDAO().getAllTests();
//        System.out.println("---All test---");
//        for (Test test : tests) {
//            System.out.println("Tname: " + test.getTname() +
//                    ", id: " + test.getId());
//            System.out.println("---");
//        }

//        List<Statistics> stats =Factory.getInstance().getStudentDAO().getStudentStat(1L);
//        for (Statistics stat : stats) {
//            System.out.println(stat);
//        }

//        System.out.println(stat1);
//        Statistics statistics = Factory.getInstance().getStudentDAO().getStudentTests(st1);

        System.exit(0);

    }
}
