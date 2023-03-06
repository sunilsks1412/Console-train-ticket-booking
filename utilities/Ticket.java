package utilities;
public class Ticket 
{
    public String id;
    public int from;
    public int to;
    String type = "";
    String train_id;
    String name;
    public Ticket(String id,int from,int to,String train_id,String type,String name)
    {
        this.id = id;
        this.from = from;
        this.to = to;
        this.train_id = train_id;
        this.type = type;
        this.name = name;
    }
}
