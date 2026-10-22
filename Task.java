public class Task {

 private final String taskID;
 private String name;
 private String description;

 public Task(String taskID, String name, String description) {
  if (taskID == null || taskID.isBlank() || taskID.length() > 10) {
   throw new IllegalArgumentException("Invalid Task ID.");
  }

  if (name == null || name.isBlank() || name.length() > 20) {
   throw new IllegalArgumentException("Invalid Name.");
  }


  if (description == null || description.isBlank() || description.length() > 50) {
   throw new IllegalArgumentException("Invalid description.");
  }

  this.taskID = taskID;
  this.name = name;
  this.description = description;
 }

 public String getTaskID() {
  return taskID;
 }

 public String getName() {
  return name;
 }

 public String getDescription() {
  return description;
 }

 public void setName(String name) {
  if (name == null || name.isBlank() || name.length() > 20) {
   throw new IllegalArgumentException("Invalid Name.");
  }
  this.name = name;
 }

 public void setDescription(String description) {
  if (description == null || description.isBlank() || description.length() > 50) {
   throw new IllegalArgumentException("Invalid description.");
  }
  this.description = description;
 }

}
