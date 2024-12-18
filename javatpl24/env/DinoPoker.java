import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DinoPoker {
  static final String[] dd = { "ティラノ", "スピノ", "アロサウルス", "ステゴ", "ヴェロキラプトル", "コアトルス", "プテラ", "始祖鳥" };
  static final int co = 100;
  static final int rs = 5;

  int pc = co;
  int cc = co;
  Random random = new Random();
  Scanner scanner = new Scanner(System.in);
  ArrayList<String> ph = new ArrayList<>();
  ArrayList<String> ch = new ArrayList<>();
  int[] pct = new int[dd.length];
  int[] cct = new int[dd.length];
  ArrayList<String> r = new ArrayList<>();
  ArrayList<String> cr = new ArrayList<>();
  ArrayList<String> formattedph = new ArrayList<>();
  ArrayList<String> formattedch = new ArrayList<>();

  public void playGame() {
    for (int round = 0; round < rs; round++) {
      if (round > 0) {
        System.out.println("\n=== 第 " + (round + 1) + " ラウンド開始 ===\n");
      }
      ph.clear();
      ch.clear();
      for (int i = 0; i < 5; i++) {
        ph.add(dd[random.nextInt(dd.length)]);
        ch.add(dd[random.nextInt(dd.length)]);
      }

      System.out.print("あなたの手札: ");
      formattedph.clear();
      for (String d : ph) {
        String traits = "";
        if (d.equals("ティラノ") || d.equals("スピノ") || d.equals("アロサウルス")) {
          traits += "[肉]";
        }
        if (d.equals("ヴェロキラプトル") || d.equals("始祖鳥") || d.equals("プテラ")) {
          traits += "[速]";
        }
        if (d.equals("コアトルス") || d.equals("プテラ") || d.equals("始祖鳥")) {
          traits += "[飛]";
        }
        formattedph.add(d + traits);
      }
      System.out.println(formattedph);

      System.out.println("あなたのコイン: " + pc + " 枚. ベット額を入力してください:");
      int b = Integer.parseInt(scanner.nextLine());
      int c = random.nextInt(Math.min(10, cc)) + 1;

      System.out.println("引き直すカードのインデックスを入力してください (カンマ区切り):");
      String[] indices = scanner.nextLine().split(",");
      for (String index : indices) {
        ph.set(Integer.parseInt(index.trim()), dd[random.nextInt(dd.length)]);
      }

      System.out.print("引き直した後のあなたの手札: ");
      formattedph.clear();
      for (String d : ph) {
        String traits = "";
        if (d.equals("ティラノ") || d.equals("スピノ") || d.equals("アロサウルス")) {
          traits += "[肉]";
        }
        if (d.equals("ヴェロキラプトル") || d.equals("始祖鳥") || d.equals("プテラ")) {
          traits += "[速]";
        }
        if (d.equals("コアトルス") || d.equals("プテラ") || d.equals("始祖鳥")) {
          traits += "[飛]";
        }
        formattedph.add(d + traits);
      }
      System.out.println(formattedph);

      for (int i = 0; i < 5; i++) {
        if (random.nextBoolean()) {
          ch.set(i, dd[random.nextInt(dd.length)]);
        }
      }

      for (int i = 0; i < dd.length; i++) {
        pct[i] = 0;
        cct[i] = 0;
      }
      for (String d : ph) {
        for (int i = 0; i < dd.length; i++) {
          if (d.equals(dd[i])) {
            pct[i]++;
          }
        }
      }
      for (String d : ch) {
        for (int i = 0; i < dd.length; i++) {
          if (d.equals(dd[i])) {
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

      int pcs = 0, pfa = 0, pfly = 0;
      for (int i = 0; i < ph.size(); i++) {
        if (ph.get(i).equals("ティラノ") || ph.get(i).equals("スピノ") || ph.get(i).equals("アロサウルス")) {
          pcs++;
        }
        if (ph.get(i).equals("ヴェロキラプトル") || ph.get(i).equals("始祖鳥") || ph.get(i).equals("プテラ")) {
          pfa++;
        }
        if (ph.get(i).equals("コアトルス") || ph.get(i).equals("プテラ") || ph.get(i).equals("始祖鳥")) {
          pfly++;
        }
      }
      if (pcs >= 3) {
        r.add("肉食恐竜3枚以上");
      }
      if (pfa >= 3) {
        r.add("速い恐竜3枚以上");
      }
      if (pfly >= 3) {
        r.add("飛ぶ恐竜3枚以上");
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

      int ccs = 0, cfd = 0, cflyd = 0;
      for (int i = 0; i < ch.size(); i++) {
        if (ch.get(i).equals("ティラノ") || ch.get(i).equals("スピノ") || ch.get(i).equals("アロサウルス")) {
          ccs++;
        }
        if (ch.get(i).equals("ヴェロキラプトル") || ch.get(i).equals("始祖鳥") || ch.get(i).equals("プテラ")) {
          cfd++;
        }
        if (ch.get(i).equals("コアトルス") || ch.get(i).equals("プテラ") || ch.get(i).equals("始祖鳥")) {
          cflyd++;
        }
      }
      if (ccs >= 3) {
        cr.add("肉食恐竜3枚以上");
      }
      if (cfd >= 3) {
        cr.add("速い恐竜3枚以上");
      }
      if (cflyd >= 3) {
        cr.add("飛ぶ恐竜3枚以上");
      }

      System.out.print("CPUの手札: ");
      formattedch.clear();
      for (String d : ch) {
        String traits = "";
        if (d.equals("ティラノ") || d.equals("スピノ") || d.equals("アロサウルス")) {
          traits += "[肉]";
        }
        if (d.equals("ヴェロキラプトル") || d.equals("始祖鳥") || d.equals("プテラ")) {
          traits += "[速]";
        }
        if (d.equals("コアトルス") || d.equals("プテラ") || d.equals("始祖鳥")) {
          traits += "[飛]";
        }
        formattedch.add(d + traits);
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
