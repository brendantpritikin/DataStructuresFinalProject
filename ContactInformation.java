class ContactInformation{

  private int number;
  private String name;
  private String company;
  private String job;
  private InteractionDictionary interactions; 

  public ContactInformation(int number, String name, String job, String company, InteractionDictionary interactions){
    this.number = number;
    this.name = name;
    this.job = job;
    this.company = company;
    this.interactions = interactions;
  }

  public void showContact(){
    System.out.println("Contact: "+ name);
    System.out.println("Phone: " + number);
    System.out.println("Company: " + company);
    System.out.println("Job: " + job);
    System.out.println("All interactions: ");
    interactions.showInteractions();
  }


  public int key(){
    String keyString = this.name.toLowerCase();
    int key = 0;
    for (int i = 0; i < keyString.length(); i++) {
            key += keyString.charAt(i);
    }
    return key;
  }

  public String getName(){
        return name;
    }

  public InteractionList getSubject(String subjectName)
  {
     return interactions.findSubject(subjectName);
  }

  public InteractionDictionary getInteractions()
  {
    return interactions;
  }
}