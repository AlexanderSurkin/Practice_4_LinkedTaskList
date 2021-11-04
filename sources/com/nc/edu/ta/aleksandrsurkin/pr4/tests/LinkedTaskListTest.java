package com.nc.edu.ta.aleksandrsurkin.pr4.tests;

import org.junit.Before;
import com.nc.edu.ta.aleksandrsurkin.pr4.*;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
