public class MyNetwork{
    private ContactInformation[] network;

    public MyNetwork(){
        this.network = new ContactInformation[7];
    }

    public int HashCode(int key){
        return key % this.network.length;
    }

    public boolean addContact(ContactInformation newContact){
        // Brendan:
        // Refactor method so that array resizes for load factor of 0.75
        // Make sure the new size is next prime number after doubling
        if (isFull()){
            return false;
        }
        else{
            network[this.HashCode(newContact.key())] = newContact;
        }
        return true;
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