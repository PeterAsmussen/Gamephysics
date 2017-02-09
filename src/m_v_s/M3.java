package m_v_s;

public class M3 {
	
	public double a,b,c,d,e,f,g,h,i;

	

	public M3(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
	}

	public M3 mul(M3 sz) {
		return new M3(	a*sz.a+b*sz.d+c*sz.g, d*sz.a+e*sz.d+f*sz.g, g*sz.a+h*sz.d+i*sz.g, 
								a*sz.b+b*sz.e+c*sz.h, d*sz.b+e*sz.e+f*sz.h, g*sz.b+h*sz.e+i*sz.h, 
								a*sz.c+b*sz.f+c*sz.i, d*sz.c+e*sz.f+f*sz.h, g*sz.c+h*sz.f+i*sz.i
					 );
	}	

	public M3 add(M3 m) {
		return new M3(	a+m.a, b+m.b, c+m.c,
								d+m.d, e+m.e, f+m.f,
								g+m.g, h+m.h, i+m.i
					 );
	}
	
	public V3 add(V3 v) {
		return new V3(a+v.x, b+v.y, c+v.z);
	}

	public M3 mul(double sin) {
		
		
		return new M3(	a*sin, b*sin, c*sin,
								d*sin, e*sin, f*sin,
								g*sin, h*sin, i*sin
					 );
				
	}

	public V3 mul(V3 s) {
		return new V3(	a*s.x()+b*s.y()+c*s.z(),
								d*s.x()+e*s.y()+f*s.z(),
								g*s.x()+h*s.y()+i*s.z()
					 );
	}
}
