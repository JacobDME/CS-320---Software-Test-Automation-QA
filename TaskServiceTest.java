import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    // Creates a new TaskService before every test.
    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    // Creates a valid task that can be reused in the tests.
    private Task createValidTask(String taskID) {
        return new Task(
                taskID,
                "Study",
                "Complete CS 320 assignment"
        );
    }


    // ========================================
    // ADD TASK TESTS
    // ========================================

    @Nested
    @DisplayName("Add Task Tests")
    class AddTaskTests {

        @Test
        @DisplayName("Add a task with a unique ID")
        void testAddTask() {

            Task task = createValidTask("12345");

            assertDoesNotThrow(() -> {
                taskService.addTask(task);
            });
        }

        @Test
        @DisplayName("Add multiple tasks with unique IDs")
        void testAddMultipleTasks() {

            Task task1 = createValidTask("12345");
            Task task2 = createValidTask("12346");

            assertDoesNotThrow(() -> {
                taskService.addTask(task1);
                taskService.addTask(task2);
            });

            taskService.updateName("12345", "Homework");
            taskService.updateName("12346", "Reading");

            assertEquals("Homework", task1.getName());
            assertEquals("Reading", task2.getName());
        }

        @Test
        @DisplayName("Reject a null task")
        void testAddNullTask() {

            assertThrows(IllegalArgumentException.class, () -> {
                taskService.addTask(null);
            });
        }

        @Test
        @DisplayName("Reject tasks with duplicate IDs")
        void testDuplicateTaskID() {

            Task task1 = createValidTask("12345");

            Task task2 = new Task(
                    "12345",
                    "Homework",
                    "Complete another assignment"
            );

            taskService.addTask(task1);

            assertThrows(IllegalArgumentException.class, () -> {
                taskService.addTask(task2);
            });

            // Original task should still be the one stored.
            taskService.updateName("12345", "Reading");

            assertEquals("Reading", task1.getName());
            assertEquals("Homework", task2.getName());
        }
    }


    // ========================================
    // REMOVE TASK TESTS
    // ========================================

    @Nested
    @DisplayName("Remove Task Tests")
    class RemoveTaskTests {

        @Test
        @DisplayName("Remove a task using its ID")
        void testRemoveTask() {

            Task task = createValidTask("12345");

            taskService.addTask(task);
            taskService.removeTask("12345");

            // If the ID was removed, it should be available again.
            Task newTask = new Task(
                    "12345",
                    "Homework",
                    "Complete another assignment"
            );

            assertDoesNotThrow(() -> {
                taskService.addTask(newTask);
            });
        }

        @Test
        @DisplayName("Remove only the task with the specified ID")
        void testRemoveSpecificTask() {

            Task task1 = createValidTask("12345");
            Task task2 = createValidTask("12346");

            taskService.addTask(task1);
            taskService.addTask(task2);

            taskService.removeTask("12345");

            // Task 2 should still be stored and updatable.
            taskService.updateName("12346", "Homework");

            assertEquals("Homework", task2.getName());

            // Task 1 should no longer receive updates.
            taskService.updateName("12345", "Reading");

            assertEquals("Study", task1.getName());
        }
    }


    // ========================================
    // UPDATE TASK TESTS
    // ========================================

    @Nested
    @DisplayName("Update Task Tests")
    class UpdateTaskTests {


        // ========================================
        // NAME UPDATE TESTS
        // ========================================

        @Nested
        @DisplayName("Name Update Tests")
        class NameUpdateTests {

            @Test
            @DisplayName("Update a task name")
            void testUpdateName() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                taskService.updateName("12345", "Homework");

                assertEquals("Homework", task.getName());
            }

            @Test
            @DisplayName("Task ID remains unchanged after name update")
            void testTaskIDAfterNameUpdate() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                taskService.updateName("12345", "Homework");

                assertEquals("12345", task.getTaskID());
            }

            @Test
            @DisplayName("Reject a null name during an update")
            void testUpdateNameNull() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateName("12345", null);
                });

                assertEquals("Study", task.getName());
            }

            @Test
            @DisplayName("Reject an empty name during an update")
            void testUpdateNameEmpty() {

                Task task = createValidTask("12345");
                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateName("12345", "");
                });

                assertEquals("Study", task.getName());
            }

            @Test
            @DisplayName("Reject a whitespace-only name during an update")
            void testUpdateNameWhitespace() {

                Task task = createValidTask("12345");
                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateName("12345", "   ");
                });

                assertEquals("Study", task.getName());
            }

            @Test
            @DisplayName("Accept a name containing exactly 20 characters during an update")
            void testUpdateNameMaximumLength() {

                Task task = createValidTask("12345");
                taskService.addTask(task);

                String name = "12345678901234567890";
                taskService.updateName("12345", name);

                assertEquals(name, task.getName());
            }

            @Test
            @DisplayName("Reject a name longer than 20 characters during an update")
            void testUpdateNameTooLong() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateName(
                            "12345",
                            "123456789012345678901"
                    );
                });

                assertEquals("Study", task.getName());
            }

            @Test
            @DisplayName("Update only the task with the specified ID")
            void testUpdateSpecificTaskName() {

                Task task1 = createValidTask("12345");
                Task task2 = createValidTask("12346");

                taskService.addTask(task1);
                taskService.addTask(task2);

                taskService.updateName("12346", "Homework");

                assertEquals("Study", task1.getName());
                assertEquals("Homework", task2.getName());
            }
        }


        // ========================================
        // DESCRIPTION UPDATE TESTS
        // ========================================

        @Nested
        @DisplayName("Description Update Tests")
        class DescriptionUpdateTests {

            @Test
            @DisplayName("Update a task description")
            void testUpdateDescription() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                taskService.updateDescription(
                        "12345",
                        "Finish milestone"
                );

                assertEquals(
                        "Finish milestone",
                        task.getDescription()
                );
            }

            @Test
            @DisplayName("Task ID remains unchanged after description update")
            void testTaskIDAfterDescriptionUpdate() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                taskService.updateDescription(
                        "12345",
                        "Finish milestone"
                );

                assertEquals("12345", task.getTaskID());
            }

            @Test
            @DisplayName("Reject a null description during an update")
            void testUpdateDescriptionNull() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateDescription("12345", null);
                });

                assertEquals(
                        "Complete CS 320 assignment",
                        task.getDescription()
                );
            }

            @Test
            @DisplayName("Reject an empty description during an update")
            void testUpdateDescriptionEmpty() {

                Task task = createValidTask("12345");
                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateDescription("12345", "");
                });

                assertEquals(
                        "Complete CS 320 assignment",
                        task.getDescription()
                );
            }

            @Test
            @DisplayName("Reject a whitespace-only description during an update")
            void testUpdateDescriptionWhitespace() {

                Task task = createValidTask("12345");
                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateDescription("12345", "   ");
                });

                assertEquals(
                        "Complete CS 320 assignment",
                        task.getDescription()
                );
            }

            @Test
            @DisplayName("Accept a description containing exactly 50 characters during an update")
            void testUpdateDescriptionMaximumLength() {

                Task task = createValidTask("12345");
                taskService.addTask(task);

                String description =
                        "12345678901234567890123456789012345678901234567890";

                taskService.updateDescription("12345", description);

                assertEquals(description, task.getDescription());
            }

            @Test
            @DisplayName("Reject a description longer than 50 characters during an update")
            void testUpdateDescriptionTooLong() {

                Task task = createValidTask("12345");

                taskService.addTask(task);

                assertThrows(IllegalArgumentException.class, () -> {
                    taskService.updateDescription(
                            "12345",
                            "123456789012345678901234567890123456789012345678901"
                    );
                });

                assertEquals(
                        "Complete CS 320 assignment",
                        task.getDescription()
                );
            }

            @Test
            @DisplayName("Update only the task with the specified ID")
            void testUpdateSpecificTaskDescription() {

                Task task1 = createValidTask("12345");
                Task task2 = createValidTask("12346");

                taskService.addTask(task1);
                taskService.addTask(task2);

                taskService.updateDescription(
                        "12346",
                        "Finish milestone"
                );

                assertEquals(
                        "Complete CS 320 assignment",
                        task1.getDescription()
                );

                assertEquals(
                        "Finish milestone",
                        task2.getDescription()
                );
            }
        }
    }
}