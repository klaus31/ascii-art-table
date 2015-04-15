# ascii-art-table

a trivial way to print data to a table in ascii over java (without much configuration possibilities)

this ...

```java
AsciiArtTable aat = new AsciiArtTable();
aat.addHeaderCols("some", "foo");
aat.addHeaderCols("bar");
aat.add("bello", "pussy", "hans");
aat.add(1, 2, 3., "a very long thing");
aat.print(System.out);
```

... will print out that:

```
╔═══════════════════╤═══════╤══════╗
║              some │   foo │  bar ║
╠═══════════════════╪═══════╪══════╣
║             bello │ pussy │ hans ║
╟───────────────────┼───────┼──────╢
║                 1 │     2 │  3.0 ║
╟───────────────────┼───────┼──────╢
║ a very long thing │       │      ║
╚═══════════════════╧═══════╧══════╝
```
    
use of another padding:

```java
AsciiArtTable aat = new AsciiArtTable(5);
```

```
╔═══════════════════════════╤═══════════════╤══════════════╗
║                  some     │       foo     │      bar     ║
╠═══════════════════════════╪═══════════════╪══════════════╣
║                 bello     │     pussy     │     hans     ║
╟───────────────────────────┼───────────────┼──────────────╢
║                     1     │         2     │      3.0     ║
╟───────────────────────────┼───────────────┼──────────────╢
║     a very long thing     │               │              ║
╚═══════════════════════════╧═══════════════╧══════════════╝```