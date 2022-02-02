import java.util.*;

 class wait{
        public int tid;
        public int st;
        public int ed;
    
        wait(int td,int sta,int end){
            this.tid=td;
            this.st=sta;
            this.ed=end;
        }
    }


public class Railway {

    
    public static Scanner sc=new Scanner(System.in);
    public static List<List<Integer>>ta=new ArrayList<>();
    static int r,c,st,ed;

    public String uname,upass;
    
    Railway(String name, String pass) {
        this.uname= name;
        this.upass = pass;
        
    }
    static List<Railway> ulist = new ArrayList<>();
    static List<wait> wat = new ArrayList<>();
    static String[] bh=new String[50];

    public static void admin(){
        while(true){
            System.out.println("welcome to admin");
            System.out.println("1.Allocation");
            System.out.println("2.view Available Seats");
            System.out.println("3.view waiting list");
            System.out.println("4.Exit");
            int q=sc.nextInt();
            if(q==1){
                System.out.println("Enter number of seats ");
                r=sc.nextInt();
                System.out.println("Enter number of stations ");
                c=sc.nextInt();
                for(int i=0;i<r;i++){
                    List<Integer>l1=new ArrayList<>();
                    for(int j=0;j<c;j++){
                        l1.add(0);
                    }
                    ta.add(l1);
                }
                System.out.println("created successfully");
                System.out.println("press enter");
                sc.nextLine();
                sc.nextLine();
            }
            else if(q==2){
                 System.out.println("Seat pattern");
                 for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                       System.out.print( ta.get(i).get(j)+"  ");
                    }System.out.println();
                }
                sc.nextLine();
                sc.nextLine();
            }
            else if(q==3){
                System.out.println("Passengers in waiting List");
                for(int i=0;i<wat.size();i++){
                    System.out.println("pid: "+(wat.get(i).tid)+" "+"st point: "+(wat.get(i).st+" "+"End point: "+(wat.get(i).ed)));
                }
            }
            else{
                break;
            }
        }
    
    }

    public static void tcancel(){
        for(int k=0;k<wat.size();k++){
            for(int i=0;i<ta.size();i++){
                int c=0;
                for(int j=wat.get(k).st;j<=wat.get(k).ed;j++){
                    c+=ta.get(i).get(j);
                }
                int d=wat.get(k).tid;
                if(c==0){
                    alloc(i,st,ed,((wat.get(k).tid)));
                    //System.out.println("Ticket Bookes succesfully and Sno: "+(i+1)+" Ticket id: "+(cuser+1));
                    bh[d]="SeatNo: "+Integer.toString(i)+" Ticket id: "+Integer.toString(d)+" Conformed";
                    wat.remove(k);
                    break;
                }
            }
        }
    }

    public static void alloc(int i,int st,int ed,int cuser){
        for(int j=st;j<=ed;j++){
            ta.get(i).set(j, cuser);
        }
    }

    public static void upanel(int cuser){
        while(true){
            System.out.println("Welcome "+ulist.get(cuser).uname);
            System.out.println("1.Book ticket");
            System.out.println("2.Cancel Ticket");
            System.out.println("3.Booking History");
            System.out.println("4.Exit");
            int ch=sc.nextInt();
            if(ch==1){
                System.out.println("Enter start station number");
                st=sc.nextInt();
                st-=1;
                System.out.println("Enter destination station number");
                ed=sc.nextInt();
                ed-=1;
                int fl=0;
                int i;
                for( i=0;i<ta.size();i++){
                    int c=0;
                    for(int j=st;j<=ed;j++){
                        c+=ta.get(i).get(j);
                    }
                    if(c==0){
                        alloc(i,st,ed,cuser+1);
                        fl=1;
                        System.out.println("Ticket Bookes succesfully and Sno: "+(i+1)+" Ticket id: "+(cuser+1));
                        bh[cuser+1]="SeatNo: "+Integer.toString(i+1)+" Ticket id: "+Integer.toString(cuser+1);
                        break;
                    }
                }
                if(fl==0){
                    System.out.println("No seats available now! You are added to waiting list");
                    bh[cuser+1]="SeatNo: 0"+" Ticket id: "+Integer.toString(cuser+1);
                    bh[cuser+1]=bh[cuser+1]+" -->Waiting List";
                    wat.add(new wait((cuser+1), st, ed));

                }
            }
            else if(ch==2){
                System.out.println("Enter the ticket id to cancel");
                int a=sc.nextInt();
                for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                        if (a==ta.get(i).get(j)){
                            ta.get(i).set(j, 0);
                        }
                    }
                }
                bh[cuser+1]=bh[cuser+1]+" -->Cancelled";
                System.out.println("Ticket cancelled Succesfully");
                tcancel();

            }
            else if(ch==3){
                System.out.println("Your booking History");
                System.out.println(bh[cuser+1]);
                
            }
            else{
                break;
            }

        }
        
    }

        
    
    public static void user(){
        while(true){
        System.out.println("User panel");
        System.out.println("1.New user");
        System.out.println("2.Existing user");
        System.out.println("3.Exit");
        System.out.println("ENter your choice");
        int q=sc.nextInt();
        if(q==1){
            System.out.println("Enter your name:");
            String name=sc.next();
            System.out.println("Enter your pass");
            String pass=sc.next();
            ulist.add(new Railway(name, pass));
            System.out.println("Registered Successfully");
            System.out.println("Press enter to continue");
            sc.nextLine();
            sc.nextLine();
        }
        else if(q==2){
            System.out.println("Enter your name:");
            String name=sc.next();
            System.out.println("Enter your pass");
            String pass=sc.next();
            int f=0;
            for(int i=0;i<ulist.size();i++){
                    if(name.equals(ulist.get(i).uname) && pass.equals(ulist.get(i).upass)){
                        upanel(i);
                        f=1;
                        break;
                        
                    }
            }
            if(f==0){
                System.out.println("Enter correct details");
            }
        }
        else{
            break;
        }
        }
        
    }
    public static void main(String[] args){
        while(true){
        System.out.println("Welcome to Railway reservation");
        System.out.println("1.Admin");
        System.out.println("2.User");
        System.out.println("3.Exit");
        System.out.println("Enter your choice");
        int a=sc.nextInt();
       
            if(a==1){
                System.out.println("Enter your name");
                String na=sc.next();
                System.out.println("Enter your password");
                String pa=sc.next();
                if(na.equals("Admin") && pa.equals("1234")){
                    admin();
                }
                else{
                    System.out.println("Enter crt details");
                }
                
            }
            else if(a==2){
                user();
            }
            else{
                break;
            }
        }
    }
}
