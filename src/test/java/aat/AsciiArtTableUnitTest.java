package aat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsciiArtTableUnitTest {

  @Test
  public void testDefault() {
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    String expected = "";
    expected += "╔═══════════════════╤═══════╤══════╗" + System.lineSeparator();
    expected += "║              some │   foo │  bar ║" + System.lineSeparator();
    expected += "╠═══════════════════╪═══════╪══════╣" + System.lineSeparator();
    expected += "║             bello │ pussy │ hans ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║                 1 │     2 │  3.0 ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║ a very long thing │       │      ║" + System.lineSeparator();
    expected += "╚═══════════════════╧═══════╧══════╝" + System.lineSeparator();
    assertEquals(expected, aat.toString());
  }

  @Test
  public void testPadding() {
    AsciiArtTable aat = new AsciiArtTable(2);
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    String expected = "";
    expected += "╔═════════════════════╤═════════╤════════╗" + System.lineSeparator();
    expected += "║               some  │    foo  │   bar  ║" + System.lineSeparator();
    expected += "╠═════════════════════╪═════════╪════════╣" + System.lineSeparator();
    expected += "║              bello  │  pussy  │  hans  ║" + System.lineSeparator();
    expected += "╟─────────────────────┼─────────┼────────╢" + System.lineSeparator();
    expected += "║                  1  │      2  │   3.0  ║" + System.lineSeparator();
    expected += "╟─────────────────────┼─────────┼────────╢" + System.lineSeparator();
    expected += "║  a very long thing  │         │        ║" + System.lineSeparator();
    expected += "╚═════════════════════╧═════════╧════════╝" + System.lineSeparator();
    assertEquals(expected, aat.toString());
  }
}
