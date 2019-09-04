import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;

public class ToDoList {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();
	
	public void addTask (Task task) {
		tasks.put(task.getDescription(), task);
	}
	public void completeTask(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			task.setComplete(true);
		};
	}
	public boolean getStatus(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			return task.isComplete();
		};
		return false;
	}
	public Task getTask(String description) {
		return tasks.get(description);
	}
	public Task removeTask(String description) {
		return tasks.remove(description);
	}
	public Task editTask(String old_desc, String new_desc) {
		Task tOld= tasks.get(old_desc);
		if(tOld != null ) {
			Task tNew = new Task(new_desc, tOld.isComplete());
			tasks.remove(tOld.getDescription());
			tasks.put(tNew.getDescription(), tNew);
			return tNew;
		}
		return tOld;		 
	}
	public Collection<Task> getAllTasks() {
		return tasks.values();
	}
	public Collection<Task> getCompletedTasks() {
		Collection<Task> completedTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == true) completedTasks.add(task);
		return completedTasks;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		ToDoList tdl = new ToDoList();
		while(true) {
			printMainMenu();
			String cmd = sc.nextLine();
			String[] cmdArr = cmd.trim().split(" ");
			String firstCmd = cmdArr[0];
			int argLeng = cmdArr.length - 1;
			
			switch(firstCmd) {
				case "add":
					if (argLeng != 1) {
						printArgErrMsg();
						break;
					}
					Task t1 = new Task(cmdArr[1]);
					tdl.addTask(t1);
					printAddMsg(t1);
					break;
					
				case "view":
					if (argLeng != 1) {
						printArgErrMsg();
						break;
					}
					Task t2 = tdl.getTask(cmdArr[1]);
					if (t2 == null) {
						printArgErrMsg();
						break;
					}
					printViewMsg(t2);
					break;
					
				case "edit":
					if (argLeng != 2) {
						printArgErrMsg();
						break;
					}
					Task t3 = tdl.editTask(cmdArr[1], cmdArr[2]);
					if (t3 == null) {
						printArgErrMsg();
					} else {
						printEditMsg(t3);
					}
					break;
				case "delete":
					if (argLeng != 1) {
						printArgErrMsg();
						break;
					}
					Task t4 = tdl.getTask(cmdArr[1]);
					if (t4 == null) {
						printArgErrMsg();
						break;
					}
					tdl.removeTask(t4.getDescription());
					printDeleteMsg(t4);
					break;
			}
		}
	}
	
	private static void printMainMenu() {
		
		System.out.println("***********************************************\n" 
				         + "***    Add Task: add <description>          ***\n"
						 + "***   View Task: view <description>         ***\n"
				         + "***   Edit Task: edit <old_desc> <new_desc> ***\n"
						 + "*** Delete Task: delete <description>       ***\n"
				         + "***                                         ***\n"
						 + "*** Please type a command to proceed...     ***\n"
				         + "***********************************************\n");
	}
	
	private static void printArgErrMsg() {
		System.out.println("Illegal paramenters.");
	}
	
	private static void printAddMsg(Task task) {
		System.out.println("A new task has been added.");
		System.out.println(task.toString());
	}
	
	private static void printViewMsg(Task task) {
		System.out.println("The task details is as follow:");
		System.out.println(task.toString());
	}
	
	private static void printEditMsg(Task task) {
		System.out.println("The task has been edited with the following details:");
		System.out.println(task.toString());
	}
	
	private static void printDeleteMsg(Task task) {
		System.out.println("The task," + task.getDescription() + " has been removed.");
	}
}