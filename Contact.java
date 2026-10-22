public class Contact {

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Contact(String contactId, String firstName, String lastName,
                   String phoneNumber, String address) {

        if (contactId == null ||
                contactId.isBlank() ||
                contactId.length() > 10) {

            throw new IllegalArgumentException(
                    "Invalid contact ID parameters"
            );
        }

        this.contactId = contactId;

        if (firstName == null ||
                firstName.isBlank() ||
                firstName.length() > 10) {

            throw new IllegalArgumentException(
                    "Invalid first name parameters"
            );
        }

        this.firstName = firstName;

        if (lastName == null ||
                lastName.isBlank() ||
                lastName.length() > 10) {

            throw new IllegalArgumentException(
                    "Invalid last name parameters"
            );
        }

        this.lastName = lastName;

        if (phoneNumber == null ||
                phoneNumber.length() != 10 ||
                !phoneNumber.matches("[0-9]+")) {

            throw new IllegalArgumentException(
                    "Invalid phone number parameters"
            );
        }

        this.phoneNumber = phoneNumber;

        if (address == null ||
                address.isBlank() ||
                address.length() > 30) {

            throw new IllegalArgumentException(
                    "Invalid address parameters"
            );
        }

        this.address = address;
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {

        if (firstName == null ||
                firstName.isBlank() ||
                firstName.length() > 10) {

            throw new IllegalArgumentException(
                    "Invalid first name parameters"
            );
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        if (lastName == null ||
                lastName.isBlank() ||
                lastName.length() > 10) {

            throw new IllegalArgumentException(
                    "Invalid last name parameters"
            );
        }

        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {

        if (phoneNumber == null ||
                phoneNumber.length() != 10 ||
                !phoneNumber.matches("[0-9]+")) {

            throw new IllegalArgumentException(
                    "Invalid phone number parameters"
            );
        }

        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {

        if (address == null ||
                address.isBlank() ||
                address.length() > 30) {

            throw new IllegalArgumentException(
                    "Invalid address parameters"
            );
        }

        this.address = address;
    }
}

