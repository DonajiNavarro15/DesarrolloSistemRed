namespace AppConHilosTres;

public class Persona
{
    public string Nombre { get; set;}
    public int Edad { get; set; }
    public string Sexo { get; set; }

    int workers, ports;

    public Persona(string nombre, int edad, string sexo){
        this.Nombre = nombre;
        this.Edad = edad;
        this.Sexo = sexo;
    }

    static void TareaDeFondoConParametro(Object? satateInfo)
    {
        if (satateInfo == null)
        {
            return;
        }

        Persona data = (Persona)satateInfo;
        Console.WriteLine($"Hola 2: Hola {data.Nombre} tu edad es {data.Nombre}");
        Thread.Sleep(500);
    }
    static void TareaDeFondo(Object? satateInfo){
        Console.WriteLine($"Hilo 1");
        Thread.Sleep(1500);
    }

    static void Main (string[] args)
    {
        int workers, ports;
        ThreadPool.GetMaxThreads(out workers, out ports);
        Console.WriteLine($"Maximos hilos de trabajo: {workers}");
         Console.WriteLine($"Maximos puertos de trabajo: {ports}");

         ThreadPool.GetMinThreads(out workers, out ports);
        Console.WriteLine($"Mínimos hilos de trabajo: {workers}");
         Console.WriteLine($"Mínimos puertos de trabajo: {ports}");

         ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Disponibles hilos de trabajo: {workers}");
         Console.WriteLine($"Disponibles puertos de trabajo: {ports}");

         int processCount = Environment.ProcessorCount;
        Console.WriteLine($"No. de procesadores disponibles en el sistema: {processCount}");
        Console.WriteLine($"---------------------------------");

        ThreadPool.QueueUserWorkItem(TareaDeFondo);

        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles después del hilo 1: {workers} ");

        Persona p = new Persona("Donaji Navarro", 22, "Mujer");
        ThreadPool.QueueUserWorkItem(TareaDeFondoConParametro, p);
        // Obtener hilos disponibles
        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles después del hilo 2: {workers} ");
        Thread.Sleep(2000);
        // Obtener hilos disponibles
        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles al final: {workers} ");

        Console.ReadKey();
    }
}
