import java.util.ArrayList;
import java.util.Calendar;

public class InteractionDictionary{
    private Interaction[] chronology;

    public InteractionStack(){
        this.chronology = new Interaction[7];
    }

    /**
    public boolean isEmpty(){

    }
    */
    public void showInteractions(){
        ArrayList<Interaction> interactions = new ArrayList<>();
        for (Interaction interaction : chronology){
            if (interaction != null){
                interactions.add(interaction);
            }
        }
        ArrayList<Interaction> sortedInteractions = mergeSort(interactions); // Emma: If you can manage the datetime object you mentioned for this part, please do.
        for (Interaction interaction : interactions){
            System.out.println(interaction);
        }
    }
// merge sort helper function
    public static ArrayList<Interaction> getSublist(ArrayList<Interaction> list, int startIndex, int endIndex)
    {
        ArrayList<Interaction> sublist = new ArrayList<Interaction>();
        for(int index = startIndex; index <= endIndex; index ++)
        {
            sublist.add(list.get(index));
        }
        return sublist;
    }

    public static ArrayList<Interaction> merge(ArrayList<Interaction> l1, ArrayList<Interaction> l2)
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

            if(winVal == 0 || winVal == -1)
            {
                resultList.add(interaction1);
                i++;
                k++;
            }
            else
            {
                resultList.add(interaction2);
                j++;
                k++;
            }
        }

        for (int k = 0; i < l1.size(); k++)
        {
            resultList.add(l1.get(i));
            i++;
            k++;
        }
        for(int k = 0; j < l2.size(); k++)
        {
            resultList.add(l2.get(j));
            j++;
            k++;
        }
        return resultList;

    }

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



    public static int asciiValue(String subject){
        String lowSubject = subject.toLowerCase();
        int key = 0;
        for (int i = 0; i < lowSubject.length(); i++) {
            key += lowSubject.charAt(i);
        }
        return key;
    }

    // I think this method is actually the hashIndex method
    // and the asciiValue method is actually the hashCode method -Emma
    public int HashCode(String subject){
        int key = asciiValue(subject);
        return key % this.chronology.length;
    }

    public void addInteraction(Interaction interaction){
        String subject = interaction.getSubject();
        int index = this.HashCode(subject);
        int i = 0;
        // I think for line 48 you meant != instead of == ? -Emma
        while (this.chronology[index] == null){
          // I would suggest this commented code for the loop body:
          //if(i < 2){ i ++; }
          //else{i = (i*i)}
          //index += i
          // -Emma
            index += i + (i*i); //Needs to wrap around
            i++;
        }
        this.chronology[index] = interaction;
    }

    public void checkLastInteraction(){

    }

    public Interaction findSubject(String subject){

    }
}