package tplparser.plus1;

import tplparser.ParseException;
import tplparser.Source;

public class Parser extends Source {
  public Parser(String str) {
    super(str);
  }

  // <number>::=[0‐9]+
  public int number() throws ParseException {
    StringBuilder sb = new StringBuilder();
    int ch;
    while ((ch = peek()) >= 0 && Character.isDigit(ch)) {
      sb.append((char) ch);
      next();
    }
    return Integer.parseInt(sb.toString());
  }

  public int expression() throws ParseException {
    int x = number();
    if (peek() == '+') {
      next();
      x += number();
    } else
      throw new ParseException("Syntax Error:'+' is not detected");
    return x;
  }
}
