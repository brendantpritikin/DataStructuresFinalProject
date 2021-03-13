import java.util.Calendar;
class Main {

    public static void main(String[] args) {
      // Build up to MyNetwork
        Interaction test = new Interaction("03-10-2021", "Taking a test run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        Interaction test2 = new Interaction("03-09-2021", "Another Test Run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        InteractionStack testStack = new InteractionStack();

        testStack.addInteraction(test2);
        testStack.addInteraction(test);

        ContactInformation emma = new ContactInformation(1234567890, "Emma Flatland", "Data Analyst",
                "Data Co.", testStack);

        ContactInformation brendan = new ContactInformation(5642843395L, "Brendan Pritikin",
                "Software Programmer", "Cal Data", testStack);

        // MyNetwork Construction

        MyNetwork network = new MyNetwork();
        network.addContact(emma);
        network.addContact(brendan);

        // MyNetwork Testing

        network.showNetwork();

        Calendar today = Calendar.getInstance();
        today.set(2021, 3, 11);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(2021, 3, 12);
        System.out.println(today.compareTo(tomorrow));
    
    }
}