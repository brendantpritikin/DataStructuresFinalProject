import java.util.*;
public class InteractionDictionaryTest {
    public static void main(String[] args) {
        Interaction name1 = new Interaction(3, 11, 2021, "Contact Name", "Sally Jones would be a useful person to contact");
        Interaction name2 = new Interaction(4, 11, 2017, "Contact Name", "John Smith would be a useful person to contact");
        Interaction jobDetails = new Interaction(2, 1, 2021, "Job Details", "The hiring team is looking for computer science majors");


        InteractionDictionary testDict = new InteractionDictionary();

        testDict.addInteraction(name1);
        testDict.addInteraction(name2);
        testDict.addInteraction(jobDetails);

        testDict.showInteractions();

        InteractionList nameList = testDict.findSubject("Contact Name");
        nameList.showInteractions();


        testStack.checkLastInteraction();
        System.out.println(testStack.isEmpty());



// A test of merge sort
       
        ArrayList<Interaction> list = new ArrayList <> (Arrays.asList(name1, name2, jobDetails));
        ArrayList<Interaction> mergeSorted = InteractionStack.mergeSort(list);
        
        for (int index = 0; index < mergeSorted.size(); index++)
        {

            Interaction currentInteraction = mergeSorted.get(index);
            System.out.println("Index: "+ index);
            System.out.println( currentInteraction);
        }


    }
}