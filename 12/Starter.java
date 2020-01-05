import java.util.function.Consumer;
import java.lang.reflect.*;
import java.lang.annotation.*;

public class Starter implements Consumer<String> {

	class arg0{}
	
	@Override	
	public void accept(String arg0)
	{
		boolean mToStart = false;
		boolean mDis = false;
		String arg = null;
		int repeat = 1 ;
		
		try {
			Class<?> object = Class.forName(arg0);
			Method[] ms = object.getMethods();
			for ( Method m : ms)
			{
				mToStart = false;
				mDis = false;
				Class<?> pvec[] = m.getParameterTypes();
				if(pvec.length == 0 || (pvec.length == 1 && pvec[0].getName() == "java.lang.String"))
				{
					Annotation[] ano = m.getDeclaredAnnotations();
					for(Annotation a: ano)
					{
						if(a.annotationType().getName() == "MethodToStart")
						{
							mToStart = true;
							repeat = m.getAnnotation(MethodToStart.class).value();
						}
						else if(a.annotationType().getName() == "MethodDisabled")
							mDis = true;
						else if(a.annotationType().getName() == "StringParameter")
							arg = m.getAnnotation(StringParameter.class).value();
						
					}
					if(mToStart == true && mDis == false)
					{
						for(int i=0; i<repeat; i++)
						{
							try {
								try {
									if(pvec.length == 1)
									m.invoke(object.newInstance(), arg);
									else
									m.invoke(object.newInstance());
								} catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}								
						} 
					}
				}
			}		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}