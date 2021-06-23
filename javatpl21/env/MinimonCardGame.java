import java.util.Random;
import java.util.Scanner;

/**
 * MonsterPoker
 */
public class MinimonCardGame {

  Random card = new Random();

  int playerHp = 200; // PlayerのHP
  int cpuHp = 200; // cpuのHP
  int playerDeck[] = new int[5]; // 0~4までの数字（モンスターID）が入る
  int cpuDeck[] = new int[5];
  String minimons[] = { "ミニチュウ", "ヤジロー", "オモロバナ", "ピュー", "グルリン" };// それぞれが0~4のIDのモンスターに相当する
  int monsterAp[] = { 10, 20, 30, 25, 30 }; // 各モンスターのAP
  int monsterHp[] = { 40, 20, 25, 15, 20 }; // 各モンスターのHP
  int cpuExchangeCards[] = new int[5];// それぞれ0,1でどのカードを交換するかを保持する．{0,1,1,0,1}の場合は2,3,5枚目のカードを交換することを表す
  String c13 = new String();// 交換するカードを1~5の数字の組み合わせで保持する．上の例の場合，"235"となる．
  int playerYaku[] = new int[5];// playerのモンスターカードがそれぞれ何枚ずつあるかを保存する配列．{2,3,0,0,0}の場合，ID0が2枚,ID1が3枚あることを示す．
  int cpuYaku[] = new int[5];// playerのモンスターカードがそれぞれ何枚ずつあるかを保存する配列．{2,3,0,0,0}の場合，ID0が2枚,ID1が3枚あることを示す．

