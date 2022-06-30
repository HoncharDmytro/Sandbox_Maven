package com.sandbox_maven.test01.package2;

import java.util.List;

interface Base {
    void add(User user);
    User getByLastname(String name);
    List<User> getAll();
    void delete(String str);
}
