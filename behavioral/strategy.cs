// if have multiple ways to do the same thing e.g. navigate (walking/cycling), cake (different cakes), each are "same" but have different processes to achieve it

namespace Strategy;

public class StrategyPattern
{
    public static void Main(string[] args)
    {
        var cake = Cake.BirthdayCake;
        Strategy s;
        switch (cake)
        {
            case Cake.BirthdayCake:
                s = new BirthdayCake();
                break;
            case Cake.CarrotCake:
                s = new CarrotCake();
                break;
            default:
                s = new BirthdayCake();
                break
        }
    }

    foreach (Ingredient i in s.GetIngredients()) {
        Console.WriteLine($"{i.Item}: {i.Quantity}{i.Unit}")
    }
    
    foreach (string m in s.GetMethod())
    {
        Console.WriteLine(m);
    }
}

public interface ICakeStrategy
{
    List<Ingredient> GetIngredients();
    List<string> GetMethod();
}

public class BirthdayCake : ICakeStrategy
{
    public List<Ingredient> GetIngredients()
    {
        var ingredients = new List<Ingredient> {
            new Ingredient(225, "g", "self-raising flour"),
            new Ingredient(2, "tsp", "baking powder"),
            new Ingredient(4, "", "eggs"),
            // etc
        }

        return ingredients;
    }

    public List<string> GetMethod()
    {
        var method = new List<string> {
            "Preheat the oven to 160C/140C"
            // etc
        };

        return method;
    }
}

public class CarrotCake : ICakeStrategy
{
    public List<Ingredient> GetIngredients()
    {
        var ingredients = new List<Ingredient> {
            new Ingredient(100, "g", "natural yoghurt"),
            // etc
        }

        return ingredients;
    }

    public List<string> GetMethod()
    {
        var method = new List<string> {
            "Heat the oven to 180/160C"
            // etc
        }

        return method;
    }
}

public enum Cake
{
    BirthdayCake = 0;
    CarrotCake = 1;
}

public class Ingredient
{
    public decimal Quantity { get; }
    public string Unit { get; }
    public string Item { get; }

    public Ingredient(decimal quantity, string unit, string item)
    {
        Quantity = quantity;
        Unit = unit;
        Item = item;
    }
}