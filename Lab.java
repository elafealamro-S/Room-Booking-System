package Lab22;

public class Lab extends Room {

   
  String opreatingSys;
    
    public Lab(int RoomNo,int Floor,int Capacity,String Status,String opreatingSys)
    {
    super(RoomNo,Floor,Capacity,Status)	;
    this.opreatingSys=opreatingSys;
    }
    
    public void setOpreatingSys(String opreatingSys){
        this.opreatingSys = opreatingSys;
     }
     
    public String getOpreatingSys(){
        return opreatingSys; 
     }
    
    //=============================================================
    // Q2: Override the method toString
    //Solution:
   
     @Override
    public String toString() {
        return super.toString()+"Lab:" + "opreatingSys=" + opreatingSys + '}';
    }
    
    
    //============================================================
  
}
