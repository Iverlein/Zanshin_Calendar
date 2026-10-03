# The text catalogs key every engine term by its enum's simple name
# (glossKey in core/.../texts/Catalog.kt: "Rokuyo.SENBU"). R8 renames classes,
# which turns those keys into "lh1.SENBU" and shows them in place of the text:
# keep the names of the core's enums. Their constants keep their names anyway.
-keepnames enum zanshin.core.**
