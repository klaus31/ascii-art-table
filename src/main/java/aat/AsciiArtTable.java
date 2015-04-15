package aat;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class AsciiArtTable {

  private static String prependToLength(final Object subject, final int length) {
    if (subject.toString().length() < length) {
      return StringUtils.repeat(' ', length - subject.toString().length()) + subject;
    }
    return subject.toString();
  }

  private final List<Object> contentCols;
  private final List<Object> headerCols;
  private final int padding;

  public AsciiArtTable() {
    this(1);
  }

  public AsciiArtTable(final int padding) {
    this.headerCols = new ArrayList<>();
    this.contentCols = new ArrayList<>();
    this.padding = padding;
  }

  public void add(final List<Object> contentCols) {
    this.contentCols.addAll(contentCols);
  }

  public void add(final Object... contentCols) {
    add(new ArrayList<>(Arrays.asList(contentCols)));
  }

  public void addHeaderCols(final List<Object> headerCols) {
    this.headerCols.addAll(headerCols);
  }

  public void addHeaderCols(final Object... headerCols) {
    addHeaderCols(new ArrayList<>(Arrays.asList(headerCols)));
  }

  private int[] getColWidths() {
    int[] result = new int[headerCols.size()];
    int col = 0;
    while (col < headerCols.size()) {
      result[col] = headerCols.get(col).toString().length();
      col++;
    }
    int index = 0;
    while (index < contentCols.size()) {
      col = index % headerCols.size();
      if (contentCols.get(index).toString().length() > result[col]) {
        result[col] = contentCols.get(index).toString().length();
      }
      index++;
    }
    return result;
  }

  private String line(final char left, final char middle, final char columnSeparator, final char right) {
    final int[] colWidths = getColWidths();
    String result = left + "";
    int col = 0;
    while (col < headerCols.size()) {
      result += StringUtils.repeat(middle, padding + colWidths[col] + padding);
      col++;
      result += col == headerCols.size() ? right : columnSeparator;
    }
    return result;
  }

  private String line(final List<Object> contents, final char left, final char columnSeparator, final char right) {
    final int[] colWidths = getColWidths();
    String result = left + "";
    int col = 0;
    while (col < headerCols.size()) {
      result += prependToLength(contents.get(col), padding + colWidths[col]);
      result += StringUtils.repeat(' ', padding);
      col++;
      result += col == headerCols.size() ? right : columnSeparator;
    }
    return result;
  }

  public void print(final PrintStream out) {
    out.print(toString());
  }

  @Override
  public String toString() {
    // prepare data
    while (contentCols.size() % headerCols.size() != 0) {
      contentCols.add("");
    }
    // build header
    String result = line('╔', '═', '╤', '╗') + System.lineSeparator();
    result += line(headerCols, '║', '│', '║') + System.lineSeparator();
    result += line('╠', '═', '╪', '╣') + System.lineSeparator();
    int col = 0;
    while (col < contentCols.size()) {
      result += line(contentCols.subList(col, col + headerCols.size()), '║', '│', '║') + System.lineSeparator();
      col += headerCols.size();
      if (col == contentCols.size()) {
        result += line('╚', '═', '╧', '╝') + System.lineSeparator();
      } else {
        result += line('╟', '─', '┼', '╢') + System.lineSeparator();
      }
    }
    return result;
  }
}
