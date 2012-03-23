/**Name     : Akhilesh G
 * Roll No. : CS10B037
 * Defines PolarComplex Class and it's methods which implements IComplex Interface
 * and uses Comparable interface and we write the method compareTo.
 */
public class PolarComplex implements Comparable<IComplex>, IComplex 
{

	double r,theeta;
	public PolarComplex(double r, double theeta) 
	{
		// TODO Auto-generated constructor stub
		this.r=r;
		this.theeta = theeta;
	}

	@Override
	public double Re() 
	{
		return r*Math.cos(theeta);
	}

	@Override
	public double Im() 
	{
		return r*Math.sin(theeta);
	}

	@Override
	public double Abs() 
	{
		return r;
	}

	@Override
	public double Arg() 
	{
		return theeta;
	}

	@Override
	public IComplex conj() 
	{
		return new PolarComplex(r,-theeta);
	}

	@Override
	public IComplex log() 
	{
		return new Complex(Math.log(r),theeta);
	}

	@Override
	public IComplex exp() 
	{
		return new PolarComplex(Math.exp(r*(Math.cos(theeta))),r*(Math.sin(theeta)));
	}

	@Override
	public IComplex rotate(double radians) 
	{
		return new PolarComplex(r,theeta+radians);
	}

	@Override
	public IComplex stretch(double scale) 
	{
		return new PolarComplex(r*scale, theeta);
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

}
