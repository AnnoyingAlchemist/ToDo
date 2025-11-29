import java.io.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        ToDoList toDo = new ToDoList();
        String name;
        String description;
        String category;
        String command;
        Scanner scan = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);

        File taskFile = new File("tasks.ser");

        // Check if file exists
        if (!taskFile.exists()) {
            System.out.println("Warning: Task file was not found.");
        } else {
            try (
                FileInputStream filein = new FileInputStream(taskFile);
                ObjectInputStream in = new ObjectInputStream(filein)
                ) {
                // Deserialize the object from the file
                toDo = (ToDoList) in.readObject();  
            } catch (EOFException e) {
                System.out.println("Task file is empty.");
            } catch (Exception e) {
                System.out.println("Error reading from the task file: " + e.toString());
            }
        }

        //System.out.println("Tasks:");
        //toDo.displayTasks();
        System.out.println("__     _____           _         _ _     _    __   \n" + //
                        "\\ \\   |_   _|         | |       | (_)   | |   \\ \\  \n" + //
                        " \\ \\    | | ___     __| | ___   | |_ ___| |_   \\ \\ \n" + //
                        "  > >   | |/ _ \\   / _` |/ _ \\  | | / __| __|   > >\n" + //
                        " / /    | | (_) | | (_| | (_) | | | \\__ \\ |_   / / \n" + //
                        "/_/     \\_/\\___/   \\__,_|\\___/  |_|_|___/\\__| /_/  \n" + //
                        "                                                   \n" + //
                        "                                                   ");
        System.out.println("Welcome to the ToDo list App! Please enter a command to get started, or type 'help' for a list of commands.\n");

        // User menu for commands
        outerloop:
        while (true) {
            try {
                command = scan.nextLine().toLowerCase();
            } catch (Exception e) {
                command = "???";
            }

            switch (command) {
                case "help":
                    System.out.println("Commands: \nadd - Add a task to the task list\nremove - Remove a task from the task list\ndisplay - Display all currently added tasks\nquit - quit the program\ncat display - Display Tasks by specified category\n");
                    break;
                case "add":
                    System.out.println("Type the name for the task: ");
                    name = scan.nextLine();
                    System.out.println("\n");
                    System.out.println("Type the description for the task: ");
                    description = scan.nextLine();
                    System.out.println("\n");
                    System.out.println("Type the category for the task: ");
                    category = scan.nextLine();
                    System.out.println("\n");
                    toDo.addTask(new Task(name, description, category));
                    System.out.println("Task added successfully!");

                    saveToFile(toDo);
                    break;

                case "remove":
                    toDo.displayTasks();
                    System.out.println("Type the index of the task you want to remove: ");
                    try {
                        int index = scanInt.nextInt();
                        toDo.removeTask(toDo.getTasklist().get(index));
                        System.out.println("Task removed successfully!");

                        saveToFile(toDo);
                    } catch (Exception e) {
                        System.out.println("Something went wrong! (Is the index of the task valid?)");
                    }
                    break;

                case "display":
                    if(toDo.getTasklist().isEmpty()){
                        System.out.println("You currently have no tasks to display.\n");
                    }
                    else{
                        System.out.println("          _____         _                 \n" + //
                                                        "         |_   _|       | |                \n" + //
                                                        " ______    | | __ _ ___| | _____   ______ \n" + //
                                                        "|______|   | |/ _` / __| |/ / __| |______|\n" + //
                                                        "           | | (_| \\__ \\   <\\__ \\         \n" + //
                                                        "           \\_/\\__,_|___/_|\\_\\___/         \n" + //
                                                        "                                          \n" + //
                                                        "                                          ");
                        toDo.displayTasks();
                        System.out.println("                                            \n" + //
                                                        "                                            \n" + //
                                                        " ______   ______   ______   ______   ______ \n" + //
                                                        "|______| |______| |______| |______| |______|\n" + //
                                                        "                                            \n" + //
                                                        "                                            \n" + //
                                                        "                                            \n" + //
                                                        "                                            ");
                    }
                    break;
                case "cat display":
                    
                    if(toDo.getTasklist().isEmpty()){
                        System.out.println("You currently have no tasks to display.\n");
                    }

                    
                    else{
                        System.out.println("Please type the category you would like to view: ");
                        category = scan.nextLine().toLowerCase();

                        System.out.println("          _____         _                 \n" + //
                                                        "         |_   _|       | |                \n" + //
                                                        " ______    | | __ _ ___| | _____   ______ \n" + //
                                                        "|______|   | |/ _` / __| |/ / __| |______|\n" + //
                                                        "           | | (_| \\__ \\   <\\__ \\         \n" + //
                                                        "           \\_/\\__,_|___/_|\\_\\___/         \n" + //
                                                        "                                          \n" + //
                                                        "                                          ");
                        for(Task task: toDo.getTasklist()){
                            if(task.getCategory().toLowerCase().equals(category)){
                                task.displayTask();
                            }
                        }
                        System.out.println("                                            \n" + //
                                                        "                                            \n" + //
                                                        " ______   ______   ______   ______   ______ \n" + //
                                                        "|______| |______| |______| |______| |______|\n" + //
                                                        "                                            \n" + //
                                                        "                                            \n" + //
                                                        "                                            \n" + //
                                                        "                                            ");
                    }
                    break;
                case "quit":
                    System.out.println("\nquitting...");
                    break outerloop;

                default:
                    System.out.println("Unsupported Command");
                    break;
            }
        }

        scan.close();
        scanInt.close();
    }

    // Should only be called after toDo is updated by the user 
    private static void saveToFile(ToDoList toDo) {
        try (FileOutputStream fileout = new FileOutputStream("tasks.ser");
             ObjectOutputStream outStream = new ObjectOutputStream(fileout)) {
            outStream.writeObject(toDo);
            //System.out.println("Task list saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving task list: " + e.toString());
        }
    }
}
