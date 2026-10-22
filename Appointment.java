import java.util.Date;

public class Appointment
{
    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    public Appointment(String appointmentId, Date appointmentDate, String description)
    {
        // Appointment ID is required, cannot be blank, and cannot exceed 10 characters.
        if (appointmentId == null || appointmentId.isBlank() || appointmentId.length() > 10)
        {
            throw new IllegalArgumentException("Invalid Appointment ID.");
        }

        // Appointment date is required and cannot be in the past.
        if (appointmentDate == null || appointmentDate.before(new Date()))
        {
            throw new IllegalArgumentException("Invalid Date.");
        }

        // Description is required, cannot be blank, and cannot exceed 50 characters.
        if (description == null || description.isBlank() || description.length() > 50)
        {
            throw new IllegalArgumentException("Invalid Description.");
        }

        this.appointmentId = appointmentId;

        // Store a defensive copy so outside code cannot change the internal Date.
        this.appointmentDate = new Date(appointmentDate.getTime());

        this.description = description;
    }

    public String getAppointmentId()
    {
        return appointmentId;
    }

    public Date getAppointmentDate()
    {
        // Return a defensive copy so callers cannot modify the internal Date.
        return new Date(appointmentDate.getTime());
    }

    public String getDescription()
    {
        return description;
    }
}
