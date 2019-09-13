public class Ball 
{
	private int Xpos, Ypos, deltaX, deltaY;
	private int angle, speed;
	
	public Ball()
	{
		
	}
	
	public Ball(int X, int Y, int angle, int speed)
	{
		Xpos = X;
		Ypos = Y;
		this.angle = angle;
		this.speed = speed;
		
//		if (deltaX > 0 && deltaY > 0){
//			angle = (int) (Math.toDegrees(Math.atan2(deltaY, deltaX)));
//		} else if (deltaX < 0 && deltaY > 0){
//			angle = (int) (Math.toDegrees(Math.atan2(deltaY, deltaX)));
//		} else if (deltaX < 0 && deltaY < 0){
//			angle = (int) (Math.toDegrees(2*Math.PI + Math.atan2(deltaY, deltaX)));
//		} else if (deltaX > 0 && deltaY < 0){
//			angle = (int) (Math.toDegrees(2*Math.PI + Math.atan2(deltaY, deltaX)));
//		}
	}

	//-----------Get/Set-----------//
	public int getXpos() {	return Xpos; }
	
	public int getYpos() {  return Ypos; }
	
	public int getDeltaX() {  return deltaX; }
	
	public int getDeltaY() {  return deltaY; }
	
	public int getAngle() {  return angle; }
	
	public void update()
	{
		Xpos = (int) (Xpos + ((int) ((double) speed) * Math.cos(Math.toRadians((double) (angle)))));
		Ypos = (int) (Ypos +  ((int) ((double) speed) * Math.sin(Math.toRadians((double) (angle)))));
	}
	
	public int getR()
	{
		return (int) Math.sqrt(Math.pow((Xpos - 350), 2) + Math.pow((Ypos - 350), 2));
	}
	
	public void bounce()
	{
		angle = (angle + 180) % 360 + ((int) (Math.random() * 60) - 30);
	}
	
	public void set259()
	{
		Xpos = (int) (((double) 259) * Math.cos(Math.toRadians((double) (angle)))) + 350;
		Ypos = (int) (((double) 259) * Math.sin(Math.toRadians((double) (angle)))) + 350;
	}
	
	public void setXpos(int xpos) { Xpos = xpos; }

	public void setYpos(int ypos) { Ypos = ypos; }

	public void setDeltaX(int deltaX) {  this.deltaX = deltaX; }

	public void setDeltaY(int deltaY) {	this.deltaY = deltaY; }
	//----------------------------------------//
	

}