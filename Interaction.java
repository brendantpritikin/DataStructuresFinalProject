import java.util.Calendar;

public class Interaction{
    public Calendar date;
    public String subject;
    private String notes;

    public Interaction(int month, int day, int year, String subject, String notes){
        this.date = dateMaker(month, day, year);
        this.subject = subject;
        this.notes = notes;
    }

    public static Calendar dateMaker(int month, int day, int year){
      Calendar date = Calendar.getInstance();
      date.set(year, month, day);
      return date;
    }

    public void showInteraction(){
        System.out.println("Date: " + date.getTime());
        System.out.println("Subject: " + subject);
        System.out.println("Notes:");
        System.out.println(notes);
        System.out.println("");
    }

    public String getSubject(){
        return this.subject;
    }

    public Calendar getDate(){
        return this.date;
    }

    public String toString(){
        return "Date: " + date + "\n" + "Subject: " + subject + "\n" + "Notes:\n" + notes + "\n";
    }
}