  /**
   * 5枚のミニモンカードをプレイヤー/CPUが順に引く
   *
   * @throws InterruptedException
   */
  public void drawPhase(Scanner scanner) throws InterruptedException {
    // 初期Draw
    System.out.println("PlayerのDraw！");
    for (int i = 0; i < playerDeck.length; i++) {
      this.playerDeck[i] = card.nextInt(5);
    }
    // カードの表示
    System.out.print("[Player]");
    for (int i = 0; i < playerDeck.length; i++) {
      System.out.printf("%s ", this.minimons[playerDeck[i]]);
    }
    System.out.println("");

    System.out.println("カード交換");
    System.out.println("ミニモンカードを交換する場合は1から5の数字（左から数えた位置を表す）を続けて入力してください．交換しない場合は0と入力してください");
    String exchangeCard = scanner.nextLine();
    if (exchangeCard.charAt(0) != '0') {
      for (int i = 0; i < exchangeCard.length(); i++) {
        this.playerDeck[Character.getNumericValue(exchangeCard.charAt(i)) - 1] = card.nextInt(5);
      }
      // カードの表示
      System.out.print("[Player]");
      for (int i = 0; i < playerDeck.length; i++) {
        System.out.printf("%s ", this.minimons[playerDeck[i]]);
      }
      System.out.println();
      System.out.println("もう一度カードを交換する場合は1から5の数字（左から数えた位置を表す）を続けて入力してください．交換しない場合は0と入力してください");
      exchangeCard = scanner.nextLine();
      if (exchangeCard.charAt(0) != '0') {
        for (int i = 0; i < exchangeCard.length(); i++) {
          this.playerDeck[Character.getNumericValue(exchangeCard.charAt(i)) - 1] = card.nextInt(5);
        }
        // カードの表示
        System.out.print("[Player]");
        for (int i = 0; i < playerDeck.length; i++) {
          System.out.printf("%s ", this.minimons[playerDeck[i]]);
        }
        System.out.println();
      }
    }

    System.out.println("CPUのDraw！");
    for (int i = 0; i < cpuDeck.length; i++) {
      this.cpuDeck[i] = card.nextInt(5);
    }
    // カードの表示
    System.out.print("[CPU]");
    for (int i = 0; i < cpuDeck.length; i++) {
      System.out.printf("%s ", this.minimons[cpuDeck[i]]);
    }
    System.out.println();

    // 交換するカードの決定
    System.out.println("CPUが交換するカードを考えています・・・・・・");
    Thread.sleep(2000);
    // cpuDeckを走査して，重複するカード以外のカードをランダムに交換する
    // 0,1,0,2,3 といったcpuDeckの場合，2枚目，4枚目，5枚目のカードをそれぞれ交換するかどうか決定し，例えば24といった形で決定する
    // 何番目のカードを交換するかを0,1で持つ配列の初期化
    // 例えばcpuExchangeCards[]が{0,1,1,0,0}の場合は2,3枚目を交換の候補にする
    for (int i = 0; i < this.cpuExchangeCards.length; i++) {
      this.cpuExchangeCards[i] = -1;
    }
    for (int i = 0; i < this.cpuDeck.length; i++) {
      if (this.cpuExchangeCards[i] == -1) {
        for (int j = i + 1; j < this.cpuDeck.length; j++) {
          if (this.cpuDeck[i] == this.cpuDeck[j]) {
            this.cpuExchangeCards[i] = 0;
            this.cpuExchangeCards[j] = 0;
          }
        }
        if (this.cpuExchangeCards[i] != 0) {
          this.cpuExchangeCards[i] = this.card.nextInt(2);// 交換するかどうかをランダムに最終決定する
          // this.cpuExchangeCards[i] = 1;
        }
      }
    }

    // 交換するカード番号の表示
    this.c13 = "";
    for (int i = 0; i < cpuExchangeCards.length; i++) {
      if (this.cpuExchangeCards[i] == 1) {
        this.c13 = this.c13 + (i + 1);
      }
    }
    if (this.c13.length() == 0) {
      this.c13 = "0";
    }
    System.out.println(this.c13);

    // カードの交換
    if (c13.charAt(0) != '0') {
      for (int i = 0; i < c13.length(); i++) {
        this.cpuDeck[Character.getNumericValue(c13.charAt(i)) - 1] = card.nextInt(5);
      }
      // カードの表示
      System.out.print("[CPU]");
      for (int i = 0; i < cpuDeck.length; i++) {
        System.out.printf("%s ", this.minimons[cpuDeck[i]]);
      }
      System.out.println();
    }

    // 交換するカードの決定
    System.out.println("CPUが交換するカードを考えています・・・・・・");
    Thread.sleep(2000);
    // cpuDeckを走査して，重複するカード以外のカードをランダムに交換する
    // 0,1,0,2,3 といったcpuDeckの場合，2枚目，4枚目，5枚目のカードをそれぞれ交換するかどうか決定し，例えば24といった形で決定する
    // 何番目のカードを交換するかを0,1で持つ配列の初期化
    // 例えばcpuExchangeCards[]が{0,1,1,0,0}の場合は2,3枚目を交換の候補にする
    for (int i = 0; i < this.cpuExchangeCards.length; i++) {
      this.cpuExchangeCards[i] = -1;
    }
    for (int i = 0; i < this.cpuDeck.length; i++) {
      if (this.cpuExchangeCards[i] == -1) {
        for (int j = i + 1; j < this.cpuDeck.length; j++) {
          if (this.cpuDeck[i] == this.cpuDeck[j]) {
            this.cpuExchangeCards[i] = 0;
            this.cpuExchangeCards[j] = 0;
          }
        }
        if (this.cpuExchangeCards[i] != 0) {
          this.cpuExchangeCards[i] = this.card.nextInt(2);// 交換するかどうかをランダムに最終決定する
          // this.cpuExchangeCards[i] = 1;
        }
      }
    }

    // 交換するカード番号の表示
    this.c13 = "";
    for (int i = 0; i < cpuExchangeCards.length; i++) {
      if (this.cpuExchangeCards[i] == 1) {
        this.c13 = this.c13 + (i + 1);
      }
    }
    if (this.c13.length() == 0) {
      this.c13 = "0";
    }
    System.out.println(this.c13);

    // カードの交換
    if (c13.charAt(0) != '0') {
      for (int i = 0; i < c13.length(); i++) {
        this.cpuDeck[Character.getNumericValue(c13.charAt(i)) - 1] = card.nextInt(5);
      }
      // カードの表示
      System.out.print("[CPU]");
      for (int i = 0; i < cpuDeck.length; i++) {
        System.out.printf("%s ", this.minimons[cpuDeck[i]]);
      }
      System.out.println();
    }
  }

