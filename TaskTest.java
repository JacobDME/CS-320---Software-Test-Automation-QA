import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    // Creates a valid task that can be reused throughout the tests.
    private Task createValidTask() {
        return new Task(
                "12345",
                "Study",
                "Complete CS 320 assignment"
        );
    }

    // ========================================
    // TASK ID TESTS
    // ========================================

    @Nested
    @DisplayName("Task ID Tests")
    class TaskIDTests {

        @Test
        @DisplayName("Accept a valid task ID")
        void testValidTaskID() {
            Task task = createValidTask();

            assertEquals("12345", task.getTaskID());
        }

        @Test
        @DisplayName("Reject a null task ID")
        void testTaskIDNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        null,
                        "Study",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Reject an empty task ID")
        void testTaskIDEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "",
                        "Study",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only task ID")
        void testTaskIDWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "   ",
                        "Study",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Accept a task ID containing exactly 10 characters")
        void testTaskIDMaximumLength() {
            Task task = new Task(
                    "1234567890",
                    "Study",
                    "Complete CS 320 assignment"
            );

            assertEquals("1234567890", task.getTaskID());
        }

        @Test
        @DisplayName("Reject a task ID longer than 10 characters")
        void testTaskIDTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345678901",
                        "Study",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Task ID remains unchanged when other fields are updated")
        void testTaskIDCannotBeUpdated() {
            Task task = createValidTask();

            task.setName("Homework");
            task.setDescription("Finish milestone");

            assertEquals("12345", task.getTaskID());
        }
    }

    // ========================================
    // NAME TESTS
    // ========================================

    @Nested
    @DisplayName("Name Tests")
    class NameTests {

        @Test
        @DisplayName("Accept a valid task name")
        void testValidName() {
            Task task = createValidTask();

            assertEquals("Study", task.getName());
        }

        @Test
        @DisplayName("Reject a null task name")
        void testNameNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        null,
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Reject an empty task name")
        void testNameEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only task name")
        void testNameWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "   ",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Accept a task name containing exactly 20 characters")
        void testNameMaximumLength() {
            String name = "12345678901234567890";

            Task task = new Task(
                    "12345",
                    name,
                    "Complete CS 320 assignment"
            );

            assertEquals(name, task.getName());
        }

        @Test
        @DisplayName("Reject a task name longer than 20 characters")
        void testNameTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "123456789012345678901",
                        "Complete CS 320 assignment"
                );
            });
        }

        @Test
        @DisplayName("Update the task name with a valid value")
        void testUpdateName() {
            Task task = createValidTask();

            task.setName("Homework");

            assertEquals("Homework", task.getName());
        }

        @Test
        @DisplayName("Reject a null task name during an update")
        void testUpdateNameNull() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setName(null);
            });

            assertEquals("Study", task.getName());
        }

        @Test
        @DisplayName("Reject an empty task name during an update")
        void testUpdateNameEmpty() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setName("");
            });

            assertEquals("Study", task.getName());
        }

        @Test
        @DisplayName("Reject a whitespace-only task name during an update")
        void testUpdateNameWhitespace() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setName("   ");
            });

            assertEquals("Study", task.getName());
        }

        @Test
        @DisplayName("Reject a task name longer than 20 characters during an update")
        void testUpdateNameTooLong() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setName("123456789012345678901");
            });

            assertEquals("Study", task.getName());
        }
    }

    // ========================================
    // DESCRIPTION TESTS
    // ========================================

    @Nested
    @DisplayName("Description Tests")
    class DescriptionTests {

        @Test
        @DisplayName("Accept a valid task description")
        void testValidDescription() {
            Task task = createValidTask();

            assertEquals(
                    "Complete CS 320 assignment",
                    task.getDescription()
            );
        }

        @Test
        @DisplayName("Reject a null task description")
        void testDescriptionNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "Study",
                        null
                );
            });
        }

        @Test
        @DisplayName("Reject an empty task description")
        void testDescriptionEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "Study",
                        ""
                );
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only task description")
        void testDescriptionWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "Study",
                        "   "
                );
            });
        }

        @Test
        @DisplayName("Accept a description containing exactly 50 characters")
        void testDescriptionMaximumLength() {
            String description =
                    "12345678901234567890123456789012345678901234567890";

            Task task = new Task(
                    "12345",
                    "Study",
                    description
            );

            assertEquals(description, task.getDescription());
        }

        @Test
        @DisplayName("Reject a description longer than 50 characters")
        void testDescriptionTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Task(
                        "12345",
                        "Study",
                        "123456789012345678901234567890123456789012345678901"
                );
            });
        }

        @Test
        @DisplayName("Update the task description with a valid value")
        void testUpdateDescription() {
            Task task = createValidTask();

            task.setDescription("Finish milestone");

            assertEquals("Finish milestone", task.getDescription());
        }

        @Test
        @DisplayName("Reject a null description during an update")
        void testUpdateDescriptionNull() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setDescription(null);
            });

            assertEquals(
                    "Complete CS 320 assignment",
                    task.getDescription()
            );
        }

        @Test
        @DisplayName("Reject an empty description during an update")
        void testUpdateDescriptionEmpty() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setDescription("");
            });

            assertEquals(
                    "Complete CS 320 assignment",
                    task.getDescription()
            );
        }

        @Test
        @DisplayName("Reject a whitespace-only description during an update")
        void testUpdateDescriptionWhitespace() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setDescription("   ");
            });

            assertEquals(
                    "Complete CS 320 assignment",
                    task.getDescription()
            );
        }

        @Test
        @DisplayName("Reject a description longer than 50 characters during an update")
        void testUpdateDescriptionTooLong() {
            Task task = createValidTask();

            assertThrows(IllegalArgumentException.class, () -> {
                task.setDescription(
                        "123456789012345678901234567890123456789012345678901"
                );
            });

            assertEquals(
                    "Complete CS 320 assignment",
                    task.getDescription()
            );
        }
    }
}