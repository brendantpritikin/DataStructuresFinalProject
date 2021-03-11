public class Interaction{
    public String date;
    public String subject;
    private String notes;

    public Interaction(String date, String subject, String notes){
        this.date = date;
        this.subject = subject;
        this.notes = notes;
    }

    public void showInteraction(){
        System.out.println("Date: " + date);
        System.out.println("Subject: " + subject);
        System.out.println("Notes:");
        System.out.println(notes);
        System.out.println("");
    }

    public String getSubject(){
        return this.subject;
    }

    public String getDate(){
        return this.date;
    }

    public String toString(){
        return "Date: " + date + "\n" + "Subject: " + subject + "\n" + "Notes:\n" + notes + "\n";
    }
}