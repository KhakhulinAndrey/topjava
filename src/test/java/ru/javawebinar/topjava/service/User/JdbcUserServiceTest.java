package ru.javawebinar.topjava.service.User;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Admin on 25.08.2017.
 */
@ActiveProfiles({Profiles.POSTGRES_DB, Profiles.JDBC})
public class JdbcUserServiceTest extends UserServiceTest {
}
