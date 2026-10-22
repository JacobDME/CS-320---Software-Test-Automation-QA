import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest
{
    private AppointmentService appointmentService;

    // Creates a fresh AppointmentService before every test.
    @BeforeEach
    void setUp()
    {
        appointmentService = new AppointmentService();
    }

    private Date createFutureDate()
    {
        return new Date(System.currentTimeMillis() + 86400000);
    }

    private Appointment createValidAppointment(String appointmentId)
    {
        return new Appointment(
                appointmentId,
                createFutureDate(),
                "Doctor appointment"
        );
    }


    // ========================================
    // ADD APPOINTMENT TESTS
    // ========================================

    @Nested
    @DisplayName("Add Appointment Tests")
    class AddAppointmentTests
    {
        @Test
        @DisplayName("Add an appointment with a unique ID")
        void testAddAppointment()
        {
            Appointment appointment = createValidAppointment("12345");

            assertDoesNotThrow(() ->
                    appointmentService.addAppointment(appointment)
            );
        }

        @Test
        @DisplayName("Add multiple appointments with unique IDs")
        void testAddMultipleAppointments()
        {
            Appointment appointment1 = createValidAppointment("12345");
            Appointment appointment2 = createValidAppointment("12346");

            assertDoesNotThrow(() -> {
                appointmentService.addAppointment(appointment1);
                appointmentService.addAppointment(appointment2);
            });
        }

        @Test
        @DisplayName("Reject appointments with duplicate IDs")
        void testDuplicateAppointmentId()
        {
            Appointment appointment1 = createValidAppointment("12345");

            Appointment appointment2 = new Appointment(
                    "12345",
                    createFutureDate(),
                    "Dentist appointment"
            );

            appointmentService.addAppointment(appointment1);

            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.addAppointment(appointment2)
            );
        }

        @Test
        @DisplayName("Reject a null appointment")
        void testAddNullAppointment()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.addAppointment(null)
            );
        }
    }


    // ========================================
    // DELETE APPOINTMENT TESTS
    // ========================================

    @Nested
    @DisplayName("Delete Appointment Tests")
    class DeleteAppointmentTests
    {
        @Test
        @DisplayName("Delete an appointment using its ID")
        void testDeleteAppointment()
        {
            Appointment appointment = createValidAppointment("12345");

            appointmentService.addAppointment(appointment);
            appointmentService.deleteAppointment("12345");

            Appointment newAppointment = new Appointment(
                    "12345",
                    createFutureDate(),
                    "Dentist appointment"
            );

            // If deletion worked, the same ID should now be available again.
            assertDoesNotThrow(() ->
                    appointmentService.addAppointment(newAppointment)
            );
        }

        @Test
        @DisplayName("Delete only the appointment with the specified ID")
        void testDeleteSpecificAppointment()
        {
            Appointment appointment1 = createValidAppointment("12345");
            Appointment appointment2 = createValidAppointment("12346");

            appointmentService.addAppointment(appointment1);
            appointmentService.addAppointment(appointment2);

            appointmentService.deleteAppointment("12345");

            assertDoesNotThrow(() ->
                    appointmentService.addAppointment(
                            createValidAppointment("12345")
                    )
            );

            // The second appointment should still be stored.
            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.addAppointment(
                            createValidAppointment("12346")
                    )
            );
        }

        @Test
        @DisplayName("Reject deletion of an appointment ID that does not exist")
        void testDeleteNonexistentAppointment()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.deleteAppointment("99999")
            );
        }

        @Test
        @DisplayName("Reject deletion using a null appointment ID")
        void testDeleteNullAppointmentId()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.deleteAppointment(null)
            );
        }

        @Test
        @DisplayName("Reject deletion using an empty appointment ID")
        void testDeleteEmptyAppointmentId()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.deleteAppointment("")
            );
        }

        @Test
        @DisplayName("Reject deletion using a whitespace-only appointment ID")
        void testDeleteWhitespaceAppointmentId()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    appointmentService.deleteAppointment("   ")
            );
        }
    }
}
