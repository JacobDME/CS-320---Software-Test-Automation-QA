import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest
{
    private Date createFutureDate()
    {
        return new Date(System.currentTimeMillis() + 86400000);
    }

    private Date createPastDate()
    {
        return new Date(System.currentTimeMillis() - 86400000);
    }

    @Test
    @DisplayName("Create an appointment with valid values")
    void testValidAppointment()
    {
        Date futureDate = createFutureDate();

        Appointment appointment = new Appointment(
                "12345",
                futureDate,
                "Doctor appointment"
        );

        assertEquals("12345", appointment.getAppointmentId());
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals("Doctor appointment", appointment.getDescription());
    }


    // ========================================
    // APPOINTMENT ID TESTS
    // ========================================

    @Nested
    @DisplayName("Appointment ID Tests")
    class AppointmentIDTests
    {
        @Test
        @DisplayName("Reject a null appointment ID")
        void testAppointmentIdNull()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            null,
                            createFutureDate(),
                            "Doctor appointment"
                    )
            );
        }

        @Test
        @DisplayName("Reject an empty appointment ID")
        void testAppointmentIdEmpty()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "",
                            createFutureDate(),
                            "Doctor appointment"
                    )
            );
        }

        @Test
        @DisplayName("Reject a whitespace-only appointment ID")
        void testAppointmentIdWhitespace()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "   ",
                            createFutureDate(),
                            "Doctor appointment"
                    )
            );
        }

        @Test
        @DisplayName("Accept an appointment ID containing exactly 10 characters")
        void testAppointmentIdMaximumLength()
        {
            Appointment appointment = new Appointment(
                    "1234567890",
                    createFutureDate(),
                    "Doctor appointment"
            );

            assertEquals("1234567890", appointment.getAppointmentId());
        }

        @Test
        @DisplayName("Reject an appointment ID longer than 10 characters")
        void testAppointmentIdTooLong()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345678901",
                            createFutureDate(),
                            "Doctor appointment"
                    )
            );
        }
    }


    // ========================================
    // APPOINTMENT DATE TESTS
    // ========================================

    @Nested
    @DisplayName("Appointment Date Tests")
    class AppointmentDateTests
    {
        @Test
        @DisplayName("Accept a future appointment date")
        void testValidAppointmentDate()
        {
            Date futureDate = createFutureDate();

            Appointment appointment = new Appointment(
                    "12345",
                    futureDate,
                    "Doctor appointment"
            );

            assertEquals(futureDate, appointment.getAppointmentDate());
        }

        @Test
        @DisplayName("Reject a null appointment date")
        void testAppointmentDateNull()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345",
                            null,
                            "Doctor appointment"
                    )
            );
        }

        @Test
        @DisplayName("Reject an appointment date in the past")
        void testAppointmentDateInPast()
        {
            Date pastDate = createPastDate();

            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345",
                            pastDate,
                            "Doctor appointment"
                    )
            );
        }

        @Test
        @DisplayName("Changing the original Date does not change the appointment date")
        void testConstructorDefensiveDateCopy()
        {
            Date futureDate = createFutureDate();
            long originalTime = futureDate.getTime();

            Appointment appointment = new Appointment(
                    "12345",
                    futureDate,
                    "Doctor appointment"
            );

            futureDate.setTime(System.currentTimeMillis() - 86400000);

            assertEquals(originalTime, appointment.getAppointmentDate().getTime());
        }

        @Test
        @DisplayName("Changing a returned Date does not change the appointment date")
        void testGetterDefensiveDateCopy()
        {
            Appointment appointment = new Appointment(
                    "12345",
                    createFutureDate(),
                    "Doctor appointment"
            );

            long originalTime = appointment.getAppointmentDate().getTime();
            Date returnedDate = appointment.getAppointmentDate();

            returnedDate.setTime(System.currentTimeMillis() - 86400000);

            assertEquals(originalTime, appointment.getAppointmentDate().getTime());
        }
    }


    // ========================================
    // DESCRIPTION TESTS
    // ========================================

    @Nested
    @DisplayName("Description Tests")
    class DescriptionTests
    {
        @Test
        @DisplayName("Accept a valid description")
        void testValidDescription()
        {
            Appointment appointment = new Appointment(
                    "12345",
                    createFutureDate(),
                    "Doctor appointment"
            );

            assertEquals("Doctor appointment", appointment.getDescription());
        }

        @Test
        @DisplayName("Reject a null description")
        void testDescriptionNull()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345",
                            createFutureDate(),
                            null
                    )
            );
        }

        @Test
        @DisplayName("Reject an empty description")
        void testDescriptionEmpty()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345",
                            createFutureDate(),
                            ""
                    )
            );
        }

        @Test
        @DisplayName("Reject a whitespace-only description")
        void testDescriptionWhitespace()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345",
                            createFutureDate(),
                            "   "
                    )
            );
        }

        @Test
        @DisplayName("Accept a description containing exactly 50 characters")
        void testDescriptionMaximumLength()
        {
            String description =
                    "12345678901234567890123456789012345678901234567890";

            Appointment appointment = new Appointment(
                    "12345",
                    createFutureDate(),
                    description
            );

            assertEquals(description, appointment.getDescription());
        }

        @Test
        @DisplayName("Reject a description longer than 50 characters")
        void testDescriptionTooLong()
        {
            String description =
                    "123456789012345678901234567890123456789012345678901";

            assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(
                            "12345",
                            createFutureDate(),
                            description
                    )
            );
        }
    }
}
