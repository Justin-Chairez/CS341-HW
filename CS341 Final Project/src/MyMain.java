
public class MyMain {

	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		canvas.requestFocus();
		
		/*
		Ball ball = new Ball(100,100);
		canvas.addGameObject(ball);
		*/
	
		/*
		for(int row = 1; row <= 15; row++)
		{
			for(int col = 1; col <= 10; col++)
			{
				canvas.addGameObject(new Ball(row*60, col*60));
			}
		}
		*/
		
		Type_A_GameObject objA = new Type_A_GameObject(400,400);
		objA.setVelocity(10);
		canvas.addKeyListener(objA);
		canvas.addGameObject(objA);
		
	
		Type_C_GameObject objC = new Type_C_GameObject(300, 300);
		objC.setVelocity(10);
		canvas.addKeyListener(objC);
		canvas.addGameObject(objC);
		
		Type_D_GameObject objD = new Type_D_GameObject(200,200);
		objD.setVelocity(10);
		canvas.addKeyListener(objD);
		canvas.addGameObject(objD);
	}

}
