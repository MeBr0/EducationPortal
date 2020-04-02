package com.mebr0.study.time;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Immutable class for semesters
 *
 * @author A.Yergali
 * @version 1.0
 */
public final class Semester {

    private final int YEAR;
    private final Type TYPE;

    private Semester(int year, Type type) {
        this.YEAR = year;
        this.TYPE = Objects.requireNonNull(type, "Type cannot be null");
    }

    public static Semester from(int year, Type type) {
        return new Semester(year, type);
    }

    public static Semester getCurrent() {
        int year = LocalDate.now().getYear();

        return new Semester(year, Type.getCurrent());
    }

    public int getYear() {
        return YEAR;
    }

    public Type getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + YEAR + " " + TYPE.getTitle() + "]";
    }

    /**
     * Types of semesters with date bounds for each
     */
    enum Type {

        FALL("Fall", 8, 31, 12, 31),
        WINTER("Winter", 1, 1, 1, 20),
        SPRING("Spring", 1, 21, 5, 20),
        SUMMER("Summer", 5, 21, 8, 30);

        private final String TITLE;
        private final int START_MONTH;
        private final int START_DAY;
        private final int END_MONTH;
        private final int END_DAY;

        Type(String title, int startMonth, int startDay, int endMonth, int endDay) {
            this.TITLE = title;
            this.START_MONTH = startMonth;
            this.START_DAY = startDay;
            this.END_MONTH = endMonth;
            this.END_DAY = endDay;
        }

        public String getTitle() {
            return TITLE;
        }

        private LocalDate start() {
            int year = LocalDate.now().getYear();

            return LocalDate.of(year, START_MONTH, START_DAY);
        }

        public LocalDate end() {
            int year = LocalDate.now().getYear();

            return LocalDate.of(year, END_MONTH, END_DAY);
        }

        public static Type getCurrent() {
            LocalDate now = LocalDate.now();

            for (Type type: values()) {
                if (now.compareTo(type.start()) >= 0 && now.compareTo(type.end()) <= 0) {
                    return type;
                }
            }

            return null;
        }

        @Override
        public String toString() {
            return TITLE;
        }
    }
}
