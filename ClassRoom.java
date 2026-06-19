package Lab22;

public class ClassRoom extends Room {
 String projector;
    
    public ClassRoom(int RoomNo,int Floor,int Capacity,String Status,String projector)
    {
    super(RoomNo,Floor,Capacity,Status)	;
    this.projector=projector;
    }
    
    public void setProjector(String projector){
        this.projector = projector;
     }
     
    public String getProjector(){
        return projector; 
     }
    
 @Override
    public String toString(){
        return super.toString()+ " Projector : "+this.projector;
    }
    
   
}
