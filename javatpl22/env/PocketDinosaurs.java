import java.util.Scanner;

public class PocketDinosaurs {
  double dt = 0.0;// 歩いた距離
  int cards = 5;// 恐竜を捕まえられるカードの数
  int sub = 5;
  int hyb = 5;
  int p = 5;
  int c = 5;
  Scanner sc = new Scanner(System.in);

  // 卵は最大9個まで持てる．卵を取得するとeggにtrueが代入され，
  // 移動するたびに,eggDistanceに1.0kmずつ加算される．
  // 3km移動するとランダムで恐竜が孵る
  double eggDistance[] = new double[9];
  boolean egg[] = new boolean[9];

  // ユーザがGetした恐竜一覧
  String userDino[] = new String[100];
  String userDinoSize[] = new String[22];// 無し，ミニ，スーパー，ウルトラの称号を保持する

  // 恐竜図鑑．恐竜の名前とレア度(0~9)がそれぞれの配列に保存されている
  // レア度が高いほうが捕まえにくい
  String dinoDict[] = new String[22];
  int dinoRare[] = new int[22];

  int pf = 0;
  int cf = 0;
  int bf = 0;
  int f1 = 0;
  int caf = 0;

  // 呼び出すと1km distanceが増える．throwObjectの値が返る
  int move() {
    int throwObject = 0;
    cf = 0;
    pf = 0;

    this.dt++;
    for (int i = 0; i < this.egg.length; i++) {// 卵は移動距離が進むと孵化するため，何km移動したかを更新する
      if (this.egg[i] == true) {
        this.eggDistance[i]++;
      }
    }

    f1 = (int) (Math.random() * 10 + 1);// 1,2の場合はDinoSpot，5以上の場合は恐竜がでる
    if (f1 <= 2) {
      System.out.println("DinoSpotを見つけた！");
      int b = (int) (Math.random() * 4);// ball,meats,eggがランダムに出る
      int sb = (int) (Math.random() * 3);
      int hb = (int) (Math.random() * 2);
      // int f = (int) (Math.random() * 3);
      int p = (int) (Math.random() * 3);
      int c = (int) (Math.random() * 3);
      int e = (int) (Math.random() * 3);
      System.out.println("DinoCardを" + b + "個，"
          + "Super DinoCardを" + sb + "個，"
          + "Hyper DinoCardを" + hb + "個，"
          + "Porkを" + p + "個，"
          + "Chickenを" + c + "個，"
          + "Eggを" + e + "個Getした！");
      this.cards = this.cards + b;
      this.sub += sb;
      this.hyb += hb;
      this.p += p;
      this.c += c;
      // this.meats = this.meats + f;
      if (e >= 1) {// 卵を1つ以上Getしたら
        // egg[]に10個以上卵がない場合は新しい卵データをセットする
        for (int i = 0; i < this.eggDistance.length; i++) {
          if (this.egg[i] == false) {
            this.egg[i] = true;
            this.eggDistance[i] = 0.0;
            break;
          }
        }
      }
    } else if (f1 >= 5) {
      int m = (int) (this.dinoDict.length * Math.random());//
      // dinoDictからランダムに恐竜を出す

      System.out.println("野生の" + this.dinoDict[m] + "が現れた！");

      while (true) {

        System.out.println("何を使うか下記の数字から選択だ！(括弧内は現在の所持数)");
        System.out.println("1.DinoCard(" + this.cards +
            ")，2.Super DinoCard(" + this.sub +
            ")，3.Hyper DinoCard(" + this.hyb +
            ")，4.Pork(" + this.p +
            ")，5.Chicken(" + this.c + ")" +
            ")，6.End");
        throwObject = sc.nextInt();// 何を投げたか
        if (throwObject == 4) {
          if (this.p <= 0) {
            System.out.println("もうPorkは存在しない");
            System.out.println("まごまごしているうちに恐竜は逃げてしまった");
            cf = 0;
            pf = 0;

            break;
          }
          System.out.println("Porkを投げた．既に所持している恐竜を捕まえたら2段階進化するぞ");
          pf = 1;
          cf = 0;
          this.p--;
          continue;
        } else if (throwObject == 5) {
          if (this.c <= 0) {
            System.out.println("もうChickenは存在しない");
            System.out.println("まごまごしているうちに恐竜は逃げてしまった");
            cf = 0;
            pf = 0;
            break;
          }
          System.out.println("Chickenを投げた．恐竜を捕まえやすくなるぞ");
          pf = 0;
          cf = 1;
          this.c--;
          continue;
        } else if (throwObject == 1) {
          if (this.cards <= 0) {
            System.out.println("もうDinoCardは存在しない");
            System.out.println("まごまごしているうちに恐竜は逃げてしまった");
            cf = 0;
            pf = 0;
            break;
          }
          System.out.println(this.dinoDict[m] + "にDinoCardを掲げた．");
          int r = (int) (8 * Math.random());// 0~7までの数字をランダムに返す
          if (cf == 1) {
            r = r + 3;
          }
          this.cards--;
          if (this.dinoRare[m] <= r) {// dinoRare[m]の値がr以下の場合
            System.out.println(this.dinoDict[m] + "を捕まえた！");

            for (int j = 0; j < userDino.length; j++) {
              if (this.userDino[j] != null && this.userDino[j].equals(dinoDict[m])) {
                if (pf == 1) {
                  if (this.userDinoSize[j] == null) {
                    this.userDinoSize[j] = "スーパー";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ミニ")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("スーパー")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ウルトラ")) {
                    System.out
                        .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
                  }
                } else {
                  if (this.userDinoSize[j] == null) {
                    this.userDinoSize[j] = "ミニ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ミニ")) {
                    this.userDinoSize[j] = "スーパー";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("スーパー")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ウルトラ")) {
                    System.out
                        .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
                  }
                }
                cf = 0;
                pf = 0;
                break;
              } else if (this.userDino[j] == null) {
                this.userDino[j] = this.dinoDict[m];
                cf = 0;
                pf = 0;
                break;
              }
            }
            cf = 0;
            pf = 0;

            break;
          } else {
            System.out.println("恐竜は逃げ出した");
            cf = 0;
            pf = 0;
            break;
          }

        } else if (throwObject == 2) {
          if (this.sub <= 0) {
            System.out.println("もうDinoCardは存在しない");
            System.out.println("まごまごしているうちに恐竜は逃げてしまった");
            cf = 0;
            pf = 0;

            break;
          }

          System.out.println("Super DinoCardを掲げた");
          int r = (int) (5 * Math.random() + 2);// 0~4までの数字をランダムに返す
          if (cf == 1) {
            r = r + 2;
          }
          this.sub--;
          if (this.dinoRare[m] <= r) {// dinoRare[m]の値がr以下の場合
            System.out.println(this.dinoDict[m] + "を捕まえた！");

            for (int j = 0; j < userDino.length; j++) {
              if (this.userDino[j] != null && this.userDino[j].equals(this.dinoDict[m])) {
                if (pf == 1) {
                  if (this.userDinoSize[j] == null) {
                    this.userDinoSize[j] = "スーパー";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ミニ")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("スーパー")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ウルトラ")) {
                    System.out
                        .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
                  }
                } else {
                  if (this.userDinoSize[j] == null) {
                    this.userDinoSize[j] = "ミニ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ミニ")) {
                    this.userDinoSize[j] = "スーパー";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("スーパー")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ウルトラ")) {
                    System.out
                        .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
                  }
                }
                cf = 0;
                pf = 0;
                break;
              } else if (this.userDino[j] == null) {
                this.userDino[j] = this.dinoDict[m];
                cf = 0;
                pf = 0;
                break;
              }
            }
            cf = 0;
            pf = 0;

            break;
          } else {
            System.out.println("恐竜はカードから抜け出した．再挑戦！");
            continue;
          }
        } else if (throwObject == 3) {
          if (this.hyb <= 0) {
            System.out.println("もうHyper DinoCardは存在しない");
            System.out.println("まごまごしているうちに恐竜は逃げてしまった");
            cf = 0;
            pf = 0;

            break;
          }

          System.out.println("Hyper DinoCardを掲げた");
          int r = (int) (5 * Math.random() + 3);// 0~4までの数字をランダムに返す
          if (cf == 1) {
            r = r + 2;
          }
          this.hyb--;
          if (this.dinoRare[m] <= r) {// dinoRare[m]の値がr以下の場合
            System.out.println(this.dinoDict[m] + "を捕まえた！");

            for (int j = 0; j < userDino.length; j++) {
              if (this.userDino[j] != null && this.userDino[j].equals(this.dinoDict[m])) {
                if (pf == 1) {
                  if (this.userDinoSize[j] == null) {
                    this.userDinoSize[j] = "スーパー";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ミニ")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("スーパー")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ウルトラ")) {
                    System.out
                        .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
                  }
                } else {
                  if (this.userDinoSize[j] == null) {
                    this.userDinoSize[j] = "ミニ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ミニ")) {
                    this.userDinoSize[j] = "スーパー";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("スーパー")) {
                    this.userDinoSize[j] = "ウルトラ";
                    System.out
                        .println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
                  } else if (this.userDinoSize[j].equals("ウルトラ")) {
                    System.out
                        .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
                  }
                }
                cf = 0;
                pf = 0;

                break;
              } else if (this.userDino[j] == null) {
                this.userDino[j] = this.dinoDict[m];
                cf = 0;
                pf = 0;
                break;
              }
            }
            cf = 0;
            pf = 0;

            break;
          } else {
            System.out.println("恐竜はカードから抜け出した．再挑戦！");
            continue;
          }
        } else {
          System.out.println("何も投げられなかった．．．恐竜は逃げ出した");
          throwObject = -1;
          cf = 0;
          pf = 0;

          break;
        }
      }

    }
    for (int i = 0; i < this.egg.length; i++) {
      if (this.egg[i] == true && this.eggDistance[i] >= 3) {
        System.out.println("卵が孵った！");
        int m = (int) (this.dinoDict.length * Math.random());
        System.out.println(this.dinoDict[m] + "が産まれた！");

        for (int j = 0; j < userDino.length; j++) {
          if (this.userDino[j] != null && this.userDino[j].equals(this.dinoDict[m])) {
            if (this.userDinoSize[j] == null) {
              this.userDinoSize[j] = "ミニ";
              System.out.println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
            } else if (this.userDinoSize[j].equals("ミニ")) {
              this.userDinoSize[j] = "スーパー";
              System.out.println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
            } else if (this.userDinoSize[j].equals("スーパー")) {
              this.userDinoSize[j] = "ウルトラ";
              System.out.println(this.userDino[j] + "は" + this.userDinoSize[j] + this.userDino[j] + "に進化した");
            } else if (this.userDinoSize[j].equals("ウルトラ")) {
              System.out
                  .println(this.userDino[j] + "は既に" + this.userDinoSize[j] + this.userDino[j] + "に進化している");
            }
            break;
          } else if (this.userDino[j] == null) {
            this.userDino[j] = this.dinoDict[m];
            break;
          }
        }
        this.egg[i] = false;
        this.eggDistance[i] = 0.0;
      }
    }
    return throwObject;
  }

  public double getDistance() {
    return dt;
  }

  public int getCards() {
    return cards;
  }

  public String[] getUserDino() {
    return userDino;
  }

  public void setDinoDict(String[] dinoDict) {
    this.dinoDict = dinoDict;
  }

  public void setDinoRare(int[] dinoRare) {
    this.dinoRare = dinoRare;
  }

  public int getSuperCards() {
    return sub;
  }

  public void setSuperCards(int superCards) {
    this.sub = superCards;
  }

  public int getHyperCards() {
    return hyb;
  }

  public void setHyperCards(int hyperCards) {
    this.hyb = hyperCards;
  }

  public int getPork() {
    return p;
  }

  public void setPork(int pork) {
    this.p = pork;
  }

  public int getChicken() {
    return c;
  }

  public void setChicken(int chicken) {
    this.c = chicken;
  }

  public String[] getUserDinoSize() {
    return userDinoSize;
  }

  public void setUserDinoSize(String[] userDinoSize) {
    this.userDinoSize = userDinoSize;
  }

}
