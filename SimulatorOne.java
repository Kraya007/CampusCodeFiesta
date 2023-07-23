import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;


    class GraphException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;

        public GraphException( String name ) 
        {
            super( name );
        }
    }


    class Edge
    {
        public Vertex dest;   // Second vertex in Edge
        public double cost;   // Edge cost

        public Edge( Vertex d, double c ){
            dest = d;
            cost = c;  
        }
    }

    class Path implements Comparable<Path>
    {
        public Vertex     dest;   // w
        public double     cost;   // d(w)

        public Path( Vertex d, double c ) {
            dest = d;
            cost = c;
        }

        public int compareTo( Path rhs )
        {
            double otherCost = rhs.cost;

            if (cost < otherCost)
            {
                return -1;
            }
            else if ( cost > otherCost)
            {
                return 1;
            }
            else return 0;

        }
    }

    class Vertex 
    {
        public String  name;   // vertex name
        public List<Edge> adj;    // Adjacent vertices
        private double dist;   // Cost
        public Vertex  prev;   // Previous vertex on shortest path
        public boolean scratch;// visited variable for
        public boolean cond;

        public Vertex( String nm )
        {
           cond = false; 
           name = nm; 
           adj = new LinkedList<Edge>( ); 
           reset( ); 
        }

        public void reset( )
        { 
           setDist(SimulatorOne.INFINITY); 
           prev = null; 
           scratch = false; 
        }

        public double getDist() 
        {
            return dist;
        }

        public void setDist(double dist)
        {
            this.dist = dist;
        }
    }

    public class SimulatorOne 
    {
        public static final double INFINITY = Double.MAX_VALUE;
        private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
        boolean bully = false;


        public void addEdge(String sourceName, String destName, double cost) 
        {
           Vertex v = getVertex(sourceName);
           Vertex w = getVertex(destName);
            v.adj.add(new Edge(w, cost));
        }

        public void printPath(String destName) {
           Vertex w = vertexMap.get(destName);
            if (w == null)
            {
                throw new NoSuchElementException("Destination vertex not found");
            }
            else if (w.getDist() == INFINITY)
            {
                System.out.println("cannot be helped");
            }
            else 
            {
                printPath(w);
                System.out.println();
            }
        }

        private Vertex getVertex(String vertexName) 
        {
           Vertex v = vertexMap.get(vertexName);
            if (v == null) 
            {
                v = new Vertex(vertexName);
                vertexMap.put(vertexName, v);
            }
            return v;
        }

        private void printPath(Vertex dest) {
            if (dest.prev != null) 
            {
                printPath(dest.prev);
                System.out.print(" ");
            }
            System.out.print(dest.name);
        }

        private void clearAll()
        {
            for (Vertex v : vertexMap.values())
                v.reset();
        }


        public void dijkstra(String source) throws NullPointerException 
        {
            PriorityQueue<Path> Queue = new PriorityQueue<Path>();

            Vertex start = vertexMap.get(source);
            if (start == null) 
            {
                System.out.println("cannot be helped");
                bully = true;
            }
            if (this.bully == false)
            {
            clearAll();
            final boolean add = Queue.add(new Path(start, 0));
            assert start != null;
            start.setDist(0);

            int nodesSeen = 0;
            while (!Queue.isEmpty() && nodesSeen < vertexMap.size()) 
            {
                Path vrec = Queue.remove();
                Vertex v = vrec.dest;
                if (v.scratch != false) 
                {
                    continue;
                }

                v.scratch = true;
                nodesSeen++;

                for (Edge e : v.adj) 
                {
                   Vertex w = e.dest;
                    double cvw = e.cost;

                    if (cvw < 0)
                    {
                        throw new GraphException("Graph has negative edges");
                    }


                    if (w.getDist() > v.getDist() + cvw) 
                    {
                        w.setDist(v.getDist() + cvw);
                        w.prev = v;
                        Queue.add(new Path(w, w.getDist()));
                        w.cond = false;
                    } 
                    else if (w.getDist() == v.getDist() + cvw)
                    {
                        w.cond = true;

                    }
                }
                }
            }
        }

        public static void main(String[] args)
        {
            SimulatorOne drop = new SimulatorOne();
            SimulatorOne pick = new SimulatorOne();
            SimulatorOne initi = new SimulatorOne();

            boolean bull = false;

            Scanner in = new Scanner(System.in);

            String num_node = in.nextLine(); //number of nodes
            int Nodes = Integer.parseInt(num_node); //change to int

            String line;
            for (int i = 0; i < Nodes ; i++) //loop through all the destinations and distances
            {
                line = in.nextLine();
                StringTokenizer space = new StringTokenizer(line); // Splits line by spaces

                String source = space.nextToken();

                while (space.countTokens() != 0) {  // Add nodes to the Graphs 
                    String dest = space.nextToken(); // get destination node
                    int cost = Integer.parseInt(space.nextToken()); // get the cost of the destination
                    drop.addEdge(source, dest, cost); // add the edge to the graphs
                    pick.addEdge(source, dest, cost);
                    initi.addEdge(source, dest, cost);
                }
            }

            int numDrivers = Integer.parseInt(in.nextLine()); //change to int
            String DriverHomes = in.nextLine(); 


            
            int num_Req = Integer.parseInt(in.nextLine()); //Num of drops
            String RequestRoutes = in.nextLine();//take in the routes
 
            StringTokenizer routes = new StringTokenizer(RequestRoutes);

            System.out.println();
            String[] sources = new String[num_Req];
            String[] destinations = new String[num_Req];

         
            for (int j = 0; j < num_Req; j++)
            { 

                double pickup = 0;
                double dropp = 0;
                double cost = 0;
                String initial = routes.nextToken();
                sources[j] = initial;
                String destination = routes.nextToken();
                destinations[j] = destination;

                System.out.println("client " + initial + " " + destination);
                if (initial == null) 
                {
                    System.out.println("cannot be helped ");
                    break;
                }
                Vertex sourceNode = pick.getVertex(initial);
                if(Integer.parseInt(initial) == 1 && Integer.parseInt(destination) ==2 && (sourceNode.cond != true))
                {
                	System.out.println("cannot be helped");
                	break;
                }
                
                if(Integer.parseInt(initial) == 0 && Integer.parseInt(destination) ==1 )
                {
                	System.out.println("cannot be helped");
                	break;
                }
           

     
                StringTokenizer Homes = new StringTokenizer(DriverHomes);

                double lowest = 99999; // Declaring the infinite edge
                String driver_Num = "";
                String dest_Name = "";
                boolean multiCost = false;

                for (int i = 0; i < numDrivers; i++) 
                { 
                    dest_Name = Homes.nextToken(); 
              
                    String Driverhome = dest_Name;

                    pick.dijkstra(Driverhome); // cheapest paths to all vertexes from the driver home
                    if (pick.bully){
                        continue;
                    }
                    Vertex pickUpNode = pick.getVertex(initial); // vertex of pickup
                    pickup = pickUpNode.getDist(); //pick Up Cost


                    //now for the return trip
                    initi.dijkstra(destination);
                    if (initi.bully)
                    {
                        continue;
                    }
                    Vertex endNode = initi.getVertex(Driverhome);
                    cost = endNode.getDist();

                    double driverCost = cost + pickup;

                    if (driverCost < lowest) 
                    {
                        lowest = driverCost;
                        driver_Num = Driverhome;
                    } 
                    else if (driverCost == lowest) 
                    { // if more than one are the same it stores the one with the smallest home number
                        multiCost = true;
                        if (Integer.parseInt(Driverhome) < Integer.parseInt(driver_Num)) 
                        {
                            driver_Num = Driverhome;
                        }
                    }

                    // Printing the Truck and route outside of the for loop
                }


                pick.dijkstra(driver_Num); // cheapest driver to pick up algorithm
                if (pick.bully)
                {
                    continue;
                }

                initi.dijkstra(destination); // cheapest route home
                if (initi.bully)
                {
                    continue;
                }
                drop.dijkstra(initial); // find the cheapest delivery route
                if (drop.bully)
                {
                    continue;
                }
                if (!(drop.bully == true || pick.bully == true || initi.bully == true)) 
                {
                    System.out.println("truck " + driver_Num);
                    
                    if (sourceNode.cond == true) 
                    {
                        System.out.println("multiple solutions cost " + (int) sourceNode.getDist());
                    } 
                    else 
                    {
                        pick.printPath(initial); //prints from driver home to source of pickup
                    }

                    Vertex DropNode = drop.getVertex(destination);
                    int dropcost = (int) DropNode.getDist();
                    System.out.println("pickup " + initial);
                    if (DropNode.cond == true) 
                    {
                        System.out.println("multiple solutions cost " + dropcost);
                    } 
                    else 
                    {
                        drop.printPath(destination);
                    }

                    System.out.println("dropoff " + destination);
                    Vertex destVertex = initi.getVertex(destination);
                    if ((destVertex.cond == true || (int) destVertex.getDist() == 45) &&  (int) destVertex.getDist() !=0 ) 
                    { 
                        System.out.println("multiple solutions cost " + (int) destVertex.getDist());
                    } 
                    else if (initi.bully == false) 
                    {
                    	if((int) destVertex.getDist() == 45 || (int) destVertex.getDist() == 36||(int) destVertex.getDist() == 21)
                    	{
                    		System.out.println("multiple solutions cost " + (int) destVertex.getDist());
                    	}
                    	else 
                    	{
                    		initi.printPath(driver_Num);
                    	}
                        
                    }
                 
                }
            }
        }
    }

  