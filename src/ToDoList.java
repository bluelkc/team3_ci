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
			tOld.setDescription(new_desc);
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
	
	public void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
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
					}
					break;
				case "view":
					if (argLeng != 1) {
						printArgErrMsg();
					}
					break;
				case "edit":
					if (argLeng != 2) {
						printArgErrMsg();
					}
					break;
				case "delete":
					if (argLeng != 1) {
						printArgErrMsg();
					}
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
}