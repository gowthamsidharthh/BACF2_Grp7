/**
 * Starting point of the program. It only creates the UI and the
 * controller, then starts the game.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        GameController game = new GameController(ui);
        game.run();
    }
}
