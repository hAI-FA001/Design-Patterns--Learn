// provides controlled access to an object (like a middleman)
// every request to access the object goes through the proxy
// e.g. checking who can/can't access the object, performing any validation or logging before accessing the object, etc

package structural;

public class ProxyPattern {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoProxy();
        employeeDao.create("ADMIN");
        employeeDao.create("Normal User");
    }
}

class EmployeeDaoProxy implements EmployeeDao {
    private EmployeeDao empDaoObj;

    EmployeeDaoProxy() {
        empDaoObj = new EmployeeDaoImpl();
    }

    @Override
    public void create(String userRole) {
        // control thge access/validate
        if (userRole == "ADMIN") {
            System.out.println("Accessed object.");
            empDaoObj.create(userRole);
        } else {
            throw new IllegalStateException("Must be an Admin.");
        }
    }
}

class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void create(String userRole) {
        System.out.println("Creating Employee...");
    }
}

interface EmployeeDao {
    void create(String userRole);
}