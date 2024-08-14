// when u want to notify if something happened

namespace Observer;

public class ObserverPattern
{
    public static void Main(string[] args)
    {
        var pub = new Publisher();

        var sub1 = new Subscriber("Sub-A");
        pub.Subscribe(sub1);

        var sub2 = new Subscriber("Sub-B");
        pub.Subscribe(sub2);

        pub.PublishNewsletter();

        pub.Unsubscribe(sub2);
        pub.PublishNewsletter();
    }
}

public interface IPublisher
{
    void Subscribe(ISubscriber subscriber);
    void Unsubscribe(ISubscriber subscriber);
    void Notify();
}

public interface ISubscriber
{
    string Name { get; }
    void Update(IPublisher subject);
}

public class Publisher : IPublisher
{
    public int IssueNumber { get; set; } = 0;
    private List<ISubscriber> _subs = new List<ISubscriber>();

    public void Subscribe(ISubscriber subscriber)
    {
        _subs.Add(subscriber);
    }

    public void Unsubscribe(ISubscriber subscriber)
    {
        _subs.Remove(subscriber);
    }

    public void Notify()
    {
        foreach (var sub in _subs)
        {
            sub.Update(this);
        }
    }

    public void PublishNewsletter()
    {
        this.IssueNumber++;
        this.Notify();
    }
}

public class Subscriber : ISubscriber
{
    private int _latestIssue = 0;

    public string Name { get; private set; }

    public Subscriber(string name)
    {
        Name = name ?? throw new ArgumentNullException(nameof(name));
    }

    public void Update(IPublisher pub)
    {
        var concretePub = pub as Publisher;
        if (concretePub != null && concretePub.IssueNumber > _latestIssue)
        {
            _latestIssue = concretePub.IssueNumber;
        }
    }
}