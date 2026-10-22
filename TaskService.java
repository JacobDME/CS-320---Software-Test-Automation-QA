import java.util.HashMap;

public class TaskService
{
    private final HashMap<String, Task> tasks = new HashMap<>();

    public void addTask(Task task)
    {
         if (task == null)
         {
             throw new IllegalArgumentException("Task cannot be null.");
         }

         if (tasks.containsKey(task.getTaskID()))
         {
             throw new IllegalArgumentException("Task ID already exists.");
         }

         tasks.put(task.getTaskID(), task);
    }

    public void removeTask(String taskId) {tasks.remove(taskId);}

    public void updateName(String taskId, String name)
    {
        Task task = tasks.get(taskId);

        if (task != null)
        {
        task.setName(name);
        }

    }

    public void updateDescription(String taskId, String description)
    {
        Task task = tasks.get(taskId);

        if (task != null)
        {
            task.setDescription(description);
        }

    }
}
