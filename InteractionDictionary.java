import java.util.ArrayList;
import java.util.Calendar;

public class InteractionDictionary{
    private InteractionList[] subjectList;

    public InteractionDictionary(){
        this.subjectList = new InteractionList[7];
    }

    /**
     * Shows all interactions in the dictionary.
     */
    public void showInteractions(){

        ArrayList<Interaction> allInteractions = getAllInteractions();
        ArrayList<Interaction> sortedInteractions = mergeSort(allInteractions);

        for (Interaction interaction : sortedInteractions) {
            System.out.println(interaction);
        }
    }

    /**
     * Gets all interactions in the dictionary
     *
     * @return ArrayList of all interactions
     */
    public ArrayList<Interaction> getAllInteractions()
    {
        ArrayList<Interaction> allInteractions = new ArrayList<>();
        for (InteractionList entry: subjectList){
            if (entry != null){
                ArrayList<Interaction> interactions = entry.getInteractions();
                for(Interaction currentInteraction : interactions)
                {
                    allInteractions.add(currentInteraction);
                }
            }
        }
        return allInteractions;
    }

    /**
     * A sublist helper function for mergeSort.
     *
     * @param list The list to be sorted
     * @param startIndex The beginning index
     * @param endIndex The final index
     * @return The sorted list
     */
    private static ArrayList<Interaction> getSublist(ArrayList<Interaction> list, int startIndex, int endIndex)
    {
        ArrayList<Interaction> sublist = new ArrayList<Interaction>();
        for(int index = startIndex; index <= endIndex; index ++)
        {
            sublist.add(list.get(index));
        }
        return sublist;
    }

    /**
     * A merge helper function for mergeSort
     * @param listA The first list to be merged
     * @param listB The second list to be merged
     * @return The combined list
     */
    private static ArrayList<Interaction> merge(ArrayList<Interaction> listA, ArrayList<Interaction> listB)
    {
        int sizeA = listA.size();
        int sizeB = listB.size();

        ArrayList<Interaction> mergedList = new ArrayList<Interaction>();
        int i = 0;
        int j = 0;
        for (int k = 0; i <  sizeA && j < sizeB; k++)
        {
            Interaction interactionA =listA.get(i);
            Interaction interactionB = listB.get(j);

            Calendar dayA = interactionA.getDate();
            Calendar dayB = interactionB.getDate();

            int winVal = dayA.compareTo(dayB);

            if(winVal == 1)
            {
                mergedList.add(interactionA);
                i++;
            }
            else
            {
                mergedList.add(interactionB);
                j++;
            }
        }

        for (int k = 0; i < listA.size(); k++)
        {
            mergedList.add(listA.get(i));
            i++;

        }
        for(int k = 0; j < listB.size(); k++)
        {
            mergedList.add(listB.get(j));
            j++;
        }
        return mergedList;

    }

    /**
     * A mergeSort helper method to sort Interactions for sortInteractions.
     * @param list The list to be sorted
     * @return The sorted list
     */
     public static ArrayList<Interaction> mergeSort(ArrayList<Interaction> list)
    {
        int listSize = list.size();
        if (listSize <= 1)
        {
            return list;
        }
        else
            {

            int middleIndex = (listSize) / 2;

            ArrayList<Interaction> listA = getSublist(list, 0, middleIndex - 1);
            ArrayList<Interaction> listB = getSublist(list, middleIndex, listSize - 1);

            ArrayList<Interaction> sortedListA = mergeSort(listA);
            ArrayList<Interaction> sortedListB = mergeSort(listB);

            return merge(sortedListA, sortedListB);
        }
    }


    /**
     * Takes the subject of an Interaction and returns the sum of the ascii values of each character after conversion to
     * lowercase.
     *
     * @param subject The subject of the interaction
     * @return The key value for the hash table
     */
    private static int hashCode(String subject){
        String lowSubject = subject.toLowerCase();
        int key = 0;
        for (int i = 0; i < lowSubject.length(); i++) {
            key += lowSubject.charAt(i);
        }
        return key;
    }

