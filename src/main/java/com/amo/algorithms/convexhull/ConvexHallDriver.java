package com.amo.algorithms.convexhull;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * run the follow from terminal to test this class
 *  rbox 20 D2 | java -cp build/libs/coursea_agorithms_course-1.0-SNAPSHOT.jar com.amo.algorithms.convexhull.ConvexHallDriver
 */
public class ConvexHallDriver {

    public static void main(String[]args){
        test();
    }

    public static void test(){
        String testData = "0.3215348546593775 0.03629583077160248 \n" +
                "0.02402358131857918 -0.2356728797179394 \n" +
                "0.04590851212470659 -0.4156409924995536 \n" +
                "0.3218384001607433 0.1379850698988746   \n" +
                "0.11506479756447 -0.1059521474930943    \n" +
                "0.2622539999543261 -0.29702873322836    \n" +
                "-0.161920957418085 -0.4055339716426413  \n" +
                "0.1905378631228002 0.3698601009043493   \n" +
                "0.2387090918968516 -0.01629827079949742\n" +
                "0.07495888748668034 -0.1659825110491202\n" +
                "0.3319341836794598 -0.1821814101954749\n" +
                "0.07703635755650362 -0.2499430638271785\n" +
                "0.2069242999022122 -0.2232970760420869\n" +
                "0.04604079532068295 -0.1923573186549892\n" +
                "0.05054295812784038 0.4754929463150845\n" +
                "-0.3900589168910486 0.2797829520700341\n" +
                "0.3120693385713448 -0.0506329867529059\n" +
                "0.01138812723698857 0.4002504701728471\n" +
                "0.009645149586391732 0.1060251100976254\n" +
                "-0.03597933197019559 0.2953639456959105\n" +
                "0.1818290866742182 0.001454397571696298\n" +
                "0.444056063372694 0.2502497166863175\n" +
                "-0.05301752458607545 -0.06553921621808712\n" +
                "0.4823896228171788 -0.4776170002088109\n" +
                "-0.3089226845734964 -0.06356112199235814\n" +
                "-0.271780741188471 0.1810810595574612\n" +
                "0.4293626522918815 0.2980897964891882\n" +
                "-0.004796652127799228 0.382663812844701\n" +
                "0.430695573269106 -0.2995073500084759\n" +
                "0.1799668387323309 -0.2973467472915973\n" +
                "0.4932166845474547 0.4928094162538735\n" +
                "-0.3521487911717489 0.4352656197131292\n" +
                "-0.4907368011686362 0.1865826865533206\n" +
                "-0.1047924716070224 -0.247073392148198\n" +
                "0.4374961861758457 -0.001606279519951237\n" +
                "0.003256207800708899 -0.2729194320486108\n" +
                "0.04310378203457577 0.4452604050238248\n" +
                "0.4916198379282093 -0.345391701297268\n" +
                "0.001675087028811806 0.1531837672490476\n" +
                "-0.4404289572876217 -0.2894855991839297";

        Scanner sc = new Scanner(testData);
        List<Point> allPoints = new ArrayList<>();
        ConvexHullFinder convexHullFinder = new ConvexHullFinder();

        while(sc.hasNextLine()) {
            String temp []= sc.nextLine().split(" ");
            allPoints.add(new Point(Double.valueOf(temp[0]), Double.valueOf(temp[1])));
        }
        System.out.println("All Points Given");
        allPoints.forEach(System.out::println);

        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(new Font("TimesRoman", Font.PLAIN, 15));

        drawPoints(allPoints);


        List<Point> convexPoints = convexHullFinder.findConvexHall(allPoints);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        drawLineForAllPoints(convexPoints);
        drawLine(convexPoints.get(0), convexPoints.get(convexPoints.size()-1));
        convexPoints.forEach(p -> {
            System.out.println("point " + p.i  + " " + p);
        });


    }

    private static  void drawPoints(List<Point> points){
        points.forEach(ConvexHallDriver::drawPoint);
    }

    private static List<Point> convertDrawablePoints(List<Point> points){
        List<Point> tempPoints = new ArrayList<>(points);
        for (Point point : points) {
            tempPoints.add(convertDrawablePoint(point));
        }
        return tempPoints;
    }

    private static Point convertDrawablePoint(Point point){
        return new Point(point.x + 0.5, point.y + 0.5);
    }

    private static void drawPoint(Point p1){
        Point p = convertDrawablePoint(p1);
        StdDraw.point(p.x, p.y);
    }

    private static void drawLine(Point p1, Point p2){
        p1 =  convertDrawablePoint(p1);
        p2 = convertDrawablePoint(p2);
        StdDraw.line(p1.x, p1.y, p2.x, p2.y);
    }

    private static void drawLineForAllPoints(List<Point> points){
        for(int i=0; i<points.size() -1; i++){
            drawLine(points.get(i), points.get(i+1));
        }
    }

    private static void drawLine(Point p1, Point p2, int number){
        Point t = convertDrawablePoint(p2);
        drawLine(p1, p2);
        StdDraw.text(t.x, t.y, String.valueOf(number));
    }

    private static void drawPointNumber(Point p){
        Point t = convertDrawablePoint(p);
        StdDraw.text(t.x, t.y, String.valueOf(p.i));
    }

}
