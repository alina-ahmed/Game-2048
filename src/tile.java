
import java.awt.Color;

public class tile {
	public int value;
	Color color;
	tile()
	{
		this.value=0;
	}
	public tile(int a)
	{
		this.value=a;
		
	}
	
	public int getValue()
	{
		return this.value;
	}
	Color setColor()
	{
		if (this.value==0)
			return color= new Color(0xCDC1B4);
		
		else if (value==2)
			return color=new Color(0xFFE4C3);
		else if (value==4)
			return color=new Color(0xfff4d3);
		else if (value==8)
			return color= new Color(0xffdac3);
		else if (value==16)
			return color= new Color(0xe7b08e);
		else  if (value==32)
			return 	color=  new Color(0xe7bf8e);
		else  if (value==64)
			return    	color=     new Color(0xffc4c3);
		else    if (value==128)
			return    	color= new Color(0xE7948e);
		else   if (value==256)
			return    	color=  new Color(0xbe7e56);
		else    if (value==512)
			return    	color=   new Color(0xbe5e56);
		else    if (value==1024)
       		return  color=  new Color(0x9c3931);
		else if (value==2048)
			return  color=  new Color(0x701710);
		else
			return color= new Color(0xffdac3);
	}
	
	
}

