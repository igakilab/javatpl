import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AnimalPoker {
  static final String[] ANIMALS = { "ライオン", "トラ", "クマ", "ゾウ", "チーター", "ワシ", "タカ", "ハヤブサ" };
  static final int INITIAL_COINS = 100;
  static final int ROUNDS = 5;

  int pc = INITIAL_COINS;
  int cc = INITIAL_COINS;
  Random random = new Random();
  Scanner scanner = new Scanner(System.in);
  ArrayList<String> ph = new ArrayList<>();
  ArrayList<String> ch = new ArrayList<>();
  int[] pct = new int[ANIMALS.length];
  int[] cct = new int[ANIMALS.length];
  ArrayList<String> r = new ArrayList<>();
  ArrayList<String> cr = new ArrayList<>();
  ArrayList<String> formattedph = new ArrayList<>();
  ArrayList<String> formattedch = new ArrayList<>();

  public void playGame() {
    for (int round = 0; round < ROUNDS; round++) {
      if (round > 0) {
        System.out.println("\n=== 第 " + (round + 1) + " ラウンド開始 ===\n");
      }
      ph.clear();
      ch.clear();
      for (int i = 0; i < 5; i++) {
        ph.add(ANIMALS[random.nextInt(ANIMALS.length)]);
        ch.add(ANIMALS[random.nextInt(ANIMALS.length)]);
      }

      System.out.print("あなたの手札: ");
      formattedph.clear();
      for (String animal : ph) {
        String traits = "";
        if (animal.equals("ライオン") || animal.equals("トラ") || animal.equals("クマ")) {
          traits += "[肉]";
        }
        if (animal.equals("チーター") || animal.equals("ハヤブサ") || animal.equals("タカ")) {
          traits += "[速]";
        }
        if (animal.equals("ワシ") || animal.equals("タカ") || animal.equals("ハヤブサ")) {
          traits += "[飛]";
        }
        formattedph.add(animal + traits);
      }
      System.out.println(formattedph);

      System.out.println("あなたのコイン: " + pc + " 枚. ベット額を入力してください:");
      int b = Integer.parseInt(scanner.nextLine());
      int c = random.nextInt(Math.min(10, cc)) + 1;

      System.out.println("引き直すカードのインデックスを入力してください (カンマ区切り):");
      String[] indices = scanner.nextLine().split(",");
      for (String index : indices) {
        ph.set(Integer.parseInt(index.trim()), ANIMALS[random.nextInt(ANIMALS.length)]);
      }

      System.out.print("引き直した後のあなたの手札: ");
      formattedph.clear();
      for (String animal : ph) {
        String traits = "";
        if (animal.equals("ライオン") || animal.equals("トラ") || animal.equals("クマ")) {
          traits += "[肉]";
        }
        if (animal.equals("チーター") || animal.equals("ハヤブサ") || animal.equals("タカ")) {
          traits += "[速]";
        }
        if (animal.equals("ワシ") || animal.equals("タカ") || animal.equals("ハヤブサ")) {
          traits += "[飛]";
        }
        formattedph.add(animal + traits);
      }
      System.out.println(formattedph);

      for (int i = 0; i < 5; i++) {
        if (random.nextBoolean()) {
          ch.set(i, ANIMALS[random.nextInt(ANIMALS.length)]);
        }
      }

      for (int i = 0; i < ANIMALS.length; i++) {
        pct[i] = 0;
        cct[i] = 0;
      }
      for (String animal : ph) {
        for (int i = 0; i < ANIMALS.length; i++) {
          if (animal.equals(ANIMALS[i])) {
            pct[i]++;
          }
        }
      }
      for (String animal : ch) {
        for (int i = 0; i < ANIMALS.length; i++) {
          if (animal.equals(ANIMALS[i])) {
            cct[i]++;
          }
        }
      }

      r.clear();
      for (int count : pct) {
        if (count == 2) {
          r.add("ペア");
        }
        if (count == 3) {
          r.add("スリーカード");
        }
        if (count == 4) {
          r.add("フォーカード");
        }
        if (count == 5) {
          r.add("ファイブカード");
        }
      }
      for (int i = 0; i < pct.length; i++) {
        for (int j = 0; j < pct.length; j++) {
          if (i != j && pct[i] == 2 && pct[j] == 3) {
            r.add("フルハウス");
          }
        }
      }

      int playerCarnivores = 0, playerFastAnimals = 0, playerFlyingAnimals = 0;
      for (int i = 0; i < ph.size(); i++) {
        if (ph.get(i).equals("ライオン") || ph.get(i).equals("トラ") || ph.get(i).equals("クマ")) {
          playerCarnivores++;
        }
        if (ph.get(i).equals("チーター") || ph.get(i).equals("ハヤブサ") || ph.get(i).equals("タカ")) {
          playerFastAnimals++;
        }
        if (ph.get(i).equals("ワシ") || ph.get(i).equals("タカ") || ph.get(i).equals("ハヤブサ")) {
          playerFlyingAnimals++;
        }
      }
      if (playerCarnivores >= 3) {
        r.add("肉食動物3枚以上");
      }
      if (playerFastAnimals >= 3) {
        r.add("速い動物3枚以上");
      }
      if (playerFlyingAnimals >= 3) {
        r.add("飛ぶ動物3枚以上");
      }

      cr.clear();
      for (int count : cct) {
        if (count == 2) {
          cr.add("ペア");
        }
        if (count == 3) {
          cr.add("スリーカード");
        }
        if (count == 4) {
          cr.add("フォーカード");
        }
        if (count == 5) {
          cr.add("ファイブカード");
        }
      }
      for (int i = 0; i < cct.length; i++) {
        for (int j = 0; j < cct.length; j++) {
          if (i != j && cct[i] == 2 && cct[j] == 3) {
            cr.add("フルハウス");
          }
        }
      }

      int cpuCarnivores = 0, cpuFastAnimals = 0, cpuFlyingAnimals = 0;
      for (int i = 0; i < ch.size(); i++) {
        if (ch.get(i).equals("ライオン") || ch.get(i).equals("トラ") || ch.get(i).equals("クマ")) {
          cpuCarnivores++;
        }
        if (ch.get(i).equals("チーター") || ch.get(i).equals("ハヤブサ") || ch.get(i).equals("タカ")) {
          cpuFastAnimals++;
        }
        if (ch.get(i).equals("ワシ") || ch.get(i).equals("タカ") || ch.get(i).equals("ハヤブサ")) {
          cpuFlyingAnimals++;
        }
      }
      if (cpuCarnivores >= 3) {
        cr.add("肉食動物3枚以上");
      }
      if (cpuFastAnimals >= 3) {
        cr.add("速い動物3枚以上");
      }
      if (cpuFlyingAnimals >= 3) {
        cr.add("飛ぶ動物3枚以上");
      }

      System.out.print("CPUの手札: ");
      formattedch.clear();
      for (String animal : ch) {
        String traits = "";
        if (animal.equals("ライオン") || animal.equals("トラ") || animal.equals("クマ")) {
          traits += "[肉]";
        }
        if (animal.equals("チーター") || animal.equals("ハヤブサ") || animal.equals("タカ")) {
          traits += "[速]";
        }
        if (animal.equals("ワシ") || animal.equals("タカ") || animal.equals("ハヤブサ")) {
          traits += "[飛]";
        }
        formattedch.add(animal + traits);
      }
      System.out.println(formattedch);

      System.out.println("プレイヤーの役: " + r);
      System.out.println("CPUの役: " + cr);

      int prr = r.size();
      int crr = cr.size();
      int result = prr - crr;

      if (result > 0) {
        pc += result * b;
        cc -= result * b;
        System.out.println("プレイヤーが勝ちました！ " + result * b + " コインを獲得！");
      } else if (result < 0) {
        pc -= Math.abs(result) * c;
        cc += Math.abs(result) * c;
        System.out.println("CPUが勝ちました！ " + Math.abs(result) * c + " コインを獲得！");
      } else {
        System.out.println("引き分けです！");
      }

      System.out.println("ラウンド " + (round + 1) + " の結果: プレイヤーのコイン: " + pc + " 枚, CPUのコイン: " + cc + " 枚");

      if (pc <= 0 || cc <= 0) {
        break;
      }
    }

    if (pc > cc) {
      System.out.println("ゲーム終了！ プレイヤーの勝ち！");
    } else if (pc < cc) {
      System.out.println("ゲーム終了！ CPUの勝ち！");
    } else {
      System.out.println("ゲーム終了！ 引き分け！");
    }
  }
}