  public void battlePhase(Scanner scanner) throws InterruptedException {

    int playerMinimonAp[] = new int[5];
    // 役判定用配列の初期化
    for (int i = 0; i < playerYaku.length; i++) {
      this.playerYaku[i] = 0;
      playerMinimonAp[i] = monsterAp[i];
    }
    // ミニモンカードが何が何枚あるかをplayerYaku配列に登録
    for (int i = 0; i < playerDeck.length; i++) {
      this.playerYaku[this.playerDeck[i]]++;
    }
    // プレイヤーの2枚以上あるミニモンのAPを融合
    for (int i = 0; i < playerYaku.length; i++) {
      int aprate = 1;// AP倍率
      if (playerYaku[i] >= 2) {
        aprate = playerYaku[i];
        if (playerYaku[i] >= 3) {// 融合ボーナス
          aprate = playerYaku[i] + 1;
          if (playerYaku[i] >= 4) {
            aprate = playerYaku[i] + 2;
            if (playerYaku[i] >= 5) {
              aprate = playerYaku[i] * 2;
            }
          }
        }
      }
      playerMinimonAp[i] = monsterAp[i] * aprate;
    }

    // カードの表示
    System.out.println("融合！");
    System.out.print("[Player]");
    for (int i = 0; i < playerDeck.length; i++) {
      System.out.printf("%s(%d/%d) ", this.minimons[playerDeck[i]], playerMinimonAp[playerDeck[i]],
          this.monsterHp[playerDeck[i]]);
    }
    System.out.println();

    int cpuMinimonAp[] = new int[5];
    // 役判定用配列の初期化
    for (int i = 0; i < cpuYaku.length; i++) {
      this.cpuYaku[i] = 0;
      cpuMinimonAp[i] = monsterAp[i];
    }
    // ミニモンカードが何が何枚あるかをcpuYaku配列に登録
    for (int i = 0; i < cpuDeck.length; i++) {
      this.cpuYaku[this.cpuDeck[i]]++;
    }
    // CPUの2枚以上あるミニモンのAPを融合
    for (int i = 0; i < cpuYaku.length; i++) {
      int aprate = 1;// AP倍率
      if (cpuYaku[i] >= 2) {
        aprate = cpuYaku[i];
        if (cpuYaku[i] >= 3) {// 融合ボーナス
          aprate = cpuYaku[i] + 1;
          if (cpuYaku[i] >= 4) {
            aprate = cpuYaku[i] + 2;
            if (cpuYaku[i] >= 5) {
              aprate = cpuYaku[i] * 2;
            }
          }
        }
      }
      cpuMinimonAp[i] = monsterAp[i] * aprate;
    }
    // カードの表示
    System.out.print("[CPU]");
    for (int i = 0; i < cpuDeck.length; i++) {
      System.out.printf("%s(%d/%d) ", this.minimons[cpuDeck[i]], cpuMinimonAp[cpuDeck[i]], this.monsterHp[cpuDeck[i]]);
    }
    System.out.println();

    // バトル
    System.out.println("バトル場に出すミニモンを選択(左から1~5のいずれかを選択して入力する)");
    int selectedPlayerMinimon = Integer.parseInt(scanner.nextLine()) - 1;
    int playerMinimonNumber = this.playerDeck[selectedPlayerMinimon];
    System.out.println("Playerは" + this.minimons[playerMinimonNumber] + ":" + playerMinimonAp[playerMinimonNumber] + "/"
        + this.monsterHp[playerMinimonNumber] + "をバトル場に出した！");

    System.out.println("CPUはバトル場に出すミニモンを考えている．．．");
    Thread.sleep(2000);
    int selectedCpuMinimon;
    for (int i = 0; i < cpuDeck.length; i++) {
      int maxSumah = 0;
      selectedCpuMinimon = 0;

      int sumah = cpuMinimonAp[cpuDeck[i]] + this.monsterHp[cpuDeck[i]];
      if (maxSumah < sumah) {
        maxSumah = sumah;
        selectedCpuMinimon = cpuDeck[i];
      } else if (maxSumah == sumah) {
        if (this.monsterHp[cpuDeck[i]] > this.monsterHp[selectedCpuMinimon]) {
          selectedCpuMinimon = cpuDeck[i];
        }
      }
    }
    int cpuMinimonNumber = this.cpuDeck[selectedPlayerMinimon];
    System.out.println("CPUは" + this.minimons[cpuMinimonNumber] + ":" + cpuMinimonAp[cpuMinimonNumber] + "/"
        + this.monsterHp[cpuMinimonNumber] + "をバトル場に出した！");
    Thread.sleep(1000);

    // Playerの攻撃
    int playerBattleMinimonAp = playerMinimonAp[playerMinimonNumber];
    int playerBattleMinimonHp = this.monsterHp[playerMinimonNumber];
    int cpuBattleMinimonAp = cpuMinimonAp[cpuMinimonNumber];
    int cpuBattleMinimonHp = this.monsterHp[cpuMinimonNumber];
    System.out.print("Playerの" + this.minimons[playerMinimonNumber] + "の攻撃！");
    if (playerBattleMinimonAp > cpuBattleMinimonHp) {
      System.out.printf("%sは%sの攻撃をガードできなかった！\n", this.minimons[cpuMinimonNumber], this.minimons[playerMinimonNumber]);
      int cpuDamage = playerBattleMinimonAp - cpuBattleMinimonHp;
      System.out.printf("CPUは%dのダメージを受けた！\n", cpuDamage);
      this.cpuHp = this.cpuHp - cpuDamage;

    } else {
      System.out.printf("%sは%sの攻撃に耐えた！\n", this.minimons[cpuMinimonNumber], this.minimons[playerMinimonNumber]);
    }
    System.out.print("CPUの" + this.minimons[cpuMinimonNumber] + "の攻撃！");
    if (cpuBattleMinimonAp > playerBattleMinimonHp) {
      System.out.printf("%sは%sの攻撃をガードできなかった！\n", this.minimons[playerMinimonNumber], this.minimons[cpuMinimonNumber]);
      int playerDamage = cpuBattleMinimonAp - playerBattleMinimonHp;
      System.out.printf("Playerは%dのダメージを受けた！\n", playerDamage);
      this.playerHp = this.playerHp - playerDamage;

    } else {
      System.out.printf("%sは%sの攻撃に耐えた！\n", this.minimons[playerMinimonNumber], this.minimons[cpuMinimonNumber]);
    }

    System.out.println("PlayerのHPは" + this.playerHp);
    System.out.println("CPUのHPは" + this.cpuHp);
    Thread.sleep(2000);

  }

