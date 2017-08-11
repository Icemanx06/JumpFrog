package shell;

import java.util.LinkedList;
import shell.Shell;


public class Jumpfrog extends Shell{

    boolean vanLepesVarangy = false;
    boolean vanLepesLeveli = false;
    boolean vanUgrasVarangy = false;
    boolean vanUgrasLeveli = false;
    
    boolean fut = false;
    int N = 0; 
    int szam = 0;
    WaterLilyStatus[] frog = null;
    
    public void setN(int beka)
    {
        this.N = beka;
        frog = new WaterLilyStatus[2*beka+1];
    }
    
    public boolean Lose()
    {
        for (int i = frog.length-2; i > -1; i--)
        {
            if(frog[i] == WaterLilyStatus.TOAD && frog[i+1] == WaterLilyStatus.FREE)
            {
                vanLepesVarangy = true;
                break;
            }else
            {
              vanLepesVarangy = false;  
            }
            
        }
        for (int i = frog.length-3; i > -1; i--)
        {
            if(frog[i] == WaterLilyStatus.TOAD && frog[i+1] == WaterLilyStatus.TREE_FROG && frog[i+2] == WaterLilyStatus.FREE)
            {
                vanUgrasVarangy = true;
                break;
            }else
            {
              vanUgrasVarangy = false;  
            }
            
        }
        for (int i = frog.length-2; i > -1; i--)
        {
            if(frog[i] == WaterLilyStatus.TOAD && frog[i+1] == WaterLilyStatus.FREE)
            {
                vanLepesVarangy = true;
                break;
            }else
            {
              vanLepesVarangy = false;  
            }
            
        }
        for (int i = 1; i < frog.length; i++)
        {
            if(frog[i] == WaterLilyStatus.TREE_FROG && frog[i-1] == WaterLilyStatus.FREE)
            {
                vanLepesLeveli = true;
                break;
            }else
            {
              vanLepesLeveli = false;  
            }
            
        }
          for (int i = 2; i < frog.length; i++)
        {
            if(frog[i] == WaterLilyStatus.TREE_FROG && frog[i-1] == WaterLilyStatus.TOAD && frog[i-2] == WaterLilyStatus.FREE)
            {
                vanUgrasLeveli = true;
                break;
            }else
            {
              vanUgrasLeveli = false;  
            }
            
        }
          if(vanLepesVarangy == false && vanUgrasVarangy == false && vanLepesLeveli == false && vanUgrasLeveli == false)
          {
              return true;
          }else
          {
              return false;
          }
    }
    
    public boolean Winner()
    {
        int db = 0;
        boolean win = false;
        for (int i = 0; i < N; i++) {
            if(frog[i] == WaterLilyStatus.TREE_FROG && frog[N+i+1] == WaterLilyStatus.TOAD)
            {
                db++;
            }else
            {
                db = 0;
            }
        }
        if(db == N)
        {
            win = true;
        }else
        {
            win = false;
        }
        return win;
    }
    
    public void palya()
    {
        for (int i = 0; i < N; i++) {
            frog[i] = WaterLilyStatus.TOAD;
        }
        frog[N] = WaterLilyStatus.FREE;
        for (int j = N+1; j < ((2*N)+1); j++) {
            frog[j] = WaterLilyStatus.TREE_FROG;
        }
    }

