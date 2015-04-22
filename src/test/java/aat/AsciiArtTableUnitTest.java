package aat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsciiArtTableUnitTest {

  @Test
  public void testDefault() {
    // when
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
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
  public void testLineSeparationOnLongInputs() {
    // when
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("a", "b", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.");
    aat.add(1, "cdefg", 3., "Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
    aat.setMaxColumnWidth(25);
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
    String expected = "";
    expected += "╔═══════════════════════════╤═══════╤═══════════════════════════╗" + System.lineSeparator();
    expected += "║                      some │   foo │                       bar ║" + System.lineSeparator();
    expected += "╠═══════════════════════════╪═══════╪═══════════════════════════╣" + System.lineSeparator();
    expected += "║                         a │     b │ Lorem ipsum dolor sit     ║" + System.lineSeparator();
    expected += "║                           │       │ amet, consetetur          ║" + System.lineSeparator();
    expected += "║                           │       │ sadipscing elitr, sed     ║" + System.lineSeparator();
    expected += "║                           │       │ diam nonumy eirmod tempor ║" + System.lineSeparator();
    expected += "║                           │       │ invidunt ut labore et     ║" + System.lineSeparator();
    expected += "║                           │       │ dolore magna aliquyam     ║" + System.lineSeparator();
    expected += "║                           │       │ erat, sed diam voluptua.  ║" + System.lineSeparator();
    expected += "╟───────────────────────────┼───────┼───────────────────────────╢" + System.lineSeparator();
    expected += "║                         1 │ cdefg │                       3.0 ║" + System.lineSeparator();
    expected += "╟───────────────────────────┼───────┼───────────────────────────╢" + System.lineSeparator();
    expected += "║ Lorem ipsum dolor sit     │       │                           ║" + System.lineSeparator();
    expected += "║ amet, consetetur          │       │                           ║" + System.lineSeparator();
    expected += "║ sadipscing elitr          │       │                           ║" + System.lineSeparator();
    expected += "╚═══════════════════════════╧═══════╧═══════════════════════════╝" + System.lineSeparator();
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testManyHeadlines() {
    // when
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
    // then
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
  public void testManyMinimiseHeight() {
    // when
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeadline("Some test headline");
    aat.addHeadline("And a long explaining text, that may stop here");
    aat.addHeadline("or it will end up here.");
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.minimiseHeight();
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
    String expected = "";
    expected += "╔══════════════════════════════════╗" + System.lineSeparator();
    expected += "║ Some test headline               ║" + System.lineSeparator();
    expected += "║ And a long explaining text, that ║" + System.lineSeparator();
    expected += "║ may stop here                    ║" + System.lineSeparator();
    expected += "║ or it will end up here.          ║" + System.lineSeparator();
    expected += "╟───────────────────┬───────┬──────╢" + System.lineSeparator();
    expected += "║              some │   foo │  bar ║" + System.lineSeparator();
    expected += "╠═══════════════════╪═══════╪══════╣" + System.lineSeparator();
    expected += "║             bello │ pussy │ hans ║" + System.lineSeparator();
    expected += "║                 1 │     2 │  3.0 ║" + System.lineSeparator();
    expected += "║ a very long thing │       │      ║" + System.lineSeparator();
    expected += "╚═══════════════════╧═══════╧══════╝" + System.lineSeparator();
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testNoHeaderColumns() {
    // when
    AsciiArtTable aat = new AsciiArtTable();
    aat.setNoHeaderColumns(3);
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
    String expected = "";
    expected += "╔═══════════════════╤═══════╤══════╗" + System.lineSeparator();
    expected += "║             bello │ pussy │ hans ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║                 1 │     2 │  3.0 ║" + System.lineSeparator();
    expected += "╟───────────────────┼───────┼──────╢" + System.lineSeparator();
    expected += "║ a very long thing │       │      ║" + System.lineSeparator();
    expected += "╚═══════════════════╧═══════╧══════╝" + System.lineSeparator();
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testNoHeaderColumnsButHeadlineWithPaddingAndCustomBorders() {
    // when
    AsciiArtTable aat = new AsciiArtTable(3);
    aat.addHeadline("Some test headline");
    aat.setNoHeaderColumns(3);
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    aat.setBorderCharacters("╭──╮││ ─││⎬⎨│╰─╯│");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
    String expected = "";
    expected += "╭──────────────────────────────────────────────╮" + System.lineSeparator();
    expected += "│   Some test headline                         │" + System.lineSeparator();
    expected += "⎬──────────────────────────────────────────────⎨" + System.lineSeparator();
    expected += "│               bello   │   pussy   │   hans   │" + System.lineSeparator();
    expected += "│                       │           │          │" + System.lineSeparator();
    expected += "│                   1   │       2   │    3.0   │" + System.lineSeparator();
    expected += "│                       │           │          │" + System.lineSeparator();
    expected += "│   a very long thing   │           │          │" + System.lineSeparator();
    expected += "╰──────────────────────────────────────────────╯" + System.lineSeparator();
    assertEquals(expected, aat.getOutput());
  }

  @Test
  public void testOneHeadline() {
    // when
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeadline("Some test headline");
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
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
  public void testOtherBorders() {
    // when
    AsciiArtTable aat = new AsciiArtTable();
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    aat.setBorderCharacters("┏━┯┓┃┠─┬┨┿┣┫│┗┷┛┼");
    // have a visual impression (not part of the test)
    aat.print(System.out);
    // then
    String expected = "";
    expected += "┏━━━━━━━━━━━━━━━━━━━┯━━━━━━━┯━━━━━━┓" + System.lineSeparator();
    expected += "┃              some │   foo │  bar ┃" + System.lineSeparator();
    expected += "┣━━━━━━━━━━━━━━━━━━━┿━━━━━━━┿━━━━━━┫" + System.lineSeparator();
    expected += "┃             bello │ pussy │ hans ┃" + System.lineSeparator();
    expected += "┠───────────────────┼───────┼──────┨" + System.lineSeparator();
    expected += "┃                 1 │     2 │  3.0 ┃" + System.lineSeparator();
    expected += "┠───────────────────┼───────┼──────┨" + System.lineSeparator();
    expected += "┃ a very long thing │       │      ┃" + System.lineSeparator();
    expected += "┗━━━━━━━━━━━━━━━━━━━┷━━━━━━━┷━━━━━━┛" + System.lineSeparator();
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
    // then
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
