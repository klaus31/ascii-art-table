# ascii-art-table

a trivial way to print data to a table in ascii over java (without much configuration possibilities)

### trivial case

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
    
### use of another padding:

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
╚═══════════════════════════╧═══════════════╧══════════════╝
```

### add a headline

```java
AsciiArtTable aat = new AsciiArtTable();
aat.addHeadline("Some test headline");
```

```
╔══════════════════════════════════╗
║ Some test headline               ║
╟───────────────────┬───────┬──────╢
║              some │   foo │  bar ║
╠═══════════════════╪═══════╪══════╣
║             bello │ pussy │ hans ║
╟───────────────────┼───────┼──────╢
║                 1 │     2 │  3.0 ║
╟───────────────────┼───────┼──────╢
║ a very long thing │       │      ║
╚═══════════════════╧═══════╧══════╝
```

### add many headlines

```java
AsciiArtTable aat = new AsciiArtTable();
aat.addHeadline("Some test headline");
aat.addHeadline("And a long explaining text, that may stop here");
aat.addHeadline("or it will end up here.");
```

```
╔══════════════════════════════════╗
║ Some test headline               ║
╟──────────────────────────────────╢
║ And a long explaining text, that ║
║ may stop here                    ║
╟──────────────────────────────────╢
║ or it will end up here.          ║
╟───────────────────┬───────┬──────╢
║              some │   foo │  bar ║
╠═══════════════════╪═══════╪══════╣
║             bello │ pussy │ hans ║
╟───────────────────┼───────┼──────╢
║                 1 │     2 │  3.0 ║
╟───────────────────┼───────┼──────╢
║ a very long thing │       │      ║
╚═══════════════════╧═══════╧══════╝
```

### no header columns

```java
AsciiArtTable aat = new AsciiArtTable();
aat.setNoHeaderColumns(3);
```

```
╔═══════════════════╤═══════╤══════╗
║             bello │ pussy │ hans ║
╟───────────────────┼───────┼──────╢
║                 1 │     2 │  3.0 ║
╟───────────────────┼───────┼──────╢
║ a very long thing │       │      ║
╚═══════════════════╧═══════╧══════╝
```

### no header columns but headline with padding

```java
AsciiArtTable aat = new AsciiArtTable(3);
aat.addHeadline("Some test headline");
aat.setNoHeaderColumns(3);
```

```
╔══════════════════════════════════════════════╗
║   Some test headline                         ║
╠═══════════════════════╤═══════════╤══════════╣
║               bello   │   pussy   │   hans   ║
╟───────────────────────┼───────────┼──────────╢
║                   1   │       2   │    3.0   ║
╟───────────────────────┼───────────┼──────────╢
║   a very long thing   │           │          ║
╚═══════════════════════╧═══════════╧══════════╝
```
