public class InteractionStackTest {
    public static void main(String[] args) {
        Interaction test = new Interaction("03-10-2021", "Taking a test run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        Interaction test2 = new Interaction("03-09-2021", "Another Test Run",
                "This is simply to test the Interaction class. Don\'t you worry, there\'s nothing else going on here.");

        InteractionStack testStack = new InteractionStack();

        testStack.addInteraction(test2);
        testStack.addInteraction(test);

        testStack.showInteractions();

        testStack.checkLastInteraction();
        testStack.showInteractions();
        testStack.findSubject("Another Test Run");
        System.out.println(testStack.isEmpty());
    }
}