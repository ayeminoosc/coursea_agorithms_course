package com.amo.algorithms.convexhull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class ConvexHullFinder {

    /**
     * To find convex hull
     * 1. get smallest y point
     * 2. get all counter clockwise point from smallest y point
     * 3. get only counter clockwise point from previous point starting from smallest y point
     *
     * @param points
     * @return
     */
    public List<Point> findConvexHall(List<Point> points) {
        if(points.size()<=3) return points;
        //1. get smallest y point
        Point smallestPoint = findSmallestPoint(points);

        //get all counter clockwise point from smallest y point
        points.remove(smallestPoint);
        points = sortGivenPointsInCounterClockwiseFromGivenPoint(points, smallestPoint);

        Stack<Point> stack = new Stack<>();
        stack.push(smallestPoint);

        for(int i = 0; i < points.size();i ++){
            if(stack.size()<2){
                stack.push(points.get(i));
                continue;
            }
            Point b = stack.pop();
            Point a = stack.peek();
            Point c = points.get(i);
            if(orientation(a, b, c) == 2){
                stack.push(b);
                stack.push(c);
            }
        }
        return stack.stream().toList();
    }

    // To find orientation of ordered triplet (p1, p2, p3).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    int orientation(Point p1, Point p2, Point p3) {
        // See 10th slides from following link for derivation
        // of the formula
        double val = ((p2.y - p1.y) * (p3.x - p2.x)) - ((p2.x - p1.x) * (p3.y - p2.y));
        if (val == 0) return 0;  // colinear
        return (val > 0)? 1: 2; // clock or counter clock wise
    }
    public List<Point> sortGivenPointsInCounterClockwiseFromGivenPoint(List<Point> points, Point smallest) {
        Collections.sort(points, new SlopeComparator(smallest));
//        Collections.reverse(points);
        return points;
    }

    public Point findSmallestPoint(List<Point> points) {
        return points.stream().min(Comparator.naturalOrder()).get();
    }
}

class SlopeComparator implements Comparator<Point> {
    private Point originPoint;

    public SlopeComparator(Point originPoint) {
        this.originPoint = originPoint;
    }

    @Override
    public int compare(Point p1, Point p2) {
        double slope1 = (p1.y - originPoint.y) / (p1.x - originPoint.x);
        double slope2 = (p2.y - originPoint.y) / (p2.x - originPoint.x);
        if(slope1 >= 0 && slope2 < 0) return -1;
        if(slope2 >= 0 && slope1 < 0) return 1;

        return Double.compare(slope1, slope2);
    }
}
