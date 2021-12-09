
public abstract class TypeBAdapter extends GameObject
{
	GameObject gObj;
	
	public TypeBAdapter(GameObject gObj) 
	{
		super(gObj.getX(), gObj.getY());
	}
	
	public abstract void move(Canvas c);
}