import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements  Serializable {
    private ArrayList<Task> taskList;

    public ToDoList(){
        this.taskList = new ArrayList<>(); 
    }

    public void addTask(Task task){
        this.taskList.add(task); 
    }

    public Boolean removeTask(Task task){
        return this.taskList.remove(task);
    }

    public ArrayList<Task> getTasklist(){
        return this.taskList;
        
    }

    public void setTasklist(ArrayList<Task> list){
        this.taskList = list;
    }
    
    public void displayTasks(){
        for (Task item : this.getTasklist()) {
            System.out.println("Task: " + item.getTitle() + " ");
            System.out.print("Description: " + item.getDescription() + " ");
            System.out.print("Category: " + item.getCategory() + " ");
            System.out.println("\n");
        }
    }
}
