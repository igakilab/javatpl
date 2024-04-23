import tplparser.ParseException;
import tplparser.plus1.Parser;

public class CheckPlus1 {
  public static void main(String[] args) throws ParseException {
    System.out.println(new Parser("20+12").expression());
    System.out.println(new Parser("20+12+34+56").expression());
    System.out.println(new Parser("20-12+34+56").expression());
  }
}
