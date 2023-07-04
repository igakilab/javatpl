import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);
    ServantDraw sgame = new ServantDraw();
    sgame.initLibrary();
    sgame.callServants();
    while (true) {
      sgame.startPhase();

      // HPが0になったら勝敗が決定されて終了する
      if (sgame.getPlayerHp() <= 0 && sgame.getCpuHp() <= 0) {
        System.out.println("引き分け！");
        break;
      } else if (sgame.getPlayerHp() <= 0) {
        System.out.println("CPU Win!");
        break;
      } else if (sgame.getCpuHp() <= 0) {
        System.out.println("Player Win!");
        break;
      }

      // HPが0ではない場合は次のターンか終了を選択できる
      System.out.println("次のターン:1, 終了:0");
      int turnFlg = sc.nextInt();
      // Thread.sleep(2000);
      if (turnFlg == 0) {
        System.out.println("ゲームを終了します");
        System.out.println("CPU:" + sgame.getCpuHp());
        System.out.println("Player:" + sgame.getPlayerHp());
        break;// ゲーム終了
      }
    }
    sc.close();

  }
}
