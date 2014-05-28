import com.tantch.utilities.MathUtilities;


public class Teste {

	public static void main(String[] args) {
		
		MathUtilities mth= new MathUtilities();
		float ang=mth.GetAngleOfLineBetweenTwoPoints(1, 1, -1, 5);
		System.out.println("angulo :"+ ang);
		

	}

}
