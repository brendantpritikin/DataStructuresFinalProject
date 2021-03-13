public class MyNetwork{
    private ContactInformation[] network;

    /**
     * Creates 7-entry (prime) default network.
     */
    public MyNetwork(){
        this.network = new ContactInformation[7];
    }


    // Hey guys, see the three methods I've written here..
    // They all probably need proper tie-ins but aside from that,
    // I think they're good to go. Might look with fresh eyes in the
    // AM, CA-time.
    //
    // Tossed in docstrings to help out a bit.
    // Happy to work on the rest Sat. morning. Just LMK. -Brendan
    /**
     * Method mod/new method 1:
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


    public int HashCode(int key){
        return key % this.network.length;
    }


    //Method mod/new method 2:
    //needs docstring still.
    public boolean addContact(ContactInformation newContact){
        // Brendan:
        // Refactor method so that array resizes for load factor of 0.75
        // Make sure the new size is next prime number after doubling
        int currentSize = this.length();
        if (needsResizing()){
          this.network = MyNetwork(currentSize);
        }

        if (isFull()){
            return false;
        }
        else{
            network[this.HashCode(newContact.key())] = newContact;
        }
        return true;
    }


    /**
     * Method mod/new method 3:
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

     public int quadraticProbe(int hashIndex, String key)
    {
      int increment = 1;
      int emptyIndex = -1;

        
      while(network[hashIndex] != null)
        {     
          hashIndex = (hashIndex + increment) % network.length;
          increment = adjustIncrement(increment);
            }
        
       return getOutput(emptyIndex, hashIndex);
    }

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
    
    public int getOutput(int emptyIndex, int hashIndex)
    {
        if(emptyIndex == -1)
        {
            return hashIndex;
        }
        else{
            return emptyIndex;
        }
    }
    public void showNetwork(){
        for (ContactInformation contact:network){
            if (contact != null){
                contact.showContact();
                System.out.println(" ");
            }
        }
    }
}