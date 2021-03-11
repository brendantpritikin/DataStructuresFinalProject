public class InteractionTest{
    public static void main(String[] args){
        Interaction test = new Interaction("03-10-2021", "Taking a test run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        test.showInteraction();
        System.out.println(test.getSubject());
        System.out.println(test.getDate());
    }
}