package aat;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class AsciiArtTable {

  private static String appendToLength(final Object subject, final int length) {
    final String subjectString = subject == null ? "" : subject.toString();
    if (subjectString.length() < length) {
      return subjectString + StringUtils.repeat(' ', length - subjectString.length());
    }
    return subjectString;
  }

  private static String prependToLength(final Object subject, final int length) {
    final String subjectString = subject == null ? "" : subject.toString();
    if (subjectString.length() < length) {
      return StringUtils.repeat(' ', length - subjectString.length()) + subjectString;
    }
    return subjectString;
  }

  private String borderCharacters;
  private final List<Object> contentCols;
  private final List<Object> headerCols;
  private final List<Object> headlines;
  private int maxColumnWidth = 80;
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

  private boolean alignLeft(final List<List<String>> linesContents, final int col) {
    // TODO actual state: left-aligned, if multi lines in this cell. wanted state: left-aligned, if a multi line cell in the row.
    boolean result = false;
    if (linesContents.size() > 1) {
      // are lines > first line all empty?
      for (List<String> lineContents : linesContents) {
        if (linesContents.indexOf(lineContents) != 0 && lineContents.get(col).trim().isEmpty() == false) {
          result = true;
          break;
        }
      }
    }
    return result;
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
      int length = headerCols.get(col).toString().length();
      result[col] = length > maxColumnWidth ? maxColumnWidth : length;
      col++;
    }
    int index = 0;
    while (index < contentCols.size()) {
      col = index % headerCols.size();
      final String content = contentCols.get(index) == null ? "" : contentCols.get(index).toString();
      if (content.length() > result[col]) {
        int length = contentCols.get(index).toString().length();
        result[col] = length > maxColumnWidth ? maxColumnWidth : length;
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
      result += row(headerCols, borderCharacters.charAt(4), borderCharacters.charAt(12), borderCharacters.charAt(4)) + System.lineSeparator();
      result += row(borderCharacters.charAt(10), borderCharacters.charAt(1), borderCharacters.charAt(9), borderCharacters.charAt(11)) + System.lineSeparator();
    }
    int col = 0;
    while (col < contentCols.size()) {
      result += row(contentCols.subList(col, col + headerCols.size()), borderCharacters.charAt(4), borderCharacters.charAt(12), borderCharacters.charAt(4)) + System.lineSeparator();
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

  private String row(final List<Object> contents, final char left, final char columnSeparator, final char right) {
    final int[] colWidths = getColWidths();
    String result = "";
    List<List<String>> linesContents = splitToMaxLength(contents, maxColumnWidth);
    for (List<String> lineContents : linesContents) {
      int col = 0;
      result += left;
      while (col < headerCols.size()) {
        if (alignLeft(linesContents, col)) {
          result += StringUtils.repeat(' ', padding);
          result += appendToLength(lineContents.get(col), padding + colWidths[col]);
        } else {
          result += prependToLength(lineContents.get(col), padding + colWidths[col]);
          result += StringUtils.repeat(' ', padding);
        }
        col++;
        result += col == headerCols.size() ? right : columnSeparator;
      }
      if (linesContents.indexOf(lineContents) != linesContents.size() - 1) {
        result += System.lineSeparator();
      }
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
    for (String headlineWord : headlineWords) {
      if ((StringUtils.join(rowWords, ' ') + ' ' + headlineWord).length() > contentWidth) {
        headlineLines.add(StringUtils.join(rowWords, ' '));
        rowWords.clear();
      }
      rowWords.add(headlineWord);
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

  public void setMaxColumnWidth(final int maxColumnWidth) {
    this.maxColumnWidth = maxColumnWidth;
  }

  public void setNoHeaderColumns(int withColumns) {
    this.headerCols.clear();
    while (withColumns-- > 0) {
      this.headerCols.add("");
    }
  }

  // XXX omg ...
  private List<List<String>> splitToMaxLength(final List<Object> subjects, final int maxLength) {
    final List<List<String>> result = new ArrayList<>();
    // get the count of rows in a cell must be used for given subjects
    int countRows = 1;
    for (Object subject : subjects) {
      if (subject.toString().length() > maxLength) {
        // FIXME a single word could be longer than allowed
        int countRowsForThisSubject = 1;
        final String[] words = subject.toString().split(" ");
        final List<String> columnWords = new ArrayList<>();
        for (String word : words) {
          if ((StringUtils.join(columnWords, ' ') + ' ' + word).length() > maxLength) {
            countRowsForThisSubject++;
            columnWords.clear();
          }
          columnWords.add(word);
        }
        if (countRows < countRowsForThisSubject) {
          countRows = countRowsForThisSubject;
        }
      }
    }
    // build the cellContents
    final List<List<String>> cellContents = new ArrayList<>();
    for (Object subject : subjects) {
      String content = subject.toString();
      final List<String> cellContentLines = new ArrayList<>();
      if (content.length() > maxLength) {
        final String[] words = content.split(" ");
        final List<String> cellContentLineWords = new ArrayList<>();
        for (String word : words) {
          if ((StringUtils.join(cellContentLineWords, ' ') + ' ' + word).length() > maxLength) {
            final String cellContentLine = StringUtils.join(cellContentLineWords, ' ');
            cellContentLines.add(cellContentLine);
            cellContentLineWords.clear();
          }
          cellContentLineWords.add(word);
        }
        cellContentLines.add(StringUtils.join(cellContentLineWords, ' '));
      } else {
        cellContentLines.add(content);
      }
      while (cellContentLines.size() < countRows) {
        cellContentLines.add("");
      }
      cellContents.add(cellContentLines);
    }
    // we have (pseudocode):
    // cellContents.get(0) == ["peter", ""]
    // cellContents.get(1) == ["a very", "long text"]
    // but we need (pseudocode):
    // result.add("peter", "a very")
    // result.add("", "long text")
    int row = 0;
    while (row < countRows) {
      final List<String> lineOverColumns = new ArrayList<>();
      for (int col = 0; col < headerCols.size(); col++) {
        lineOverColumns.add(cellContents.get(col).get(row));
      }
      result.add(lineOverColumns);
      row++;
    }
    return result;
  }
}
