import java.util.*;

public class MonsterGame {
  static String[] m = { "ドラゴ", "ゴーレム", "ゾンビ", "スライム", "ケンタウロス", "ユニコーン", "デーモン", "グリフォン" };
  static int[] atks = { 9, 10, 4, 3, 7, 9, 6, 8 };
  static int[] defs = { 7, 5, 3, 2, 4, 4, 4, 6 };
  static String[] attrs = { "炎", "闇", "光", "風" };
  static String[] els = { "炎", "闇", "光", "光", "風", "炎", "闇", "風" };
  int p = 100;
  int c = 100;
  int t = 5;
  Random r = new Random();
  Scanner s = new Scanner(System.in);
  ArrayList<Integer> ph = new ArrayList<>();
  ArrayList<Integer> ch = new ArrayList<>();
  int maxbet;
  int bet;
  int cpuE;
  int extraPAtk, extraPDef, extraCAtk, extraCDef;
  int ev, stat;
  int totalPlayerAtk, totalPlayerDef, totalCPUAtk, totalCPUDef;
  int[] phcount;
  int[] chcount;
  int[] phAttrCount;
  int[] chAttrCount;

  public void doG() {
    for (int ro = 0; ro < t; ro++) {
      System.out.println("\n==============================");
      System.out.println("★ Round " + (ro + 1) + " / 全" + t + "ラウンド");
      System.out.println("[あなたの所持コイン: " + p + " ／ CPUの所持コイン: " + c + "]");
      System.out.println("==============================");

      ph.clear();
      ch.clear();
      for (int i = 0; i < 5; i++) {
        ph.add(r.nextInt(m.length));
        ch.add(r.nextInt(m.length));
      }

      System.out.println("\n[あなたの手札]");
      for (int i = 0; i < 5; i++) {
        int mon = ph.get(i);
        System.out.println("[" + (i + 1) + "]" + m[mon] + " ／ 攻撃力[" + atks[mon] + "]" + "／防御力[" + defs[mon] + "]" + "／"
            + els[mon] + "属性");
      }
      System.out.println("------------------------------------------------");
      System.out.println("[あなたのコイン] " + p);

      maxbet = p;
      System.out.println("今ラウンドで賭けられるコイン数は 1～" + maxbet);
      bet = 0;
      while (true) {
        System.out.print("賭けるコイン数を入力してください: ");
        try {
          bet = Integer.parseInt(s.nextLine());
          if (bet > 0 && bet <= maxbet)
            break;
          System.out.println("  ※1以上" + maxbet + "以下で指定してください");
        } catch (Exception e) {
          System.out.println("  ※数値で入力してください");
        }
      }
      System.out.println("(あなたは「" + bet + "」コインを賭けました)");

      System.out.println("\nカードの入れ替えフェーズ");
      System.out.print("  交換したいカードの番号をカンマ区切りで指定（例:2,5）してEnter:");
      String[] ki = s.nextLine().split(",");
      for (String z : ki) {
        try {
          if (z.trim().equals(""))
            continue;
          int idx = Integer.parseInt(z.trim()) - 1;
          if (idx >= 0 && idx < 5) {
            ph.set(idx, r.nextInt(m.length));
          }
        } catch (Exception e) {
        }
      }
      // 交換後の手札表示
      System.out.println("\n[交換後のあなたの手札]");
      for (int i = 0; i < 5; i++) {
        int mon = ph.get(i);
        System.out.println("[" + (i + 1) + "]" + m[mon] + " ／ 攻撃力[" + atks[mon] + "]" + "／防御力[" + defs[mon] + "]" + "／"
            + els[mon] + "属性");
      }
      System.out.println("------------------------------------------------");

      cpuE = r.nextInt(5);
      for (int i = 0; i < cpuE; i++) {
        int j = r.nextInt(5);
        int val = r.nextInt(m.length);
        ch.set(j, val);
      }

      extraPAtk = 0; extraPDef = 0; extraCAtk = 0; extraCDef = 0;
      ev = r.nextInt(3);
      if (ev == 1) {
        System.out.println("[イベント] あなたの全カード攻撃力+1");
        extraPAtk += 1;
      }
      if (ev == 2) {
        System.out.println("[イベント] CPU全カード防御力+2");
        extraCDef += 2;
      }
      stat = r.nextInt(3);
      if (stat == 0) {
        System.out.println("[状態異常] あなた防御力-1");
        extraPDef -= 1;
      }
      if (stat == 1) {
        System.out.println("[状態異常] CPU攻撃力-1");
        extraCAtk -= 1;
      }
      if (stat == 2) {
        System.out.println("[状態異常] 双方防御力+1");
        extraPDef += 1;
        extraCDef += 1;
      }

      System.out.println("\n[CPUの手札]");
      for (int i = 0; i < 5; i++) {
        int mon = ch.get(i);
        System.out.println("[" + (i + 1) + "]" + m[mon] + " ／ 攻撃力[" + atks[mon] + "]" + "／防御力[" + defs[mon] + "]" + "／"
            + els[mon] + "属性");
      }
      System.out.println("------------------------------------------------");

      totalPlayerAtk = 0; totalPlayerDef = 0; totalCPUAtk = 0; totalCPUDef = 0;
      for (int i = 0; i < 5; i++) {
        totalPlayerAtk += atks[ph.get(i)] + extraPAtk;
        totalPlayerDef += defs[ph.get(i)] + extraPDef;
        totalCPUAtk += atks[ch.get(i)] + extraCAtk;
        totalCPUDef += defs[ch.get(i)] + extraCDef;
      }

      phcount = new int[m.length];
      chcount = new int[m.length];
      phAttrCount = new int[5];
      chAttrCount = new int[5];
      for (int i = 0; i < 5; i++) {
        phcount[ph.get(i)]++;
        chcount[ch.get(i)]++;
        for (int j = 0; j < 4; j++) {
          if (els[ph.get(i)].equals(attrs[j]))
            phAttrCount[j]++;
          if (els[ch.get(i)].equals(attrs[j]))
            chAttrCount[j]++;
        }
      }
      for (int i = 0; i < m.length; i++) {
        int pc = phcount[i];
        int cc = chcount[i];
        if (pc >= 2) {
          int b = 0;
          if (pc == 2) {
            b = 5;
          } else if (pc == 3) {
            b = 10;
          } else if (pc == 4) {
            b = 18;
          } else if (pc == 5) {
            b = 30;
          }
          System.out.println("[バトルボーナス] あなた「" + m[i] + "」が" + pc + "枚 → 攻撃力+" + b);
          totalPlayerAtk += b;
        }
        if (cc >= 2) {
          int b = 0;
          if (cc == 2) {
            b = 5;
          } else if (cc == 3) {
            b = 10;
          } else if (cc == 4) {
            b = 18;
          } else if (cc == 5) {
            b = 30;
          }
          System.out.println("[バトルボーナス] CPU「" + m[i] + "」が" + cc + "枚 → 攻撃力+" + b);
          totalCPUAtk += b;
        }
      }
      for (int e = 0; e < 5; e++) {
        int pc = phAttrCount[e];
        int cc = chAttrCount[e];
        if (pc >= 2) {
          int b = 0;
          if (pc == 2) {
            b = 4;
          } else if (pc == 3) {
            b = 9;
          } else if (pc == 4) {
            b = 16;
          } else if (pc == 5) {
            b = 25;
          }
          System.out.println("[バトルボーナス] あなた「" + attrs[e] + "属性" + "」が" + pc + "枚 → 防御力+" + b);
          totalPlayerDef += b;
        }
        if (cc >= 2) {
          int b = 0;
          if (cc == 2) {
            b = 4;
          } else if (cc == 3) {
            b = 9;
          } else if (cc == 4) {
            b = 16;
          } else if (cc == 5) {
            b = 25;
          }
          System.out.println("[バトルボーナス] CPU「" + attrs[e] + "属性" + "」が" + cc + "枚 → 防御力+" + b);
          totalCPUDef += b;
        }
      }

      System.out.println("====================================");
      System.out.println("あなたの攻撃力: " + totalPlayerAtk + "    CPUの防御力: " + totalCPUDef);
      System.out.println("CPUの攻撃力  : " + totalCPUAtk + "    あなたの防御力: " + totalPlayerDef);
      boolean pWin = totalPlayerAtk > totalCPUDef;
      boolean cWin = totalCPUAtk > totalPlayerDef;
      int diffP = totalPlayerAtk - totalCPUDef;
      int diffC = totalCPUAtk - totalPlayerDef;
      if (pWin && cWin) {
        if (diffP > diffC) {
          System.out.println("[バトル結果] あなたが攻め勝ち（突破差で上回った）→ 勝利!");
          p += bet;
          c -= bet;
        } else if (diffC > diffP) {
          System.out.println("[バトル結果] CPUが攻め勝ち（突破差で上回った）→ 敗北");
          p -= bet;
          c += bet;
        } else {
          System.out.println("[バトル結果] 両者とも突破→引き分け!");
        }
      } else if (pWin) {
        System.out.println("[バトル結果] あなたの攻撃力がCPU防御力を突破→勝利");
        p += bet;
        c -= bet;
      } else if (cWin) {
        System.out.println("[バトル結果] CPUの攻撃力があなたの防御力を突破→敗北");
        p -= bet;
        c += bet;
      } else {
        System.out.println("[バトル結果] 両者突破できず→引き分け!");
      }
      System.out.println("[ラウンド終了] 残コイン あなた:" + p + " ／ CPU:" + c);
      System.out.println("-----------------------------------");
      if (p <= 0 || c <= 0) {
        break;
      }
    }
    System.out.println("================================");
    if (p > c) {
      System.out.println("【ゲーム終了】あなたの勝ち！");
    } else if (p < c) {
      System.out.println("【ゲーム終了】CPUの勝ち！");
    } else {
      System.out.println("【ゲーム終了】引き分け");
    }
  }
}