    @Override
    protected void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        setN(3);
        palya();
    }
    
   public void kirajzol()
   {
       for (int i = 0; i < 2*N+1; i++) {
           if(frog[i] == WaterLilyStatus.TREE_FROG)
           {
               System.out.print(" _<-L-_ ");
           }
           else if(frog[i] == WaterLilyStatus.TOAD)
           {
               System.out.print(" _-V->_ ");
           }
           else
           {
              System.out.print(" ______ "); 
           }
           if(i == frog.length-1)
           {
           System.out.println("");
           }
       }
   }
   
   public Jumpfrog()
   {
       init();
       
       addCommand(new Command("new") {
           @Override
           public boolean execute(String... args) {
              if(args.length == 0)
              {
                  fut = true;
                  setN(3);
                  palya();
                  return true;
              }else if(args.length == 1)
              {
                  for (int i = 0; i < args[0].length(); i++) {
                      if(args[0].charAt(i) > 47 && args[0].charAt(i) < 58){
                          szam++;
                      }else
                      {
                          szam = 0;
                      }
                  }
                  if(szam == args[0].length())
                  {
                      if(Integer.parseInt(args[0]) > 0)
                      {
                          fut=true;
                          setN(Integer.parseInt(args[0]));
                          palya();
                          szam = 0;
                          return true;
                      }else
                      {
                          System.err.println("Csak egy pozitív egész számot lehet megadni, ami nagyobb mint 0!");
//                          System.out.println(args.length);
                          szam = 0;
                      }
                  }else
                  {
                      System.err.println("Csak egy pozitív egész számot lehet megadni, ami nagyobb mint 0!!");
//                      System.out.println(args.length);
//                      System.out.println(args[0].length());
                  }
              }else if(args.length > 1)
              {
                  System.err.println("Csak egy (N) pozitív egész számot lehet megadni, ami nagyobb mint 0;");
              }
              return false;                               
            }
       });
       
       addCommand(new Command("exit") {
           @Override
           public boolean execute(String... args) {
               exit(0);
               return true;
           }
       });
       
       addCommand(new Command("print") {
           @Override
           public boolean execute(String... args) {
               if(args.length == 0)
               {
                   if(fut)
                   {
                       if(Winner())
                       {
                           System.out.println("Nyertél!!!");
                           kirajzol();
                           fut = false;
                           return true;
                       }else if(Lose())
                       {
                           System.out.println("Vesztettél!!!");
                           kirajzol();
                           fut =false;
                           return true;
                       }else
                       {
                           kirajzol();
                           return true;
                       }
                   }else
                   {
                       System.err.println("Nincs futó játék!");
                   }
               }else
               {
                   System.err.println("Nem kell paraméter!");
               }
               return false;
           }
       });
       
       addCommand(new Command("X") {
           @Override
           public boolean execute(String... args) {
               if(fut)
               {
                   if(args.length > 1)
                   {
                       System.err.println("Csak a (walk) vagy (jump) szavakkal lehet lépkedni!");
                   }else if(args.length == 0)
                   {
                       System.err.println("Meg kell adnod a walk vagy jump szót paraméterkénk a mozgáshoz!");
                   }else if(args[0].equals("walk"))
                   {
                       for (int i = frog.length-2; i > -1; i--) {
                           if(frog[i] == WaterLilyStatus.TOAD && frog[i+1] == WaterLilyStatus.FREE)
                           {
                               frog[i] = WaterLilyStatus.FREE;
                               frog[i+1] = WaterLilyStatus.TOAD;
                               vanLepesVarangy = true;
                               break;
                           }else
                           {
                               vanLepesVarangy = false;
                           }
                       }
                       if(vanLepesVarangy){
                           return vanLepesVarangy;
                       }else
                       {
                           System.err.println("A varangyosbékával nem tudsz lépni!");
                           return vanLepesVarangy;
                       }
                   }else if(args[0].equals("jump"))
                   {
                       for (int i = frog.length-3; i > -1; i--) {
                           if(frog[i] == WaterLilyStatus.TOAD && frog[i+1] == WaterLilyStatus.TREE_FROG && frog[i+2] == WaterLilyStatus.TREE_FROG)
                           {
                             frog[i] = WaterLilyStatus.FREE;
                             frog[i+2] = WaterLilyStatus.TOAD;
                             vanUgrasVarangy = true;
                             break;
                           }else
                           {
                               vanUgrasVarangy = false;
                           }
                       }
                       if(vanUgrasVarangy)
                       {
                           return vanUgrasVarangy;
                       }else
                       {
                           System.err.println("A varangyosbékával nem tudsz ugrani!");
                           return vanUgrasVarangy;
                       }
                           
                       }
                    }else
                    {
                        System.err.println("Nincs futó játék!");
                    }
               return false;
           }
       });
       
              addCommand(new Command("O") {
           @Override
           public boolean execute(String... args) {
               if(fut)
               {
                   if(args.length > 1)
                   {
                       System.err.println("Csak a 'walk' vagy 'jump' szavakkal lehet lépkedni!");
                   }else if(args.length == 0)
                   {
                       System.err.println("Meg kell adnod a 'walk' vagy 'jump' szót paraméterkénk a mozgáshoz!");
                   }else if(args[0].equals("walk"))
                   {
                       for (int i = 1; i < frog.length; i++) {
                           if(frog[i] == WaterLilyStatus.TREE_FROG && frog[i-1] == WaterLilyStatus.FREE)
                           {
                               frog[i] = WaterLilyStatus.FREE;
                               frog[i-1] = WaterLilyStatus.TREE_FROG;
                               vanLepesLeveli = true;
                               break;
                           }else
                           {
                               vanLepesLeveli = false;
                           }
                       }
                       if(vanLepesLeveli){
                           return vanLepesLeveli;
                       }else
                       {
                           System.err.println("A levelibékával nem tudsz lépni!");
                           return vanLepesLeveli;
                       }
                   }else if(args[0].equals("jump"))
                   {
                       for (int i = 2; i < frog.length; i++) {
                           if(frog[i] == WaterLilyStatus.TREE_FROG && frog[i-1] == WaterLilyStatus.TOAD && frog[i-2] == WaterLilyStatus.FREE)
                           {
                             frog[i] = WaterLilyStatus.FREE;
                             frog[i-2] = WaterLilyStatus.TREE_FROG;
                             vanUgrasLeveli = true;
                             break;
                           }else
                           {
                               vanUgrasLeveli = false;
                           }
                       }
                       if(vanUgrasLeveli)
                       {
                           return vanUgrasLeveli;
                       }else
                       {
                           System.err.println("A levelibékával nem tudsz ugrani!");
                           return vanUgrasLeveli;
                       }
                           
                       }
                    }else
                    {
                        System.err.println("Nincs futó játék!");
                    }
               return false;
           }
       });
   }
 
    
}
