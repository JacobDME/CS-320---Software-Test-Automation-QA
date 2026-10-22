import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService contactService;

    // Create a new ContactService before each test.
    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }

    // Creates a valid contact for testing.
    private Contact createValidContact(String contactId) {
        return new Contact(
                contactId,
                "Jake",
                "Eschenbach",
                "4456780322",
                "12345 Study Street"
        );
    }


    // ========================================
    // ADD CONTACT TESTS
    // ========================================

    @Nested
    @DisplayName("Add Contact Tests")
    class AddContactTests {

        @Test
        @DisplayName("Add a contact with a unique ID")
        void testAddContact() {

            Contact contact = createValidContact("12345");

            assertDoesNotThrow(() -> {
                contactService.addContact(contact);
            });
        }


        @Test
        @DisplayName("Add multiple contacts with unique IDs")
        void testAddMultipleContacts() {

            Contact contact1 = createValidContact("12345");
            Contact contact2 = createValidContact("12346");

            assertDoesNotThrow(() -> {
                contactService.addContact(contact1);
                contactService.addContact(contact2);
            });

            // Verify that both contacts can be updated independently.
            contactService.updateFirstName("12345", "Jacob");
            contactService.updateFirstName("12346", "John");

            assertEquals("Jacob", contact1.getFirstName());
            assertEquals("John", contact2.getFirstName());
        }


        @Test
        @DisplayName("Reject contacts with duplicate IDs")
        void testDuplicateContactId() {

            Contact contact1 = createValidContact("12345");

            Contact contact2 = new Contact(
                    "12345",
                    "John",
                    "Smith",
                    "7575551234",
                    "456 Main Street"
            );

            contactService.addContact(contact1);

            assertThrows(IllegalArgumentException.class, () -> {
                contactService.addContact(contact2);
            });

            // Verify that the original contact was not replaced.
            contactService.updateFirstName("12345", "Jacob");

            assertEquals("Jacob", contact1.getFirstName());
            assertEquals("John", contact2.getFirstName());
        }
    }


    // ========================================
    // DELETE CONTACT TESTS
    // ========================================

    @Nested
    @DisplayName("Delete Contact Tests")
    class DeleteContactTests {

        @Test
        @DisplayName("Delete a contact using its ID")
        void testDeleteContact() {

            Contact contact = createValidContact("12345");

            contactService.addContact(contact);

            contactService.deleteContact("12345");

            // Create a new contact using the deleted ID.
            Contact newContact = new Contact(
                    "12345",
                    "John",
                    "Smith",
                    "7575551234",
                    "456 Main Street"
            );

            // The deleted ID should now be available.
            assertDoesNotThrow(() -> {
                contactService.addContact(newContact);
            });

            // Verify the new contact is stored under that ID.
            contactService.updateFirstName("12345", "Johnny");

            assertEquals("Johnny", newContact.getFirstName());

            // The original contact should remain unchanged.
            assertEquals("Jake", contact.getFirstName());
        }


        @Test
        @DisplayName("Delete only the contact with the specified ID")
        void testDeleteSpecificContact() {

            Contact contact1 = createValidContact("12345");
            Contact contact2 = createValidContact("12346");

            contactService.addContact(contact1);
            contactService.addContact(contact2);

            contactService.deleteContact("12345");

            // The remaining contact should still be available.
            contactService.updateFirstName("12346", "Jacob");

            assertEquals("Jacob", contact2.getFirstName());

            // Verify that the deleted ID can be used again.
            Contact newContact = createValidContact("12345");

            assertDoesNotThrow(() -> {
                contactService.addContact(newContact);
            });

            contactService.updateLastName("12345", "Smith");

            assertEquals("Smith", newContact.getLastName());

            // The deleted contact should not be updated.
            assertEquals("Eschenbach", contact1.getLastName());
        }


        @Test
        @DisplayName("Deleting a nonexistent ID does not remove another contact")
        void testDeleteNonexistentContact() {

            Contact contact = createValidContact("12345");

            contactService.addContact(contact);

            // Attempt to delete an ID that does not exist.
            assertDoesNotThrow(() -> {
                contactService.deleteContact("99999");
            });

            // Verify that the existing contact can still be updated.
            contactService.updateFirstName("12345", "Jacob");

            assertEquals("Jacob", contact.getFirstName());
        }
    }


    // ========================================
    // UPDATE CONTACT TESTS
    // ========================================

    @Nested
    @DisplayName("Update Contact Tests")
    class UpdateContactTests {


        // ========================================
        // FIRST NAME UPDATE TESTS
        // ========================================

        @Nested
        @DisplayName("First Name Update Tests")
        class FirstNameUpdateTests {

            @Test
            @DisplayName("Update a contact's first name")
            void testUpdateFirstName() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                contactService.updateFirstName("12345", "Jacob");

                assertEquals("Jacob", contact.getFirstName());

                // The contact ID should remain unchanged.
                assertEquals("12345", contact.getContactId());
            }


            @Test
            @DisplayName("Reject a null first name during an update")
            void testUpdateFirstNameNull() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateFirstName("12345", null);
                });

                // Verify that the original name remains unchanged.
                assertEquals("Jake", contact.getFirstName());
            }


            @Test
            @DisplayName("Reject an empty first name during an update")
            void testUpdateFirstNameEmpty() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateFirstName("12345", "");
                });

                assertEquals("Jake", contact.getFirstName());
            }

            @Test
            @DisplayName("Reject a whitespace-only first name during an update")
            void testUpdateFirstNameWhitespace() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateFirstName("12345", "   ");
                });

                assertEquals("Jake", contact.getFirstName());
            }

            @Test
            @DisplayName("Reject a first name longer than 10 characters")
            void testUpdateFirstNameTooLong() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateFirstName("12345", "Christopher");
                });

                assertEquals("Jake", contact.getFirstName());
            }
        }


        // ========================================
        // LAST NAME UPDATE TESTS
        // ========================================

        @Nested
        @DisplayName("Last Name Update Tests")
        class LastNameUpdateTests {

            @Test
            @DisplayName("Update a contact's last name")
            void testUpdateLastName() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                contactService.updateLastName("12345", "Smith");

                assertEquals("Smith", contact.getLastName());

                assertEquals("12345", contact.getContactId());
            }


            @Test
            @DisplayName("Reject a null last name during an update")
            void testUpdateLastNameNull() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateLastName("12345", null);
                });

                assertEquals("Eschenbach", contact.getLastName());
            }


            @Test
            @DisplayName("Reject an empty last name during an update")
            void testUpdateLastNameEmpty() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateLastName("12345", "");
                });

                assertEquals("Eschenbach", contact.getLastName());
            }

            @Test
            @DisplayName("Reject a whitespace-only last name during an update")
            void testUpdateLastNameWhitespace() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateLastName("12345", "   ");
                });

                assertEquals("Eschenbach", contact.getLastName());
            }

            @Test
            @DisplayName("Reject a last name longer than 10 characters")
            void testUpdateLastNameTooLong() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateLastName("12345", "May-Eschenbach");
                });

                assertEquals("Eschenbach", contact.getLastName());
            }
        }


        // ========================================
        // PHONE NUMBER UPDATE TESTS
        // ========================================

        @Nested
        @DisplayName("Phone Number Update Tests")
        class PhoneNumberUpdateTests {

            @Test
            @DisplayName("Update a contact's phone number")
            void testUpdatePhoneNumber() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                contactService.updatePhoneNumber("12345", "7575551234");

                assertEquals("7575551234", contact.getPhoneNumber());

                assertEquals("12345", contact.getContactId());
            }


            @Test
            @DisplayName("Reject a null phone number during an update")
            void testUpdatePhoneNumberNull() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", null);
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }


            @Test
            @DisplayName("Reject an empty phone number during an update")
            void testUpdatePhoneNumberEmpty() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", "");
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }

            @Test
            @DisplayName("Reject a whitespace-only phone number during an update")
            void testUpdatePhoneNumberWhitespace() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", "          ");
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }

            @Test
            @DisplayName("Reject a phone number shorter than 10 digits")
            void testUpdatePhoneNumberTooShort() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", "123456789");
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }


            @Test
            @DisplayName("Reject a phone number longer than 10 digits")
            void testUpdatePhoneNumberTooLong() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", "12345678901");
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }


            @Test
            @DisplayName("Reject a phone number containing letters")
            void testUpdatePhoneNumberContainsLetters() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", "abcdefghij");
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }


            @Test
            @DisplayName("Reject a phone number containing special characters")
            void testUpdatePhoneNumberContainsSpecialCharacters() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updatePhoneNumber("12345", "757-555-12");
                });

                assertEquals("4456780322", contact.getPhoneNumber());
            }
        }


        // ========================================
        // ADDRESS UPDATE TESTS
        // ========================================

        @Nested
        @DisplayName("Address Update Tests")
        class AddressUpdateTests {

            @Test
            @DisplayName("Update a contact's address")
            void testUpdateAddress() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                contactService.updateAddress("12345", "456 Main Street");

                assertEquals("456 Main Street", contact.getAddress());

                assertEquals("12345", contact.getContactId());
            }


            @Test
            @DisplayName("Reject a null address during an update")
            void testUpdateAddressNull() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateAddress("12345", null);
                });

                assertEquals("12345 Study Street", contact.getAddress());
            }


            @Test
            @DisplayName("Reject an empty address during an update")
            void testUpdateAddressEmpty() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateAddress("12345", "");
                });

                assertEquals("12345 Study Street", contact.getAddress());
            }

            @Test
            @DisplayName("Reject a whitespace-only address during an update")
            void testUpdateAddressWhitespace() {
                Contact contact = createValidContact("12345");
                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateAddress("12345", "   ");
                });

                assertEquals("12345 Study Street", contact.getAddress());
            }

            @Test
            @DisplayName("Reject an address longer than 30 characters")
            void testUpdateAddressTooLong() {

                Contact contact = createValidContact("12345");

                contactService.addContact(contact);

                assertThrows(IllegalArgumentException.class, () -> {
                    contactService.updateAddress(
                            "12345",
                            "1234567890123456789012345678901"
                    );
                });

                assertEquals("12345 Study Street", contact.getAddress());
            }
        }


        // ========================================
        // ADDITIONAL UPDATE TESTS
        // ========================================

        @Test
        @DisplayName("Update only the contact with the specified ID")
        void testUpdateSpecificContact() {

            Contact contact1 = createValidContact("12345");
            Contact contact2 = createValidContact("12346");

            contactService.addContact(contact1);
            contactService.addContact(contact2);

            contactService.updateFirstName("12346", "Jacob");

            assertEquals("Jake", contact1.getFirstName());
            assertEquals("Jacob", contact2.getFirstName());
        }


        @Test
        @DisplayName("Updating a nonexistent ID does not change existing contacts")
        void testUpdateNonexistentContact() {

            Contact contact = createValidContact("12345");

            contactService.addContact(contact);

            contactService.updateFirstName("99999", "Jacob");
            contactService.updateLastName("99999", "Smith");
            contactService.updatePhoneNumber("99999", "7575551234");
            contactService.updateAddress("99999", "456 Main Street");

            // Verify that the existing contact remains unchanged.
            assertEquals("Jake", contact.getFirstName());
            assertEquals("Eschenbach", contact.getLastName());
            assertEquals("4456780322", contact.getPhoneNumber());
            assertEquals("12345 Study Street", contact.getAddress());
        }
    }
}