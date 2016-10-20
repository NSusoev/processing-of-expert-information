package app.model.ranking.group;

public enum Relation {
    BIGGER {
        @Override
        public String toString() {
            return ">";
        }
    },
    EQUAL {
        @Override
        public String toString() {
            return "~";
        }
    }
}
