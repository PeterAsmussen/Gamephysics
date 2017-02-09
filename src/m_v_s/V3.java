package m_v_s;

public class V3 {

	public double x, y, z;
	
	public V3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public V3 add(V3 v){
		return new V3(this.x+v.x, this.y+v.y, this.z+v.z);
	}
	
	public V3 sub(V3 p){
		return new V3(this.x-p.x, this.y-p.y, this.z-p.z);
	}

	public V3 mul(double d){
		return new V3(this.x*d, this.y*d, this.z*d);
	}
	
	public V3 unit(){
		double x = Math.pow(this.x, 2);
		double y = Math.pow(this.y, 2);
		double z = Math.pow(this.z, 2);
		double length = Math.sqrt(x+y+z);
		return new V3(this.x/length, this.y/length, this.z/length);
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double z() {
		return z;
	}

	public double dot(V3 d) {
		return x*d.x()+y*d.y()+z*d.z();
	}

	public V3 cross(V3 k) {
		return new V3(	this.y*k.z-this.z*k.y, 
								this.z*k.x-this.x*k.z,
								this.x*k.y-this.y*k.x
								);
	}
}
