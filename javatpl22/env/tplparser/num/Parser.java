package tplparser.num;

import tplparser.Source;

public class Parser extends Source {
  public Parser(String str) {
    super(str);
  }

  // <number>::=('0'|...|'9'){('0'|...|'9')}
  // <number>::=[0‐9]+
  public int number() {
    StringBuilder sb = new StringBuilder();
    int ch;
    while ((ch = peek()) >= 0 && Character.isDigit(ch)) {
      sb.append((char) ch);
      next();
    }
    return Integer.parseInt(sb.toString());
  }

  public int number2() {
    StringBuilder sb = new StringBuilder();
    int ch;
    if ((ch = peek()) >= 0 && Character.isDigit(ch)) {
      sb.append((char) ch);
      next();
      if ((ch = peek()) >= 0 && Character.isDigit(ch)) {
        sb.append(number2());
      }
    }
    return Integer.parseInt(sb.toString());
  }

  public int number3() {
    StringBuilder sb = new StringBuilder();
    int ch;
    if ((ch = peek()) >= 0 && Character.isDigit(ch)) {
      sb.append((char) ch);
      next();
    } else if ((ch = peek()) >= 0 && Character.isDigit(ch)) {
      sb.append((char) ch);
      next();
      if ((ch = peek()) >= 0 && Character.isDigit(ch)) {
        sb.append(number3());
      }
    }
    return Integer.parseInt(sb.toString());
  }
}
