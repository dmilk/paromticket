package security.jaas;

import java.security.Principal;

public class RolePrincipal implements Principal {

    private String name;

    public RolePrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("NULL role name");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RolePrincipal [name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolePrincipal)) return false;

        RolePrincipal that = (RolePrincipal) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}