public class ContactInformationTest {
    public static void main(String[] args) {

        // Creating a dummy Contact
        Interaction test = new Interaction("03-10-2021", "Taking a test run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        Interaction test2 = new Interaction("03-09-2021", "Another Test Run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        InteractionStack testStack = new InteractionStack();

        testStack.addInteraction(test2);
        testStack.addInteraction(test);

        ContactInformation emma = new ContactInformation(1234567890, "Emma Flatland", "Data Analyst",
                "Data Co.", testStack);

        // Testing ContactInformation methods

        emma.showContact();

        System.out.println(emma.key());
    }
}