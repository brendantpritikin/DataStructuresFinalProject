public class MyNetworkTest {
    public static void main(String[] args) {
        // // Build up to MyNetwork
        // Interaction test = new Interaction("03-10-2021", "Taking a test run",
        //         "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        // Interaction test2 = new Interaction("03-09-2021", "Another Test Run",
        //         "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        // InteractionStack testStack = new InteractionStack();

        // testStack.addInteraction(test2);
        // testStack.addInteraction(test);

      

        // MyNetwork Construction

        MyNetwork testNetwork = new MyNetwork();


        InteractionDictionary emmaDict = new InteractionDictionary();
        ContactInformation emma = new ContactInformation(1111111, "Emma Flatland", "Data Analyst", "Data Co.", emmaDict);

        InteractionDictionary brendanDict = new InteractionDictionary();
        ContactInformation brendan = new ContactInformation(2222222, "Brendan Pritikin", "Software Programmer", "Cal Data", brendanDict);

        testNetwork.addContact(emma);
        testNetwork.addContact(brendan);

        Interaction name1 = new Interaction(3, 11, 2021, "Contact Name", "Sally Jones would be a useful person to contact");
        Interaction name2 = new Interaction(3, 5, 2021, "Contact Name", "John Smith would be a useful person to contact");
        Interaction jobDetails = new Interaction(3, 1, 2021, "Job Details", "The hiring team is looking for computer science majors");


        ContactInformation brendanInfo = testNetwork.getContactInfo("Brendan Pritikin");
        System.out.println("Here is Brendan Pritikin's contact information:");
        brendanInfo.showContact();


        testNetwork.addInteraction("Brendan Pritikin", name1);
        testNetwork.addInteraction("Brendan Pritikin", name2);
        testNetwork.addInteraction("Brendan Pritikin", jobDetails);

        InteractionList subjectList = brendanInfo.getSubject("Contact Name");
        System.out.println("-----------------------------------------------");
        System.out.println("Here are all interactions with Brendan Pritikin regarding other peoples names");
        subjectList.showInteractions();

        System.out.println("----------------------------------------------");
        System.out.println("Here are all interactions with Brendan Pritikin, chronological order");
        testNetwork.showInteractions("Brendan Pritikin");


        System.out.println("-----------------------------------------------------");
        System.out.println("Here is the entire network:");
        testNetwork.showNetwork();
    }
}