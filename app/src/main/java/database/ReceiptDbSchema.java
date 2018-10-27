package database;

public class ReceiptDbSchema {
    public static final class ReceiptTable {
        public static final String NAME = "receipts";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String SHOP = "shop"; //edit
            public static final String COMMENT = "comment"; //edit2
            public static final String DATE = "date";
        }
    }
}
