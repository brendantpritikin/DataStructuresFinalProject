import java.util.ArrayList;
public class InteractionList
{
    private String subject;
    private ArrayList<Interaction> interactions;

    public InteractionList(String inSubject, ArrayList<Interaction> inInteractions)
    {
        subject = inSubject;
        interactions = inInteractions;
    }

    /**
     * Gets subject of InteractionList
     *
     * @return Subject
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Gets interactions from InteractionList
     *
     * @return interactions
     */
    public ArrayList<Interaction> getInteractions()
    {
        return interactions;
    }

    /**
     * Shows all interactions in the list.
     */
    public void showInteractions()
    {
        for(Interaction currentInteraction : interactions)
        {
            System.out.println(currentInteraction);
        }
    }

    /**
     * Adds an interaction to the list.
     *
     * @param toAdd Interaction to add
     */
    public void addInteraction(Interaction toAdd)
    {
        interactions.add(toAdd);
    }

}