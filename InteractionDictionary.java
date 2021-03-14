import java.util.ArrayList;
import java.util.Calendar;

public class InteractionDictionary{
    private InteractionList[] subjectList;

    /**
    *
    *
    */
    public InteractionDictionary(){
        this.subjectList = new InteractionList[7];
    }

    /**
     public boolean isEmpty(){

     }
     */

    /**
    *
    *
    */
    public void showInteractions(){

        ArrayList<Interaction> allInteractions = getAllInteractions();
        ArrayList<Interaction> sortedInteractions = mergeSort(allInteractions);

        for (Interaction interaction : sortedInteractions) {
            System.out.println(interaction);
        }
    }

    /**
    *
    *
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
     * A merge helper function for mergeSort.
     *
     * @param l1 The first list to be merged
     * @param l2 The second list to be merged
     * @return The combined list
     */
    private static ArrayList<Interaction> merge(ArrayList<Interaction> l1, ArrayList<Interaction> l2)
    {
        int size1 = l1.size();
        int size2 = l2.size();

        ArrayList<Interaction> resultList = new ArrayList<Interaction>();
        int i = 0;
        int j = 0;

        for (int k = 0; i <  size1 && j < size2; k++)
        {
            Interaction interaction1 =l1.get(i);
            Interaction interaction2 = l2.get(j);
            Calendar day1 = interaction1.getDate();
            Calendar day2 = interaction2.getDate();


            int winVal = day1.compareTo(day2);

            if(winVal == 1)
            {
                resultList.add(interaction1);
                i++;

            }
            else
            {
                resultList.add(interaction2);
                j++;

            }
        }

        for (int k = 0; i < l1.size(); k++)
        {
            resultList.add(l1.get(i));
            i++;

        }
        for(int k = 0; j < l2.size(); k++)
        {
            resultList.add(l2.get(j));
            j++;

        }
        return resultList;

    }

    /**
     * A mergeSort helper method to sort Interactions for sortInteractions.
     *
     * @param list The list to be sorted
     * @return The sorted list
     */
    public static ArrayList<Interaction> mergeSort(ArrayList<Interaction> list)
    {
        int n = list.size();
        if (n <= 1)
        {
            return list;
        }
        int mid = (n)/2;


        ArrayList<Interaction> list1 = getSublist(list, 0, mid-1);

        ArrayList<Interaction> list2 = getSublist(list, mid, n-1);

        ArrayList<Interaction> sortedList1 = mergeSort(list1);
        ArrayList<Interaction> sortedList2 = mergeSort(list2);

        return merge(sortedList1, sortedList2);
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


// The two commented methods below are the ones I would suggest with the InteractionList, I had some difficulty adapting the existing versions of these methods -Emma

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



/*

    public void addInteraction(Interaction interaction) {
        String subject = interaction.getSubject();
        int index = this.hashIndex(subject);
        if (this.subjectList[index] != null) {
            int newIndex = this.linearProbe(index, 1);
            this.subjectList[newIndex] = interaction;
        }
        else {
            this.subjectList[index] = interaction;
        }
        if (this.atLoadCapacity()) {
            this.increaseCapacity();
        }
    }




    public int linearProbe(int index, int j) {
        index += j;
        if (index > this.subjectList.length - 1) {
            index -= this.subjectList.length;
        }
        if (this.subjectList[index] != null){
            return linearProbe(index, j + 1);
        }
        else{
            return index;
        }
    }
*/

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
    *
    *
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

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (num % 2 == 0 || num % 3 == 0)
            return false;

        for (int i = 5; i * i <= num; i = i + 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;

        return true;
    }

    /**
    *
    *@return most recent interaction.
    */
    public void checkLastInteraction()
    {

    }


   public InteractionList findSubject(String subject){
        int hashIndex = this.hashIndex(subject);
        int probeIndex = this.linearProbe(subject, hashIndex);
        return subjectList[probeIndex];
    }
}