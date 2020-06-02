package tplparser.plusn;

import tplparser.Source;

public class Parser extends Source {
  public Parser(String str) {
    super(str);
  }

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

  public int expression() {
    int x = expression();
    if (peek() == '+') {
      next();
      x += number();
    }
    return x;
  }

  public int expression2() {
    int x = number();
    while (peek() == '+') {
      next();
      x += number();
    }
    return x;
  }

  public int expression3_1() {
    int x = number();
    x = expression3_2(x);
    return x;
  }

  public int expression3_2(int x) {
    if (peek() == '+') {
      next();
      x += number();
      x = expression3_2(x);
    }
    return x;
  }

  public int expression4() {
    int x = number();
    if (peek() == '+') {
      next();
      x += expression4();
    }
    return x;
  }
}
