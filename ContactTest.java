import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    // Creates a valid contact that can be reused in our tests.
    private Contact createValidContact() {
        return new Contact(
                "12345",
                "Jake",
                "Eschenbach",
                "4456780322",
                "12345 Study Street"
        );
    }


    // ========================================
    // CONTACT ID TESTS
    // ========================================

    @Nested
    @DisplayName("Contact ID Tests")
    class ContactIdTests {

        @Test
        @DisplayName("Accept a valid contact ID")
        void testValidContactId() {

            Contact contact = createValidContact();

            assertEquals("12345", contact.getContactId());
        }

        @Test
        @DisplayName("Reject a null contact ID")
        void testContactIdNull() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        null,
                        "Jake",
                        "Eschenbach",
                        "4456780322",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject an empty contact ID")
        void testContactIdEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("", "Jake", "Eschenbach", "4456780322", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only contact ID")
        void testContactIdWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("   ", "Jake", "Eschenbach", "4456780322", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject contact IDs longer than 10 characters")
        void testContactIdTooLong() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345678901",
                        "Jake",
                        "Eschenbach",
                        "4456780322",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Accept a contact ID containing exactly 10 characters")
        void testContactIdMaximumLength() {

            Contact contact = new Contact(
                    "1234567890",
                    "Jake",
                    "Eschenbach",
                    "4456780322",
                    "12345 Study Street"
            );

            assertEquals("1234567890", contact.getContactId());
        }

        @Test
        @DisplayName("Contact ID remains unchanged when updating other fields")
        void testContactIdCannotBeUpdated() {

            Contact contact = createValidContact();

            contact.setFirstName("Jacob");
            contact.setLastName("Smith");
            contact.setPhoneNumber("7575551234");
            contact.setAddress("456 Main Street");

            assertEquals("12345", contact.getContactId());
        }
    }


    // ========================================
    // FIRST NAME TESTS
    // ========================================

    @Nested
    @DisplayName("First Name Tests")
    class FirstNameTests {

        @Test
        @DisplayName("Accept a valid first name")
        void testValidFirstName() {

            Contact contact = createValidContact();

            assertEquals("Jake", contact.getFirstName());
        }

        @Test
        @DisplayName("Reject a null first name")
        void testFirstNameNull() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        null,
                        "Eschenbach",
                        "4456780322",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject an empty first name")
        void testFirstNameEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "", "Eschenbach", "4456780322", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only first name")
        void testFirstNameWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "   ", "Eschenbach", "4456780322", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject first names longer than 10 characters")
        void testFirstNameTooLong() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Christopher",
                        "Eschenbach",
                        "4456780322",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Accept a first name containing exactly 10 characters")
        void testFirstNameMaximumLength() {

            Contact contact = new Contact(
                    "12345",
                    "1234567890",
                    "Eschenbach",
                    "4456780322",
                    "12345 Study Street"
            );

            assertEquals("1234567890", contact.getFirstName());
        }

        @Test
        @DisplayName("Update the first name with a valid value")
        void testUpdateFirstName() {

            Contact contact = createValidContact();

            contact.setFirstName("Jacob");

            assertEquals("Jacob", contact.getFirstName());
        }

        @Test
        @DisplayName("Reject a null first name during an update")
        void testUpdateFirstNameNull() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setFirstName(null);
            });

            assertEquals("Jake", contact.getFirstName());
        }

        @Test
        @DisplayName("Reject an empty first name during an update")
        void testUpdateFirstNameEmpty() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setFirstName("");
            });

            assertEquals("Jake", contact.getFirstName());
        }

        @Test
        @DisplayName("Reject a whitespace-only first name during an update")
        void testUpdateFirstNameWhitespace() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setFirstName("   ");
            });

            assertEquals("Jake", contact.getFirstName());
        }

        @Test
        @DisplayName("Reject a first name longer than 10 characters during an update")
        void testUpdateFirstNameTooLong() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setFirstName("Christopher");
            });

            assertEquals("Jake", contact.getFirstName());
        }
    }


    // ========================================
    // LAST NAME TESTS
    // ========================================

    @Nested
    @DisplayName("Last Name Tests")
    class LastNameTests {

        @Test
        @DisplayName("Accept a valid last name")
        void testValidLastName() {

            Contact contact = createValidContact();

            assertEquals("Eschenbach", contact.getLastName());
        }

        @Test
        @DisplayName("Reject a null last name")
        void testLastNameNull() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        null,
                        "4456780322",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject an empty last name")
        void testLastNameEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "Jake", "", "4456780322", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only last name")
        void testLastNameWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "Jake", "   ", "4456780322", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject last names longer than 10 characters")
        void testLastNameTooLong() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "May-Eschenbach",
                        "4456780322",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Accept a last name containing exactly 10 characters")
        void testLastNameMaximumLength() {

            Contact contact = createValidContact();

            assertEquals("Eschenbach", contact.getLastName());
        }

        @Test
        @DisplayName("Update the last name with a valid value")
        void testUpdateLastName() {

            Contact contact = createValidContact();

            contact.setLastName("Smith");

            assertEquals("Smith", contact.getLastName());
        }

        @Test
        @DisplayName("Reject a null last name during an update")
        void testUpdateLastNameNull() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setLastName(null);
            });

            assertEquals("Eschenbach", contact.getLastName());
        }

        @Test
        @DisplayName("Reject an empty last name during an update")
        void testUpdateLastNameEmpty() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setLastName("");
            });

            assertEquals("Eschenbach", contact.getLastName());
        }

        @Test
        @DisplayName("Reject a whitespace-only last name during an update")
        void testUpdateLastNameWhitespace() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setLastName("   ");
            });

            assertEquals("Eschenbach", contact.getLastName());
        }

        @Test
        @DisplayName("Reject a last name longer than 10 characters during an update")
        void testUpdateLastNameTooLong() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setLastName("May-Eschenbach");
            });

            assertEquals("Eschenbach", contact.getLastName());
        }
    }


    // ========================================
    // PHONE NUMBER TESTS
    // ========================================

    @Nested
    @DisplayName("Phone Number Tests")
    class PhoneNumberTests {

        @Test
        @DisplayName("Accept a valid 10-digit phone number")
        void testValidPhoneNumber() {

            Contact contact = createValidContact();

            assertEquals("4456780322", contact.getPhoneNumber());
        }

        @Test
        @DisplayName("Reject a null phone number")
        void testPhoneNumberNull() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        null,
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject an empty phone number")
        void testPhoneNumberEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "Jake", "Eschenbach", "", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only phone number")
        void testPhoneNumberWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "Jake", "Eschenbach", "          ", "12345 Study Street");
            });
        }

        @Test
        @DisplayName("Reject phone numbers shorter than 10 digits")
        void testPhoneNumberTooShort() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        "445678032",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject phone numbers longer than 10 digits")
        void testPhoneNumberTooLong() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        "44567803222",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject phone numbers containing letters")
        void testPhoneNumberContainsLetters() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        "445678ABCD",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Reject phone numbers containing special characters")
        void testPhoneNumberContainsSpecialCharacters() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        "445-678-03",
                        "12345 Study Street"
                );
            });
        }

        @Test
        @DisplayName("Update the phone number with a valid value")
        void testUpdatePhoneNumber() {

            Contact contact = createValidContact();

            contact.setPhoneNumber("7575551234");

            assertEquals("7575551234", contact.getPhoneNumber());
        }

        @Test
        @DisplayName("Reject a null phone number during an update")
        void testUpdatePhoneNumberNull() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setPhoneNumber(null);
            });

            assertEquals("4456780322", contact.getPhoneNumber());
        }

        @Test
        @DisplayName("Reject an empty phone number during an update")
        void testUpdatePhoneNumberEmpty() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setPhoneNumber("");
            });

            assertEquals("4456780322", contact.getPhoneNumber());
        }

        @Test
        @DisplayName("Reject a whitespace-only phone number during an update")
        void testUpdatePhoneNumberWhitespace() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setPhoneNumber("          ");
            });

            assertEquals("4456780322", contact.getPhoneNumber());
        }

        @Test
        @DisplayName("Reject an invalid phone number during an update")
        void testUpdatePhoneNumberInvalid() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setPhoneNumber("abcdefghij");
            });

            assertEquals("4456780322", contact.getPhoneNumber());
        }
    }


    // ========================================
    // ADDRESS TESTS
    // ========================================

    @Nested
    @DisplayName("Address Tests")
    class AddressTests {

        @Test
        @DisplayName("Accept a valid address")
        void testValidAddress() {

            Contact contact = createValidContact();

            assertEquals("12345 Study Street", contact.getAddress());
        }

        @Test
        @DisplayName("Reject a null address")
        void testAddressNull() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        "4456780322",
                        null
                );
            });
        }

        @Test
        @DisplayName("Reject an empty address")
        void testAddressEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "Jake", "Eschenbach", "4456780322", "");
            });
        }

        @Test
        @DisplayName("Reject a whitespace-only address")
        void testAddressWhitespace() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("12345", "Jake", "Eschenbach", "4456780322", "   ");
            });
        }

        @Test
        @DisplayName("Reject addresses longer than 30 characters")
        void testAddressTooLong() {

            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(
                        "12345",
                        "Jake",
                        "Eschenbach",
                        "4456780322",
                        "1234567890123456789012345678901"
                );
            });
        }

        @Test
        @DisplayName("Accept an address containing exactly 30 characters")
        void testAddressMaximumLength() {

            String address = "123456789012345678901234567890";

            Contact contact = new Contact(
                    "12345",
                    "Jake",
                    "Eschenbach",
                    "4456780322",
                    address
            );

            assertEquals(address, contact.getAddress());
        }

        @Test
        @DisplayName("Update the address with a valid value")
        void testUpdateAddress() {

            Contact contact = createValidContact();

            contact.setAddress("456 Main Street");

            assertEquals("456 Main Street", contact.getAddress());
        }

        @Test
        @DisplayName("Reject a null address during an update")
        void testUpdateAddressNull() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setAddress(null);
            });

            assertEquals("12345 Study Street", contact.getAddress());
        }

        @Test
        @DisplayName("Reject an empty address during an update")
        void testUpdateAddressEmpty() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setAddress("");
            });

            assertEquals("12345 Study Street", contact.getAddress());
        }

        @Test
        @DisplayName("Reject a whitespace-only address during an update")
        void testUpdateAddressWhitespace() {
            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setAddress("   ");
            });

            assertEquals("12345 Study Street", contact.getAddress());
        }

        @Test
        @DisplayName("Reject an address longer than 30 characters during an update")
        void testUpdateAddressTooLong() {

            Contact contact = createValidContact();

            assertThrows(IllegalArgumentException.class, () -> {
                contact.setAddress("1234567890123456789012345678901");
            });

            assertEquals("12345 Study Street", contact.getAddress());
        }
    }
}



