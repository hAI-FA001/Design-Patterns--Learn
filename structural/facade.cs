// facade helps in simplicity, different libs and frameworks can make our code messy
// we make our own interface around the libraries

namespace Facade;

public class FacadePattern
{
    public static void Main(string[] args)
    {
        var badLogger = new BadLogger();
        // badly designed
        badLogger.Log("INFO MESSAGE", LogType.Info, LogTarget.Console, null);

        // use our logger
        ILogger logger = new Logger();
        try
        {
            throw new InvalidOperationException("Sample error");
        }
        catch (Exception ex)
        {
            // much better
            logger.Error("ERROR MESSAGE", ex);
        }
    }
}

public interface ILogger
{
    void Information(string message);
    void Error(string message, Exception? ex = null);
    void Fatal(string message, Exception? ex = null);
}

public interface IBadLogger
{
    void Log(string message, LogType type, LogTarget target, Exception? ex);
}

// we build a wrapper around the badly designed logger
// the badly designed logger is restricted to this class
// if we change the logging framework later, we only need to change here
public class Logger : ILogger
{
    private readonly IBadLogger _logger;

    public Logger()
    {
        _logger = new BadLogger();
    }

    public void Information(string message)
    {
        _logger.Log(message, LogType.Info, LogTarget.Console, null);
    }
    public void Error(string message, Exception? ex = null)
    {
        _logger.Log(message, LogType.Error, LogTarget.Console, ex);

    }
    public void Fatal(string message, Exception? ex = null)
    {
        _logger.Log(message, LogType.Fatal, LogTarget.Console, ex);

    }
}

// e.g. this can send logs, messages, etc to different places like DB, slack, etc
// badly designed, everything is in one method
public class BadLogger : IBadLogger
{
    public void Log(string message, LogType type, LogTarget target, Exception? ex)
    {
        Console.WriteLine($"{DateTime.UtcNow:s} [{type.ToString().ToUpper()}] {message}");
        if (ex != null)
        {
            Console.WriteLine($"Exception: {ex.Message}\n{ex.StackTrace}");
        }
    }
}

public enum LogTarget
{
    Console = 0,
    File = 1,
    Slack = 2,
    Database = 3,
}

public enum LogType
{
    Info = 0,
    Error = 1,
    Fatal = 2,
}