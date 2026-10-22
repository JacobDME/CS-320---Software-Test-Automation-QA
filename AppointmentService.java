import java.util.HashMap;

public class AppointmentService
{
    // The collection is private and is not exposed directly to callers.
    private final HashMap<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment)
    {
        if (appointment == null)
        {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }

        if (appointments.containsKey(appointment.getAppointmentId()))
        {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }

        appointments.put(appointment.getAppointmentId(), appointment);
    }

    public void deleteAppointment(String appointmentId)
    {
        if (appointmentId == null || appointmentId.isBlank())
        {
            throw new IllegalArgumentException("Invalid Appointment ID.");
        }

        if (!appointments.containsKey(appointmentId))
        {
            throw new IllegalArgumentException("Appointment ID not found.");
        }

        appointments.remove(appointmentId);
    }
}
