package tplparser.operator;

import tplparser.Source;

/**
 * Parser
 */
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

  public int expression5() {
    int x = term5_1();
    switch (peek()) {
      case '+':
        next();
        x += term5_1();
        break;
      case '-':
        next();
        x -= term5_1();
        break;
    }
    return x;
  }

  public int term5_1() {
    int x = number();
    x = term5_2(x);
    return x;
  }

  public int term5_2(int x) {
    if (peek() == '*') {
      next();
      x *= number();
      x = term5_2(x);
    }
    return x;
  }

  public int expression6() {
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
        case '*':
          next();
          x *= number();
          continue;
      }
      break;
    }
    return x;
  }

  public int expression6_1() {
    int x = number();
    x = expression6_2(x);
    return x;
  }

  public int expression6_2(int x) {
    if (peek() == '*') {
      next();
      x *= number();
      x = expression6_2(x);
    } else if (peek() == '+') {
      next();
      x += number();
      x = expression6_2(x);
    }
    return x;
  }

  public int expression7() {
    int x = term7_1();
    switch (peek()) {
      case '+':
        next();
        x += term7_1();
        break;
      case '-':
        next();
        x -= term7_1();
        break;
    }
    return x;
  }

  public int term7_1() {
    int x = number();
    x = term7_2(x);
    return x;
  }

  public int term7_2(int x) {
    if (peek() == '*') {
      next();
      x *= number();
      x = term7_2(x);
    }
    return x;
  }

  public int expression8() {
    int x = number();
    if (peek() == '*') {
      next();
      x = expression8_1(x);
    }
    if (peek() == '-') {
      next();
      x = x - expression8();
    }

    return x;
  }

  public int expression8_1(int x) {
    int y = number();
    x = x * y;

    return x;
  }

  public int term8_1() {
    int x = number();
    x = term8_2(x);
    return x;
  }

  public int term8_2(int x) {
    if (peek() == '*') {
      next();
      x *= number();
      x = term8_2(x);
    }
    return x;
  }

  public int expression9_1() {
    int x = number();
    x = expression9_2(x);
    return x;
  }

  public int expression9_2(int x) {
    if (peek() == '*') {
      next();
      x *= number();
      x = expression9_2(x);
    } else if (peek() == '+') {
      next();
      int y = number();
      x = x + expression9_2(y);
    }
    return x;
  }

  public int expression10() {
    int x = number();
    if (peek() == '*') {
      next();
      x *= expression10();
    } else if (peek() == '+') {
      next();
      x += expression10();
    }
    return x;
  }

  public int expression11() {
    int x = expression11();
    if (peek() == '*') {
      next();
      x *= number();
    } else if (peek() == '+') {
      next();
      x += number();
    }
    return x;
  }

}
