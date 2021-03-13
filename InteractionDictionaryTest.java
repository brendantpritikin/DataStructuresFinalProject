import java.util.*;
public class InteractionDictionaryTest {
    public static void main(String[] args) {
        Interaction test = new Interaction("03-10-2021", "Taking a test run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        Interaction test2 = new Interaction("03-09-2021", "Another Test Run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        InteractionStack testStack = new InteractionStack();

        testStack.addInteraction(test2);
        testStack.addInteraction(test);

        testStack.showInteractions();

        testStack.checkLastInteraction();
        testStack.showInteractions();
        testStack.findSubject("Another Test Run");
        System.out.println(testStack.isEmpty());



// A test of merge sort
        Interaction contactName = new Interaction(3, 11, 2021, "Contact Name", "");
        Interaction jobDetails = new Interaction(3, 12, 2021, "Job Details", "");
        Interaction phoneNumber = new Interaction(5, 1, 2018, "Phone Number", "");
        Interaction industryDetails = new Interaction(3, 12, 2017, "Industry Details", "");


        ArrayList<Interaction> list = new ArrayList <> (Arrays.asList(contactName, jobDetails, phoneNumber, industryDetails));
        ArrayList<Interaction> mergeSorted = InteractionStack.mergeSort(list);
        
        for (int index = 0; index < mergeSorted.size(); index++)
        {

            Interaction currentInteraction = mergeSorted.get(index);
            System.out.println("Index: "+ index);
            System.out.println( currentInteraction);
        }


    }
}