import java.util.ArrayList;
public class InteractionList
{
  private String subject;
  private ArrayList<Interaction> interactions;

   /**
   *
   *
   */
  public InteractionList(String inSubject, ArrayList<Interaction> inInteractions)
  {
    subject = inSubject;
    interactions = inInteractions;
  }

  /**
   *
   *
   */
  public String getSubject()
  {
    return subject;
  }


    /**
    *
    *
    */
   public ArrayList<Interaction> getInteractions()
  {
    return interactions;
  }

  /**
   *
   *
   */
  public void showInteractions()
  {
    for(Interaction currentInteraction : interactions)
    {
      System.out.println(currentInteraction);
    }
  }

  /**
   *
   *
   */
  public void addInteraction(Interaction toAdd)
  {
interactions.add(toAdd);
  }
  
}