  public Random getCard() {
    return card;
  }

  public void setCard(Random card) {
    this.card = card;
  }

  public int getPlayerHp() {
    return playerHp;
  }

  public void setPlayerHp(int playerHp) {
    this.playerHp = playerHp;
  }

  public int getCpuHp() {
    return cpuHp;
  }

  public void setCpuHp(int cpuHp) {
    this.cpuHp = cpuHp;
  }

  public int[] getPlayerDeck() {
    return playerDeck;
  }

  public void setPlayerDeck(int[] playerDeck) {
    this.playerDeck = playerDeck;
  }

  public int[] getCpuDeck() {
    return cpuDeck;
  }

  public void setCpuDeck(int[] cpuDeck) {
    this.cpuDeck = cpuDeck;
  }

  public String[] getMinimons() {
    return minimons;
  }

  public void setMinimons(String[] minimons) {
    this.minimons = minimons;
  }

  public int[] getMonsterAp() {
    return monsterAp;
  }

  public void setMonsterAp(int[] monsterAp) {
    this.monsterAp = monsterAp;
  }

  public int[] getMonsterHp() {
    return monsterHp;
  }

  public void setMonsterHp(int[] monsterHp) {
    this.monsterHp = monsterHp;
  }

  public int[] getCpuExchangeCards() {
    return cpuExchangeCards;
  }

  public void setCpuExchangeCards(int[] cpuExchangeCards) {
    this.cpuExchangeCards = cpuExchangeCards;
  }

  public String getC13() {
    return c13;
  }

  public void setC13(String c13) {
    this.c13 = c13;
  }

  public int[] getPlayerYaku() {
    return playerYaku;
  }

  public void setPlayerYaku(int[] playerYaku) {
    this.playerYaku = playerYaku;
  }

  public int[] getCpuYaku() {
    return cpuYaku;
  }

  public void setCpuYaku(int[] cpuYaku) {
    this.cpuYaku = cpuYaku;
  }
}
