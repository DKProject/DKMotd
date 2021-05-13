package net.pretronic.dkmotd.api;

public enum Order {

    SORTED,
    RANDOM;

    public static Order parse(String name) {
        for (Order value : values()) {
            if(value.toString().equalsIgnoreCase(name)) return value;
        }
        throw new IllegalArgumentException("Can't match order for " + name);
    }
}
