package aat;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AsciiArtTableVisualTest {

  @Test
  public void thisIsNotATest() {
    AsciiArtTable aat = new AsciiArtTable(5);
    aat.addHeaderCols("some", "foo");
    aat.addHeaderCols("bar");
    aat.add("bello", "pussy", "hans");
    aat.add(1, 2, 3., "a very long thing");
    aat.print(System.out);
    assertTrue("looks good", true);
  }
}
