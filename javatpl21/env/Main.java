import java.util.Scanner;

/**
 * Main
 */
public class Main {

  /**
   * ミニモンカードゲーム
   *
   * ミニモンカードをユーザとCPUがランダムに5枚引く(ミニモンカードは5種類存在する)．各ミニモンカードにはHPとAPが設定されている．
   * ユーザとCPU両方がバトル場に出すミニモンを同時に選択する．このとき，5枚の中に同じミニモンがいた場合，ミニモン融合が行われ，APが同じミニモンの数だけ合計される（同じミニモンが3体いた場合は3倍になる）．HPはそのまま
   * ユーザとCPUの選択したミニモンが互いに攻撃する．このとき，攻撃側ミニモンのAPが防御側ミニモンのHPを超えた場合，防御側プレイヤーへのダメージとなる．超えなかった場合はバックファイアとして攻撃側のプレイヤーにダメージが返ってくる．
   * 最終的にプレーヤーあるいはCPUのHPが0になったら終わり．
   *
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    MinimonCardGame mcg = new MinimonCardGame();
    Scanner scanner = new Scanner(System.in);// 標準入力
    while (true) {
      mcg.drawPhase(scanner);
      mcg.battlePhase(scanner);
      if (mcg.getPlayerHp() <= 0 && mcg.getCpuHp() <= 0) {
        System.out.println("引き分け！");
      } else if (mcg.getPlayerHp() <= 0) {
        System.out.println("CPU Win!");
      } else if (mcg.getCpuHp() <= 0) {
        System.out.println("Player Win!");
      } else {
        Thread.sleep(2000);
        continue;
      }
      break;
    }
    scanner.close();
  }
}