    /**
     * Takes the subject of an interaction and returns the index value in the hash table.
     *
     * @param subject Subject of an Interaction
     * @return Index value in the hash table
     */
    public int hashIndex(String subject){
        int key = hashCode(subject);
        return key % this.subjectList.length;
    }


    /**
     * Adds an interaction to the dictionary.
     *
     * @param toAdd Interaction to add
     */
    public void addInteraction (Interaction toAdd) {
        String interactionSubject = toAdd.getSubject();
        int hashIndex = hashIndex(interactionSubject);

        InteractionList currentList = subjectList[hashIndex];

        if(currentList == null)
        {
            ArrayList<Interaction> inList = new ArrayList<>();
            inList.add(toAdd);
            subjectList[hashIndex] = new InteractionList(toAdd.getSubject(), inList);
        }
        else
        {
            int nextEmptyIndex = linearProbe(interactionSubject, hashIndex);
            currentList = subjectList[nextEmptyIndex];
            currentList.addInteraction(toAdd);
        }
        if (this.atLoadCapacity()){
            this.increaseCapacity();
        }
    }

    /**
     * Performs a linear probe to return the appropriate index for the interaction.
     *
     * @param interactionSubject Subject of interaction
     * @param hashIndex Current hash index
     * @return The new index
     */
    public int linearProbe(String interactionSubject, int hashIndex)
    {
        InteractionList currentList = subjectList[hashIndex];
        String listSubject = currentList.getSubject();

        while(interactionSubject != listSubject && interactionSubject != null)
        {
            hashIndex++;
            if(hashIndex > subjectList.length - 1)
            {
                hashIndex -= this.subjectList.length;
            }
            currentList = subjectList[hashIndex];
            listSubject = currentList.getSubject();
        }
        return hashIndex;
    }

    /**
     * Checks to see if InteractionDictionary is at load capacity.
     *
     * @return True or False
     */
    private boolean atLoadCapacity(){
        int nullCounter = 0;
        int entryCounter = 0;
        for (InteractionList entry : this.subjectList){
            if (entry == null){
                nullCounter++;
            }
            else {
                entryCounter++;
            }
        }
        int ratio = entryCounter / (entryCounter + nullCounter);
        if (ratio > 0.75){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Increases the capacity of the InteractionDictionary.
     */
    private void increaseCapacity(){
        int newLength = this.subjectList.length * 2;
        while (!isPrime(newLength)){
            newLength++;
        }
        InteractionList[] temp = this.subjectList;
        this.subjectList = new InteractionList[newLength];
        for (int i = 0; i < temp.length; i++){
            this.addInteractionList(temp[i]);
        }
    }

    /**
     * Adds an InteractionList to the dictionary.
     *
     * @param list The InteractionList
     */
    private void addInteractionList(InteractionList list){
        int index = this.hashIndex(list.getSubject());
        if (this.subjectList[index] == null){
            this.subjectList[index] = list;
        }
        else{
            this.subjectList[linearProbe(list.getSubject(), index)] = list;
        }
    }

    /**
     * Checks to see if a number is prime.
     *
     * @param num A number
     * @return True or False
     */
    public static boolean isPrime(int num){
        if (num <= 1)
            return false;
        if (num <= 3)
            return true;

        if (num % 2 == 0 || num % 3 == 0)
            return false;

        for (int i = 5; i * i <= num; i = i + 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;

        return true;
    }

    /**
     * Finds an InteractionList in the dictionary with a given subject.
     *
     * @param subject Subject to find
     * @return The appropriate InteractionList
     */
    public InteractionList findSubject(String subject){
        int hashIndex = this.hashIndex(subject);
        int probeIndex = this.linearProbe(subject, hashIndex);
        return subjectList[probeIndex];
    }
}