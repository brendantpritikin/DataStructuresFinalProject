import java.util.Calendar;

public class Interaction{
    public Calendar date;
    public String subject;
    private String notes;

    public Interaction(int month, int day, int year, String subject, String notes){
        this.date = dateMaker(month - 1, day, year);
        this.subject = subject;
        this.notes = notes;
    }

    /**
     * Helper function for creating a Calendar object for the Interaction class.
     *
     * @param month Current month
     * @param day Current day
     * @param year Current year
     * @return The date of the interaction
     */
    private static Calendar dateMaker(int month, int day, int year){
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        return date;
    }

    /**
     * Prints all of the details of the Interaction.
     */
    public void showInteraction(){
        System.out.println("Date: " + date.getTime());
        System.out.println("Subject: " + subject);
        System.out.println("Notes:");
        System.out.println(notes);
        System.out.println("");
    }

    /**
     * Returns the Interaction subject.
     *
     * @return Subject of the Interaction
     */
    public String getSubject(){
        return this.subject;
    }



    /**
     * Returns the date of the Interaction.
     *
     * @return Date of Interaction.
     */
    public Calendar getDate(){
        return this.date;
    }

    /**
     * Prints a String output of the Interaction.
     *
     * @return Interaction details
     */
    public String toString(){
        return "Date: " + date.getTime() + "\n" + "Subject: " + subject + "\n" + "Notes:\n" + notes + "\n";
    }


}