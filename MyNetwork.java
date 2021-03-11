public class MyNetwork{
    private ContactInformation[] network;

    public MyNetwork(int expectedSize){
        this.network = new ContactInformation[expectedSize];
    }

    public MyNetwork(){
        this.network = new ContactInformation[300];
    }

    public int HashCode(int key){
        return key % this.network.length;
    }

    public boolean addContact(ContactInformation newContact){
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

    public void showNetwork(){
        for (ContactInformation contact:network){
            if (contact != null){
                contact.showContact();
                System.out.println(" ");
            }
        }
    }
}