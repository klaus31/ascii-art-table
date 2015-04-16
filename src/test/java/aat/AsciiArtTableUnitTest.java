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
    // have a visual impression (not part of the test)
    aat.print(System.out);
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
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testManyHeadlines() {
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeadline("Some test headline");
    aat.addHeadline("And a long explaining text, that may stop here");
    aat.addHeadline("or it will end up here.");
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    String expected = "";
    expected += "╔══════════════════════════════════╗" + System.lineSeparator();
    expected += "║ Some test headline               ║" + System.lineSeparator();
    expected += "╟──────────────────────────────────╢" + System.lineSeparator();
    expected += "║ And a long explaining text, that ║" + System.lineSeparator();
    expected += "║ may stop here                    ║" + System.lineSeparator();
    expected += "╟──────────────────────────────────╢" + System.lineSeparator();
    expected += "║ or it will end up here.          ║" + System.lineSeparator();
    expected += "╟───────────────────┬───────┬──────╢" + System.lineSeparator();
    expected += "║              some │   foo │  bar ║" + System.lineSeparator();
    expected += "╠═══════════════════╪═══════╪══════╣" + System.lineSeparator();
    expected += "║             bello │ pussy │ hans ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║                 1 │     2 │  3.0 ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║ a very long thing │       │      ║" + System.lineSeparator();
    expected += "╚═══════════════════╧═══════╧══════╝" + System.lineSeparator();
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testOneHeadline() {
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeadline("Some test headline");
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    String expected = "";
    expected += "╔══════════════════════════════════╗" + System.lineSeparator();
    expected += "║ Some test headline               ║" + System.lineSeparator();
    expected += "╟───────────────────┬───────┬──────╢" + System.lineSeparator();
    expected += "║              some │   foo │  bar ║" + System.lineSeparator();
    expected += "╠═══════════════════╪═══════╪══════╣" + System.lineSeparator();
    expected += "║             bello │ pussy │ hans ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║                 1 │     2 │  3.0 ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║ a very long thing │       │      ║" + System.lineSeparator();
    expected += "╚═══════════════════╧═══════╧══════╝" + System.lineSeparator();
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testPadding() {
    AsciiArtTable aat = new AsciiArtTable(2);
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
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
    assertEquals(expected, aat.getOutput());
  }
}
