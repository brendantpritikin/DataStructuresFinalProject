public class Main{
    public static void main(String[] args) {
        //We affirm we have carried out our academic endeavors with full academic honesty. [Signed, Brendan Pritikin, David Bond and Emma Flatland]

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

        InteractionList subjectList = testNetwork.getSubject("Brendan Pritikin", "Contact Name");
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