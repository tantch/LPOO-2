package com.tantch.utilities;


public class MathUtilities {
	public static float GetAngleOfLineBetweenTwoPoints(float x1,float y1,float x2,float y2) {
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;
		double signed_angle = Math.atan2(yDiff,xDiff) - Math.atan2(0,1);
		return (float)  Math.toDegrees(signed_angle);
	}
	public static float getDis(float x1,float y1,float x2,float y2){
		return (float) Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
	}
}
