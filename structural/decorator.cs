// to extend an object w/o changing its implementation
// implement the same interface as the compo, take the compoe in ctor, then add functionality


public class DecoratorPattern
{
    public static void Main(string[] args)
    {
        var compo = new OGCompo();
        var dec_compo = new DecoratedCompo(compo);

        dec_compo.DoSomething();
    }
}

public class DecoratedCompo : IComponent
{
    private readonly IComponent _component;

    public DecoratedCompo(IComponent component)
    {
        _component = component ?? throw new ArgumentNullException(nameof(component));
    }

    public void DoSomething()
    {
        Console.WriteLine("<Functionality before original functionality>");
        _component.DoSomething();
        Console.WriteLine("<Functionality after original functionality>");
    }
}

public class OGCompo : IComponent
{
    public void DoSomething()
    {
        Console.WriteLine("OG Compo functionality");
    }
}

public interface IComponent
{
    void DoSomething();
}