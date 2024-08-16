// to extend an object w/o changing its implementation
// implement the same interface as the compo, take the compo in ctor, then add functionality
// the Decorator itself is also a compo so we can further decorate it, e.g. new MushroomPizza(new ExtraCheesePizza(new FarmhousePizza()))
// avoids class explosion: making many classes for combinations of features (lots of combinations can lead to too many classes e.g. e.g. Mushroom, Cheese, Muchroom + Cheese, etc)

namespace Decorator;

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