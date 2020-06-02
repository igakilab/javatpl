package tplparser.plusminus;

import tplparser.Source;

public class Parser extends Source {
  public Parser(String str) {
    super(str);
  }

  // <number>::=[1-9][0-9]*
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
    switch (peek()) {
      case '+':
        next();
        x += number();
        break;
      case '-':
        next();
        x -= number();
        break;
    }
    return x;
  }

  public int expression2() {
    int x = number();
    while (true) {
      switch (peek()) {
        case '+':
          next();
          x += number();
          continue;
        case '-':
          next();
          x -= number();
          continue;
      }
      break;
    }
    return x;
  }

  public int expression3_1() {
    int x = number();
    x = expression3_2(x);
    return x;
  }

  public int expression3_2(int x) {
    switch (peek()) {
      case '+':
        next();
        x += number();
        x = expression3_2(x);
        break;
      case '-':
        next();
        x -= number();
        x = expression3_2(x);
        break;
    }
    return x;
  }

  public int expression4() {
    int x = number();
    switch (peek()) {
      case '+':
        next();
        x += expression4();
        break;
      case '-':
        next();
        x -= expression4();
        break;
    }
    return x;
  }
}
