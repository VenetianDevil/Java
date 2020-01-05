class First
{
	private void priv()
	{
		
	}
	
	@StringParameter ("dwa")
	@MethodToStart(value = 3)
	public void function(String m)
	{
		System.out.println("First: function  " + m);
	}
	
}

class Second
{
	
}

public class main
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Starter ob = new Starter();
		
		ob.accept("First");

	}

}
