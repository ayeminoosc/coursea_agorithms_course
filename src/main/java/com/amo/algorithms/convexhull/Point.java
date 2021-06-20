package com.amo.algorithms.convexhull;

public class Point implements Comparable<Point>{
    public double x, y;
    public int i;

    public Point(){

    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Point o) {
        return Double.compare(this.y, o.y);
    }

    public double slope(Point o){
        return (y - o.y) / (x - o.x);
    }
}
