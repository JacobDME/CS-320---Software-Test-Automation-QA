# CS-320---Software-Test-Automation-QA

**How can I ensure that my code, program, or software is functional and secure?**

One of the biggest ways I can make sure my code is functional is by testing it against the requirements and not just assuming it works because the normal case passes. In Project One, I learned that it is important to test both valid and invalid inputs. I tested things like null values, empty strings, duplicate IDs, incorrect lengths, and dates that were in the past. Using JUnit helped me catch issues that I probably would not have noticed just by running the program normally.

When it comes to security, I think a big part of it starts with making sure bad data cannot easily get into the program. For example, I made sure phone numbers had to be exactly 10 digits and that IDs could not be changed once they were created. I also kept the collections inside the service classes private instead of letting other parts of the program change them directly. Going forward, I would also want to use things like code reviews and continuous integration so problems can be caught earlier.

**How do I interpret user needs and incorporate them into a program?**

I usually start by looking through the requirements and breaking them down into smaller pieces. That helps me understand exactly what the user is asking for before I start coding. I used this a lot in Project One. For example, if the requirement said a task name could not be longer than 20 characters, I built that rule into the Task class and then wrote tests to make sure it actually worked.

I also learned that sometimes you have to think a little deeper than just the basic requirement. One example was required fields. At first, I was mainly checking for null values, but after getting feedback I also started testing empty strings and whitespace. That made the program stronger and showed me why it is important to keep reviewing the requirements while you work instead of only looking at them once at the beginning.

**How do I approach designing software?**

My approach to designing software is to break the project down into smaller parts and work through them one at a time. I normally start by looking at the requirements and figuring out what classes, fields, and methods I am going to need. From there, I build one section, test it, and then move on to the next part. I have found that this helps me stay organized and makes the project feel less overwhelming.

For Project One, I separated the object classes from the service classes. The Contact, Task, and Appointment classes handled the data and validation, while the service classes handled things like adding, deleting, and updating the objects. I also organized my tests by each requirement so it was easier to tell what was working and what still needed attention. Going forward, I would keep using this same approach because it helps me catch problems earlier and keeps the code easier to follow.
