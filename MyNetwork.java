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
    *
    *
    */
    public int getHashIndex(int key){
        return key % this.network.length;
    }


    /**
    * Adds new contact to Network, returns true if successful,
    * false if not.
    * @return true or false
    * - Combined your edits under the else statement, Emma, with
    * what I'd created previously to transfer data to new network.
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
// working version that I made is below -Emma 
    public boolean addContact(ContactInformation newContact){
        // Brendan:
        // Refactor method so that array resizes for load factor of 0.75
        // Make sure the new size is next prime number after doubling
        int currentSize = this.network.length;
        if (this.needsResizing()){
            MyNetwork newNetwork = new MyNetwork(currentSize);
        }

        if (isFull()){
            return false;
        }
        else{
            int hashIndex = this.getHashIndex(newContact.key());
            int probeIndex = quadraticProbe(hashIndex);
            network[probeIndex] = newContact;
        }
        return true;
    }
    *
    */

    /**
     * Checks network for load factor > 0.75.
     * Triggers replacement network creation if load factor > 0.75.
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
    *
    *
    */
    public boolean isFull(){
        for (int i=0; i < network.length; i++){
            if (network[i] == null){
                return false;
            }
        }
        return true;
    }

    // Emma:
    // Quadratic probing method

    /**
    *
    *
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
    *
    *
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
    *
    *
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
    *
    *
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
    *
    *
    */
    public InteractionList getSubject(String contactName)
    {
        ContactInformation contactInfo = getContactInfo(contactName);
        InteractionList subjectList = contactInfo.getSubject(contactName);
        return subjectList;
    }


    /**
    *
    *
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
    *
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
    *
    */
    public void addInteraction(String contactName, Interaction toAdd)
    {
      ContactInformation contactInfo = getContactInfo(contactName);
      InteractionDictionary contactDict = contactInfo.getInteractions();
      contactDict.addInteraction(toAdd);
    }

    public void showInteractions(String contactName)
    {
        ContactInformation contactInfo = getContactInfo(contactName);
        InteractionDictionary contactDict = contactInfo.getInteractions();
        contactDict.showInteractions();
    }
}