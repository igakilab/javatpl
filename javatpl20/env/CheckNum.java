import tplparser.num.Parser;

public class CheckNum {
  public static void main(String[] args) {
    System.out.println(new Parser("12").number());
    System.out.println(new Parser("121").number());
  }
}
