package com.lzumetal.immutableobject.sample01;

/**
 * @author liaosi
 * @date 2021-08-21
 */
public final class Location {

    private final double x;

    private final double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
