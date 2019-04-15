// Author: Nick Patchen

import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testPlantVsZombie {

	@Rule
	public Timeout globalTimeout = new Timeout(2, SECONDS);

	/*
	 * This tests the add method - ascending and the normal iterator
	 */
	@Test
	public void testInsertOne() {

		String str = "";

		assertEquals("str should return \"-1, 0, 1, 2, 2, \" ", "-1, 0, 1, 2, 2, ", str);
	}

}
