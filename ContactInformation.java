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

    /**
     * Shows contact information.
     */
    public void showContact(){
        System.out.println("Contact: "+ name);
        System.out.println("Phone: " + number);
        System.out.println("Company: " + company);
        System.out.println("Job: " + job);
        System.out.println("All interactions: ");
        interactions.showInteractions();
    }


    /**
     * Creates a key from the contact name.
     *
     * @return Key value
     */
    public int key(){
        String keyString = this.name.toLowerCase();
        int key = 0;
        for (int i = 0; i < keyString.length(); i++) {
            key += keyString.charAt(i);
        }
        return key;
    }

    /**
     * Gets the name of the contact.
     *
     * @return Contact name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the InteractionList for a given subject of conversation with contact.
     *
     * @param subjectName Subject of conversation
     * @return InteractionList with that subject
     */
    public InteractionList getSubject(String subjectName)
    {
        return interactions.findSubject(subjectName);
    }

    /**
     * Returns all interactions with the contact.
     *
     * @return Interactions with contact
     */
    public InteractionDictionary getInteractions()
    {
        return interactions;
    }
}