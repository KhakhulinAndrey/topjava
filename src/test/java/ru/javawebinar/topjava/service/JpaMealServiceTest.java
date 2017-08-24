package ru.javawebinar.topjava.service;

import org.springframework.context.annotation.Profile;

/**
 * Created by Admin on 24.08.2017.
 */
@Profile({"jpa", "postgres"})
public class JpaMealServiceTest extends MealServiceTest {
}
