class ContactInformation{

  private int number;
  private String name;
  private String company;
  private String job;
  private InteractionStack interactions; 

  public ContactInformation(int number, String name, String job, String company, InteractionStack interactions){
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
    system.out.println("Job: " + job);
    System.out.println("All interactions: ");
    interactions.showInteractions();
  }


  public int key(){
    keyString = this.name.toLowerCase();
    int key = 0;
    for (int i = 0; i < keyString.length(); i++) {
            key += keyString.charAt(i);
    }
    return key;
  }
}