package com.onedirect;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Menu{
	
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	ArrayList<Student> students;   
	Menu(){
		 students=new SerializeTask().read();  //reads all the data from disk as soon as the main menu initializes
		 if(students==null)
			students=new ArrayList<>(); 
	}
	public void displayMenu() {    //Displays Main menu
		System.out.println("\n\nEnter your choice:\n1.Add User details\n"+
				"2.Display User details\n"+
				"3.Delete User details\n"+
				"4.Save User details\n"+
				"5.Exit");
		
		try {
			displaySubMenu(Integer.parseInt(br.readLine())); //displays sub Menu based on choice 
		} catch (NumberFormatException e) {
			System.out.println("Not a number");
		}catch (IOException e) {
			System.out.println("Choice Input Error");
		}
		displayMenu();
	}
	
	private void displaySubMenu(int choice) {
		switch(choice) {
			case 1: addUserMenu();
				break;
			case 2: displayUserMenu();
				break;
			case 3: deleteUserMenu();
				break;
			case 4: saveUserMenu();
				break;
			case 5: exitUserMenu();
				break;
			default: System.out.println("Invalid Choice");
		}
	}

	private void exitUserMenu() {
		String choice=null;
		System.out.print("Do you want to save all the changes?(y/n):");
		try {
			choice=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("choice input error");
		}
		if(choice.toLowerCase().equals("y"))
			saveUserMenu();
		else if(!choice.toLowerCase().equals("n"))
			System.out.println("Invalid choice entered");
			
		System.out.println("Exiting..Good Bye!");
		System.exit(1);
		
	}

	private void saveUserMenu() {
		SerializeTask task=new SerializeTask();
		task.write(students);
	}

	private void deleteUserMenu() {
		int roll=0;
		System.out.println("Enter roll no to delete the student");
		try {
			roll=Integer.parseInt(br.readLine());
			if(deleteStudent(roll)==true)
				System.out.println("Student with roll no="+ roll +"successfully deleted\n");
			else
				System.out.println("Student with roll no="+ roll+"does not exist");
		} catch (NumberFormatException e) {
			System.out.println("Choice not a number");
			deleteUserMenu();
		} catch (IOException e) {
			System.out.println("Choice input error");
			deleteUserMenu();
		}
		
	}
	

	private void displayUserMenu() {
		
		int choice=0;
		System.out.println("Choose sorting parameter to display"
				+ "\n1.Default"
				+ "\n2.Sort By Name"
				+ "\n3.Sort By Age"
				+ "\n4.Sort By Roll No"
				+ "\n5.Sort By Address"
				+ "\n6.Exit from Display");
		try {
			choice=Integer.parseInt(br.readLine());
			switch(choice) {
			case 1: displayStudentsInfo();
				break;
			case 2: Collections.sort(students,Comparator.comparing(Student::getName).thenComparingInt(Student::getRoll));
					displayStudentsInfo();
				break;
			case 3: Collections.sort(students,Comparator.comparingInt(Student::getAge).thenComparing(Student::getName));
					displayStudentsInfo();
				break;
			case 4: Collections.sort(students,Comparator.comparingInt(Student::getRoll).thenComparing(Student::getName));
					displayStudentsInfo();
				break;
			case 5: Collections.sort(students,Comparator.comparing(Student::getAddress).thenComparing(Student::getName));
					displayStudentsInfo();
				break;
			case 6: return;
				
			default: System.out.println("Invalid Choice");
						displayUserMenu();
			}
		} catch (NumberFormatException e) {
			System.out.println("Choice not a number");
			displayUserMenu();
		} catch (IOException e) {
			System.out.println("Choice input error");
			displayUserMenu();
		}
		
		
	}
	

	private void addUserMenu() {
		Student student =new Student();
		String name,addr;
		int age,roll;
		ArrayList<Course>courses;
		while((name=getName())==null);
		student.setName(name);
		
		while((age=getAge())==-1);
		student.setAge(age);
		
		while((roll=getRoll())==-1);
		student.setRoll(roll);
		
		while((addr=getAddress())==null);
		student.setAddress(addr);
		
		while((courses=getCourses())==null);
		student.setEnrolledCourses(courses);		
		
		students.add(student);  //adding student to students list
		Collections.sort(students,Comparator.comparing(Student::getName).thenComparingInt(Student::getRoll));
	}
	
	private String getName() {
		String name=null;
		System.out.print("Full Name:");
		try {
			name=br.readLine();
			if(validateName(name)==false) 
				return null;
			
		} catch (IOException e) {
			System.out.println("Name input error");
			return null;
		}
		
		return name;
	}
	
	private String getAddress() {
		String addr=null;
		System.out.print("Address:");
		try {
			addr=br.readLine();
			if(validateAddress(addr)==false) 
				return null;
			
		} catch (IOException e) {
			System.out.println("Address input error");
			return null;
		}
		
		return addr;
	}
	
	private int getAge() {
		int age=0;
		System.out.print("Age:");
		try {
			age=Integer.parseInt(br.readLine());
			if(validateAge(age)==false) 
				return -1;
			
		} catch (IOException e) {
			System.out.println("age input error");
			return -1;
		}catch (NumberFormatException e) {
			System.out.println("Not a number");
			return -1;
		}
		
		return age;
	}
	private int getRoll() {
		int roll=0;
		System.out.print("Roll No:");
		try {
			roll=Integer.parseInt(br.readLine());
			if(validateRoll(roll)==false) 
				return -1;
			
			
		} catch (IOException e) {
			System.out.println("Roll number input error");
			return -1;
		}catch (NumberFormatException e) {
			System.out.println("Not a number");
			return -1;
		}
		
		return roll;
		
	}
	
	private ArrayList<Course> getCourses(){
		System.out.println("Select Courses(Note: 4 courses are Mandatory, "
				+ "Enter your choice as 1,2,3,4,5,6):"
				+ "\n1. A\t2. B\t3. C\t4. D\t5. E\t6. F");
		ArrayList<Course> courses=new ArrayList<Course>();
		int type;
		try {
			// following code monitors the enrolling of 4 mandatory subjects
			for(int i=0;i<4;i++) {
				System.out.print("Course"+(i+1)+":");
				type=Integer.parseInt(br.readLine());
				if(type<1||type>6) {
					System.out.println("Invalid Course");
					i--;
					continue;
				}
				if(validateCourse(courses,Course.available.get(type-1))==false)
					i--;
				else
					courses.add(new Course(Course.available.get(type-1)));

			}
			
			// following code monitors the enrolling of extra subjects
			String choice=null;
			System.out.print("Want to enroll some more courses?(y/n):");
			choice=br.readLine();
			if(choice.toLowerCase().equals("n"))
				return courses;
			else {
				
				int limit=0;
				while(limit!=1&&limit!=2) {
					System.out.print("Enter the number of courses as 1 or 2:");
					limit=Integer.parseInt(br.readLine());
				}
				for(int i=1;i<=limit;i++) {
					System.out.print("Course"+(4+i)+":");
					type=Integer.parseInt(br.readLine());
					if(type<1||type>6) {
						System.out.println("Invalid Course");
						i--;
						continue;
					}
					if(validateCourse(courses,Course.available.get(type-1))==false)
						i--;
					else
						courses.add(new Course(Course.available.get(type-1)));

				}
					
				}
			}catch(IOException e) {
			System.out.println("Course Input Error");
			return null;
		}catch(NumberFormatException e) {
			System.out.println("Input is not a number..Enter all the courses again!\n");
			courses.clear();
			return null;
		}
		
		return courses;
	}

	
	private boolean validateName(String name) {
		if(name.equals("")) {
			System.out.println("Name cannot be blank");
			return false;
		}
		Pattern pattern =Pattern.compile("^[a-zA-Z\\s]*$");
		Matcher matcher=pattern.matcher(name);
		if(!matcher.matches()) {
			System.out.println("Only letters or spaces allowed");
			return false;
		}
		return true;
	}
	private boolean validateAddress(String addr) {
		if(addr.equals("")) {
			System.out.println("Address cannot be blank");
			return false;
		}
		return true;
	}
	
	private boolean validateAge(int age) {
		if(age==0) {
			System.out.println("Age cannot be 0");
			return false;
		}
		return true;
	}
	
	private boolean validateRoll(int roll) {
		for(Student s:students) {
			if(s.getRoll()==roll) {
				System.out.println("Student with rollno= "+roll+" already present\n");
				return false;
			}
		}
		return true;
	}
	
	private boolean validateCourse(ArrayList<Course>courses,String type) {
		for(Course c:courses)
			if(c.toString().equals(type)) {
				System.out.println("Course Aready entered! Please enter another course");
				return false;
			}
		return true;
	}
	
	private boolean deleteStudent(int roll) {
		for(Student s:students) {
			if(s.getRoll()==roll) {
				students.remove(s);
				return true;
			}
		}
		
		return false;
	}
	
	private void displayStudentsInfo() {
		if(students.isEmpty()) {
			System.out.println("Nothing to display");
			return;
		}
		System.out.printf("%-30s|%-6s\t|%-15s|%-30s\t|Courses Enrolled\n\n","Name","Roll No ","Age","Address");
	
		for(Student s:students) {
			System.out.printf("%-30s%-15d%-15d%-30s\t",s.getName(),s.getRoll(),s.getAge(),s.getAddress());
			for(Course c:s.getEnrolledCourses()) {
				System.out.print(c+",");
			}
			System.out.print("\t\n");
		}
	}
	

}
