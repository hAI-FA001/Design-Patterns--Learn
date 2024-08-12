// this one is thread-safe

public class ThreadSafeSingleton
{
    private static readonly object _lock = new object();

    public string Value { get; set; }

    private static ThreadSafeSingleton _instance;

    public static ThreadSafeSingleton GetInstance(string value)
    {
        if (_instance == null)
        {
            lock (_lock)
            {
                if (_instance == null)
                {
                    _instance = new ThreadSafeSingleton();
                    _instance.Value = value;
                }
            }
        }
        return _instance
    }

}