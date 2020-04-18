package com.mebr0.study.time;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Immutable class for semesters
 *
 * @author A.Yergali
 * @version 1.0
 */
public final class Semester implements Serializable {

    private final int year;
    private final Type type;

    private Semester(int year, Type type) {
        this.year = year;
        this.type = Objects.requireNonNull(type, "Type cannot be null");
    }

    public static Semester from(int year, Type type) {
        return new Semester(year, type);
    }

    public static Semester getCurrent() {
        int year = LocalDate.now().getYear();

        return new Semester(year, Type.getCurrent());
    }

    public int getYear() {
        return year;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Semester))
            return false;

        Semester semester = (Semester) o;

        return year == semester.year &&
                type == semester.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, type);
    }

    @Override
    public String toString() {
        return "[" + year + " " + type.getTitle() + "]";
    }

    /**
     * Types of semesters with date bounds for each
     */
    public enum Type {

        FALL("Fall", 8, 31, 12, 31),
        WINTER("Winter", 1, 1, 1, 20),
        SPRING("Spring", 1, 21, 5, 20),
        SUMMER("Summer", 5, 21, 8, 30);

        private final String title;
        private final int startMonth;
        private final int startDay;
        private final int endMonth;
        private final int endDay;

        Type(String title, int startMonth, int startDay, int endMonth, int endDay) {
            this.title = title;
            this.startMonth = startMonth;
            this.startDay = startDay;
            this.endMonth = endMonth;
            this.endDay = endDay;
        }

        public String getTitle() {
            return title;
        }

        private LocalDate start() {
            int year = LocalDate.now().getYear();

            return LocalDate.of(year, startMonth, startDay);
        }

        public LocalDate end() {
            int year = LocalDate.now().getYear();

            return LocalDate.of(year, endMonth, endDay);
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
            return title;
        }
    }
}
