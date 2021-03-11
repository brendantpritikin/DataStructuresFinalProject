import java.util.ArrayDeque;

public class InteractionStack{
    private ArrayDeque<Interaction> chronology;

    public InteractionStack(){
        this.chronology = new ArrayDeque<Interaction>();
    }

    public boolean isEmpty(){
        if (this.chronology.size() != 0){
            return false;
        }
        else{
            return true;
        }
    }

    public void showInteractions(){
        ArrayDeque<Interaction> copyStack = chronology.clone();
        int lim = copyStack.size();
        Interaction nextInteraction = null;
        for (int i = 0; i < lim; i++){
            nextInteraction = copyStack.pop();
            System.out.println("Date: " + nextInteraction.getDate() + "; Subject: " + nextInteraction.getSubject());
        }
    }

    public void addInteraction(Interaction interaction){
        chronology.push(interaction);
    }

    public void checkLastInteraction(){
        System.out.println(chronology.peek());
    }

    public Interaction findSubject(String subject){
        ArrayDeque<Interaction> copyStack = chronology.clone();
        int lim = copyStack.size();
        for (int i = 0; i < lim; i++){
            Interaction next = copyStack.pop();
            if (next.getSubject() == subject){
                return next;
            }
        }
        return null;
    }
}