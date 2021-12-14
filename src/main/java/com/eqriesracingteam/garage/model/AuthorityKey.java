package com.eqriesracingteam.garage.model;

import java.io.Serializable;
import java.util.Objects;

// Add this class so SB can differentiatie combined IDs for repository purpose
// extend for key to use in hashcode

public class AuthorityKey implements Serializable {
    private String username;
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityKey that = (AuthorityKey) o;
        return username.equals(that.username) &&
                authority.equals(that.authority);
    }

    // Provides hash
    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }

}
