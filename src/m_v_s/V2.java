package m_v_s;

public class V2 {
	public double x,y;
	
	public V2(double x, double y){
		this.x =x;
		this.y =y;
	}
	
	public V2 add(V2 v){
		return new V2(this.x+v.x,this.y+v.y);  
	}

	public V2 sub(V2 p) {
		return new V2(this.x-p.x, this.y-p.y);
	}

	public V2 mul(double d) {
		return new V2(this.x*d, this.y*d);
	}

	public V2 unit() {
		double x = Math.pow(this.x, 2);
		double y = Math.pow(this.y, 2);
		double length = Math.sqrt(x+y);
		return new V2(this.x/length, this.y/length);
	}

	public double length() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public double dot(V2 v) {
		return this.x*v.x+this.y*v.y;
	}
}

