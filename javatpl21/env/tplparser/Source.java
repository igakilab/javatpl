package tplparser;

/**
 * Source
 */
public class Source {
  private final String str;
  protected int pos;

  public Source(String str) {
    this.str = str;
  }

  public final int peek() {
    if (pos < str.length()) {
      return str.charAt(pos);
    }
    return -1;
  }

  public final void next() {
    pos++;
  }

}
