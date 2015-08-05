import java.io.*;
import java.util.*;
public class StudentDataBase 
{
ArrayList students;
//DataInputStream dins;
//DataOutputStream dots;
BufferedWriter bw;
BufferedReader br;
File f;
StudentDataBase()  
{
students = new ArrayList<Student>();
	try {
		 f = new File("Student.txt");
		bw = new BufferedWriter(new FileWriter(f));
		br = new BufferedReader(new FileReader(f));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void addStudent(String Id,String name,String gender,String status,String Email)
{
	try {
		bw.write(Id+" "+name+" "+gender+" "+status+" "+Email);
		bw.newLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}