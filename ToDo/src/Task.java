
import java.io.Serializable;

public class Task implements Serializable{
        private String description;
        private String category;
        private String title;

        public Task(String name, String desc, String cat){
            this.title = name;
            this.description = desc;
            this.category = cat;
        }

        public Task(String name, String desc){
            this.title = name;
            this.description = desc;
            this.category = "none";
        }

        public Task(){
            this.title = "Untitled";
            this.description = "Default description.";
            this.category = "none";
        }

        public String getTitle(){
            return this.title;
        }

        public void setTitle(String name){
            this.title = name;
        }

        public String getDescription(){
            return this.description;
        }

        public void setDescription(String desc){
            this.description = desc;
        }

        public String getCategory(){
            return this.category;
        }

        public void setCategory(String cat){
            this.category = cat;
        }

        public void displayTask(){
            System.out.println("Task: " + this.getTitle() + " ");
            System.out.print("Description: " + this.getDescription() + " ");
            System.out.print("Category: " + this.getCategory() + " ");
            System.out.println("\n");
        }
    }
