package com.tantch.utilities;


public class MathUtilities {
	/**
	 * calculates the angle between two point and the horizontal line
	 * @param x1 point1.x
	 * @param y1 point1.y
	 * @param x2 point2.x
	 * @param y2 point2.y
	 * @return the angle calculated in degrees
	 */
	public static float GetAngleOfLineBetweenTwoPoints(float x1,float y1,float x2,float y2) {
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;
		double signed_angle = Math.atan2(yDiff,xDiff) - Math.atan2(0,1);
		return (float)  Math.toDegrees(signed_angle);
	}
	/**
	 * calculates distance between 2 points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static float getDis(float x1,float y1,float x2,float y2){
		return (float) Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
	}
}
