package security.jaas;

import java.security.Principal;

public class UserPrincipal implements Principal {

    private String name;

    public UserPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("NULL user name");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserPrincipal [name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrincipal)) return false;

        UserPrincipal that = (UserPrincipal) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}