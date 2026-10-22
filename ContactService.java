import java.util.HashMap;

public class ContactService
{
    private final HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact)
    {
        if (contacts.containsKey(contact.getContactId()))
        {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId)
    {
        contacts.remove(contactId);
    }

    public void updateFirstName(String contactId, String firstName)
    {
        Contact contact = contacts.get(contactId);

        if (contact != null)
        {
            contact.setFirstName(firstName);
        }
    }

    public void updateLastName(String contactId, String lastName)
    {

        Contact contact = contacts.get(contactId);

        if (contact != null)
        {
            contact.setLastName(lastName);
        }
    }

    public void updatePhoneNumber(String contactId, String phoneNumber)
    {

        Contact contact = contacts.get(contactId);

        if (contact != null)
        {
            contact.setPhoneNumber(phoneNumber);
        }
    }

    public void updateAddress(String contactId, String address)
    {

        Contact contact = contacts.get(contactId);

        if (contact != null) {
            contact.setAddress(address);
        }
    }
}
