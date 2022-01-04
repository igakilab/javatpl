public class Main {

  public static void main(String[] args) {
    PocketKaiju pkgame = new PocketKaiju();
    pkgame.initLibrary();
    pkgame.createDeck();
    while (true) {
      if (pkgame.startPhase() == false) {
        break;
      }
    }
  }
}
