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

  private String borderCharacters;
  private final List<Object> contentCols;
  private final List<Object> headerCols;
  private final List<Object> headlines;
  private final int padding;

  public AsciiArtTable() {
    this(1);
  }

  public AsciiArtTable(final int padding) {
    this(padding, "╔═╤╗║╟─┬╢╪╠╣│╚╧╝┼");
  }

  public AsciiArtTable(final int padding, final String borderCharacters) {
    this.headerCols = new ArrayList<>();
    this.contentCols = new ArrayList<>();
    this.headlines = new ArrayList<>();
    this.padding = padding;
    this.borderCharacters = borderCharacters;
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

  public void addHeadline(final Object headline) {
    this.headlines.add(headline);
  }

  public void clear() {
    headerCols.clear();
    contentCols.clear();
    headlines.clear();
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

  public String getOutput() {
    // prepare data
    while (contentCols.size() % headerCols.size() != 0) {
      contentCols.add("");
    }
    // build header
    String result = "";
    if (headlines.isEmpty()) {
      result += row(borderCharacters.charAt(0), borderCharacters.charAt(1), borderCharacters.charAt(2), borderCharacters.charAt(3)) + System.lineSeparator();
    } else {
      result += row(borderCharacters.charAt(0), borderCharacters.charAt(1), borderCharacters.charAt(1), borderCharacters.charAt(3)) + System.lineSeparator();
      for (Object headline : headlines) {
        result += rowHeadline(headline.toString(), borderCharacters.charAt(4), borderCharacters.charAt(4));
        if (headlines.indexOf(headline) == headlines.size() - 1) {
          if (outputOfHeaderColsIsRequested()) {
            result += row(borderCharacters.charAt(5), borderCharacters.charAt(6), borderCharacters.charAt(7), borderCharacters.charAt(8)) + System.lineSeparator();
          } else {
            result += row(borderCharacters.charAt(10), borderCharacters.charAt(1), borderCharacters.charAt(2), borderCharacters.charAt(11)) + System.lineSeparator();
          }
        } else {
          result += row(borderCharacters.charAt(5), borderCharacters.charAt(6), borderCharacters.charAt(6), borderCharacters.charAt(8)) + System.lineSeparator();
        }
      }
    }
    if (outputOfHeaderColsIsRequested()) {
      result += line(headerCols, borderCharacters.charAt(4), borderCharacters.charAt(12), borderCharacters.charAt(4)) + System.lineSeparator();
      result += row(borderCharacters.charAt(10), borderCharacters.charAt(1), borderCharacters.charAt(9), borderCharacters.charAt(11)) + System.lineSeparator();
    }
    int col = 0;
    while (col < contentCols.size()) {
      result += line(contentCols.subList(col, col + headerCols.size()), borderCharacters.charAt(4), borderCharacters.charAt(12), borderCharacters.charAt(4)) + System.lineSeparator();
      col += headerCols.size();
      if (col == contentCols.size()) {
        result += row(borderCharacters.charAt(13), borderCharacters.charAt(1), borderCharacters.charAt(14), borderCharacters.charAt(15)) + System.lineSeparator();
      } else {
        result += row(borderCharacters.charAt(5), borderCharacters.charAt(6), borderCharacters.charAt(16), borderCharacters.charAt(8)) + System.lineSeparator();
      }
    }
    return result;
  }

  private int getTableLength() {
    final int[] colWidths = getColWidths();
    int result = 0;
    for (int colWidth : colWidths) {
      result += colWidth + 2 * padding;
    }
    return result + colWidths.length + 1;
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

  private boolean outputOfHeaderColsIsRequested() {
    for (Object headerCol : headerCols) {
      if (headerCol.toString().length() > 0) {
        return true;
      }
    }
    return false;
  }

  public void print(final PrintStream printStream) {
    printStream.print(getOutput());
  }

  private String row(final char left, final char middle, final char columnSeparator, final char right) {
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

  private String rowHeadline(final String headline, final char left, final char right) {
    final int tableLength = getTableLength();
    final int contentWidth = tableLength - (2 * padding) - 2;
    // FIXME a single word could be longer than the table
    // split into headline rows
    final List<String> headlineLines = new ArrayList<>();
    final String[] headlineWords = headline.split(" ");
    List<String> rowWords = new ArrayList<>();
    for (int index = 0; index < headlineWords.length; index++) {
      if (index + 1 != headlineWords.length && (StringUtils.join(rowWords, ' ') + ' ' + headlineWords[index]).length() > contentWidth) {
        headlineLines.add(StringUtils.join(rowWords, ' '));
        rowWords.clear();
      }
      rowWords.add(headlineWords[index]);
    }
    if (!rowWords.isEmpty()) {
      headlineLines.add(StringUtils.join(rowWords, ' '));
    }
    // build result
    String result = "";
    for (String headlineLine : headlineLines) {
      result += left + StringUtils.repeat(' ', padding) + StringUtils.rightPad(headlineLine, tableLength - padding - 2) + right + System.lineSeparator();
    }
    return result;
  }

  public void setBorderCharacters(final String borderCharacters) {
    this.borderCharacters = borderCharacters;
  }

  public void setNoHeaderColumns(int withColumns) {
    this.headerCols.clear();
    while (withColumns-- > 0) {
      this.headerCols.add("");
    }
  }
}
