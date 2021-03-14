import java.math.BigInteger;

public class MyNetwork{
    private ContactInformation[] network;

    /**
     * Creates 7-entry (prime) default network.
     */
    public MyNetwork(){
        this.network = new ContactInformation[7];
    }

    /**
     * Creates new Network of size = double network size + X to reach
     * next prime number.
     *
     * @param currentSize Capacity of current network needing to be
     * resized.
     * @precondition Current network has load factor of 0.75
     * triggering this constructor.
     */
    public MyNetwork(int currentSize){

        BigInteger currentEntryCount = new BigInteger("" + currentSize*2 + "");
        currentEntryCount = currentEntryCount.nextProbablePrime();
        int nextPrime = currentEntryCount.intValue();
        this.network = new ContactInformation[nextPrime];
    }


    /**
     * Takes a key value and returns a hash index.
     *
     * @param key The key value
     * @return Hash index
     */
    public int getHashIndex(int key){
        return key % this.network.length;
    }


    /**
     * Adds new contact to Network, returns true if successful,
     * false if not.
     *
     * @return true or false
     */
    public boolean addContact(ContactInformation newContact){

        int currentSize = this.network.length;
        if (this.needsResizing()){
            ContactInformation[] newNetwork = new ContactInformation[currentSize];

            ContactInformation[] temp = network;
            this.network = newNetwork;
            for (int i=0; i <= temp.length; i++){ //copies entries to new, larger network.
                if (temp[i] != null){
                    this.addContact(temp[i]);
                }
            }

        }

        if (isFull()){
            return false;
        }
        else{
            int hashIndex = this.getHashIndex(newContact.key());
            int probeIndex = quadraticProbe(hashIndex, newContact.getName());
            network[probeIndex] = newContact;
        }
        return true;
    }

    /**
     * Checks network for load factor > 0.75.
     * Triggers replacement network creation if load factor > 0.75.
     *
     * @precondition Assumes no empty spaces in network array.
     * @return new network if necessary, otherwise performs no action.
     */
    private boolean needsResizing() {
        double networkSize = 0.00;
        double networkCapacity = network.length;
        for (int i = 0; i < networkCapacity; i++) {
            if (network[i] != null) {
                networkSize += 1.00;
            }
        }
        if (networkSize / networkCapacity >= 0.75) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the network is full.
     *
     * @return True or False
     */
    public boolean isFull(){
        for (int i=0; i < network.length; i++){
            if (network[i] == null){
                return false;
            }
        }
        return true;
    }


    /**
     * Performs a quadratic probe to find the next available index.
     *
     * @param hashIndex The current hash index
     * @param contactName The contact name to use
     * @return
     */
    public int quadraticProbe(int hashIndex, String contactName)
    {
        int increment = 1;
        int emptyIndex = -1;
        boolean found = false;


        while(network[hashIndex] != null && (found == false))
        {
            hashIndex = (hashIndex + increment) % network.length;
            increment = adjustIncrement(increment);

            if(network[hashIndex] != null && contactName.equals(network[hashIndex].getName()))
            {
                found = true;
            }
        }

        return getOutput(emptyIndex, hashIndex, found);
    }

    /**
     * Increases the increment that will be added to the index the probe sequence is currently at
     * @param increment the number of indexes in the hash table the probe sequence will skip next
     * @return the increment to add to the index the probe sequence is currently add
     */
    public int adjustIncrement(int increment)
    {
        if(increment == 1)
        {
            increment ++;
        }
        else{
            increment *= increment;
        }
        return increment;
    }


    /**
     * Determines the index the quadratic probe function will return
     * @param emptyIndex the first available index the probe sequence reached, or -1 if the probe sequence couldn't find an available index
     * @param hashIndex the index that the hash function returned
     * @param found a boolean that is true if the probe sequence found an entry for the contact whose name was provided as an argument to quadraticProbe
     * @return the index the quadratic probe function will return 
     */
    public int getOutput(int emptyIndex, int hashIndex, boolean found)
    {
        if(emptyIndex == -1 || (found == true))
        {
            return hashIndex;
        }
        else{
            return emptyIndex;
        }
    }

    /**
     * Prints out the complete network
     */
    public void showNetwork(){
        for (ContactInformation contact:network){
            if (contact != null){
                contact.showContact();
                System.out.println(" ");
            }
        }
    }

    /**
     * Returns a list of interactions for a given contact and subject
     * @param contactName the contact whose interactions you want to access
     * @param subjectName thesubject of the interactions you want to access
     * @return the list of interactions for this contact and subject
     */
    public InteractionList getSubject(String contactName, String subjectName)
    {
        ContactInformation contactInfo = getContactInfo(contactName);
        InteractionList subjectList = contactInfo.getSubject(subjectName);
        return subjectList;
    }


    /**
     * Gets requested contact's information, returns it.
     *
     * @param contactName The name of the contact
     * @return All information that has been stored in the InteractionDictionary for that contact
     */
    public ContactInformation getContactInfo(String contactName)
    {
        int hashCode = MyNetwork.getHashCode(contactName);
        int hashIndex = getHashIndex(hashCode);
        int probeIndex = quadraticProbe(hashIndex, contactName);
        ContactInformation contactInfo = network[probeIndex];
        return contactInfo;

    }

    /**
     *
     * Converts the contact name to a hash code 
     * @param contactName the name of the contact that will be converted to a hash code
     * @return the hash code that was created from the name provided
     */
    public static int getHashCode(String contactName){
        contactName = contactName.toLowerCase();
        int hashCode = 0;
        for (int i = 0; i < contactName.length(); i++) {
            hashCode += contactName.charAt(i);
        }
        return hashCode;
    }


    /**
     *
     * Adds an interaction that occurred with a specific contact to InteractionDictionary
     * @param contactName the name of the contact who the interaction occurred with
     * @param toAdd the notes on the interaction
     */
    public void addInteraction(String contactName, Interaction toAdd)
    {
        ContactInformation contactInfo = getContactInfo(contactName);
        InteractionDictionary contactDict = contactInfo.getInteractions();
        contactDict.addInteraction(toAdd);
    }

    /**
     * Shows the interactions with a given contact.
     * 
     * @param contactName The name of the contact
     */
    public void showInteractions(String contactName)
    {
        ContactInformation contactInfo = getContactInfo(contactName);
        InteractionDictionary contactDict = contactInfo.getInteractions();
        contactDict.showInteractions();
    }
}