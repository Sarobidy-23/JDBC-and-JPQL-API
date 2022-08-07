package Repository;

import Model.Student;
import Model.StudentRepositoryInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements StudentRepositoryInterface {
    private static String user = "postgres";
    private static String database = "school";
    private static String password = "psql";
    private static String url = "jdbc:postgresql://localhost:5432/";
    private static Connection connection;
    private static StudentRepository instance = new StudentRepository();
    public static StudentRepository getInstance(){
        return instance;
    }

    public void connectDataBase()  {
        try {
            Class.forName("org.postgresql.Driver");
            url = url+database;
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        try{
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM student;");
            while (result.next()){
                String id = result.getString("idStudent");
                String name = result.getString("name");
                boolean active = result.getBoolean("active");
                Student std = new Student(id, name, active);
                list.add(std);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    @Override
    public Student save(Student s) {
        try {
            Statement stm = connection.createStatement();
            String id = s.getIdStudent();
            String name = s.getName();
            boolean active = s.isActive();
            stm.executeUpdate("INSERT INTO student(idStudent, name, active) VALUES ('"+id+"','"+name+"',"+active+")");
        }catch (Exception e){
            System.out.println(e);
        }

        return s;
    }

    @Override
    public void deleteById(String id) {
        try{
            Statement stm = connection.createStatement();
            stm.executeUpdate("DELETE FROM student WHERE idStudent = '"+id+"';");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Student updateNameById(String idStudent, String newName) {
        Student std = new Student("STD","fictif",true);
        try{
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE student SET name = '"+newName+ "'WHERE idStudent = '"+idStudent+"'");

            ResultSet result = stm.executeQuery("SELECT * FROM student WHERE name = '"+newName+"';");
            while (result.next()){
                std.setIdStudent(result.getString("idStudent"));
                std.setName(result.getString("name"));
                std.setActive(result.getBoolean("active"));
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return std;
    }

    @Override
    public List<Student> findWhereNameLike(String query) {
        List<Student> lists = new ArrayList<>();
        try{
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM student WHERE name ILIKE '%"+query+"%';");
            while (result.next()){
                String id = result.getString("idStudent");
                String name = result.getString("name");
                boolean active = result.getBoolean("active");
                Student std = new Student(id, name, active);
                lists.add(std);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return lists;
    }
}
