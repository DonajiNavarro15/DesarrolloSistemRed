namespace AppSinHilos;

class Program
{
    public static void DoHeavyWork()
    {
        Console.WriteLine("DoHeavyWork: I'm lifting a truck ");
        Thread.Sleep(1000);
        Console.WriteLine("DoHeavyWork: I'm tired, I need a three-second nap");
        Thread.Sleep(1000);
        Console.WriteLine("DoHeavyWork: 1...");
        Thread.Sleep(1000);
        Console.WriteLine("DoHeavyWork: 2...");
        Thread.Sleep(1000);
        Console.WriteLine("DoHeavyWork: 3...");
        Thread.Sleep(1000);
        Console.WriteLine("DoHeavyWork: I wake up");

    }
    public static void MyName()
    {
        Console.WriteLine("Donaji Paola Navarro Arrieta: Desarrollo de sistemas en red");
        Thread.Sleep(1000);
    }
    public static void DoSomething()
    {
        Console.WriteLine("DoSomething: Hey! I am doing something here");
        for(int i = 0; i < 20; i++)
            Console.WriteLine($"{i}");
        Console.WriteLine();
        Console.WriteLine("DoSomething: I finished");
    }

    static void Main(string[] args)
    {
        Console.WriteLine("The main thread starts here");
        Program.DoHeavyWork();
        Program.MyName();
        Program.DoSomething();

        Console.WriteLine("The main thread ends here");
        Console.WriteLine("Press any key to finish");
        Console.ReadKey(true);
    }
}
