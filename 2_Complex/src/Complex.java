/**Name     : Akhilesh G
 * Roll No. : CS10B037
 * Defining Complex Class and it's methods which implements IComplex Interface
 * and uses Comparable interface and we write the method compareTo.
 */

public class Complex implements Comparable<IComplex>,IComplex 
{
	private static final double NAN = 0;
	private double real, imaginary;
	public Complex (double real ,double imaginary)
	{
		this.real = real;
		this.imaginary = imaginary;
	}
	@Override
	public int compareTo(IComplex c) 
	{
		// TODO Auto-generated method stub
		if(this.Abs() == c.Abs())
			return 0;
		else if(this.Abs() > c.Abs())
			return 1;
		else return -1;
		
	}

	@Override
	public double Re() 
	{	
		return real;
	}

	@Override
	public double Im() {
		return imaginary;
	}

	@Override
	public double Abs() 
	{
		return(Math.sqrt(real*real + imaginary*imaginary));
	}

	@Override
	public double Arg() 
	{
		if(real==0 && imaginary>0)
			return Math.PI/2;
		
		else if(real==0 && imaginary <0)
			return -(Math.PI/2);
	
		else if(real>0)
			return Math.atan2(imaginary,real); 
		
		else if(real < 0 && imaginary <0)
			return Math.atan2(imaginary,real) - Math.PI;
		
		else if (real < 0 && imaginary >= 0)
			return Math.atan2(imaginary, real) + Math.PI;
		
		else return NAN;
	}

	@Override
	public IComplex conj() 
	{
		return new Complex(real,-imaginary);
	}

	@Override
	public IComplex log() 
	{
		return new Complex(Math.log(Abs()),Arg());
	}

	@Override
	public IComplex exp() 
	{
		return new PolarComplex(Math.exp(real),imaginary);
	}

	@Override
	public IComplex rotate(double radians) 
	{
		return new PolarComplex(Abs(),Arg()+radians);
	}

	@Override
	public IComplex stretch(double scale) 
	{
		return new PolarComplex(Abs()*scale,Arg());
	}

}
