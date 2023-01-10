
public class Main {
  static PocketDinosaurs pz = new PocketDinosaurs();

  public static void main(String[] args) {

    setDinoDict();

    // 1000ミリ秒（1秒）ずつ止まりながらpz.move()を呼び出し続ける
    // 手持ちのDinoCardが無くなったら終了
    int moveFlg = 0;
    while (true) {
      try {
        Thread.sleep(1000);
        if ((pz.getCards() + pz.getSuperCards() + pz.getHyperCards() > 0) && moveFlg >= 0) {
          moveFlg = pz.move();
          System.out.println("手持ちのDinoCardは" + (pz.getCards() + pz.getSuperCards() + pz.getHyperCards()) + "枚");
          System.out.println(pz.getDistance() + "km歩いた．");
        } else {
          break;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("PocketDinosaursを終了します");

    for (int i = 0; i < pz.getUserDino().length; i++) {
      if (pz.getUserDino()[i] != null) {
        if (pz.getUserDinoSize()[i] != null) {
          System.out.println(pz.getUserDinoSize()[i] + pz.getUserDino()[i] + "を捕まえた．");
        } else {
          System.out.println(pz.getUserDino()[i] + "を捕まえた．");
        }
      }
    }
  }

  // テスト用の恐竜データを登録するメソッド
  public static void setDinoDict() {
    String tempDino[] = new String[11];
    int tempDinoRare[] = new int[11];
    tempDino[0] = "フタバスズキリュウ";
    tempDinoRare[0] = 9;
    tempDino[1] = "フクイサウルス";
    tempDinoRare[1] = 3;
    tempDino[2] = "トリケラトプス";
    tempDinoRare[2] = 1;
    tempDino[3] = "ディノニクス";
    tempDinoRare[3] = 2;
    tempDino[4] = "モササウルス";
    tempDinoRare[4] = 5;
    tempDino[5] = "ヴェロキラプトル";
    tempDinoRare[5] = 4;
    tempDino[6] = "モノケラトプス";
    tempDinoRare[6] = 6;
    tempDino[7] = "ステゴサウルス";
    tempDinoRare[7] = 8;
    tempDino[8] = "プテラノドン";
    tempDinoRare[8] = 7;
    tempDino[9] = "イグアノドン";
    tempDinoRare[9] = 2;
    tempDino[10] = "アルゼンティノサウルス";
    tempDinoRare[10] = 5;

    pz.setDinoDict(tempDino);
    pz.setDinoRare(tempDinoRare);
  }

}
